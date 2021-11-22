package NewWebSearch;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class mainClassNew {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the website URL (You can type No): ");
		Scanner sc=new Scanner(System.in);
		String url_val = sc.nextLine();
		String source_URL = "https://www.gutenberg.org/";
		if (url_val.contentEquals( "No")) {
			source_URL = "https://www.gutenberg.org/";
			System.out.println(source_URL);
		} else {
			source_URL = url_val;
		}
		List<String> urlsList = new ArrayList<String>();
		
		WebSearchEngine.crawUrls(source_URL, 2, urlsList);
		
		System.out.println(urlsList.size());
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
		// Shivam and Ami

		
	}

}
