package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import model.HtmlAnalyzer;

public class CtrlProgram {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Insert a URL: ");
		String url = input.nextLine();
		
		HtmlAnalyzer html = new HtmlAnalyzer();
		
		System.out.println("===== SELECT WHAT YOU WANT TO DO =====\n");
		System.out.println("==> Data Extraction");
		System.out.println("\t1. HTML Data Extractor");
		System.out.println("\t2. HTML Head Data Extractor");
		System.out.println("\t3. HTML Body Data Extractor");
		System.out.println("==> Data Download");
		System.out.println("\t4. HTML Data Download");
		System.out.println("\t5. HTML Head Data Download");
		System.out.println("\t6. HTML Body Data Download");
		System.out.println("==> Miscellaneous");
		System.out.println("\t7. HTML Tag Counter");
		System.out.print("\nYour choice: ");
		int option = input.nextInt();
		
		switch(option) {
			case 1:
				try {
					System.out.println(html.htmlDataExtractor(url));
				} catch(URISyntaxException e) {
					System.out.println("Invalid URL (" + e.getReason() + ").");
				} catch(IOException e) {
					System.out.println("Failed to connect to the URL host (" + html.getHost() + ").");
				} catch(IllegalArgumentException e) {
					System.out.println("Couldn't reach the URL (" + e.getMessage() + ").");
				}
				break;
			case 2:
				// CODE
			case 3:
				// CODE
			case 4:
				try {
					html.htmlDataDownload(html.htmlDataExtractor(url));
					System.out.println("File Downloaded");
				} catch(FileNotFoundException e) {
					System.out.println("File (" + html.getFilePath() + ") could not be found or can't be written on.");
				} catch(URISyntaxException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				// CODE
			case 6:
				// CODE
			case 7:
				// CODE
			default:
				System.out.println("Please type a number between 1 - 9.");
		}
	}
}