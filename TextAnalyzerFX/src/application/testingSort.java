/**
 * this test is to check if sort method works as expected
 */

package application;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import java.util.Map.Entry;


public class testingSort {
	
	static Main test = new Main();
	

	@Test
	public void testSort() {
		
		
		
		String text = ("A A A B B C"); 
		String stringToUse = text.replaceAll("\\s+", " ");
		String[] stringArray = stringToUse.split(" ");
		List<String> textToProcess = new ArrayList<String>();

		/**
		 * creating new map to hold data after iteration
		 */
		Map<String, Integer> map = new HashMap<String, Integer>();

		/**
		 *  counting words in array 
		 */
		for (String word : stringArray) {
			Integer num = map.get(word);
			num = (num == null) ? 1 : ++num;
			map.put(word, num);

		}

		Map<String, Integer> treeMap = new TreeMap<String, Integer>() {

			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};
		treeMap.putAll(map);
		
			
		Map<String, Integer> expected = new TreeMap<String, Integer>();
		
		expected.put("C", 1);
		expected.put("B", 2);
		expected.put("A", 3);
		

		
		
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(expected.entrySet());

		/**
		 * Sort list with Collections.sort()
		 */
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		
		/**
		 *  Loop the sorted list and put it into a new order Map LinkedHashMap
		 */
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		
		}

			
		
		System.out.print(sortedMap.toString());
		
		Map<String, Integer> output = Main.sortByValue(treeMap);
		assertEquals(output, sortedMap);
		
	}
	
	
	}



