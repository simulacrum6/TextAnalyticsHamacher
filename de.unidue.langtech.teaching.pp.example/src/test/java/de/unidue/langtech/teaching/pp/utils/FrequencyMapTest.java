package de.unidue.langtech.teaching.pp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.unidue.langtech.teaching.pp.utils.FrequencyMap;

public class FrequencyMapTest {

	@Test
	public void basicTest() 
		throws Exception
    {
		FrequencyMap frequencymap = new FrequencyMap();
				
		frequencymap.put("lemma", "n", "12345", "1");
		frequencymap.put("lemmaish", "a", "9000", "404");
		
		assertEquals("n", frequencymap.getPos("lemma"));
		assertEquals("a", frequencymap.getPos("lemmaish"));
		
		assertEquals(12345, frequencymap.getCount("lemma"));
		assertEquals(9000, frequencymap.getCount("lemmaish"));
		
		assertEquals(1, frequencymap.getRank("lemma"));
		assertEquals(404, frequencymap.getRank("lemmaish"));
		
		assertEquals(2, frequencymap.getSize());
		assertEquals(12345, frequencymap.getMaxFreq());
		assertEquals(9000, frequencymap.getMinFreq());
		
	}
}