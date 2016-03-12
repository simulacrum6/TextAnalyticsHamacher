package de.unidue.langtech.testing.pp.readers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationOutcome;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.pp.readers.ReaderTrain;
import de.unidue.langtech.pp.readers.ReaderTrainTC;

public class ReaderTrainTCTest {
	
	@Test
	public void testClassificationUnitsAndOutcome()
		throws Exception
	{
		CollectionReaderDescription reader = createReader();
		
		List<JCas> casObjects = getCasObjects(reader);
		for (JCas jcas : casObjects) 
		{
			List<TextClassificationUnit> classificationUnits = new ArrayList<TextClassificationUnit>(JCasUtil.select(jcas, TextClassificationUnit.class));
			List<TextClassificationOutcome> classificationOutcomes = new ArrayList<TextClassificationOutcome>(JCasUtil.select(jcas, TextClassificationOutcome.class));
			String metaId = JCasUtil.selectSingle(jcas, DocumentMetaData.class).getDocumentId();
            
			if( metaId.endsWith("1"))
            {
            	assertEquals(9, classificationUnits.size());
                assertEquals(new Integer(3), new Integer (classificationUnits.get(0).getId()));
                assertEquals("into", classificationUnits.get(0).getCoveredText());
                assertEquals("1" , classificationOutcomes.get(0).getOutcome() );
                return;
            } 
			
		}
            
    }
	
	
	private List<JCas> getCasObjects(CollectionReaderDescription reader)
	{
		List<JCas> casObjects = new ArrayList<JCas>();

		for (JCas jcas : new JCasIterable(reader)) {
			casObjects.add(jcas);
		}

		return casObjects;
	}
	
	private CollectionReaderDescription createReader()
		throws Exception
	{
		return CollectionReaderFactory.createReaderDescription(ReaderTrainTC.class,
				ReaderTrain.PARAM_INPUT_FILE, "src/test/resources/test/input.txt");
	}
		
}
