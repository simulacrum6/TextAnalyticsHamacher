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
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
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
    
    int meta_docCount;
    String documentText;
    List<String> lines;
    
    int currentLine;
    
    List<String> wordBuffer;
    List<Integer> positionBuffer;
    List<Integer> complexityBuffer;
    List<Integer> complexitySumBuffer;
    List<Integer> beginBuffer;
    
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
    	super.initialize(context);

        try {
           lines = FileUtils.readLines(inputFile);
           currentLine = 0;
           meta_docCount = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }
    
    public boolean hasNext()
        throws IOException, CollectionException
    {    
    	// TODO Clean up code       	
        if (currentLine < lines.size()) 
        {
        	meta_docCount++;
        	
        	wordBuffer = new ArrayList<String>();
            positionBuffer = new ArrayList<Integer>();
            complexityBuffer = new ArrayList<Integer>();
            complexitySumBuffer = new ArrayList<Integer>();
            
        	String[] split = lines.get(currentLine).split("\\s[\\.\\!\\?]\\t");
            
            if (split.length != 2) {
                throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain the following separator: '.\\t' ");
            }
            
            String currLineText = split[0];
        	documentText = currLineText;
        	
        	//TODO transform to while loop.
        	do {
        		String[] tokeninfo = split[1].split("\t", -1);
        		
                if (tokeninfo.length < 3) {
                    throw new IOException("Wrong Tokeninfo format: " + split[1]);
                }
                
                int complexitySum = 0;
                
                for (int i = 3; i < tokeninfo.length; i++) 
                {
                	if (tokeninfo[i].equals("1")) {
                		complexitySum ++;
                	}
                	
                }

                wordBuffer.add(tokeninfo[0]);
                positionBuffer.add( Integer.parseInt( tokeninfo[1] ) );
                complexitySumBuffer.add(complexitySum);
                complexityBuffer.add( (complexitySum > 0)?1:0 );
                
                //Preparations for next loop
                
                currentLine ++;
                
                // TODO write prepareNext() method
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
    	jcas.setDocumentText(documentText);
        jcas.setDocumentLanguage(documentLanguage);
        // FIXME MetaData DocumentID is not working properly
//        List<DocumentMetaData> metaDatas = new ArrayList<DocumentMetaData>(JCasUtil.select(jcas, DocumentMetaData.class));
        if(JCasUtil.select(jcas, DocumentMetaData.class).isEmpty())
        {
        	DocumentMetaData metaData = new DocumentMetaData(jcas);
		    	metaData.setDocumentId("SemEval16_Complexity" + meta_docCount);
		    	metaData.setDocumentTitle( "SemEval16_Complexity" );
		    	metaData.addToIndexes();
        }
        
    	// Set Sentence
    	Sentence sentence = new Sentence(jcas, 0, documentText.length());
    		sentence.addToIndexes();
    	
    	// Set Tokens
    	
    	int currentPosition = 0;
    	
    	String[] tokensplits = jcas.getDocumentText().split(" ");

    	for (String tokensplit : tokensplits)
    	{
	    	int begin = currentPosition;
	    	int end = currentPosition + tokensplit.length();
	    		
	    	currentPosition += tokensplit.length() + 1;
    			    	
    			int index = wordBuffer.indexOf(tokensplit);	
	    		
	    		Token token = new Token(jcas, begin, end);
	    			token.addToIndexes();
	    	
			if (wordBuffer.contains(tokensplit))
    		{	
	    	    // Set Gold Annotation.		
 				GoldComplexity goldComplexity = new GoldComplexity(jcas, begin, end);
    				goldComplexity.setWord( wordBuffer.get(index) );
    				goldComplexity.setPosition( positionBuffer.get(index) );
    				goldComplexity.setComplexity( complexityBuffer.get(index) );
    				goldComplexity.setComplexitySum( complexitySumBuffer.get(index) );
    				goldComplexity.addToIndexes();
    		}
    		
    	}

        currentLine++;
    }
    
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}