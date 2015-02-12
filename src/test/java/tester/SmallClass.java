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
import com.data_mining.model.attributes_records.DataTable;

public class SmallClass {
		
	public static void main(String[] args) 
	{
	 DataTable input = new DataTable();
	 
	 Test tt = new Test();
	 tt.name = "Johny";
	 tt.age = "5";
	 tt.tt.gir="First";
	 Test t2=null;
	try {
		t2 = tt.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 
	 t2.name = "Kevin";
	 t2.tt.gir="Second";
	 
	 System.out.println(t2.name);
	 System.out.println(tt.name);
	 System.out.println(t2.tt.gir);
	 System.out.println(tt.tt.gir);
	}
	
}

class Test implements Cloneable
{
	public String name="Hello";
	public String age = "5";
	public gir tt = new gir();
	
	@Override
	public Test clone() throws CloneNotSupportedException
	{
		return (Test) super.clone();
	}
}

class gir implements Cloneable
{
	public String gir;
	
	@Override
	public gir clone() throws CloneNotSupportedException
	{
		return (gir) super.clone();
	}
}