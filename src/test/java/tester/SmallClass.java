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

import com.data_mining.constants.Notations;
import com.data_mining.controller.MainController;
import com.data_mining.logic.CommonLogics;
import com.data_mining.model.attributes_records.DataTable;

public class SmallClass {
		
	public static void main(String[] args) 
	{
	 Map<String,Integer> map = new LinkedHashMap<String, Integer>();
	 map.put("First", 1);
	 map.put("Second", 2);
	 map.put("Third", 3);
	 
	 System.out.println(new CommonLogics().getValueofMapAtIndex(map, 2));
	}
}
