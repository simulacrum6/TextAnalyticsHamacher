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
import de.tudarmstadt.ukp.dkpro.tc.features.ngram.FrequencyDistributionNGramUFE;
import de.tudarmstadt.ukp.dkpro.tc.features.ngram.LuceneCharacterNGramUFE;
import de.tudarmstadt.ukp.dkpro.tc.features.ngram.LuceneNGramUFE;
//import de.tudarmstadt.ukp.dkpro.tc.features.wordDifficulty.IsInflectedWordUFE;
//import de.tudarmstadt.ukp.dkpro.tc.features.wordDifficulty.IsLatinWordUFE;

import de.tudarmstadt.ukp.dkpro.tc.ml.ExperimentCrossValidation;
import de.tudarmstadt.ukp.dkpro.tc.ml.report.BatchCrossValidationReport;
import de.tudarmstadt.ukp.dkpro.tc.ml.report.BatchRuntimeReport;
import de.tudarmstadt.ukp.dkpro.tc.weka.WekaClassificationAdapter;
import de.tudarmstadt.ukp.dkpro.tc.weka.report.WekaClassificationReport;
import de.unidue.langtech.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.pp.annotators.Playground;
import de.unidue.langtech.pp.featureExtractors.FrequencyUFE;
import de.unidue.langtech.pp.featureExtractors.PosUFE;
import de.unidue.langtech.pp.readers.ReaderTrainTC;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

public class Complexity_Comparison_CV
    implements Constants
{
	public static final String LANGUAGE_CODE = "en";
    public static final String EXPERIMENT_NAME = "ComplexityExperiment-Comparison";
    public static final String EXPERIMENT_TYPE = "CV";
    public static final int NUM_FOLDS = 8;
    static final String CORPUS_SMALL = "src/main/resources/inputfiles/cwi_training_allannotations.txt";
    static final String CORPUS_LARGE = "src/main/resources/inputfiles/cwi_testing_annotated.txt";
    static final String CORPUS_FILEPATH_TRAIN = CORPUS_LARGE;

    public static void main(String[] args)
        throws Exception
    {
    	// Set environment Variable
    	String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS").format(new Date());
    	System.setProperty("DKPRO_HOME", "src/main/resources/output/"+ EXPERIMENT_NAME + "/" + EXPERIMENT_TYPE + "_" + NUM_FOLDS + "fold/" + timestamp);
    	
    	// Run experiment
        new Complexity_Comparison_CV().runCrossValidation(getParameterSpace());
    }

    // Experiment Setup
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
        // Set Training Reader dimensions
        Map<String, Object> dimReaders = new HashMap<String, Object>();
	        dimReaders.put(DIM_READER_TRAIN, ReaderTrainTC.class); 
	        dimReaders.put(
	            DIM_READER_TRAIN_PARAMS,
	            Arrays.asList( new Object[] { ReaderTrainTC.PARAM_INPUT_FILE, CORPUS_FILEPATH_TRAIN })
	        );
        
	        
        // Set Classifiers 
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimClassificationArgs = Dimension.create(
        		DIM_CLASSIFICATION_ARGS,
        		Arrays.asList(new String[] { NaiveBayes.class.getName() }),
        		Arrays.asList(new String[] { J48.class.getName() }),
        		// "-I": number of trees
        		Arrays.asList(new String[] { RandomForest.class.getName(), "-I", "5" })
        );
        

        // Set Feature Extractors
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimFeatureSets = Dimension.create(DIM_FEATURE_SET,
                Arrays.asList( new String[] { 
                		NrOfCharsUFE.class.getName(),
                		FrequencyUFE.class.getName(),
                		PosUFE.class.getName(),
                		// IsLatinWordUFE.class.getName(),
                		// IsInflectedWordUFE.class.getName()
                		LuceneCharacterNGramUFE.class.getName()
//                		LuceneNGramUFE.class.getName()                		
                })
        );
       
        // Set Feature Extractor Parameters
        @SuppressWarnings("unchecked")
        Dimension<List<Object>> dimPipelineParameters = Dimension.create(DIM_PIPELINE_PARAMS,
        		 Arrays.asList(new Object[] {
        				 PosUFE.PARAM_USE_POS_INDEX, true,
        				
        				LuceneCharacterNGramUFE.PARAM_CHAR_NGRAM_MIN_N, 2,
        				LuceneCharacterNGramUFE.PARAM_CHAR_NGRAM_MAX_N, 4,
        				LuceneCharacterNGramUFE.PARAM_CHAR_NGRAM_USE_TOP_K, 50,
                        LuceneCharacterNGramUFE.PARAM_CHAR_NGRAM_LOWER_CASE, true
                        
                        
                 })
        );
        
        
        // Set Filter. Only best N number of features
        Map<String, Object> dimFeatureSelection = new HashMap<String, Object>();
        		dimFeatureSelection.put(
        				DIM_FEATURE_SEARCHER_ARGS, 
        				Arrays.asList( new String[] {
        						Ranker.class.getName(), "-N", "20" })
        		);
        		dimFeatureSelection.put(
        				DIM_ATTRIBUTE_EVALUATOR_ARGS,
        				Arrays.asList( new String[] { InfoGainAttributeEval.class.getName() })
        		);
        		dimFeatureSelection.put(DIM_APPLY_FEATURE_SELECTION, true);
        
        
        // Set Parameter Space
		@SuppressWarnings("unchecked")
		ParameterSpace pSpace = new ParameterSpace(
				Dimension.createBundle("readers", dimReaders),
                Dimension.create(DIM_LEARNING_MODE, LM_SINGLE_LABEL), 
                Dimension.create(DIM_FEATURE_MODE, FM_SEQUENCE),
                // Dimension.createBundle("featureSelection", dimFeatureSelection),
                dimPipelineParameters, 
                dimFeatureSets,
                dimClassificationArgs  
		);

        return pSpace;
    }

    
    protected AnalysisEngineDescription getPreprocessing()
        throws ResourceInitializationException
    {	
        return createEngineDescription(createEngineDescription(
        		AnalysisEngineFactory.createEngineDescription(StanfordLemmatizer.class),
        		AnalysisEngineFactory.createEngineDescription(StanfordPosTagger.class),
                AnalysisEngineFactory.createEngineDescription(FrequencyAnnotator.class, 
//                		FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/SUBTLEXus.txt",
//                		FrequencyAnnotator.PARAM_FREQUENCY_CORPUS_NAME, "subtlex")
                		FrequencyAnnotator.PARAM_FREQUENCY_LIST, "src/main/resources/required/5kwordfrequency.txt")               
        		));
    }

}