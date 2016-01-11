package de.unidue.langtech.teaching.pp.example;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.NGram;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.readability.measure.WordSyllableCounter;
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
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        //System.out.println("CAS contains " + tokens.size() + " tokens.");
        WordSyllableCounter wsc = new WordSyllableCounter("en");
        
        String word;
        int syllableCount;
        
        for(Token token : tokens){
        	word = token.getCoveredText();
        	syllableCount = wsc.countSyllables(word);
        	System.out.println(word + ", " + syllableCount + ".");
        }
        
        /*
        Collection<NGram> ngrams = JCasUtil.select(jcas, NGram.class);
        for (NGram ngram : ngrams)
        System.out.println(ngram.getText());
        */
        
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        languageAnno.setLanguage("EN");
        languageAnno.addToIndexes();
    }
}