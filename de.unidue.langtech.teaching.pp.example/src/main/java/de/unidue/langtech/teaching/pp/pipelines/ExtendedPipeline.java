package de.unidue.langtech.teaching.pp.pipelines;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.ngrams.NGramAnnotator;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.pp.readers.ReaderTrain;
import de.unidue.langtech.teaching.pp.annotators.CharNGramAnnotator;
import de.unidue.langtech.teaching.pp.annotators.Playground;
import de.unidue.langtech.teaching.pp.annotators.SyllableCountAnnotator;

public class ExtendedPipeline
{

    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderTrain.class,
                        ReaderTrain.PARAM_INPUT_FILE, "src/main/resources/inputfiles/cwi_training_allannotations.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(NGramAnnotator.class,
                		NGramAnnotator.PARAM_N, 3),
                AnalysisEngineFactory.createEngineDescription(SyllableCountAnnotator.class),
                AnalysisEngineFactory.createEngineDescription(CharNGramAnnotator.class),
                AnalysisEngineFactory.createEngineDescription(Playground.class)

        );
    }
}