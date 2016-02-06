package de.unidue.langtech.pp.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.io.JCasResourceCollectionReader_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.tc.api.io.TCReaderSequence;
import de.tudarmstadt.ukp.dkpro.tc.api.io.TCReaderSingleLabel;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationOutcome;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationSequence;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.teaching.pp.type.GoldComplexity;

public class ReaderTrainTC 
	extends ReaderTrain 
	implements TCReaderSequence
{
	public static final String PARAM_CLASSIFICATION_MODE = "PARAM_CLASSIFICATION_MODE";
    @ConfigurationParameter(name = PARAM_CLASSIFICATION_MODE, defaultValue = "single")
    String classificationMode;
    
    GoldComplexity outcome_gold;
        
    @Override
	public boolean hasNext()
        throws IOException, CollectionException
    {
        super.hasNext();
        
    	return currentLine < lines.size();
    }
    
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {  	
    	super.getNext(jcas);
    	
    	// Set TextClassificationSequence    	
    	for (Sentence sentence : JCasUtil.select(jcas, Sentence.class))
    	{
    		TextClassificationSequence sequence = new TextClassificationSequence(jcas, sentence.getBegin(), sentence.getEnd());
        		sequence.addToIndexes();
    	}
        
        // Set TextClassificationUnit & TextClassificationOutcome
    	// Not sure, whether all Tokens should be Classified or just the relevant ones.
    	int index = 0;
    	
        for (GoldComplexity goldAnno : JCasUtil.select(jcas, GoldComplexity.class) ) 
        {
            outcome_gold = goldAnno;
        	
        	TextClassificationUnit unit = new TextClassificationUnit(jcas, goldAnno.getBegin(), goldAnno.getEnd()); 
            	unit.setSuffix(goldAnno.getWord());
            	unit.setId(goldAnno.getPosition());
            	unit.addToIndexes();
                        	
            TextClassificationOutcome outcome = new TextClassificationOutcome(jcas, goldAnno.getBegin(), goldAnno.getEnd());
	            outcome.setOutcome(getTextClassificationOutcome(jcas, unit));
	            outcome.addToIndexes();
	            
	        index++;
        }
    }

    public String getTextClassificationOutcome(JCas jcas, TextClassificationUnit unit) 
			throws CollectionException
    {
    	// Not used for increased performance. Member outcome_gold is used instead.
    	// GoldComplexity goldAnno = JCasUtil.selectCovered(jcas, GoldComplexity.class, unit.getBegin(), unit.getEnd()).get(0); 
    	String outcome;
       	
    	if (classificationMode.equals("single")){
    		outcome = "" + outcome_gold.getComplexity();
    	}else if (classificationMode.equals("sum")){
    		outcome = "" + outcome_gold.getComplexitySum();
    	}else {
    		throw new CollectionException(); 
    	}
    	
		return outcome;
	}
	    
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
    
    // Considered restricting access to outcome_gold.
//    void setOutcome_gold(GoldComplexity anno)
//    	throws IllegalArgumentException
//    {
//    	if (classificationMode.equals("single")){
//    		outcome_gold = "" + anno.getComplexity();
//    	} else if (classificationMode.equals("sum")){
//    		outcome_gold = "" + anno.getComplexitySum();;
//    	} else {
//    		throw new IllegalArgumentException(); 
//    	}
//    }
}
