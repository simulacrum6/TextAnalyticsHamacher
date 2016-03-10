package de.unidue.langtech.pp.featureExtractors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.tc.api.exception.TextClassificationException;
import de.tudarmstadt.ukp.dkpro.tc.api.features.ClassificationUnitFeatureExtractor;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

/**
 * Adds a feature for each POS Tag of a given classification unit
 * 
 * The list is based on the model used by the StanfordPosTagger
 * Tags were retrieved from https://dkpro.github.io/dkpro-core/releases/1.7.0/models/ 
 */
@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.POS" })
public class PosUFE
    extends FeatureExtractorResource_ImplBase
    implements ClassificationUnitFeatureExtractor
{
    public static final String POSTAG = "PosTag";
    
	public static final String PARAM_USE_POS_TYPES = "usePosTypes";
	@ConfigurationParameter(name = PARAM_USE_POS_TYPES, defaultValue = "true")
	protected boolean usePosTypes;
	
	public static final String PARAM_USE_POS_INDEX = "usePosIndex";
	@ConfigurationParameter(name = PARAM_USE_POS_INDEX, defaultValue = "true")
	protected boolean usePosIndex;
    
    public List<Feature> extract(JCas jcas, TextClassificationUnit classificationUnit)
        throws TextClassificationException
    {
		List<String> relevantPosList = Arrays.asList(new String[]{
				"CC", "CD","DT","EX","FW","IN","JJ","JJR","JJS","LS",
				"MD","NN","NNS","NNP","NNPS", "PDT","POS","PRP","PRP$",
				"RB","RBR","RBS","RP","SYM","TO","UH","VB","VBZ","VBP",
				"VBD","VBN", "VBG","WDT","WP","WP$","WRB",".",",",":",
				"(",")","NP","PP","VP","ADVP","ADJP","SBAR","PRT","INTJ"
		});
		
    	POS posAnno = JCasUtil.selectCovered(jcas, POS.class, classificationUnit.getBegin(), classificationUnit.getEnd() ).get(0);
        String posType = posAnno.getPosValue();
        int posIdx = posAnno.getTypeIndexID();
    	
        Map<String, Boolean> posMap = new HashMap<String, Boolean>(); 
        	for(String pos : relevantPosList){
        		posMap.put(pos, new Boolean(false));
        	}
        	
        	if(posMap.containsKey(posType))
        	{
        		posMap.remove(posType);
            	posMap.put(posType, new Boolean(true));
        	}
        
        List<Feature> features = new ArrayList<Feature>();
        	if(usePosIndex) {
        		features.add(new Feature(POSTAG, posIdx));
        	}
        	if(usePosTypes){
        		for(String pos : posMap.keySet()){
            		features.add(new Feature("is_POSTYPE_" + pos, posMap.get(pos)));
            	}
        	}
 
        	
    	return features;
    }
}