package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import model.htmlAnalyzer;

public class CtrlProgram {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Insert a URL: ");
		String url = input.nextLine();
		
		htmlAnalyzer html = new htmlAnalyzer();
		
		try {
			System.out.println(html.dataExtractor(url));
		} catch(URISyntaxException e) {
			System.out.println("Invalid URL (" + e.getReason() + ").");
		} catch (IOException e) {
			System.out.println("Failed to connect to the URL host (" + html.getHost() + ").");
		}
	}
}