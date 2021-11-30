package WebSearchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class BruteForce_PageRanking {
	static Hashtable <String, Integer> hashTable = new Hashtable <>();
	public static void main(String[] args) {
	    search("a");
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
	
    public static void sortValue(Hashtable<String, Integer> t){

        //Transferring as List and sort it
        ArrayList<Map.Entry<String, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<String, Integer>>(){

          public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
             return o1.getValue().compareTo(o2.getValue());
         }});
        Collections.reverse(l);
        
        System.out.println("\nTop 5 Pages :");
        for (int i = 0; i < 5;i++) 
	      { 		      
	          System.out.println(l.get(i)); 		
	      }   
        
//        System.out.println(l);
     }
	
	public static void search(String toSearch) {
	   File[] files = new File("./Text_Files/").listFiles();
	   
		for (File file : files) {
			
		    if (file.isFile()) {
//		    	StdOut.print("./Text_Files/" + file.getName());
		    	In input = new In("./Text_Files/" + file.getName());
		    	int count = 0;
		    	int noOfLine = 0;
				   do {
					   
					   ++noOfLine;
					   String line = input.readLine();
//					   StdOut.println(line);
					   int offset1a = bruteForceSearch(toSearch, line);
					 
					   if(offset1a>=0) {	// if position is found, then print
						   System.out.println("\nFor file: " + file.getName());
						   StdOut.println("Word/pattern : ");
						   count++;
						   StdOut.println("'" + toSearch + "'"
						   		+ "' found at line " + (noOfLine) + " and position " + (offset1a));
					   }
				   } while(input.hasNextLine());
				   input.close();  
				   hashTable.put(file.getName(), count);
		    }
		}
//		StdOut.println(hashTable);	// Unsorted hashtable
		sortValue(hashTable);		// Sorting the table
	}
}
