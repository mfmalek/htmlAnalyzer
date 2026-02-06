package model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class htmlAnalyzer {
	private String host;
	
	public String dataExtractor(String hyperlink) throws URISyntaxException, IOException {
		if(!hyperlink.startsWith("http://") && !hyperlink.startsWith("https://")) {
			hyperlink = "https://" + hyperlink;
		}
		
		URI uri = new URI(hyperlink);
		if(!uri.isAbsolute()) {
			throw new URISyntaxException(hyperlink, "URI is not absolute.");
		}
		
		host = uri.getHost();
		
		URL url = uri.toURL();
		StringBuilder htmlBuilder = new StringBuilder();
		try(Scanner html = new Scanner(url.openStream());) {
			while(html.hasNextLine()) {
					htmlBuilder.append(html.nextLine()).append("\n");
			}
		}
		return htmlBuilder.toString();
	}
	
	public String getHost() {
		return host;
	}
}