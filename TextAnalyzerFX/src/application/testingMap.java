/**
 * this test is to check if map is populated with data correctly
 */

package application;
 

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class testingMap {

	@Test
	public void test() {
		Main test = new Main();
		Map<String, Integer> testMap = new TreeMap<String, Integer>();
				
				testMap.put("One", 1);
				testMap.put("Two", 2);
				testMap.put("Tree", 3);
				
				
	  
		        System.out.println(testMap); 
		  
		        System.out.println(testMap.isEmpty()); 
		     	
		        assertFalse("Map is not empty", testMap.isEmpty());
					
			}
	
	}
