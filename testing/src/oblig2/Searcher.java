package oblig2;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Searcher {
	private String stringToSearch;
	private HashMap<String, Integer> myAtomsMap = new HashMap<String, Integer>();
	
	public String getHighestNumberKey(HashMap<String, Integer> aMap) {
		String keyForHighestCount = "";
		for(String key : aMap.keySet()) {
			if(keyForHighestCount.equals("")) {
				keyForHighestCount = key;
			} else if(aMap.get(key) > aMap.get(keyForHighestCount)) {
				keyForHighestCount = key;
			} else {
				// do nothing
			}
		} return "The highest count is for the key \"" + keyForHighestCount + "\" with a count of " + aMap.get(keyForHighestCount);
	}
	
	public void getUniqueStrings() {
		try {
			BreakIterator iterator = BreakIterator.getWordInstance(Locale.ENGLISH);
			SomeClass someClass = new SomeClass();
			stringToSearch = someClass.readFile("/home/nilsma/pg100.txt");
			iterator.setText(stringToSearch);
			String someAtom;
			int start = iterator.first();
			for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
				someAtom = fixAtom(stringToSearch.substring(start,end));
				if(!atomAlreadyInMap(someAtom)) {
					myAtomsMap.put(someAtom, 1);
				} else {
					myAtomsMap.put(someAtom, myAtomsMap.get(someAtom) + 1);
				}
			}
			myAtomsMap = cleanUpMap(myAtomsMap);
			//printMapDetails();
			sortMethod(myAtomsMap);
			System.out.println(getHighestNumberKey(myAtomsMap));
		} catch(IOException e) {
			System.out.println("Hit catch-block of method getUniqueStrings()");
		}
	}
	
	public HashMap<String, Integer> cleanUpMap(HashMap<String, Integer> myMap) {
		myMap.remove("");
		return myMap;
	}
	
	public void printMapDetails() {
		for(Entry<String, Integer> i : myAtomsMap.entrySet()) {
			System.out.println(i);
		}
		System.out.println("myAtomsMap size: " + myAtomsMap.size());
	}
	
	public String fixAtom(String atom) {
		String fixedAtom = atom.replaceAll("\\W", "").toLowerCase();
		return fixedAtom;
	}
	
	public boolean atomAlreadyInMap(String atomToCheck) {
		if(myAtomsMap.keySet().contains(atomToCheck.trim().toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void sortMethod(Map<String,Integer> mapToSort) {
		Sorter sorter = new Sorter(mapToSort);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(sorter);
		
		sortedMap.putAll(mapToSort);
		for(Entry<String, Integer> entry : sortedMap.descendingMap().entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + " : " + value);
		}
	}
}
