package de.unidue.langtech.testing.pp.misc;

import static de.tudarmstadt.ukp.dkpro.tc.testing.FeatureTestUtil.assertFeatures;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.tc.api.features.Feature;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import de.unidue.langtech.pp.annotators.FrequencyAnnotator;
import de.unidue.langtech.teaching.pp.type.CharNGram;

public class CoveredAnnoSelectionTest
{
    @Test
    public void coveredAnnoSelectionTest()
        throws Exception
    {
    	AnalysisEngine ae = createEngine( BreakIteratorSegmenter.class );
		
        JCas jcas = ae.newJCas();
	    
        jcas.setDocumentLanguage("en");
        jcas.setDocumentText("This is a test");
         
        ae.process(jcas);
         
        TextClassificationUnit coveringUnit = new TextClassificationUnit(jcas, 5, 6);
        Token coveredToken = JCasUtil.selectCovering(jcas, Token.class, coveringUnit.getBegin(), coveringUnit.getEnd()).get(0);

        Assert.assertEquals(coveredToken.getBegin(), coveringUnit.getBegin());
    }
}