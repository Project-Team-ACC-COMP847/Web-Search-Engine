package WebSearchEngine;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TST_Implementation {
	static TST<Integer> st = new TST<Integer>();
	
	public static void main(String[] args) {
//		System.out.println(getMatch("The Public's Library and Digital Archive.txt", "Library"));
	}
	
	public static int getMatch (String fileName, String toSearch) {
		In in;
		String completeData = null;
		int returnInt = -1;
		try {
		    in = new In("./Text_Files/" + fileName);
		    completeData = in.readAll();
//		    completeData = completeData.replace(',', ' ');
//		    completeData = completeData.replace('-', ' ');
//		    System.out.println("After Transformation: " + completeData);
		    StringTokenizer tokenData = new StringTokenizer(completeData);
		    
		    int i = 0;
		    while (tokenData.hasMoreTokens()) {  
//		    	System.out.println(tokenData.nextToken());
		    	st.put(tokenData.nextToken(), i);
		    	i+=1;
		     }
		    if(st.get(toSearch) == null) {
		    	returnInt = -1;
		    } else {
		    	returnInt = st.get(toSearch);
		    }
		     
		    
		} catch (Exception e) { System.out.println(e); }
//        System.out.println();
		return returnInt;
		
	}
	
	public static void getAllMatches (String toSearch) {
		List<String> results = new ArrayList<String>();
		File[] files = new File("./Text_Files/").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		    	int tempVar = getMatch(file.getName(), toSearch);
		    	if(tempVar>0) {
			    	System.out.println("In the File, " + file.getName() + " at position " + tempVar);
		    	}
		    }
		}

		
	}
}
