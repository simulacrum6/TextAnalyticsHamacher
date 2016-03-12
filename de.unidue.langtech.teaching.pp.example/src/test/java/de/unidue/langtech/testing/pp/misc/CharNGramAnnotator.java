package de.unidue.langtech.testing.pp.misc;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.CharNGram;

/*
 * Simple CharNGramAnnotator. Annotates a set of character NGrams for each token in a jCas.
 * */

@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token" })
public class CharNGramAnnotator
	extends JCasAnnotator_ImplBase
{		  
	public static final String PARAM_MIN_N = "PARAM_MIN_N";
	@ConfigurationParameter(name = PARAM_MIN_N, defaultValue = "1")
	protected int minN;
	      
	public static final String PARAM_MAX_N = "PARAM_MAX_N";
	@ConfigurationParameter(name = PARAM_MAX_N, defaultValue = "3")
	protected int maxN;
	
	
	@Override
	public void process(JCas jcas)
		throws AnalysisEngineProcessException
    { 			  
		List<Token> tokens = new ArrayList<Token>(JCasUtil.select(jcas, Token.class));	    	  
		LinkedHashSet<String> charNgrams;
		StringArray annoCharNgrams;
  	  
		String word;
		int i;
  	  
		for(Token token : tokens)
		{
			i = 0;
			word = token.getCoveredText().toLowerCase();
  		  
			charNgrams = getCharNGrams(word, minN, maxN);
			annoCharNgrams = new StringArray(jcas, charNgrams.size());
  		  
			for(String ngram : charNgrams)
			{
				annoCharNgrams.set(i, ngram);
				i++;
			}
  		  
			CharNGram ngrams = new CharNGram(jcas);	  
				ngrams.setCharNGrams(annoCharNgrams);
				ngrams.addToIndexes();
  	  	}
    }
	
	//FIXME Something messes up the ordering.
	public LinkedHashSet<String> getCharNGrams(String token, int minN, int maxN)
	{
		List<String> chars = new ArrayList<String>();
		LinkedHashSet<String> ngrams = new LinkedHashSet<String>();
	 	   	  
		if(minN <= token.length())
		{
			for( int i = minN; i <= maxN; i++){
				for( int j = 0; i+j <= token.length(); j++ ){
					chars.add( token.substring(j, j+i) );
				}  
			}  
		} else {
			chars.add("-");
		}
	 	   	  
		ngrams.addAll(chars);
		return ngrams;
	};
}
