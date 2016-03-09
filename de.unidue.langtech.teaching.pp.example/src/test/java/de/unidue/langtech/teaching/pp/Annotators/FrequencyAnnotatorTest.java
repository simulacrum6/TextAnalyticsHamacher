package de.unidue.langtech.teaching.pp.Annotators;


import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

public class FrequencyAnnotatorTest
{
    @Test
    public
    void frequencyAnnotatorTest()
    throws Exception
    {
		AnalysisEngine ae = createEngine(
				FrequencyAnnotator.class,
				FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/5kwordfrequency.txt"
				);
		
        JCas jcas = ae.newJCas();
        
        List<String> testwords = new ArrayList<String>();
            testwords.add("the");
	        testwords.add("notInList");
	        testwords.add("example");
	                
        JCasBuilder jb = new JCasBuilder(jcas);
        
	        for(String word : testwords)
	        {
	        	Lemma lemmaAnno = jb.add(word, Lemma.class);
	        	
	        	lemmaAnno.setValue(word);
	        	
	        	jb.add(" ");
	        }
	        
	        jb.add("."); 
       
        jb.close();        
        ae.process(jcas);

        int i = 0;
        
        for (CorpusFrequency frequency : new ArrayList<CorpusFrequency>( select(jcas, CorpusFrequency.class)) ) 
        {
        	assertTrue((i != 0) || (1 == frequency.getRank()));
        	assertTrue((i != 0) || (22038615 == frequency.getCount()));
        	assertTrue((i != 1) || (4353 == frequency.getRank()));
        	assertTrue((i != 1) || (0 == frequency.getCount()));
        	assertTrue((i != 2) || (855 == frequency.getRank()));
        	assertTrue((i != 2) || (47134 == frequency.getCount()));
        	
            i++;
        }
    }
}


