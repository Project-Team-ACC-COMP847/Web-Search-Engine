package WebSearchEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter the website URL (You can type No): ");
		Scanner sc=new Scanner(System.in);
		String url_val = sc.nextLine();
		String source_URL = "https://www.gutenberg.org/";
		if (url_val.contentEquals( "No")) {
			source_URL = "https://www.gutenberg.org/";
		} else {
			source_URL = url_val;
		}
		List<String> urlsList = new ArrayList<String>();
		
		WebSearchEngine.crawUrls(source_URL, 3, urlsList);
		
//		System.out.println(urlsList.size());
		for (String url : urlsList) {
			try {
				Document doc = Jsoup.connect(url).get();
				
				String text = doc.text();
				String title = doc.title();
				
				if (title.contains("|")) {
					title = title.split("\\|")[0];
				}
				System.out.println(title);
				System.out.println(text);
				PrintWriter out = new PrintWriter("./Text_Files/" + title + ".txt");
				out.println(text);
				out.close();
				
			} catch (Exception e) {
				continue;
			}
		}
		System.out.println("\nEnter the word to Search: ");
		String toSearch = sc.nextLine();
		System.out.println("\n\nSearching Via TST: \n");
		double start, end, diff;
		start = end = diff = 0;
		start = System.currentTimeMillis();
		TST_Implementation.getAllMatches(toSearch);
		end  = System.currentTimeMillis();
		diff = end - start;
		System.out.println("\nTotal Time taken for TST Search: " + diff + "ms");
		

		System.out.println("\n\n\nSearching Via Brute Force: \n\n");
		start = System.currentTimeMillis();
		BruteForce_PageRanking.search(toSearch);
		end  = System.currentTimeMillis();
		diff = end - start;
		System.out.println("\nTotal Time taken for Brute Force: " + diff + "ms");
		
		//Ami
		
		String str= wordsuggestion.suggestionfunction("trein");
		System.out.print(str);
		

		
	}

}
