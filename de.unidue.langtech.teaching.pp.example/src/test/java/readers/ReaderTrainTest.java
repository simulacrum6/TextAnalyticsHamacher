package readers;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.unidue.langtech.pp.readers.ReaderTrainTC;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.annotators.Playground;
import de.unidue.langtech.teaching.pp.type.CorpusFrequency;

public class ReaderTrainTest {

	@Test
    public
    void frequencyAnnotatorTest()
    throws Exception
    {
		SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderTrainTC.class,
                        ReaderTrainTC.PARAM_INPUT_FILE, "src/main/resources/inputfiles/cwi_training_allannotations.txt",
                        ReaderTrainTC.PARAM_CLASSIFICATION_MODE, "sum"
                ), new AnalysisEngineDescription[0]

        );
		        
    }
    
}

