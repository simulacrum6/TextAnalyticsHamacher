package de.unidue.langtech.teaching.pp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.uima.resource.ResourceInitializationException;

// I could have simply implemented the map interface... or extended an existing map type. But that would be smart...
// TODO Extend capabilities to handle more frequency measures and corpus stlyes
public class FrequencyMap 
{
	private Map<String, String[]> freqMap;
	private int minFreq;
	private int maxFreq;
	private int size;
	
//Getters
	public String[] get(String key)	{
		return freqMap.get(key);
	}
	
	public String getPos(String key) {
		return freqMap.get(key)[0];
	}
	
	public int getRank(String key)	{
		return Integer.parseInt( freqMap.get(key)[2] );
	}

	public int getCount(String key)	{
		return Integer.parseInt( freqMap.get(key)[1] );
	}
	
	public int getMaxFreq() {
		return maxFreq;
	}
	
	public int getMinFreq() {
		return minFreq;
	}
	
	public int getSize() {
		return this.size;
	}
	
//Setters
	public void updateSize()
	{
		this.size = freqMap.size();
	}
	
	public void put(String lemma, String pos, String count, String rank)
	{
		String[] values = new String[3];
		values[0] = pos;
		values[1] = count;
		values[2] = rank;
		
		this.put(lemma, values);
	}
	
	public void put(String key, String[] values) {
		this.updateMaxFreq(Integer.parseInt(values[1]));
		this.updateMinFreq(Integer.parseInt(values[1]));
		
		freqMap.put(key, values);
		
		this.updateSize();
	}
	
	private void updateMaxFreq(int aFreq) {
		if(aFreq > maxFreq){
			maxFreq = aFreq;
		}
	}
	
	private void updateMinFreq(int aFreq) {
		if(aFreq < minFreq){
			minFreq = aFreq;
		}
	}
	
	public boolean contains(String lemma)
	{
		if(freqMap.containsKey(lemma)){
			return true;
		} else {
			return false;
		}
	}
	
// Constructors
	public FrequencyMap()
	{
		this.minFreq = Integer.MAX_VALUE;
		this.maxFreq = 0;
		this.size = 0;
		
		this.freqMap = new Hashtable<String, String[]>();
	}
	
	public FrequencyMap(String[] lemmas, String[] pos, String[] freqCount, String[] freqRank)
	{
		this();
		this.size = lemmas.length;
		
		for (int i = 0; i < lemmas.length; i++) 
		{
			this.put(lemmas[i], pos[i], freqCount[i], freqRank[i]);
		}
		
	}	
	
	// Default from-file constructor.
	public FrequencyMap(File inputfile)
	{
		this();
		List<String> lines;
		String[] values;
		
		try {
			
			lines = FileUtils.readLines(inputfile);	              
			
			for(String line : lines)
			{
	           	values = line.split("\\t"); 
	        	this.put(values[1].trim(), values[2].trim(), values[3].trim(), values[0].trim());
	        }
			
		}catch (IOException e) {
	    	e.printStackTrace();
	    }	
	}
	
	// From-file constructor with corpus selection. Currently supported names: "5kwordfrequency", "subtlexus"
	public FrequencyMap(File inputfile, String corpusname)
	{
		this();
		
		List<String> lines;
		String[] values;
		final String MISSING_VAL = "-1";
		final String MISSING_TYPE = "N_A";
		
		if( corpusname.toLowerCase().equals("5kwordfrequency") )
		{			
			try {
				
				lines = FileUtils.readLines(inputfile);	              
				
				for(String line : lines)
				{
		           	values = line.split("\\t"); 
		           	this.put(values[1].trim(), values[2].trim(), values[3].trim(), values[0].trim());
		        }
				
			}catch (IOException e) {
		    	e.printStackTrace();
		    }
		} else if (corpusname.toLowerCase().equals("subtlexus"))
		{			
			try {
				
				lines = FileUtils.readLines(inputfile);	              
				
				for(String line : lines)
				{
		           	values = line.split("\\t"); 
		           	this.put(values[0].trim(), MISSING_TYPE , values[1].trim(), MISSING_VAL );
		        }
				
			}catch (IOException e) {
		    	e.printStackTrace();
		    }
		}		
	}
}
