package WebSearchEngine;

import java.io.IOException;

public class wordsuggestion {

public static String suggestionfunction(String word) throws IOException
	
	{
		
		String FileName = ("./src/WebSearchEngine/dictionary.txt");
		
		String Input = word;
		
		Interface_Spell_Corrector spellcorrector = new Spell_Corrector();
		
		spellcorrector.Use_Dictionary(FileName);
		
		String suggestions = spellcorrector.SuggestSimilarWord(Input);
		
		if (suggestions == null) 
		
		{
			
		    suggestions = "Nothing to display";
		}
		return suggestions;
	}

}
