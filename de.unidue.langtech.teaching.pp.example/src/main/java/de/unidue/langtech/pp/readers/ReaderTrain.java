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
 * 
 * A training data reader for the SemEval2016 Task11: Complex Word Identification.
 * Input files must be organised in the following format:
 *	 Each INPUTFILE
 *		[Line1]
 *		[Line2]
 *		(...)
 *		[LineN]
 *	 Each LINE in INPUTFILE
 *		[Sentence][SPACE][.][TAB][Tokeninfo]
 *	 Each SENTENCE in LINE
 *		[Word1][TAB][Word2][TAB](...)[TAB][WordN]
 *	 Each TOKENINFO in LINE
 *		[TAB][WordX][TAB][Position(WordX)][TAB][Rating1][TAB](...)[TAB][RatingM]
 *		Each individual RATING must be either 1 or 0.
 *		Each individual WORD must be a sequence of characters. 
 *
 * This is probably the most complicated part of the project (codewise) and not every part might 
 * seem to make sense at first glance. Particularly the hasNext() method.
 * For those scorched by the dragons that lay beyond: 
 * https://github.com/simulacrum6/TextAnalyticsHamacher/wiki/Code#file-reader-readertrainjava
*/

public class ReaderTrain
    extends JCasCollectionReader_ImplBase
{

    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    public static final String PARAM_DOCUMENT_LANGUAGE = "DocumentLanguage";
    @ConfigurationParameter(name = PARAM_DOCUMENT_LANGUAGE, defaultValue = "en", mandatory = true)
    String documentLanguage;
    
    int meta_documentCount;
    String documentText;
    List<String> lines;
    List<String[]> lineparts;
    
    int currentLine;
    
    List<String> temp_goldAnnotation_words;
    List<Integer> temp_goldAnnotation_positions;
    List<Integer> temp_goldAnnotation_complexity;
    List<Integer> temp_goldAnnotation_complexityRatingSums;
    
    @Override
    public void initialize(UimaContext context)
        throws ResourceInitializationException
    {
    	super.initialize(context);

        try {
           lines = FileUtils.readLines(inputFile);
           lineparts = getLineParts(lines);
           currentLine = 0;
           meta_documentCount = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }
    
    public boolean hasNext()
        throws IOException, CollectionException
    {    
    	boolean hasnext = currentLine < lines.size();   	
    	     	
        if (hasnext) 
        {        	
        	documentText = getCurrentSentence();
        	
        	temp_goldAnnotation_words = new ArrayList<String>();
            temp_goldAnnotation_positions = new ArrayList<Integer>();
            temp_goldAnnotation_complexity = new ArrayList<Integer>();
            temp_goldAnnotation_complexityRatingSums = new ArrayList<Integer>();

            // Extract Gold Annotations until the next line contains a different sentence or EndOfFile is reached
        	while ( currentLine < lines.size() ) 
        	{
        		String[] annotationInfo = getAnnotationParts(currentLine);
        			// annotationInfo[0]: Rated Word
        			// annotationInfo[1]: Position of word in sentence
        			// annotationInfo[n]: Complexity Rating by Person[n]. n >=2
        	
                if (annotationInfo.length < 3) {
                    throw new IOException("Wrong Tokeninfo format: " + getAnnotationParts(currentLine));
                }
                
                // Sum complexity ratings for current word
                int complexityRatingSum = 0;   
                for (int i = 2; i < annotationInfo.length; i++) 
                {
                	if (annotationInfo[i].equals("1")) {
                		complexityRatingSum ++;
                	}
                }
                
                // Add information to buffer
                temp_goldAnnotation_words.add(annotationInfo[0]);
                temp_goldAnnotation_positions.add( Integer.parseInt( annotationInfo[1] ) );
                temp_goldAnnotation_complexityRatingSums.add(complexityRatingSum);
                temp_goldAnnotation_complexity.add( (complexityRatingSum > 0) ? 1 : 0 );
                
                // Checks if End Of Line List is reached. Otherwise loop is ended to prevent ArrayOutOfBoundsException
                if( !(currentLine+1 < lines.size()) ){
                	break;
                // Checks if next sentence is different from current sentence. loop ends, if so.
                } else if(!documentText.equals( getSentence(currentLine+1))){
                	break;
                } else {
                	// Loop continues on next line
                	currentLine ++;
                }
        	}
        }    	
        return hasnext;
    }
    
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {  	
    	meta_documentCount ++;
    	
    	jcas.setDocumentText(documentText);
        jcas.setDocumentLanguage(documentLanguage);
        
        // Set Metadata
    	DocumentMetaData metaData = DocumentMetaData.create(jcas);
	    	metaData.setDocumentId("SemEval16_Complexity_" + meta_documentCount);
	    	metaData.setDocumentTitle( "SemEval16_Complexity" );       
        
    	// Set Sentence
    	Sentence sentence = new Sentence(jcas, 0, documentText.length());
    		sentence.addToIndexes();
    	
    	// Set Tokens
    	int currentPosition = 0;    	
    	
    	String[] words = jcas.getDocumentText().split(" ", -1);
	    	for (String word : words)
	    	{
		    	int begin = currentPosition;
		    	int end = currentPosition + word.length();
		    		
		    	currentPosition += word.length() + 1;
	    			    	
		    	Token token = new Token(jcas, begin, end);
		    		token.addToIndexes();
		    	
		    	// Set Gold Annotation
				if (temp_goldAnnotation_words.contains(word))
	    		{	
					int index = temp_goldAnnotation_words.indexOf(word);
		    	    		
	 				GoldComplexity goldAnno = new GoldComplexity(jcas, begin, end);
	 					goldAnno.setWord( temp_goldAnnotation_words.get(index) );
	    				goldAnno.setPosition( temp_goldAnnotation_positions.get(index) );
	    				goldAnno.setComplexity( temp_goldAnnotation_complexity.get(index) );
	    				goldAnno.setComplexitySum( temp_goldAnnotation_complexityRatingSums.get(index) );
	    				goldAnno.addToIndexes();
	    		}
	    		
	    	}
        currentLine++;
    }
    
    
    
    public String[] getPartsSafe (String line, String splitpoint, int partCount)
    	throws IOException
    {
    	if ( splitpoint.equals("default") ) {
    		splitpoint = "[\\.\\!\\?]\\t";
    	}
 
    	String[] split = line.split( splitpoint, partCount );
    	
    	if ( partCount != -1)
    	{
    		if (split.length != partCount) {
             throw new IOException("Wrong line format: " + line + " \n Line needs to match the following separator: " + splitpoint);
    		}
    		
    	}
    	
    	return split;
    }
    
    
    void extractGoldAnnotations()
    	throws IOException
    {
    	
    }
    
    public List<String[]> getLineParts (List<String> lines)
    	throws IOException
    {
    	List<String[]> lineparts = new ArrayList<String[]>();
    	
    	for(String line : lines)
    	{
    		lineparts.add(getPartsSafe (line, "default", 2));
    	}
    	return lineparts;
    }
    
    public String[] getAnnotationParts(int lineIndex){
    	return lineparts.get(currentLine)[1].split("\t", -1);
    }
    
    public String[] getCurrentAnnotationParts(){
    	return lineparts.get(currentLine)[1].split("\t", -1);    	
    }
    
    
    public String getSentence(int lineIndex){
    	return lineparts.get(lineIndex)[0];
    }
    
    public String getCurrentSentence()
    {
    	return lineparts.get(currentLine)[0];
    }
    public String getNextSentence(){
    	return lineparts.get(currentLine+1)[0];
    }
    
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}