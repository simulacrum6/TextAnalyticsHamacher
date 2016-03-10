package de.unidue.langtech.teaching.pp.annotators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;
import de.unidue.langtech.teaching.pp.utils.FrequencyMap;

/*
 * List based annotator, adding Rank & Frequency. Uses frequency value from a text file. 
 * Currently the formatting requirements are very strict. Make sure to look into these and select appropriate txt files as sources.< 
 * */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma" })
public class FrequencyAnnotator
	  extends JCasAnnotator_ImplBase
{		  
	public static final String PARAM_FREQUENCY_LIST = "frequencyList";
    @ConfigurationParameter(name = PARAM_FREQUENCY_LIST, mandatory = true)
    File frequencyList;
    
	public static final String PARAM_FREQUENCY_CORPUS_NAME = "frequencyCorpusName";
    @ConfigurationParameter(name = PARAM_FREQUENCY_CORPUS_NAME, mandatory = true, defaultValue = "5kwordfrequency")
    String frequencyCorpusName;
    		
	List<String> lines;
	FrequencyMap freqs;
	
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
        
        freqs = new FrequencyMap(frequencyList, frequencyCorpusName);
        
//        freqs = new FrequencyMap();
//        String[] values;
//        
//        for(String line : lines)
//        {	
//        	values = line.split("\\t"); 
//        	freqs.put(values[1].trim(), values[2].trim(), values[3].trim(), values[0].trim());
//        }
        
    }

	@Override
    public void process (JCas jcas)
    	throws AnalysisEngineProcessException
	{	  
		for (Lemma l : new ArrayList<Lemma>(JCasUtil.select(jcas, Lemma.class)))
		{  
			String lemma = l.getValue();
			int begin = l.getBegin();
			int end = l.getEnd();
			int count;
			int rank;
			
			if (freqs.contains(lemma)) {
				count = freqs.getCount(lemma);
				rank = freqs.getRank(lemma);
			} else {
				//count = freqs.getMinFreq() - 1;
				count = 0;
				rank = freqs.getSize() + 1;
			}
			  
			CorpusFrequency freqAnno = new CorpusFrequency(jcas);
				freqAnno.setBegin(begin);
				freqAnno.setEnd(end);
				freqAnno.setCount(count);
				freqAnno.setRank(rank);
				freqAnno.addToIndexes();
		}
  	      
    }
	  
}
