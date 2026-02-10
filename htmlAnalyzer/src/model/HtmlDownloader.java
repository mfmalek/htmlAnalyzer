package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HtmlDownloader {
	private Path htmlDataFilePath;
	
	public Path pathBuilder(String fileName) {
		String userHomePath = System.getProperty("user.home");
		
		return htmlDataFilePath = Paths.get(userHomePath).resolve("Downloads").resolve(fileName);
	}
	
	public void fileWriter(String data, String fileName) throws IOException {
		Path filePath = pathBuilder(fileName);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
	    	writer.write(data);
	    }
	}
	
	public void htmlDataDownload(String data) throws IOException {
		fileWriter(data, "HTML Data.txt");
	}
	
	public void htmlHeadDownload(String data) throws IOException {
		fileWriter(data, "HTML Head Data.txt");
	}
	
	public String getFilePath() {
		return htmlDataFilePath.toString();
	}
}