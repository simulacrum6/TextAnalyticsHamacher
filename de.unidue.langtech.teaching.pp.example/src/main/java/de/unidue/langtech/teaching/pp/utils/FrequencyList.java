package de.unidue.langtech.teaching.pp.utils;

import java.util.HashMap;
import java.util.Map;

public class FrequencyList {

	private Map<String, String[]> freqMap;
	private int minFreq;
	private int maxFreq;
	private int size;
	
	public FrequencyList()
	{
		this.minFreq = Integer.MAX_VALUE;
		this.maxFreq = 0;
		this.size = 0;
		
		this.freqMap = new HashMap<String, String[]>();
	};
	
	public FrequencyList(String[] lemmas, String[] pos, String[] freqCount, String[] freqRank)
	{
		this.minFreq = Integer.MAX_VALUE;
		this.maxFreq = 0;
		this.size = lemmas.length;
		
		//TODO Check for length, throw exception
		this.freqMap = new HashMap<String, String[]>();
		
		for(int i = 0; i < lemmas.length; i++){
			//TODO check for correct format of freqCount/Rank
			
			this.put(lemmas[i], pos[i], freqCount[i], freqRank[i]);
		}
		
	}
	
	//Getters
	
	public String[] get(String key)
	{
		return freqMap.get(key);
	}
	
	public String getPos(String key)
	{
		return freqMap.get(key)[0];
	}
	
	public int getCount(String key)
	{
		return Integer.parseInt( freqMap.get(key)[1] );
	}
	
	public int getRank(String key)
	{
		return Integer.parseInt( freqMap.get(key)[2] );
	}
	
	public int getMaxFreq()
	{
		return maxFreq;
	}
	
	public int getMinFreq()
	{
		return minFreq;
	}
	public int getSize()
	{
		return this.size;
	}
	
	//Setters
	
	public void put(String key, String[] values)
	{
		this.setMaxFreq(Integer.parseInt(values[1]));
		this.setMinFreq(Integer.parseInt(values[1]));
		this.size ++;
		
		freqMap.put(key, values);
	}
	
	public void updateSize(){
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
	
	public void setMaxFreq(int aFreq)
	{
		if(aFreq > maxFreq){
			maxFreq = aFreq;
		}
	}
	
	public void setMinFreq(int aFreq)
	{
		if(aFreq < minFreq){
			minFreq = aFreq;
		}
	}
	public boolean contains(String lemma)
	{
		if(freqMap.containsKey(lemma)){
			return true;
		}else{
			return false;
		}
	}
	

}
