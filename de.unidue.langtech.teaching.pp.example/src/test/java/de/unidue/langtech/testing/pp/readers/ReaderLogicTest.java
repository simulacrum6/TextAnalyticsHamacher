package de.unidue.langtech.testing.pp.readers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReaderLogicTest 
{
	
	
	@Test
	public void ReaderLogicTest()
	{
		List<String> testlist = new ArrayList<String>();
			testlist.add("Line0");
			testlist.add("Line0");
			testlist.add("Line0");
			testlist.add("Line1");
			testlist.add("Line1");
			testlist.add("Line1");
			testlist.add("Line1");
			testlist.add("Line2");
			testlist.add("Line2");
			testlist.add("Line3");
			testlist.add("Line3");
			
		PseudoReader reader = new PseudoReader(testlist);
			
		while(reader.hasNext()){
				reader.getNext();
			}
		
		// Checks if all Line Data has been extracted	
		 System.out.println( "Total Lines: " + reader.lines.size() + ", Extracted Lines: " + reader.test_gatheredData);
		 assertEquals(reader.test_gatheredData, reader.lines.size());
		
	}
	
	static class PseudoReader{
		int currentLine;
		List<String> lines;
		String documentText;
		String lineText;
		
		int test_loopStart;
		int test_gatheredData;
		
		public PseudoReader(List<String> lines)
		{
			this.currentLine = 0;
			this.lines = new ArrayList<String>(lines);
			this.documentText = lines.get(0);
			this.lineText = documentText;
			System.out.println("Total Lines:" + lines.size());
			System.out.println();
		}
		
		public boolean hasNext()
		{	
			if(	currentLine < lines.size() ){
				documentText = lines.get(currentLine);
				test_loopStart = currentLine;
				
				while(currentLine < lines.size())
				{
					System.out.println("Gathered Data, line[" + currentLine + "]");
					test_gatheredData++;
					
					if( !(currentLine+1 < lines.size()) ){
						break;
					} else if( ! lines.get(currentLine).equals( lines.get(currentLine+1) ) ){
						break;
					} else {
						currentLine++;
					}
					
				}	
				return true;				
			} else {
				return false;
			}
			
		}
		
		public void getNext()
		{
			System.out.println();
			System.out.println("Current line Index: " + currentLine);
			System.out.println("Current Line Document: " + documentText);
			
			
			assertEquals( documentText, lines.get(test_loopStart) );
			System.out.println("Current Line Document: " + documentText.equals(lines.get(test_loopStart)));
			System.out.println();
			
			currentLine++;
			assertEquals( new Integer(currentLine), new Integer(test_gatheredData) );
		}
		
		
	}
	
}