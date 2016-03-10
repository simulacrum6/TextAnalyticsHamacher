package de.unidue.langtech.testing.pp.misc;

import java.util.ArrayList;
import java.util.List;

public class ReaderLogicTest 
{
	public static void main(String[] args) 
	{
		int testsize = 4;
		
		List<String> testlist = new ArrayList<String>();
			testlist.add("Line1");
			testlist.add("Line1");
			testlist.add("Line2");
			testlist.add("Line2");
			testlist.add("Line3");
			testlist.add("Line3");
			
		PseudoReader reader = new PseudoReader(testlist);
		while(reader.hasNext())
		{
			reader.getNext();
		}
		
	}
	
	static class PseudoReader{
		int currentLine;
		List<String> lines;
		String documentText;
		
		public PseudoReader(List<String> lines)
		{
			this.currentLine = 0;
			this.lines = new ArrayList<String>(lines);
			this.documentText = lines.get(currentLine);
		}
		
		public boolean hasNext()
		{
			boolean hasNext;
			
			if(	this.currentLine < this.lines.size() ){
				hasNext = true;
				
				while( currentLine < this.lines.size()-1 && this.documentText.equals(lines.get(this.currentLine))  ) {
					this.currentLine ++;
				}
				this.documentText = lines.get(this.currentLine);
				
			} else {
				hasNext = false;
			}

			return hasNext;
		}
		
		public void getNext()
		{
			System.out.println(currentLine);
			System.out.println(lines.get(currentLine-1));
			currentLine++;
		}
		
	}
	
}
