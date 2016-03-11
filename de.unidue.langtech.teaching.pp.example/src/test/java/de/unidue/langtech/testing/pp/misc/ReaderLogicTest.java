package de.unidue.langtech.testing.pp.misc;

import java.util.ArrayList;
import java.util.List;

public class ReaderLogicTest 
{
	public static void main(String[] args) 
	{
		int testsize = 4;
		
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
			
			
		
	}
	
	static class PseudoReader{
		int currentLine;
		List<String> lines;
		String documentText;
		String lineText;
		
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
			documentText = lines.get(currentLine);
			
			while(currentLine < lines.size())
			//for(; currentLine < lines.size(); currentLine++)
			{
				// Gather Current Line Data -> FUNCTION
				System.out.println("Gathered Data, line[" + currentLine + "]");
				
				// Checking for next Value  isArrayOutOfBounds
				if( !(currentLine+1 < lines.size()) ){
					break;
					// Checking for next Line != this line
				} else if( ! documentText.equals( lines.get(currentLine+1) ) ){
					break;
				}
				currentLine++;
			}
			
			
			if(	currentLine+1 <lines.size() ){	
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
			System.out.println();
			
			currentLine++;
			
		}
		
		
	}
	
}
