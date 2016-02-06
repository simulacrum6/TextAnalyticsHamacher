package de.unidue.langtech.pp.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.unidue.langtech.teaching.pp.type.GoldComplexity;

/* 
 * A training data reader for the SemEval2016 Task11: Complex Word Identification.
 * Input files must be organised in the following format:
 *	 Each TEXT in INPUT
 *		[Line1]
 *		[Line2]
 *		(...)
 *		[LineN]
 *	 Each LINE in TEXT
 *		[Sentence][TAB][.][TAB][Tokeninfo]
 *	 Each SENTENCE in LINE
 *		[Word1][TAB][Word2][TAB](...)[TAB][WordN]
 *	 Each TOKENINFO in LINE
 *		[TAB][WordX][TAB][Position(WordX)][TAB][Rating1][TAB](...)[TAB][RatingM]
 *		Each individual rating must be either 1 or 0. 
*/

public class ReaderTrain
    extends JCasCollectionReader_ImplBase
{

    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    //Language required for Stanford Lemmatizer
    public static final String PARAM_DOCUMENT_LANGUAGE = "DocumentLanguage";
    @ConfigurationParameter(name = PARAM_DOCUMENT_LANGUAGE, defaultValue = "en")
    String documentLanguage;

    String documentText;
    int currentLine;
    List<String> lines;
    
    List<String> wordBuffer;
    List<Integer> positionBuffer;
    List<Integer> complexityBuffer;
    List<Integer> complexitySumBuffer;
    
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
    	super.initialize(context);
        
        try {
           lines = FileUtils.readLines(inputFile);
           currentLine = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }
    
    public boolean hasNext()
        throws IOException, CollectionException
    {
        String currLineText;
                
        //TODO reduce to 1 split variable.
        String[] split;
        String[] _split;
        
        int complexitySum;
        
        wordBuffer = new ArrayList<String>();
        positionBuffer = new ArrayList<Integer>();
        complexityBuffer = new ArrayList<Integer>();
        complexitySumBuffer = new ArrayList<Integer>();
    	
        if (currentLine < lines.size()) 
        {
            //Splits line into sentence and tokeninfo component
            split = lines.get(currentLine).split("\\s[\\.\\!\\?]\\t");
            
            if (split.length != 2) {
                throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain the following separator: '.\\t' ");
            }
            
        	currLineText = split[0];
        	documentText = currLineText;
        	
        	//TODO transform to while loop.
        	do {
        		_split = split[1].split("\t", -1);
                if (_split.length < 3) {
                    throw new IOException("Wrong Tokeninfo format: " + split[1]);
                }
                
                complexitySum = 0;
                for (int i = 3; i < _split.length; i++) 
                {
                	if (_split[i].equals("1")) {
                		complexitySum ++;
                	}
                }
                
                wordBuffer.add(_split[0]);
                positionBuffer.add( Integer.parseInt( _split[1] ) );
                complexitySumBuffer.add(complexitySum);
                complexityBuffer.add( (complexitySum > 0)?1:0 );
                
                //Preparations for next loop
                
                currentLine ++;
                
            	split = lines.get(currentLine).split("\\s[\\.\\!\\?]\\t");
                
            	if (split.length != 2) {
                	throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain the following separator: '.\\t' ");
                }
            	
            	currLineText = split[0];
            	
        	}while (documentText.equals(currLineText)&& currentLine < lines.size()-1);

        }
            	
    	return currentLine < lines.size();
    }
    
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {  	
   	
        //Set Gold Annotation.
    	for (int i = 0; i < wordBuffer.size(); i++)
    	{
    		GoldComplexity goldComplexity = new GoldComplexity(jcas);
	            
    			goldComplexity.setWord(wordBuffer.get(i));
	            goldComplexity.setPosition(positionBuffer.get(i));
	            goldComplexity.setComplexity(complexityBuffer.get(i));
	            goldComplexity.setComplexitySum(complexitySumBuffer.get(i));
	            goldComplexity.addToIndexes();
    	}
    	
    	// TODO Set Sentence
    	Sentence sentence = new Sentence(jcas, 0, documentText.length());
    	sentence.addToIndexes();
    		
    	// TODO Set Tokens
//    	String[] tokensplits = documentText.split(" ");
//    	int currentPosition = -1;
//    	int tokenBegin = 0;
//    	int tokenEnd = 0;
//
//    	for (String tokensplit : tokensplits)
//    	{
//    		tokenBegin = currentPosition + 1;
//    		currentPosition += tokensplit.length();
//    		tokenEnd = currentPosition;
//
//    		Token token = new Token(jcas, tokenBegin, tokenEnd);
//    		token.addToIndexes();
//    	}
    	    	
    	// TODO Set Sequence
    	// TODO Set Unit
    	    	
        jcas.setDocumentText(documentText);
        jcas.setDocumentLanguage(documentLanguage);
        
        currentLine++;
    }
    
    

    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}