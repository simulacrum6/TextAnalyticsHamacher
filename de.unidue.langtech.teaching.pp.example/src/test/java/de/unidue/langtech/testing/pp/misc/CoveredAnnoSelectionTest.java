package de.unidue.langtech.testing.pp.misc;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.tc.api.type.TextClassificationUnit;
import junit.framework.Assert;

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