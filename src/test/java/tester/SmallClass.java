package tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SmallClass {
		
	public static void main(String[] args) 
	{
	List<Integer> test = new ArrayList<Integer>();
	test.add(1);
	test.add(2);
	test.add(3);
	test.add(4);
	test.add(5);
	test.add(6);
	test.add(7);
	
	test.subList(0, 4);
	test.removeAll(test.subList(4, 5));
	System.out.println(test);
	}
}
