package de.unidue.langtech.teaching.pp.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.IntegerArray;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.NGram;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.readability.measure.WordSyllableCounter;
import de.unidue.langtech.teaching.pp.TokenSyllableCount;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class BaselineExample
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
       
        
       List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));        
       List<TokenSyllableCount> tsc = new ArrayList<TokenSyllableCount>(JCasUtil.select(jcas, TokenSyllableCount.class));
       
       int syllableCount;
       String word;
              
       System.out.println("CAS contains " + tokens.size() + " tokens.");        
       System.out.println("CAS contains " + tsc.size() + " SylCounts.");
              
       for(int i = 0; i < tokens.size(); i++){
    	   word = tokens.get(i).getCoveredText();
    	   syllableCount = tsc.get(i).getCountSyllables();
    	   
    	   System.out.println("Token[" + i + "]: " + word + ", " + syllableCount + " syllables");    	   
       }
              
        System.out.println("\n");
//       
//        Collection<NGram> ngrams = JCasUtil.select(jcas, NGram.class);
//        for (NGram ngram : ngrams)
//        System.out.println(ngram.getText());
//       
        
    }
}