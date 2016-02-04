package de.unidue.langtech.teaching.pp.Annotators;


import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;
import de.unidue.langtech.teaching.pp.utils.FrequencyMap;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;

public class FrequencyAnnotatorTest
{
    @Test
    public
    void frequencyAnnotatorTest()
    throws Exception
    {
		AnalysisEngine ae = createEngine(FrequencyAnnotator.class,
				FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/5kwordfrequency.txt"
				);
        JCas jcas = ae.newJCas();
        
        JCasBuilder jb = new JCasBuilder(jcas);
        
        jb.add("the", Lemma.class);
        jb.add(" ");
        jb.add("notInList", Lemma.class);
        jb.add(" ");
        jb.add("example", Lemma.class);
        jb.add(".");

        jb.close();
        
        // FIXME Issue: No clue how to provide a context. 
        // Potential fix: change frequency map creation to different point in pipeline
        
        ae.process(jcas);

        int i = 0;
        for (CorpusFrequency frequency : select(jcas, CorpusFrequency.class)) {
        	assertTrue((i != 0) || (1 == frequency.getRank()));
        	assertTrue((i != 0) || (22038615 == frequency.getCount()));
        	assertTrue((i != 1) || (5001 == frequency.getRank()));
        	assertTrue((i != 1) || (0 == frequency.getCount()));
        	assertTrue((i != 2) || (855 == frequency.getRank()));
        	assertTrue((i != 2) || (47134 == frequency.getCount()));
        	
            i++;
        }
    }
}


