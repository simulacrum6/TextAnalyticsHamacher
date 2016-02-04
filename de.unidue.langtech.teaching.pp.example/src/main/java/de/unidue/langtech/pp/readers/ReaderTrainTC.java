package de.unidue.langtech.pp.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.io.JCasResourceCollectionReader_ImplBase;
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
    	
    	// TODO set classification outcome
    	    	
//        TextClassificationSequence sequence = new TextClassificationSequence(jcas, #sequenceBeginIndex, #sequenceEndIndex);
//        sequence.addToIndexes();
//        
//        for (int i = 0; i < wordBuffer.size(); i++) {
//            TextClassificationUnit unit = new TextClassificationUnit(jcas, #unitBeginIndex, #unitEndIndex); 
//            unit.setSuffix(wordBuffer.get(i));
//            unit.addToIndexes();
//            
//            TextClassificationOutcome outcome = new TextClassificationOutcome(jcas);
//            outcome.setOutcome(getTextClassificationOutcome(jcas, unit));
//            outcome.addToIndexes();
//        }
    }

    public String getTextClassificationOutcome(JCas jcas, TextClassificationUnit unit) 
			throws CollectionException {
		// TODO Implement PARAM_ClassificationMode = " binary " || " cumulative ";
    	// TODO return complexityBuffer || complexitySumBuffer of unit
		return null;
	}
	    
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }

}
