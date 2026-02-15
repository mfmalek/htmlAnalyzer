package model;

import java.util.ArrayDeque;
import java.util.Deque;

public class HtmlMiscellaneous {
	private Deque<String> tagsArray = new ArrayDeque<>();
	private String[] wordsArray;
	
	public int bodyTagCounter(String data) {
		int index = 0;
		int tagCounter = 0;
		
		while(index < data.length()) {
			int openingTagIndex = data.indexOf("<", index);
			if(openingTagIndex == -1) {
				break;
			}
			
			int closingTagIndex = data.indexOf(">", openingTagIndex);
			if(closingTagIndex == -1) {
				break;
			}
			
			int tagStartIndex = openingTagIndex + 1;
			int tagEndIndex = tagStartIndex;
			
			while(tagEndIndex < closingTagIndex && !Character.isWhitespace(data.charAt(tagEndIndex))) {
				tagEndIndex++;
			}
			
			String tag = data.substring(tagStartIndex, tagEndIndex).toLowerCase();
			
			if(tag.equals("body")) {
				index = closingTagIndex;
				continue;
			}
			
			if(tag.equals("script")) {
			    tagsArray.add(tag);
			    
			    if(tagsArray.size() > tagCounter) {
					tagCounter = tagsArray.size();
				}
			    
			    int scriptClose = data.toLowerCase().indexOf("</script>", closingTagIndex);
				if(scriptClose == -1) {
			    	break;
			    }
			    index = scriptClose + 9;
			    continue;
			}
			
			if(tag.startsWith("/")) {
				if(tagsArray.isEmpty()) {
					index = closingTagIndex + 1;
					continue;
				}
				
				String tagEnd = tag.substring(1);
				if(!tagsArray.peek().equals(tagEnd)) {
					index = closingTagIndex + 1;
					continue;
				}
			} else if(tag.startsWith("!--")) {
				int commentStartIndex = data.indexOf("<!--", closingTagIndex);
				int commentCloseIndex = data.indexOf("-->", commentStartIndex);	
				
				if(commentCloseIndex == -1) {
					break;
				}
				index = commentCloseIndex + 3;
			} else {
				tagsArray.add(tag);
				
				if(tagsArray.size() > tagCounter) {
					tagCounter = tagsArray.size();
				}
			}
			index = closingTagIndex + 1;
		}
		return tagCounter;
	}
	
	public int bodyWordCounter(String data) {
		data = data.replaceAll("(?is)<script.*?>.*?</script>", "");
		data = data.replaceAll("(?is)<style.*?>.*?</style>", "");
		String text = data.replaceAll("<[^>]+>", " ");
		text = text.trim().replaceAll("\\s+", " ");
		wordsArray = text.split(" ");

		return wordsArray.length;
	}
	
	public double htmlSizeCalculator(String data) {
		byte[] bytes = data.getBytes();
		double sizeConverterToKB = bytes.length / 1024;
		
		return sizeConverterToKB;
	}
	
	public Deque<String> getTags() {
		return tagsArray;
	}
	
	public String[] getWords() {
		return wordsArray;
	}
}