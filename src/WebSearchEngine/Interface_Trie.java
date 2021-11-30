
package WebSearchEngine;


public interface Interface_Trie {

	void add(String word);

	Interface_Node find(String word);

	int Get_WordCount();

	int Get_NodeCount();

	@Override
	String toString();

	@Override
	int hashCode();

	@Override
	boolean equals(Object o);
}
