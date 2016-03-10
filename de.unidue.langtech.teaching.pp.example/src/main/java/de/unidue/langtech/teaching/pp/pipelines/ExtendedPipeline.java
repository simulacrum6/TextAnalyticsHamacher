package de.unidue.langtech.teaching.pp.pipelines;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.decompounding.uima.annotator.CompoundAnnotator;
import de.tudarmstadt.ukp.dkpro.core.decompounding.uima.resource.BananaSplitterResource;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
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
                		FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/SUBTLEXus.txt",
                		FrequencyAnnotator.PARAM_FREQUENCY_CORPUS_NAME, "subtlexus"),
                AnalysisEngineFactory.createEngineDescription(StanfordPosTagger.class),
//                AnalysisEngineFactory.createEngineDescription(CompoundAnnotator.class,
//                CompoundAnnotator.PARAM_SPLITTING_ALGO, BananaSplitterResource.class),
                AnalysisEngineFactory.createEngineDescription(Playground.class)
        );
    }
}