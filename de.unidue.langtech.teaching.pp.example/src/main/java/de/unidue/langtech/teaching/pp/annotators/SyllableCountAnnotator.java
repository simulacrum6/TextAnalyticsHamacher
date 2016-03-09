package de.unidue.langtech.teaching.pp.annotators;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.readability.measure.WordSyllableCounter;
import de.unidue.langtech.teaching.pp.type.TokenSyllableCount;

/*
 * Simple Annotator for Syllable counts.  
 * Uses the syllable count algorithm from de.tudarmstadt.ukp.dkpro.core.readability.measure.
 */

public class SyllableCountAnnotator
	  extends JCasAnnotator_ImplBase
{		  
	@Override
	public void process(JCas jcas)
		throws AnalysisEngineProcessException
	{
		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
	    	  
	    WordSyllableCounter wsc = new WordSyllableCounter("en");
	      
	    String word;
	    int syllableCount;
	          
	    for(Token token : tokens){
	    	word = token.getCoveredText();
	    	syllableCount = wsc.countSyllables(word);

			TokenSyllableCount tsc = new TokenSyllableCount(jcas);
				tsc.setBegin(token.getBegin());
				tsc.setEnd(token.getEnd());
				tsc.setCountSyllables(syllableCount);
	    }
	}
}
