package de.unidue.langtech.teaching.pp.pipelines;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.ngrams.NGramAnnotator;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.annotators.SyllableCountAnnotator;
import de.unidue.langtech.teaching.pp.example.BaselineExample;
import de.unidue.langtech.teaching.pp.example.EvaluatorExample;
import de.unidue.langtech.teaching.pp.example.ReaderExample;
import de.unidue.langtech.teaching.pp.example.newType.LetterAnnotator;
import de.unidue.langtech.teaching.pp.example.newType.Printer;

public class ExtendedPipeline
{

    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(NGramAnnotator.class,
                		NGramAnnotator.PARAM_N, 3),
                AnalysisEngineFactory.createEngineDescription(SyllableCountAnnotator.class),
                AnalysisEngineFactory.createEngineDescription(BaselineExample.class)

        );
    }
}