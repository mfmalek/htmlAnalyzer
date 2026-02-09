package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlAnalyzer {
	private String host;
	private Path htmlDataFilePath;
	
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
	
	public void htmlDataDownload(String htmlData) throws IOException {
		String userHomePath = System.getProperty("user.home");
		htmlDataFilePath = Paths.get(userHomePath).resolve("Downloads").resolve("HTML Data.txt");
		
	    try(BufferedWriter writer = new BufferedWriter(new FileWriter(htmlDataFilePath.toString()))) {
	    	writer.write(htmlData);
	    }
	}

	public String getHost() {
		return host;
	}
	
	public String getFilePath() {
		return htmlDataFilePath.toString();
	}
}