package de.unidue.langtech.teaching.pp.annotators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.IntegerList;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.readability.measure.WordSyllableCounter;
import de.unidue.langtech.teaching.pp.TokenSyllableCount;
import de.unidue.langtech.teaching.pp.type.MyType;

public class SyllableCountAnnotator
	  extends JCasAnnotator_ImplBase
	  {		  
	      @Override
	      public void process(JCas jcas)
	          throws AnalysisEngineProcessException
	      {
	    	  
	    	  WordSyllableCounter wsc = new WordSyllableCounter("en");
	    	  Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
	    	  TokenSyllableCount tsc = new TokenSyllableCount(jcas, 0, tokens.size());
	    	  String word;
	          int syllableCount;
	          int i = 0;
	          List<Integer> syllableCountList = new ArrayList<Integer>();
	          
	          for(Token token : tokens){
	          	word = token.getCoveredText();
	          	syllableCount = wsc.countSyllables(word);
	          	tsc.setCountSyllables(i, syllableCount);
	          	i++;
	          }  
	          
	          tsc.addToIndexes();
	          Collection<TokenSyllableCount> cTsc = JCasUtil.select(jcas, TokenSyllableCount.class);
	          
	          for(TokenSyllableCount sylCount : cTsc){
	        	  System.out.println(sylCount);
	          }

	      }
}
