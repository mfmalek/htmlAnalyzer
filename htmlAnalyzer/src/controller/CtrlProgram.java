package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

import model.HtmlDownloader;
import model.HtmlExtractor;
import model.HtmlMiscellaneous;

public class CtrlProgram {
	public static void main(String[] args) {
		int option = -1;
		Scanner input = new Scanner(System.in);
		HtmlExtractor extractor = new HtmlExtractor();
		HtmlDownloader downloader = new HtmlDownloader();
		HtmlMiscellaneous misc = new HtmlMiscellaneous();
		
		System.out.print("Insert a URL: ");
		String url = input.nextLine();
		
		while(option != 0) {
			System.out.println("\n===== SELECT WHAT YOU WANT TO DO =====\n");
			System.out.println("==> Data Extraction");
			System.out.println("\t1. HTML Data Extractor");
			System.out.println("\t2. HTML Head Data Extractor");
			System.out.println("\t3. HTML Body Data Extractor");
			System.out.println("==> Data Download");
			System.out.println("\t4. HTML Data Download");
			System.out.println("\t5. HTML Head Data Download");
			System.out.println("\t6. HTML Body Data Download");
			System.out.println("==> Miscellaneous");
			System.out.println("\t7. Body Tag Counter");
			System.out.println("\t8. Body Word Counter");
			System.out.println("\t9. HTML Size Calculator");
			System.out.print("\nChoose which operation you would like to proceed with [type 0 to cancel]: ");
			option = input.nextInt();
			System.out.println();
			
			
			switch(option) {
				case 0:
					System.out.println("Operation closed.");
					break;
				case 1:
					try {
						System.out.println(extractor.htmlDataExtractor(url));
					} catch(URISyntaxException e) {
						System.out.println("Invalid URL (" + e.getReason() + ").");
					} catch(IOException e) {
						System.out.println("Failed to connect to the URL host (" + extractor.getHost() + ").");
					} catch(IllegalArgumentException e) {
						System.out.println("Couldn't reach the URL (" + e.getMessage() + ").");
					}
					break;
				case 2:
					try {
						System.out.println(extractor.htmlHeadExtractor(extractor.htmlDataExtractor(url)));
					} catch(URISyntaxException e) {
						System.out.println("Invalid URL (" + e.getReason() + ").");
					} catch(IOException e) {
						System.out.println("Failed to connect to the URL host (" + extractor.getHost() + ").");
					} catch(IllegalArgumentException e) {
						System.out.println("Couldn't reach the URL (" + e.getMessage() + ").");
					} catch(StringIndexOutOfBoundsException e) {
						System.out.println("Couldn't find a \"head\" tag in your html.");
					}
					break;
				case 3:
					try {
						System.out.println(extractor.htmlBodyExtractor(extractor.htmlDataExtractor(url)));
					} catch(URISyntaxException e) {
						System.out.println("Invalid URL (" + e.getReason() + ").");
					} catch(IOException e) {
						System.out.println("Failed to connect to the URL host (" + extractor.getHost() + ").");
					} catch(IllegalArgumentException e) {
						System.out.println("Couldn't reach the URL (" + e.getMessage() + ").");
					} catch(StringIndexOutOfBoundsException e) {
						System.out.println("Couldn't find a \"body\" tag in your html.");
					}
					break;
				case 4:
					try {
						downloader.htmlDataDownload(extractor.htmlDataExtractor(url));
						System.out.println("File Downloaded to (" + downloader.getFilePath() + ").");
					} catch(FileNotFoundException e) {
						System.out.println("File (" + downloader.getFilePath() + ") could not be found or can't be written on.");
					} catch(URISyntaxException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					try {
						downloader.htmlHeadDownload(extractor.htmlHeadExtractor(extractor.htmlDataExtractor(url)));
						System.out.println("File Downloaded to (" + downloader.getFilePath() + ").");
					} catch(FileNotFoundException e) {
						System.out.println("File (" + downloader.getFilePath() + ") could not be found or can't be written on.");
					} catch(URISyntaxException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					try {
						downloader.htmlBodyDownload(extractor.htmlBodyExtractor(extractor.htmlDataExtractor(url)));
						System.out.println("File Downloaded to (" + downloader.getFilePath() + ").");
					} catch(FileNotFoundException e) {
						System.out.println("File (" + downloader.getFilePath() + ") could not be found or can't be written on.");
					} catch(URISyntaxException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					try {
						System.out.println("Amount of tags: " + misc.bodyTagCounter(extractor.htmlBodyExtractor(extractor.htmlDataExtractor(url))));
						System.out.println("Tags in the body: " + misc.getTags());
					} catch(URISyntaxException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 8:
					try {
						System.out.println("Amount of words: " + misc.bodyWordCounter(extractor.htmlBodyExtractor(extractor.htmlDataExtractor(url))));
						System.out.println("Words in the body: " + Arrays.toString(misc.getWords()));
					} catch (URISyntaxException | IOException e) {
						e.printStackTrace();
					}
						break;
				case 9:
					try {
						System.out.printf("HTML Size: ≈%.2f KB\n", misc.htmlSizeCalculator(extractor.htmlDataExtractor(url)));
					} catch (URISyntaxException | IOException e) {
						e.printStackTrace();
					}
						break;
				default:
					System.out.println("Please type a number between 0 - 7.");
			}
		}
	}
}