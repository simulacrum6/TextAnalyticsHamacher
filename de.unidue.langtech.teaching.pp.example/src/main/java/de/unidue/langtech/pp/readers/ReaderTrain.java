package de.unidue.langtech.pp.readers;

import java.io.File;
import java.io.IOException;
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
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

/* 
 * A training data reader for the SemEval2016 Task11: Complex Word Identification.
 * Input files must be organised in the following format:
 *	 TXT-FILE
 *		[Line1]
 *		[Line2]
 *		(...)
 *		[LineN]
 *	 LINE
 *		[Sentence][TAB][.][TAB][Tokeninfo]
 *	 SENTENCE
 *		[Word1][TAB][Word2][TAB](...)[TAB][WordN]
 *	 TOKENINFO
 *		[TAB][WordX][TAB][Position(WordX)][TAB][Rating1][TAB](...)[TAB][RatingM]
 *		Each individual rating must be either 1 or 0. 
*/
	
public class ReaderTrain
    extends JCasCollectionReader_ImplBase
{

    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile; 
    
    private List<String> lines;
    private int currentLine;
    
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
        return currentLine < lines.size();
    }
    
    @Override
    public void getNext(JCas jcas)
        throws IOException, CollectionException
    {

        String[] split1 = lines.get(currentLine).split("\\.");
        if (split1.length != 2) {
            throw new IOException("Wrong line format: " + lines.get(currentLine) + " \n Line needs to contain a '.' ");
        }
        
        String[] split2 = split1[1].split("\t", -1);
        if (split2.length < 3) {
            throw new IOException("Wrong Tokeninfo format: " + split1[1]);
        }
        
        int complexitySum = 0;
        for(int i = 3; i < split2.length; i++){
        	if(split2[i].equals("1")){
        		complexitySum ++;
        	}
        }
        
        GoldComplexity goldComplexity = new GoldComplexity(jcas);
        goldComplexity.setToken(split2[1]);
        goldComplexity.setPosition( Integer.parseInt(split2[2]) );
        goldComplexity.setComplexitySum(complexitySum);
        if(complexitySum > 0){
        	goldComplexity.setComplexity(1);
        }else{
        	goldComplexity.setComplexity(1);
        }
        goldComplexity.addToIndexes();
        
        jcas.setDocumentText(split1[0]);
        
        currentLine++;
    }

    public Progress[] getProgress()
    {
        return new Progress[] { new ProgressImpl(currentLine, lines.size(), "lines") };
    }
}