
package WebSearchEngine;


public class Trie implements Interface_Trie 

{
	Trie_Node rootnode = new Trie_Node();
	
	int hash_code = 0;
	
	@Override
	public void add(String word) 
	
	{
	   	 Trie_Node tr = rootnode;
	   	 
	     for (char chr : word.toCharArray()) 
	     
	     {
	         if (tr.nodes[chr-'a'] == null) 
	        	 
	             tr.nodes[chr-'a'] = new Trie_Node();
	         
	         tr = tr.nodes[chr-'a'];	        
	     }
	     
	     hash_code +=word.hashCode();
	     
	     tr.Increment_Value();
	     
	     tr.isEnd = true;
	}

	@Override
	public Interface_Node find(String word) 
	
	{
	   	 Trie_Node tr = rootnode;
	   	 
	     for (char chr : word.toCharArray()) 
	     
	     {
	        if (tr.nodes[chr-'a'] == null)  
	        	
	        	return null;
	        
	        tr = tr.nodes[chr-'a']; 
	     }
	     
	     
	     if( tr != null && tr.isEnd)
	    	 
	    	 return tr;
	     
		    return null;
	}
	
	

	@Override
	public int Get_NodeCount() 
	
	{
		return CountNodesInTrie(rootnode); 
		
	}
	
	private int CountNodesInTrie(Trie_Node node)  
	
	{ 
		if (node == null) 
			
			return 0;
		
		int c = 0; 
		
		for (int i = 0; i < 26; i++) 
		
		{ 
			if (node.nodes[i] != null) 
				
				c += CountNodesInTrie(node.nodes[i]); 
		} 
		return (1 + c); 
	} 
	 
	@Override
	public int Get_WordCount() 
	
	{
		return WordCount(rootnode);
	}
		
	private int WordCount(Trie_Node root) 
	
	{ 
        int output = 0; 
        
        if (root.isEnd) 
        	
            output++; 	      
        
        for (int ia = 0; ia < 26; ia++)  
        	
        	if (root.nodes[ia] != null ) 
        		
        		output += WordCount(root.nodes[ia]); 
        
        return output;    
    } 
	 
	 @Override
	 public String toString() 
	 
	 {
		 char[] WordArray = new char[50];
		 
		 StringBuilder sbr = new StringBuilder();
		 
		 printAllWords(rootnode, WordArray, 0, sbr);
		 
		 if(sbr.toString().length()==0)
			 
			 return "";
		 
		 return sbr.toString().substring(1);
	 }

	 private void printAllWords(Trie_Node root, char[] wordArray, int pos, StringBuilder sb) 
	 
	 {
	    if(root == null)
	       return;
	    if(root.isEnd)  {
	    	 sb.append("\n");
			 for(int i=0; i<pos; i++) {
				 sb.append(wordArray[i]);
			 }
	    }
	    for(int i=0; i<26; i++) {
	       if(root.nodes[i] != null)  {
	          wordArray[pos] = (char)(i+'a');
	          printAllWords(root.nodes[i], wordArray, pos+1, sb);
	       }
	    }
	 }
	 
	 @Override
	 public int hashCode()
	 {
		 return hash_code;
	 }
	 
	 @Override
	 
	 public boolean equals(Object obj)
	 {
		if(obj instanceof Trie)
		{
			Trie p = (Trie) obj;	
			
			if(this.Get_NodeCount() != p.Get_NodeCount())
				
				 return false;
			
		    if(this.Get_WordCount() != p.Get_WordCount())
		    	
		    	return false;  
		    
			return compareTrie(this, p);
			
		}
		
		else 
		
		{
			return false;
		}
	 }

	 private boolean compareTrie(Trie tr1, Trie tr2) 
	 
	 {
		 String str1 = tr1.toString();
		 
		 String str2 = tr2.toString();
		 
		 if(str1.equals("") && str2.equals(""))
			 
			 return true;
		 
		 String[] strs1 = str1.split("\n");
		 
		 String[] strs2= str2.split("\n");
		 
		 if(strs1.length!= strs2.length)
			 
			 return false;
		 
		 for(String s: strs1)
		 
		 {
			 Interface_Node node1 = tr1.find(s);
			 
			 Interface_Node node2 = tr2.find(s);
			 
			 if(node1.Get_Value()!=node2.Get_Value())
				 
				 return false;
		 }
		return true;
	 }
}