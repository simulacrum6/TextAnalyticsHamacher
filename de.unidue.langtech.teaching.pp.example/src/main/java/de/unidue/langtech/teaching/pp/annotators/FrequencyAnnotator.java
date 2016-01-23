package de.unidue.langtech.teaching.pp.annotators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.IntegerList;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.readability.measure.WordSyllableCounter;
import de.unidue.langtech.teaching.pp.type.TokenSyllableCount;
import de.unidue.langtech.teaching.pp.utils.FrequencyList;
import de.unidue.langtech.teaching.pp.type.CharNGram;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;
import de.unidue.langtech.teaching.pp.type.MyType;

public class FrequencyAnnotator
	  extends JCasAnnotator_ImplBase
	  {		  
		public static final String PARAM_FREQUENCY_LIST = "FrequencyList";
	    @ConfigurationParameter(name = PARAM_FREQUENCY_LIST, mandatory = true)
	    private File frequencyList;
	    		
		private List<String> lines;
		private FrequencyList freqs;
		
		@Override
	    public void initialize(UimaContext context)
	        throws ResourceInitializationException
	    {
	        super.initialize(context);
	        
	        try {
	           lines = FileUtils.readLines(frequencyList);	              
	        }
	        catch (IOException e) {
	            throw new ResourceInitializationException(e);
	        }
	        
	        freqs = new FrequencyList();
	        String[] values;
	        for(String line : lines){
	        	
	        	values = line.split("\\t"); 
	        	freqs.put(values[1].trim(), values[2].trim(), values[3].trim(), values[0].trim());
	        	
	        }
	    }
	
		  @Override
	      public void process(JCas jcas)
	          throws AnalysisEngineProcessException
	      {
			  
			  String lemma;
			  int count;
			  int rank;
			  //FIXME NEEDS LEMMATISATION
			  for(Lemma l : JCasUtil.select(jcas, Lemma.class)){
				  
				  lemma = l.getValue();
				  
				  if(freqs.contains(lemma)){
					  count = freqs.getCount(lemma);
					  rank = freqs.getRank(lemma);
				  }else{
					  count = freqs.getMinFreq() - 1;
					  rank = freqs.getSize() + 1;
				  }
				  
				  CorpusFrequency freqAnno = new CorpusFrequency(jcas);
				  freqAnno.setCount(count);
				  freqAnno.setRank(rank);
				  freqAnno.addToIndexes();
			  }
			  
	      
	      }
}
