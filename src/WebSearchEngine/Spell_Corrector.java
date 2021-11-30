/**
 * 
 */
package WebSearchEngine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.TreeMap;


/**
 * @author suhailsinghbains
 *
 */

public class Spell_Corrector implements Interface_Spell_Corrector {

	Trie trie = new Trie();
	
	Map<String, Integer> dict = new HashMap<>();
	
	final static List<String> invalid = Arrays.asList("lol", "abcdefghijklmnopqrstuvwxyz");
	
	@Override
	public void Use_Dictionary(String FileName) throws IOException {
		
		
		try {
			
			
			FileReader file = new FileReader(FileName);
			
			BufferedReader bfreader = new BufferedReader(file);	  
			
			String line = null;
			
			while ((line = bfreader.readLine()) != null)
			
			{          			        					
				
				String word = line.toLowerCase();
				
				if (!line.contains(" "))
				
				{
					dict.put(word, dict.getOrDefault(word, 0)+1);
					
					trie.add(word);
				} 
				
				else 
				
				{
					String[] strs= line.split("\\s");
					
					for(String str: strs) 
					
					{
						dict.put(str, dict.getOrDefault(str, 0)+1);
						
						trie.add(str);
					}
				}
			}
			
			file.close();
			
			bfreader.close();
		} 
		catch (FileNotFoundException ex)
		
		{
			System.err.println(ex);
		} 
		
		catch (IOException ei) 
		
		{
			System.err.println(ei);
		}
	}

	@Override
	
	public String SuggestSimilarWord(String Input) 
	
	{
		if (Input.length()==0 || Input==null || invalid.contains(Input.toLowerCase()) )
			
			
			return null;
		
		String g = Input.toLowerCase();
		
		String r=null;
		
		TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> map = new TreeMap<>();	
		
		Interface_Node node = trie.find(g);		
		
		if(node == null) 
		
		{
			
			for (String wr: dict.keySet())
			
			{
				int distance = editDistance(wr, g);	
				
				TreeMap<Integer, TreeSet<String>> similarWords = map.getOrDefault(distance, new TreeMap<>());
				
				int frequency = dict.get(wr);
				
				TreeSet<String> set = similarWords.getOrDefault(frequency, new TreeSet<>());
				
				set.add(wr);	
				
				similarWords.put(frequency, set);
				
				map.put(distance, similarWords);		
			}	
			
			r= map.firstEntry().getValue().lastEntry().getValue().first();
		
		 } 
		else if (node !=null) 
		
		{
			 
			 r = g;
		 }
		 return r;
	}

	private int editDistance(String string1, String string2)
	{
		int a = string1.length();
		
		int b = string2.length();
		
	    int dp[][] = new int[a+1][b+1];
	    
	    for (int i = 0; i <= a; i++) 
	    
	    {
	        for (int j = 0; j <= b; j++)
	        
	        {
	        	
	        	if (i == 0)
	        		
	                dp[i][j] = j;  
	        	
	            else if (j == 0)
	            	
	                dp[i][j] = i; 
	        	
	            else if (string1.charAt(i-1) == string2.charAt(j-1))
	            	
	                dp[i][j] = dp[i-1][j-1];
	        	
	        	
	            else if (i>1 && j>1  && string1.charAt(i-1) == string2.charAt(j-2) 
	            		
	            		&& string1.charAt(i-2) == string2.charAt(j-1))
	            	
	            	
	            	dp[i][j] = 1+Math.min(Math.min(dp[i-2][j-2], dp[i-1][j]), Math.min(dp[i][j-1], dp[i-1][j-1]));
	        	
	            else
	            	
	                dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])); 
	        }
	    } 
	    return dp[a][b];
	}
}


