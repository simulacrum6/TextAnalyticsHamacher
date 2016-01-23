package de.unidue.langtech.teaching.pp.annotators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.NGram;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.ngrams.NGramIterable;
import de.tudarmstadt.ukp.dkpro.core.ngrams.util.CharacterNGramStringIterable;
import de.unidue.langtech.teaching.pp.type.TokenSyllableCount;
import de.unidue.langtech.teaching.pp.type.CharNGram;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class Playground
    extends JCasAnnotator_ImplBase
{
	

	
    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
       
        
       List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));        
       List<TokenSyllableCount> tsc = new ArrayList<TokenSyllableCount>(JCasUtil.select(jcas, TokenSyllableCount.class));
       List<CharNGram> ngrams = new ArrayList<CharNGram>(JCasUtil.select(jcas, CharNGram.class));
       
       int syllableCount;
       String word;
       String charNgrams = "";
              
       System.out.println("CAS contains " + tokens.size() + " tokens.");        
       System.out.println("CAS contains " + tsc.size() + " SylCounts.");
              
       for(int i = 0; i < tokens.size(); i++){
    	   word = tokens.get(i).getCoveredText();
    	   syllableCount = tsc.get(i).getCountSyllables();
    	   charNgrams = "";
    	   
    	   for(int j = 0; j < ngrams.get(i).getCharNGrams().size(); j++){
    		   charNgrams += ngrams.get(i).getCharNGrams(j) + ", ";
    	   }
    	   
    	   System.out.println("Token[" + i + "]: " + word + ", " + syllableCount + " syllables. CharNGrams: " + charNgrams);    	   
       }
              
        System.out.println("\n");
        
        //NGram test
        
        
        
        
      
//       
//        Collection<NGram> ngrams = JCasUtil.select(jcas, NGram.class);
//        for (NGram ngram : ngrams)
//        System.out.println(ngram.getText());
//       
        
    }
}