package oblig2;

import java.util.Comparator;
import java.util.Map;

public class Sorter implements Comparator<String> {
	
	Map<String,Integer> base;
	
	public Sorter(Map<String,Integer> base) {
		this.base = base;
	}

	@Override
	public int compare(String a, String b) {
		if(base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}
