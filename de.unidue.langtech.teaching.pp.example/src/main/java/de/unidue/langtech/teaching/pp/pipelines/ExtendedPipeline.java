package de.unidue.langtech.teaching.pp.pipelines;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.unidue.langtech.pp.readers.ReaderTrainTC;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.annotators.Playground;

/*
 * Simple test pipeline to test readers and annotators informally.
 * */

// TODO Move to test.
public class ExtendedPipeline
{
	
    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderTrainTC.class,
                        ReaderTrainTC.PARAM_INPUT_FILE, "src/main/resources/inputfiles/cwi_training_allannotations.txt",
                        ReaderTrainTC.PARAM_CLASSIFICATION_MODE, "sum"
                ),
                AnalysisEngineFactory.createEngineDescription(StanfordLemmatizer.class),
                AnalysisEngineFactory.createEngineDescription(FrequencyAnnotator.class, 
                		FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/5kwordfrequency.txt"),
//                AnalysisEngineFactory.createEngineDescription(CharNGramAnnotator.class),
                AnalysisEngineFactory.createEngineDescription(Playground.class)

        );
    }
}