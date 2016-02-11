package de.unidue.langtech.teaching.pp.annotators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationOutcome;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

public class Playground
    extends JCasAnnotator_ImplBase
{

	
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS").format(new Date()));
    	System.out.println("Document is: " + jcas.getDocumentText());
        System.out.println();
        
        // Token Test
        List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));
        Token token;
        
        System.out.println("CAS contains " + tokens.size() + " Token Annotations.");
        
        // Lemma Test
        List<Lemma> lemmas = new ArrayList<Lemma>(JCasUtil.select(jcas, Lemma.class));
        Lemma lemma;
        
        System.out.println("CAS contains " + lemmas.size() + " Lemma Annotations.");
        
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
     	   frequency = frequencies.get(i);
     	       	       	   
     	   System.out.println(
     			   "Token[" + i + "](" + token.getCoveredText() + ") " +
     			   "Lemma: " + lemma.getValue() + ", " +
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