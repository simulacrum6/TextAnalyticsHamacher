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
import de.unidue.langtech.teaching.pp.type.TokenSyllableCount;
import de.unidue.langtech.teaching.pp.type.MyType;

public class SyllableCountAnnotator
	  extends JCasAnnotator_ImplBase
	  {		  
	      @Override
	      public void process(JCas jcas)
	          throws AnalysisEngineProcessException
	      {
	    	  
	    	  Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
	    	  
	    	  WordSyllableCounter wsc = new WordSyllableCounter("en");
	    	  IntegerArray intArr = new IntegerArray(jcas, tokens.size());
	    	  
	    	  String word;
	          int syllableCount;
	          int i = 0;
	          
	          for(Token token : tokens){
	          	word = token.getCoveredText();
	          	syllableCount = wsc.countSyllables(word);
	          	//intArr.set(i, syllableCount);
	          	i++;
	          
	          	TokenSyllableCount tsc = new TokenSyllableCount(jcas);
		        tsc.setCountSyllables(syllableCount);
		        tsc.addToIndexes();
		          
	          	
	          }  
	    }
}
