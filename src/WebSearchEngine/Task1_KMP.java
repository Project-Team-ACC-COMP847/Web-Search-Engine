package WebSearchEngine;

import java.io.File;

public class Task1_KMP {
	public static void main(String[] args) {
	    
	}
	public static void search(String toSearch) {
	   File[] files = new File("./Text_Files/").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		    	StdOut.print("./Text_Files/" + file.getName());
		    	In input = new In("./Text_Files/" + file.getName());
		    	
		    	int noOfLine = 0;
		    	KMP kmp1 = new KMP(toSearch);
		    	do {
					   ++noOfLine;
					   String line = input.readLine();				//Reading the line and giving input
					   int offset1 = kmp1.search(line);
					   if(offset1!=line.length()) {	// if position is found, then print(position is not found when it is equal to line length
						   StdOut.print("pattern: ");
						   StdOut.println(toSearch + " at line " + (noOfLine) + " and position " + (offset1));
					   }
				   } while(input.hasNextLine());
				   input.close();
		    	
		    }
		}
	}

}
