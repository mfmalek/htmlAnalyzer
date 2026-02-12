package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HtmlExtractor {
	private String host;
	
	public String htmlDataExtractor(String hyperlink) throws URISyntaxException, IOException {
		if(hyperlink == null || hyperlink.isBlank()) {
			throw new IllegalArgumentException("URL must not be empty");
		}
		
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

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        htmlBuilder.append(line).append("\n");
		    }
		}
		return htmlBuilder.toString();
	}
	
	public String htmlHeadExtractor(String htmlData) {
		int headOpeningTag = htmlData.indexOf("<head");
		int headClosingTag = htmlData.indexOf("</head>");
		String htmlHead = htmlData.substring(headOpeningTag, headClosingTag + 7);
		
		return htmlHead;
	}
	
	public String htmlBodyExtractor(String htmlData) {
		int bodyOpeningTag = htmlData.indexOf("<body");
		int bodyClosingTag = htmlData.indexOf("</body>");
		String htmlBody = htmlData.substring(bodyOpeningTag, bodyClosingTag + 7);
		
		return htmlBody;
	}

	public String getHost() {
		return host;
	}
}