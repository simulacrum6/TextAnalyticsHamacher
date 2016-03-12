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
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;

import de.tudarmstadt.ukp.dkpro.lab.Lab;
import de.tudarmstadt.ukp.dkpro.lab.task.Dimension;
import de.tudarmstadt.ukp.dkpro.lab.task.ParameterSpace;
import de.tudarmstadt.ukp.dkpro.lab.task.impl.BatchTask.ExecutionPolicy;

import de.tudarmstadt.ukp.dkpro.tc.core.Constants;
import de.tudarmstadt.ukp.dkpro.tc.features.length.NrOfCharsUFE;
import de.tudarmstadt.ukp.dkpro.tc.features.ngram.LuceneCharacterNGramUFE;
import de.tudarmstadt.ukp.dkpro.tc.ml.ExperimentCrossValidation;
import de.tudarmstadt.ukp.dkpro.tc.ml.report.BatchCrossValidationReport;
import de.tudarmstadt.ukp.dkpro.tc.ml.report.BatchRuntimeReport;
import de.tudarmstadt.ukp.dkpro.tc.weka.WekaClassificationAdapter;
import de.tudarmstadt.ukp.dkpro.tc.weka.report.WekaClassificationReport;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.RandomForest;

import de.unidue.langtech.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.pp.featureExtractors.FrequencyUFE;
import de.unidue.langtech.pp.featureExtractors.PosUFE;
import de.unidue.langtech.pp.readers.ReaderTrainTC;

public class Complexity_ZeroR_CV
    implements Constants
{
    public static final String LANGUAGE_CODE = "en";
    public static final String EXPERIMENT_NAME = "ComplexityExperiment-ZeroR";
    public static final String EXPERIMENT_TYPE = "CV";
    public static final int NUM_FOLDS = 10;
    static final String CORPUS_SMALL = "src/main/resources/inputfiles/cwi_training_allannotations.txt";
    static final String CORPUS_LARGE = "src/main/resources/inputfiles/cwi_testing_annotated.txt";
    static final String CORPUS_FILEPATH_TRAIN = CORPUS_SMALL;

    public static void main(String[] args)
        throws Exception
    {
    	// Set environment Variable
    	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS").format(new Date());
    	System.setProperty("DKPRO_HOME", "src/main/resources/output/"+ EXPERIMENT_NAME + "/" + EXPERIMENT_TYPE + "_" + NUM_FOLDS + "fold/" + timestamp);
    	
    	// Run experiment
        new Complexity_ZeroR_CV().runCrossValidation(getParameterSpace());
    }

    
    protected void runCrossValidation(ParameterSpace pSpace)
        throws Exception
    {
    	
    	ExperimentCrossValidation batch = new ExperimentCrossValidation(
    			EXPERIMENT_NAME, 
    			WekaClassificationAdapter.class, null, NUM_FOLDS
    	);
	    	batch.setPreprocessing(getPreprocessing());
	        batch.setParameterSpace(pSpace);
	        batch.setExecutionPolicy(ExecutionPolicy.RUN_AGAIN);
	        batch.addInnerReport(WekaClassificationReport.class);
	        batch.addReport(BatchCrossValidationReport.class);
	        batch.addReport(BatchRuntimeReport.class);

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
                 }));
        
        // Select Feature Extractors
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimFeatureSets = Dimension.create(DIM_FEATURE_SET,
                Arrays.asList(new String[] {              		               		
                }));
        
        // Fit for Experiment (LM_Single_Label, FM_UNIT/FM_SEQUENCE)
		@SuppressWarnings("unchecked")
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