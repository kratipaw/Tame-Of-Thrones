package main.java.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.java.universe.Kingdom;

public class Utility {

	
	public static Map<Kingdom, Set<Kingdom>> sortMap(Map<Kingdom, Set<Kingdom>> map) {
		
		List<Map.Entry<Kingdom, Set<Kingdom>>> list = new LinkedList<Map.Entry<Kingdom, Set<Kingdom>> >(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<Kingdom, Set<Kingdom>>>() {

			@Override
			public int compare(Entry<Kingdom, Set<Kingdom>> o1, Entry<Kingdom, Set<Kingdom>> o2) {
				return o1.getValue().size() - o2.getValue().size();
			}
		});
		
		Map<Kingdom, Set<Kingdom>> sortedMap = new LinkedHashMap<Kingdom, Set<Kingdom>>();
		
		for(Map.Entry<Kingdom, Set<Kingdom>> entry : list) {
			sortedMap.put(entry.getKey(),entry.getValue());
		}
		
		return sortedMap;
	}
}
