package WebSearchEngine;

import java.io.File;


public class BruteForceSearch {
	public static void main(String[] args) {
//	    search("a");
	}
	
	public static int bruteForceSearch(String pat, String txt) {
	       int M = pat.length();
	       int N = txt.length();
//	       StdOut.println(N);
	       for (int i = 0; i <= N - M; i++) {
	           int j;
	           for (j = 0; j < M; j++) {
	               if (txt.charAt(i+j) != pat.charAt(j))
	                   break;
	           }
	           if (j == M) return i;            // found at offset i
	       }
	       return -1;                            // not found, return -1 so that we know the pattern is not found
	}
	
	public static void search(String toSearch) {
	   File[] files = new File("./Text_Files/").listFiles();
		int count = 0;
		for (File file : files) {
			
		    if (file.isFile()) {
//		    	StdOut.print("./Text_Files/" + file.getName());
		    	In input = new In("./Text_Files/" + file.getName());
		    	
		    	int noOfLine = 0;
				   do {
					   ++noOfLine;
					   String line = input.readLine();
//					   StdOut.println(line);
					   int offset1a = bruteForceSearch(toSearch, line);
					 
					   if(offset1a>=0) {	// if position is found, then print
						   System.out.println("\nFor file: " + file.getName());
						   StdOut.println("Word/pattern : ");
						   StdOut.println("'" + toSearch + "' found at line " + (noOfLine) + " and position " + (offset1a));
					   }
				   } while(input.hasNextLine());
				   input.close();
		    }
		}
		StdOut.println("No of times the word is repeated : " + count);
	}
}
