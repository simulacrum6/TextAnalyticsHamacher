package de.unidue.langtech.testing.pp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.unidue.langtech.pp.utils.FrequencyMap;

public class FrequencyMapTest {

	@Test
	public void basicTest() 
		throws Exception
    {
		FrequencyMap frequencymap = new FrequencyMap();
		String[] input = new String[]{"a", "9000", "404"};
		
		frequencymap.put("lemma", "n", "12345", "1");
		frequencymap.put("lemmaish", input);
		
		assertEquals( "12345" , frequencymap.get("lemma")[1] );
		assertEquals( "a" , frequencymap.get("lemmaish")[0] );
		
		assertEquals("n", frequencymap.getPos("lemma"));
		assertEquals("a", frequencymap.getPos("lemmaish"));
		
		assertEquals(12345, frequencymap.getCount("lemma"));
		assertEquals(9000, frequencymap.getCount("lemmaish"));
		
		assertEquals(1, frequencymap.getRank("lemma"));
		assertEquals(404, frequencymap.getRank("lemmaish"));
		
		assertEquals(2, frequencymap.getSize());
		assertEquals(12345, frequencymap.getMaxFreq());
		assertEquals(9000, frequencymap.getMinFreq());
		
		assertTrue( frequencymap.contains("lemmaish") );
		assertTrue( !frequencymap.contains("not in my book"));
	}
}