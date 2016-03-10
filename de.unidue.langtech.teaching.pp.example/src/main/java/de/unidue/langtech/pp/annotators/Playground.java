package de.unidue.langtech.pp.annotators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Compound;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationOutcome;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

/*
 * Console based testing environment for quick and dirty testing.
 * Used as final annotator in pipeline.
 * */

// TODO Move to test.
public class Playground
    extends JCasAnnotator_ImplBase
{
	
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
    	System.out.println("Document is: " + jcas.getDocumentText());
    	
    	// DocumentMetaData Test
    	DocumentMetaData metaData = JCasUtil.selectSingle(jcas, DocumentMetaData.class);
       
        System.out.println("DocumentId: " + metaData.getDocumentId());
        System.out.println();
        
        // Token Test
        List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));
        Token token;
        
        System.out.println("CAS contains " + tokens.size() + " Token Annotations.");
        
        // Lemma Test
        List<Lemma> lemmas = new ArrayList<Lemma>(JCasUtil.select(jcas, Lemma.class));
        Lemma lemma;
        
        System.out.println("CAS contains " + lemmas.size() + " Lemma Annotations.");
        
        // POS Test
        List<POS> posTags = new ArrayList<POS>(JCasUtil.select(jcas, POS.class));
        POS posTag;
        
        System.out.println("CAS contains " + posTags.size() + " POS Annotations.");
        
        // Decompounder Test
//        List<Compound> compounds = new ArrayList<Compound>(JCasUtil.select(jcas, Compound.class));
//        Compound compound;
        
        // Frequency Test
        List<CorpusFrequency> frequencies = new ArrayList<CorpusFrequency>(JCasUtil.select(jcas, CorpusFrequency.class));
        CorpusFrequency frequency;
        
        System.out.println("CAS contains " + frequencies.size() + " Frequency Annotations.");
        
        // Classification Units Test
        List<TextClassificationUnit> units = new ArrayList<TextClassificationUnit>(JCasUtil.select(jcas, TextClassificationUnit.class));
        TextClassificationUnit unit;
        
        System.out.println("CAS contains " + units.size() + " Classification Units.");
             
        // Classification Outcomes Test
        List<TextClassificationOutcome> outcomes = new ArrayList<TextClassificationOutcome>(JCasUtil.select(jcas, TextClassificationOutcome.class));
        TextClassificationOutcome outcome;
        
        System.out.println("CAS contains " + outcomes.size() + " Classification Outcomes.");
        System.out.println();
        
        // Annotation Info
        for(int i = 0; i < tokens.size(); i++){
     	   token = tokens.get(i);
     	   lemma = lemmas.get(i);
     	   posTag = posTags.get(i);
     	   // compound = compounds.get(i);
     	   frequency = frequencies.get(i);
     	   
     	   System.out.println(
     			   "Token[" + i + "](" + token.getCoveredText() + ") " +
     			   "Lemma: " + lemma.getValue() + ", " +
     			   "POS: " + posTag.getTypeIndexID() + posTag.getPosValue() + ", " +
//     			   "Compound:" + compound.getSplits(i) + ", " +
     			   "Frequency Count: " + frequency.getCount() + ", " +
     			   "Frequency Rank: " + frequency.getRank()
     			   );    	   
        }
        System.out.println();
        
        //Classification Info
        for(int i = 0; i < units.size(); i++)
        {
        	unit = units.get(i);
        	outcome = outcomes.get(i);
        	
        	System.out.println(
      			   "ClassificationUnit[" + i + "](" + unit.getCoveredText() + ") " +
      			   "Classification Outcome: " + outcome.getOutcome()
      			   );
        }
        System.out.println();
        
        System.out.println("----------");
        System.out.println();

        
        
        
//        Junkyard
//       
//        Collection<NGram> ngrams = JCasUtil.select(jcas, NGram.class);
//        for (NGram ngram : ngrams)
//        System.out.println(ngram.getText());
//       
//     for(CharNGram ngram : ngrams){
// 	   for(String charNGram : ngram.getCharNGrams().toArray()){
// 		   System.out.print(charNGram);
// 	   }
// 	   System.out.println();
//    }        
    }
}