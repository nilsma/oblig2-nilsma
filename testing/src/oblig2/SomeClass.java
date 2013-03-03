package oblig2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SomeClass {
	private LineNumberReader lnr, myLnr;
	private FileReader filereader;
	//private File afile, bfile;
	private File file;
	private String breakIteratorSource;
	private ArrayList<Integer> posList = new ArrayList<Integer>();
	private ArrayList<Integer> lnrList = new ArrayList<Integer>();
	
	public LineNumberReader openStream(String pathToFile) {
		try {
			file = new File(pathToFile);
			filereader = new FileReader(file);
			lnr = new LineNumberReader(filereader);
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		return lnr;
	}
	
	public String readFile(String pathname) throws IOException {
	    file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");
	    
	    try {
	    	while(scanner.hasNextLine()) {
	    		fileContents.append(scanner.nextLine() + lineSeparator);
	    	}
	    } finally {
	    	scanner.close();
	    }
	    return fileContents.toString();
	}
	
	@SuppressWarnings("unused")
	public void sentenceHandler(String pathToFile, String stringToSearchFor) {
		try {
			String someLine;
			int lineCounter = 0;
			int sentenceCounter = 0;
			int searchCounter = 0;
			int lastLine = 0;
			myLnr = openStream(pathToFile);
			BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.ENGLISH);
			breakIteratorSource = readFile(pathToFile);
			iterator.setText(breakIteratorSource);
			int start = iterator.first();
			
			for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
				sentenceCounter++;
				System.out.println(breakIteratorSource.substring(start,end));
				if(breakIteratorSource.substring(start,end).trim().toLowerCase().contains(stringToSearchFor.trim().toLowerCase())) {
					posList.add(sentenceCounter);
					searchCounter++;
				}
			}
			while((someLine = myLnr.readLine()) != null) {
				lineCounter++;
				if(someLine.trim().toLowerCase().contains(stringToSearchFor)) {
					lnrList.add(myLnr.getLineNumber());
				} else {
					lastLine = myLnr.getLineNumber();
				}
			}
			
			System.out.println("posList: " + posList);
			System.out.println("lnrList: " + lnrList);
			System.out.println("Search counter: " + searchCounter);
			System.out.println("Number of sentences: " + sentenceCounter);
			System.out.println("Number of lines: " + lastLine);
			System.out.println("Number of lines: " + lineCounter);
		} catch(IOException e) {
			System.out.println("File not found!");
		}
	}
	
	public void testPrint() {
		System.out.println("Print something");
	}
	

	
}
