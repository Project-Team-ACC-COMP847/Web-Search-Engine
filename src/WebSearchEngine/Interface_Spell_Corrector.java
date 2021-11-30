
package WebSearchEngine;

import java.io.IOException;

public interface Interface_Spell_Corrector 

{
	void Use_Dictionary(String FileName) throws IOException;
	
	String SuggestSimilarWord(String Input);
}
