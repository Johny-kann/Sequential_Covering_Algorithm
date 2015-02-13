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
import com.data_mining.view.console.Outputs;

public class SmallClass {
		
	public static void main(String[] args) 
	{
	MainController mc = new MainController();
	mc.loadAttributesAndRecords();
	DataTable table1 = mc.getTrainAttributes();
	
	DataTable table2 = null;
	try {
		table2 = table1.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 table2.getRecords().remove(1
			 );
	 table2.getRecords().remove(2
			 );
	
	 new Outputs().outPutTable(table1);
	 new Outputs().outPutTable(table2);
	 
	 new CommonLogics().removeRecords(table1, table2);
	 
	 System.out.println(table1.sizeOfRecords());
	 System.out.println(table2.sizeOfRecords());
	 
	 new Outputs().outPutTable(table1);
	 new Outputs().outPutTable(table2);
	 
	}
}
