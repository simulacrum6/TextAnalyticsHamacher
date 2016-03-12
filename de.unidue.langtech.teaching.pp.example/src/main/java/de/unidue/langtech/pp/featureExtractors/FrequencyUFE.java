package de.unidue.langtech.pp.featureExtractors;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.fit.descriptor.ConfigurationParameter;
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
     * A simple Frequency Feature Extractor, based on the Frequency Feature Extractor of this project.
     * 
     * It is possible to extract either the occurence frequency of the word in the base corpus unit or the frequency rank.
     * For the Frequency, it is possible to get the logarithmic frequency.
     * For the Ranks, it is possible to divide the ranks into different categories (divisions) and get the rank division of a term.
     * 
     */
    public static final String FREQUENCY = "Frequency";
    
    // Possible Types: "count", "rank"
    public static final String PARAM_FEATURE_TYPE = "featureType";
	@ConfigurationParameter(name = PARAM_FEATURE_TYPE, mandatory = true, defaultValue = "count")
	protected String featureType;
    
	// Possible Inputs: "raw", "log"
    public static final String PARAM_USE_FREQUENCY_LOG = "useFrequencyLog";
	@ConfigurationParameter(name = PARAM_USE_FREQUENCY_LOG, defaultValue = "false")
	protected boolean useFrequencyLog;
	
    
    public List<Feature> extract(JCas jcas, TextClassificationUnit classificationUnit)
        throws TextClassificationException, IllegalArgumentException
    {
        CorpusFrequency frequencyAnno = JCasUtil.selectCovering(jcas, CorpusFrequency.class, classificationUnit.getBegin(), classificationUnit.getEnd() ).get(0);    	
    	
        return getFeature(frequencyAnno).asList();
    }
    
    protected Feature getFeature(CorpusFrequency anno)
    		throws IllegalArgumentException
    {
    	if(featureType.equals("count")){
    		return extractCount(anno);
    	} else if( featureType.equals("rank") ){
    		return extractRank(anno);
    	} else {
    		throw new IllegalArgumentException("Illegal Parameter. PARAM_FREQUENCY_TYPE: " + featureType + ". Must be 'raw' or 'log'.");
    	}
    }
    
    
    protected Feature extractCount(CorpusFrequency anno)
    	throws IllegalArgumentException
    {
    	double frequencyCount;
    	
    	if(useFrequencyLog){
    		frequencyCount = Math.log(  anno.getCount() );
    	} else  {
    		frequencyCount = (double) anno.getCount();
    	}
    	
    	return new Feature("FREQUENCY_" + featureType, frequencyCount);
    }
    
    protected Feature extractRank(CorpusFrequency anno)
    {
    	int frequencyRank = anno.getRank();
    	
    	return new Feature("FREQUENCY_" + featureType, frequencyRank);
    }
}