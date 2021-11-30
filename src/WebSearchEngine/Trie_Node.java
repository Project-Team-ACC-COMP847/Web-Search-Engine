
package WebSearchEngine;

public class Trie_Node implements Interface_Node 

{
	
	Trie_Node[] nodes = new Trie_Node[26];
	
	int count;
	
    boolean isEnd;

	@Override
	public int Get_Value()
	
	{		
		return count;
	}
	
	@Override
	
	public void Increment_Value() 
	
	{
		count++;	
	}
	
	@Override
	
	public Interface_Node[] Get_Children() 
	
	{
		return nodes;
	}	
}


