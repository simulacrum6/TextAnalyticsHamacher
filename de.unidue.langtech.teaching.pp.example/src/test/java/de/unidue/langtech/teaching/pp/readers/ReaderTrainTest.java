package de.unidue.langtech.teaching.pp.readers;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.xmlgraphics.xmp.Metadata;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.unidue.langtech.pp.readers.ReaderTrain;
import de.unidue.langtech.pp.readers.ReaderTrainTC;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.annotators.Playground;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;
import de.unidue.langtech.teaching.pp.type.GoldComplexity;

public class ReaderTrainTest {
	
	@Test
	public void testDocumentText() 
		throws Exception
	{
		CollectionReaderDescription reader = createReader();
		
		List<JCas> casObjects = getCasObjects(reader);
		for (JCas jcas : casObjects) {
            String docText = jcas.getDocumentText();
			
			assertNotNull(docText);
            assertFalse(docText.isEmpty());            
        }
		
	}
	
	
	@Test
	public void testTokens()
		throws Exception
	{
		CollectionReaderDescription reader = createReader();
		
		List<JCas> casObjects = getCasObjects(reader);
		for (JCas jcas : casObjects) 
		{
			List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));
			String metaId = JCasUtil.selectSingle(jcas, DocumentMetaData.class).getDocumentId();
            
			if( metaId.endsWith("1"))
            {
            	assertEquals(9, tokens.size());
                assertEquals("Peter", tokens.get(0).getCoveredText());
                assertEquals("there", tokens.get(8).getCoveredText());
                return;
                
            } 
            else if( metaId.endsWith("2") )
            {
            	assertEquals(5, tokens.size());
                assertEquals("This", tokens.get(0).getCoveredText());
                assertEquals("test", tokens.get(4).getCoveredText());
                return;
            }
            
        }
		
	}
	
	
	@Test
	public void testGoldAnno()
		throws Exception
	{
		CollectionReaderDescription reader = createReader();
		
		List<JCas> casObjects = getCasObjects(reader);
		for (JCas jcas : casObjects)
		{
			List<GoldComplexity> goldAnno = new ArrayList<GoldComplexity>(JCasUtil.select(jcas, GoldComplexity.class));
			String metaId = JCasUtil.selectSingle(jcas, DocumentMetaData.class).getDocumentId();
			
			if( metaId.endsWith("0"))
            {
            	assertEquals(2, goldAnno.size());
                assertEquals("into", goldAnno.get(0).getWord());
                assertEquals(3, goldAnno.get(0).getPosition());
                assertEquals(1, goldAnno.get(1).getComplexity());
                assertEquals(7, goldAnno.get(1).getComplexitySum());
                return;
            } 
            else if( metaId.endsWith("2") )
            {
            	System.out.println(jcas.getDocumentText());
            	assertEquals( 2 , goldAnno.size());
                assertEquals( "test" , goldAnno.get(0).getWord());
                assertEquals( 5 , goldAnno.get(0).getPosition());
                assertEquals( 0 , goldAnno.get(1).getComplexity());
                assertEquals( 0 , goldAnno.get(1).getComplexitySum());
                return;
            }
		}
	}
	
	
	@Test
	public void testFunctionGetSafeSplits() 
		throws IOException
	{
		ReaderTrain reader = new ReaderTrain();
		String testLine = "Hollow words waver , syllables five seven five , just three lines suffice , else your haiku is kaputt.";
		
		String[] splits = reader.getSafeSplits(testLine, "\\s,\\s", -1);
		
		assertEquals( 4 , splits.length );
		assertEquals( "Hollow words waver" , splits[0] );
		assertEquals( "syllables five seven five" , splits[1] );
		assertEquals( "else your haiku is kaputt." , splits[3] );
		
	}
	
	@Test
	public void testFunctionGetLineSplits() 
		throws IOException
	{
		ReaderTrain reader = new ReaderTrain();
		
		List<String> testLines = new ArrayList<String>(); 
			testLines.add("Line should be split right here !	And it should not be split hereafter!-CPT.CAPS"); 
			testLines.add("Don't break my heart !	Please?");
			testLines.add("You Monster .	</3");
		
		List<String[]> lineSplits = reader.getLineSplits(testLines);
		
		assertEquals( 3 , lineSplits.size() );
		assertEquals( "Line should be split right here" , lineSplits.get(0)[0]);
		assertEquals( "And it should not be split hereafter!-CPT.CAPS" , lineSplits.get(0)[1] );
		assertEquals( "</3" , lineSplits.get(2)[1] ); 
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
		return CollectionReaderFactory.createReaderDescription(ReaderTrain.class,
				ReaderTrain.PARAM_INPUT_FILE, "src/test/resources/test/input.txt");
	}
		
}

