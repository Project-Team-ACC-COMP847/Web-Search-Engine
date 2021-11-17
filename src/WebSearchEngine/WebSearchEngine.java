package WebSearchEngine;
/**
 * 
 */

/**
 * @author Suhail-UWindsor

 *
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebSearchEngine {
		public static final int NO_OF_URLS = 1;
	
	/**
	 * Browse the WWW and return a number of random urls derived from the source url
	 * This method will be called recursively and stop when the urlsList size > NO_OF_URLS  
	 * @param url	source url
	 * @param noOfUrls	number of random urls
	 * @param urlsList	final urls list
	 */
	
		public static void crawUrls(String url, int noOfUrls, List<String> urlsList) {
		List<String> tmpUrlsList = new ArrayList<String>();
		
		try {
			Document doc = Jsoup.connect(url).get();
			String regex = "(http://|https://)(www.)?.*?[.net|.com|.org|.ca].*?\"\\s?";

			String matchedUrl = "";
			Pattern r = Pattern.compile(regex );
			Matcher m = r.matcher(doc.html());
			while (m.find()) {
				matchedUrl = m.group();
				tmpUrlsList.add(matchedUrl.substring(0, matchedUrl.lastIndexOf("\"")));
			}

			// return a random noOfUrls
			if (tmpUrlsList.size() > noOfUrls) {
				Collections.shuffle(tmpUrlsList);
				tmpUrlsList = tmpUrlsList.subList(0, noOfUrls);
			}
			
			urlsList.addAll(tmpUrlsList);
			while (urlsList.size() <= NO_OF_URLS && tmpUrlsList.size() > 0 ) {
				String randomUrl = tmpUrlsList.get((int)(Math.random() * tmpUrlsList.size()));
				crawUrls(randomUrl, noOfUrls, urlsList);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
		public static void main(String[] args) throws IOException {
			// Start crawling from this web page
			String sourceUrl = "https://www.gutenberg.org/";
			
		}
}