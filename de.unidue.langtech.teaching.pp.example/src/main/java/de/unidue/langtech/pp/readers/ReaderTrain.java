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
*/
// FIXME First ClassificationUnits of each jCas and the very last ClassificationUnit are not registered. In this case: student
public class ReaderTrain
    extends JCasCollectionReader_ImplBase
{

    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    public static final String PARAM_DOCUMENT_LANGUAGE = "DocumentLanguage";
    @ConfigurationParameter(name = PARAM_DOCUMENT_LANGUAGE, defaultValue = "en", mandatory = true)
    String documentLanguage;
    
    int meta_docCount;
    String documentText;
    List<String> lines;
    List<String[]> linesplits;
    
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
           linesplits = getLineSplits(lines);
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
    	boolean hasnext = currentLine < lines.size();   	
    	     	
        if (hasnext) 
        {        	
        	documentText = getCurrentSentence();
        	
        	wordBuffer = new ArrayList<String>();
            positionBuffer = new ArrayList<Integer>();
            complexityBuffer = new ArrayList<Integer>();
            complexitySumBuffer = new ArrayList<Integer>();

        	while ( currentLine < lines.size() ) 
        	{
        		String[] tokeninfo = linesplits.get(currentLine)[1].split("\t", -1);
        		
                if (tokeninfo.length < 3) {
                    throw new IOException("Wrong Tokeninfo format: " + linesplits.get(currentLine)[1]);
                }
                
                int complexitySum = 0;   
                for (int i = 2; i < tokeninfo.length; i++) 
                {
                	if (tokeninfo[i].equals("1")) {
                		complexitySum ++;
                	}
                	
                }

                wordBuffer.add(tokeninfo[0]);
                positionBuffer.add( Integer.parseInt( tokeninfo[1] ) );
                complexitySumBuffer.add(complexitySum);
                complexityBuffer.add( (complexitySum > 0)?1:0 );
                
                
                if( !(currentLine+1 < lines.size()) ){
                	break;
                } else if( !getSentence(currentLine).equals( getSentence(currentLine+1) )){
                	break;
                } else {
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
    	jcas.setDocumentText(documentText);
        jcas.setDocumentLanguage(documentLanguage);
         
        meta_docCount ++;
        
        // Set Metadata
    	DocumentMetaData metaData = DocumentMetaData.create(jcas);
	    	metaData.setDocumentId("SemEval16_Complexity_" + meta_docCount);
	    	metaData.setDocumentTitle( "SemEval16_Complexity" );       
        
    	// Set Sentence
    	Sentence sentence = new Sentence(jcas, 0, documentText.length());
    		sentence.addToIndexes();
    	
    	// Set Tokens
    	int currentPosition = 0;
    	
    	String[] tokensplits = jcas.getDocumentText().split(" ", -1);
    	for (String tokensplit : tokensplits)
    	{
	    	int begin = currentPosition;
	    	int end = currentPosition + tokensplit.length();
	    		
	    	currentPosition += tokensplit.length() + 1;
    			    	
	    	Token token = new Token(jcas, begin, end);
	    		token.addToIndexes();
	    	
			if (wordBuffer.contains(tokensplit))
    		{	
				int index = wordBuffer.indexOf(tokensplit);
	    	    // Set Gold Annotation.		
 				GoldComplexity goldAnno = new GoldComplexity(jcas, begin, end);
    				goldAnno.setWord( wordBuffer.get(index) );
    				goldAnno.setPosition( positionBuffer.get(index) );
    				goldAnno.setComplexity( complexityBuffer.get(index) );
    				goldAnno.setComplexitySum( complexitySumBuffer.get(index) );
    				goldAnno.addToIndexes();
    		}
    		
    	}
    	// TODO Do not forget!
        currentLine++;
    }
    
    public String[] getSafeSplits (String line, String splitpoint, int splitCount)
    	throws IOException
    {
    	if ( splitpoint.equals("default") ) {
    		splitpoint = "[\\.\\!\\?]\\t";
    	}
 
    	String[] split = line.split( splitpoint, splitCount );
    	
    	if ( splitCount != -1){
    		
    		if (split.length != splitCount) {
             throw new IOException("Wrong line format: " + line + " \n Line needs to match the following separator: " + splitpoint);
    		}
    		
    	}
    	
    	return split;
    }
    
    public List<String[]> getLineSplits (List<String> lines)
    	throws IOException
    {
    	List<String[]> linesplits = new ArrayList<String[]>();
    	
    	for(String line : lines)
    	{
    		linesplits.add(getSafeSplits (line, "default", 2));
    	}
    	return linesplits;
    }
    
    public String getSentence(int index){
    	return linesplits.get(index)[0];
    }
    
    public String getCurrentSentence()
    {
    	return linesplits.get(currentLine)[0];
    }
    public String getNextSentence(){
    	return linesplits.get(currentLine+1)[0];
    }
    
    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}