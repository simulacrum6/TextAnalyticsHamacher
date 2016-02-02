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

//FIXME ISSUE: Out of bounds exception.

public class ReaderTrain
    extends JCasCollectionReader_ImplBase
{

    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    //Language required for Stanford Lemmatizer
    public static final String PARAM_DOCUMENT_LANGUAGE = "DocumentLanguage";
    @ConfigurationParameter(name = PARAM_DOCUMENT_LANGUAGE, defaultValue = "en")
    private String documentLanguage;
    
    private List<String> lines;
    private int currentLine;
    private String documentText;
    
    private List<String> wordBuffer = new ArrayList<String>();
    private List<Integer> positionBuffer = new ArrayList<Integer>();
    private List<Integer> complexityBuffer = new ArrayList<Integer>();
    private List<Integer> complexitySumBuffer = new ArrayList<Integer>();
    
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
        
        wordBuffer = new ArrayList<String>();
        positionBuffer = new ArrayList<Integer>();
        complexityBuffer = new ArrayList<Integer>();
        complexitySumBuffer = new ArrayList<Integer>();
        
        //TODO reduce to 1 split variable.
        String[] split1;
        String[] split2;
        
        int complexitySum;
        
        wordBuffer = new ArrayList<String>();
        positionBuffer = new ArrayList<Integer>();
        complexityBuffer = new ArrayList<Integer>();
        complexitySumBuffer = new ArrayList<Integer>();
    	
        if(currentLine < lines.size()){
            //Splits line into sentence and tokeninfo component
            split1 = lines.get(currentLine).split("\\s[\\.\\!\\?]\\t");
            if (split1.length != 2) {
                throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain the following separator: '.\\t' ");
            }
        	currLineText = split1[0];
        	documentText = currLineText;
        	
        	//TODO transform to while loop.
        	do{
                split2 = split1[1].split("\t", -1);
                if (split2.length < 3) {
                    throw new IOException("Wrong Tokeninfo format: " + split1[1]);
                }
                
                complexitySum = 0;
                for(int i = 3; i < split2.length; i++){
                	if(split2[i].equals("1")){
                		complexitySum ++;
                	}
                }
                
                wordBuffer.add(split2[0]);
                positionBuffer.add( Integer.parseInt( split2[1] ) );
                complexitySumBuffer.add(complexitySum);
                complexityBuffer.add( (complexitySum > 0)?1:0 );
                
                //Preparations for next loop
                
                currentLine ++;
                
            	split1 = lines.get(currentLine).split("\\s[\\.\\!\\?]\\t");
                if (split1.length != 2) {
                	throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain the following separator: '.\\t' ");
                }    	
            	currLineText = split1[0];
            	
        	}while(documentText.equals(currLineText)&& currentLine < lines.size()-1);

        }
        
    	
    	return currentLine < lines.size();
    }
    
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {  	
   	
        //Set Gold Annotation.
    	for(int i = 0; i < wordBuffer.size(); i++){
    		GoldComplexity goldComplexity = new GoldComplexity(jcas);
            goldComplexity.setWord(wordBuffer.get(i));
            goldComplexity.setPosition(positionBuffer.get(i));
            goldComplexity.setComplexity(complexityBuffer.get(i));
            goldComplexity.setComplexitySum(complexitySumBuffer.get(i));
            goldComplexity.addToIndexes();
    	}
    	
        jcas.setDocumentText(documentText);
        jcas.setDocumentLanguage(documentLanguage);
        
        currentLine++;
    }

    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}