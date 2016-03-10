package de.unidue.langtech.pp.experiments;

import static de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase.INCLUDE_PREFIX;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.component.NoOpAnnotator;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.tudarmstadt.ukp.dkpro.lab.Lab;
import de.tudarmstadt.ukp.dkpro.lab.task.Dimension;
import de.tudarmstadt.ukp.dkpro.lab.task.ParameterSpace;
import de.tudarmstadt.ukp.dkpro.lab.task.impl.BatchTask.ExecutionPolicy;
//TODO Add dependencies for required Packages.
//weka -> weka
//de.tudarmstadt.ukp.dkpro.lab.task -> dkpro.lab
//de.tudarmstadt.ukp.dkpro.tc.examples
//de.tudarmstadt.ukp.dkpro.tc.ml
//de.tudarmstadt.ukp.dkpro.tc.weka
//import weka.classifiers.bayes.NaiveBayes;
//import de.tudarmstadt.ukp.dkpro.lab.Lab;
//import de.tudarmstadt.ukp.dkpro.lab.task.BatchTask.ExecutionPolicy;
//import de.tudarmstadt.ukp.dkpro.lab.task.Dimension;
//import de.tudarmstadt.ukp.dkpro.lab.task.ParameterSpace;
import de.tudarmstadt.ukp.dkpro.tc.core.Constants;
import de.tudarmstadt.ukp.dkpro.tc.features.length.NrOfCharsUFE;
import de.tudarmstadt.ukp.dkpro.tc.features.ngram.LuceneNGramUFE;
//import de.tudarmstadt.ukp.dkpro.tc.examples.util.DemoUtils;
//import de.tudarmstadt.ukp.dkpro.tc.features.length.NrOfCharsUFE;
//import de.tudarmstadt.ukp.dkpro.tc.ml.ExperimentCrossValidation;
//import de.tudarmstadt.ukp.dkpro.tc.weka.WekaClassificationAdapter;
import de.tudarmstadt.ukp.dkpro.tc.ml.ExperimentCrossValidation;
import de.tudarmstadt.ukp.dkpro.tc.ml.report.BatchCrossValidationReport;
import de.tudarmstadt.ukp.dkpro.tc.weka.WekaClassificationAdapter;
import de.tudarmstadt.ukp.dkpro.tc.weka.report.WekaClassificationReport;
import de.unidue.langtech.pp.featureExtractors.FrequencyUFE;
import de.unidue.langtech.pp.readers.ReaderTrainTC;
import de.unidue.langtech.teaching.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.annotators.Playground;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.rules.ZeroR;

public class Complexity_ZeroR_CV
    implements Constants
{
    public static final String LANGUAGE_CODE = "en";
    public static final String EXPERIMENT_NAME = "ComplexityExperiment-ZeroR";
    public static final String EXPERIMENT_TYPE = "CV";
    public static final int NUM_FOLDS = 2;
    public static final String CORPUS_FILEPATH_TRAIN = "src/main/resources/inputfiles/cwi_training_allannotations.txt";

    public static void main(String[] args)
        throws Exception
    {
    	// Set environment Variable
    	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS").format(new Date());
    	System.setProperty("DKPRO_HOME", "src/main/resources/output/"+ EXPERIMENT_NAME + "/" + EXPERIMENT_TYPE + "/" + NUM_FOLDS + "fold/" + timestamp);
    	
    	// Run experiment
        new Complexity_Bayesline_CV().runCrossValidation(getParameterSpace());
    }

    
    protected void runCrossValidation(ParameterSpace pSpace)
        throws Exception
    {
    	
    	ExperimentCrossValidation batch = new ExperimentCrossValidation(EXPERIMENT_NAME,
        		WekaClassificationAdapter.class, null, NUM_FOLDS);
        batch.setPreprocessing(getPreprocessing());
     //   batch.addInnerReport(WekaClassificationReport.class);
        batch.setParameterSpace(pSpace);
        batch.setExecutionPolicy(ExecutionPolicy.RUN_AGAIN);
     //   batch.addReport(BatchCrossValidationReport.class);

        // Run
        Lab.getInstance().run(batch);
    }

    public static ParameterSpace getParameterSpace()
    {
        // Configure training reader dimension
        Map<String, Object> dimReaders = new HashMap<String, Object>();
      
        dimReaders.put(DIM_READER_TRAIN, ReaderTrainTC.class); 
        dimReaders.put(
                DIM_READER_TRAIN_PARAMS,
                // Parameter List
                Arrays.asList(new Object[] { ReaderTrainTC.PARAM_INPUT_FILE, CORPUS_FILEPATH_TRAIN
                }));
        
        // Select Classifier. 
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimClassificationArgs = Dimension.create(DIM_CLASSIFICATION_ARGS,
                Arrays.asList(new String[] { ZeroR.class.getName() }));
        
        
        @SuppressWarnings("unchecked")
        Dimension<List<Object>> dimPipelineParameters = Dimension.create(DIM_PIPELINE_PARAMS,
        		 Arrays.asList(new Object[] {
        				 //LuceneNGramUFE.PARAM_NGRAM_LOWER_CASE, "true"
                 }));
        
        // Select Feature Extractors
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimFeatureSets = Dimension.create(DIM_FEATURE_SET,
                Arrays.asList(new String[] { 
                		NrOfCharsUFE.class.getName(),
                		FrequencyUFE.class.getName()               		               		
                }));
        
        // Fit for Experiment (LM_Single_Label, FM_UNIT/FM_SEQUENCE)
		ParameterSpace pSpace = new ParameterSpace(Dimension.createBundle("readers", dimReaders),
                Dimension.create(DIM_LEARNING_MODE, LM_SINGLE_LABEL), Dimension.create(
                        DIM_FEATURE_MODE, FM_SEQUENCE), dimPipelineParameters, dimFeatureSets,
                dimClassificationArgs);

        return pSpace;
    }

    protected AnalysisEngineDescription getPreprocessing()
        throws ResourceInitializationException
    {	
    	
        return createEngineDescription(createEngineDescription(
        		AnalysisEngineFactory.createEngineDescription(StanfordLemmatizer.class),
                AnalysisEngineFactory.createEngineDescription(FrequencyAnnotator.class, 
                		FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/5kwordfrequency.txt")
        		));
    }

}