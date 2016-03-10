package de.unidue.langtech.pp.featureExtractors;

import java.util.List;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

/**
 * 
 */
public class FrequencyUFE
    extends FeatureExtractorResource_ImplBase
    implements ClassificationUnitFeatureExtractor
{
    /**
     * 
     */
    public static final String FREQUENCY = "Frequency";
    
    public List<Feature> extract(JCas jcas, TextClassificationUnit classificationUnit)
        throws TextClassificationException
    {
        CorpusFrequency frequencyAnno = JCasUtil.selectCovered(jcas, CorpusFrequency.class, classificationUnit.getBegin(), classificationUnit.getEnd() ).get(0);
        int frequencyCount = frequencyAnno.getCount();
    	
    	return new Feature(FREQUENCY, frequencyCount).asList();
    }
}