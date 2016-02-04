package de.unidue.langtech.teaching.pp.tc;

import static de.tudarmstadt.ukp.dkpro.core.api.io.ResourceCollectionReaderBase.INCLUDE_PREFIX;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.component.NoOpAnnotator;
import org.apache.uima.resource.ResourceInitializationException;

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
//import de.tudarmstadt.ukp.dkpro.tc.examples.util.DemoUtils;
//import de.tudarmstadt.ukp.dkpro.tc.features.length.NrOfCharsUFE;
//import de.tudarmstadt.ukp.dkpro.tc.ml.ExperimentCrossValidation;
//import de.tudarmstadt.ukp.dkpro.tc.weka.WekaClassificationAdapter;

//TODO Delete that stuff
/**
 * This is an example for POS tagging as unit classification. Each POS is treated as a
 * classification unit, but unlike sequence tagging the decision for each POS is taken
 * independently. This will usually give worse results, so this is only to showcase the concept.
 * 
 */
public class ComplexityClassification
    implements Constants
{

    public static final String LANGUAGE_CODE = "en";
    public static final int NUM_FOLDS = 2;
    //TODO Check dat stuff
    public static final String corpusFilePathTrain = "src/main/resources/inputfiles/cwi_testing_allannotations.txt";

    public static void main(String[] args)
        throws Exception
    {
    	//TODO Set environment Variable
    	// This is used to ensure that the required DKPRO_HOME environment variable is set.
    	// Ensures that people can run the experiments even if they haven't read the setup instructions first :)
    	// Don't use this in real experiments! Read the documentation and set DKPRO_HOME as explained there.
    	DemoUtils.setDkproHome(ComplexityClassification.class.getSimpleName());
    	
        new ComplexityClassification().runCrossValidation(getParameterSpace());
    }

    // ##### CV #####
    protected void runCrossValidation(ParameterSpace pSpace)
        throws Exception
    {
    	//TODO Check Experiment Types, identify correct ones
        ExperimentCrossValidation batch = new ExperimentCrossValidation("SemEval16Complexity",
        		WekaClassificationAdapter.class, NUM_FOLDS);
        batch.setPreprocessing(getPreprocessing());
//        batch.addInnerReport(WekaClassificationReport.class);
        batch.setParameterSpace(pSpace);
        batch.setExecutionPolicy(ExecutionPolicy.RUN_AGAIN); //Not sure, what that one does. Needs checking.
//        batch.addReport(BatchCrossValidationReport.class);
        // batch.addReport(BatchRuntimeReport.class);

        // Run
        Lab.getInstance().run(batch);
    }

    public static ParameterSpace getParameterSpace()
    {
        // configure training and test data reader dimension
        Map<String, Object> dimReaders = new HashMap<String, Object>();
        //TODO Insert Training Reader
        dimReaders.put(DIM_READER_TRAIN, MyReaderTrain.class); 
        dimReaders.put(
                DIM_READER_TRAIN_PARAMS,
                //Parameter List
                Arrays.asList(new Object[] { MyReaderTrain.PARAM_LANGUAGE, "en"
                }));
        
        //TODO Select Classifier. SVM?
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimClassificationArgs = Dimension.create(DIM_CLASSIFICATION_ARGS,
                Arrays.asList(new String[] { NaiveBayes.class.getName() }));
        
        //TODO Add Pipeline Parameters. Unsure as to which ones.
        @SuppressWarnings("unchecked")
        Dimension<List<Object>> dimPipelineParameters = Dimension.create(DIM_PIPELINE_PARAMS,
                Arrays.asList(new Object[] { "something", "something" }),
                Arrays.asList(new Object[] { "something2", "something2" }));
        
        //TODO Add Feature Extractors
        @SuppressWarnings("unchecked")
        Dimension<List<String>> dimFeatureSets = Dimension.create(DIM_FEATURE_SET,
                Arrays.asList(new String[] { NrOfCharsUFE.class.getName() }));
        
        //TODO Fit for Experiment (LM_Single_Label, FM_UNIT/FM_SEQUENCE)
		ParameterSpace pSpace = new ParameterSpace(Dimension.createBundle("readers", dimReaders),
                Dimension.create(DIM_LEARNING_MODE, LM_SINGLE_LABEL), Dimension.create(
                        DIM_FEATURE_MODE, FM_UNIT), dimPipelineParameters, dimFeatureSets,
                dimClassificationArgs);

        return pSpace;
    }

    protected AnalysisEngineDescription getPreprocessing()
        throws ResourceInitializationException
    {	
    	//TODO Add Annotators.
        return createEngineDescription(createEngineDescription(MyAnnotator.class));
    }

}