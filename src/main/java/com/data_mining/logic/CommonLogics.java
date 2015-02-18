package com.data_mining.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.data_mining.constants.Notations;
import com.data_mining.logs.TrainingLog;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.Records;
import com.data_mining.model.errors.ErrorModelList;
import com.data_mining.model.errors.PassingAttribute;
import com.data_mining.model.rules.RuleCondition;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.model.rules.Rules;

/**
 * @author Janakiraman
 *Basic logic functions like sort, remove etx
 */
public class CommonLogics {

	
	
	/**
	 * 
	 * Removes a record from the list and gives a new list unaffected by the old list
	 * @param original
	 * @return
	 */
	public List<String> removeElement(List<String> original,int index)
	{
		List<String> newList = new ArrayList<String>();
		
		for(int i=0;i<original.size();i++)
		{
			if(i!=index)
			{
				newList.add(original.get(i));
			}
		}
		
		return newList;
	}
	
	public static void assignInitValues(String args[])
	{
		if(args[0].equalsIgnoreCase("Prune-ON"))
		{
			System.out.println(args[0]);
			Notations.PRUNING_ON = true;
		}
		else
		{
			Notations.PRUNING_ON = false;
		}
		
		if(args[1].equalsIgnoreCase("Test-ON"))
		{
			Notations.TEST_ON = true;
		}
		else
		{
			Notations.TEST_ON = false;
		}
		
		new TrainingLog();
		
	}
	
	public String conditionGeneratorDiscrete(String name,String condition)
	{
		return name+Notations.DISCRETE_EQUALITY+condition;
	}
	
	public Map<String,Integer> sortMapValues(Map<String,Integer> map)
	{

		Set<String> keys = map.keySet();

		List<String> index = new ArrayList<String>(keys
				);
		List<Integer> objs = getListFromMap(map);
		
		for(int i=0;i<objs.size();i++)
		{
			for(int j=i;j<objs.size();j++)
			{
				if(objs.get(i)>objs.get(j))
				{
					Collections.swap(objs, i, j);
					Collections.swap(index, i, j);
				}
			}
		}
		
	Map<String,Integer> mapp = new LinkedHashMap<String, Integer>();
	
	for(int i=0;i<objs.size();i++)
	{
		mapp.put(index.get(i), objs.get(i));
	}
	
	return mapp;
	}
	
	public List<Integer> getListFromMap(Map<String, Integer> map)
	{
		Set<String> keys = map.keySet();
		
		List<Integer> str = new ArrayList<Integer>();
		
		for(String key:keys)
		{
			str.add(map.get(key));
		}
		
		return str;
	}
	
	/**
	 * 
	 * Removes a record from the list and gives a new list unaffected by the old list
	 * @param original
	 * @return refined record
	 */
	public Records removeElementFromRecordDiscrete(Records original,int index)
	{
		Records newRecords = new Records(
				removeElement(original.getElements(), index),
				original.getClassAttribute());
		
				
		return newRecords;
	}
	
	

	
	/**
	 * @param table
	 * @param class value
	 * @return count of a particular class value
	 */
	public Integer getCountOfClassValue(DataTable table,String value)
	{
		Integer sum=0;
		for(int i=0;i<table.sizeOfRecords();i++)
		{
			if(table.getRecordAtIndex(i).getClassAttribute().equals(value))
				sum++;
		}
		
		return sum;
	}
	
	/**
	 * @param table
	 * @param class value
	 * @return count of a particular class value
	 */
	public Integer getCountOfOtherClassValues(DataTable table,String value)
	{
		Integer sum=0;
		for(int i=0;i<table.sizeOfRecords();i++)
		{
			if(!(table.getRecordAtIndex(i).getClassAttribute().equals(value)))
				sum++;
		}
		
		return sum;
	}
	
	
	public Integer getValueofMapAtIndex(Map<String,Integer> map,int index)
	{
		Set<String> keys = map.keySet();
		return map.get(keys.toArray()[index]);
		
	}
	
	/**
	 * Total classes and their counts
	 * @param table
	 * @return map of classes and counts
	 */
	public Map<String,Integer> classAndCounts(DataTable table)
	{
		Map<String,Integer> categories = new LinkedHashMap<String, Integer>();
		
		for(int i=0;i<table.getClassValues().size();i++)
		{
			categories.put(
					table.getClassValues().get(i),
					getCountOfClassValue(
							table, table.getClassValues().get(i)
							));
		}
		
		return categories;
		
	}
	
	public void removeRecords(DataTable input,DataTable remover)
	{
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		for(int i=0;i<remover.sizeOfRecords();i++)
		{
			for(int j=0;j<input.sizeOfRecords();j++)
			{
				if(equalRecords(input.getRecordAtIndex(j),
						remover.getRecordAtIndex(i)))
				{
				array.add(j);
				}
			}
		}
		
		Collections.sort(array);
		Collections.reverse(array);
		
		
		for(int i:array)
		{
			input.getRecords().remove(i);
		}
		
		
	}
	
	public boolean equalRecords(Records rec1,Records rec2)
	{
		Boolean good=true;
		
		for(int i=0;i<rec1.getElements().size();i++)
		{
			if(rec1.getElements().get(i)!=rec2.getElements().get(i))
			{
				good=false;
				break;
			}
		}
		
		if(rec1.getClassAttribute()!=rec2.getClassAttribute())
		{
			good=false;
			
		}
		
		return good;
	}
	
	
	
	public void removeRecordByAttrbValue(DataTable table,int index,String value)
	{
		for(int i=0;i<table.sizeOfRecords();i++)
		{
			if(table.getRecordAtIndex(i).getElementValueAtIndex(index)==value)
			{
				table.getRecords().remove(i);
			}
		}
	}
	
	/**
	 * sorts the table according to the attribute index
	 * @param table
	 * @param attribute
	 */
	public void sort(DataTable temp,int index)
	{
		Records tem;
		
		List<Records> recs = temp.getRecords();
	//	InputGetter.consoleOutPut(recs.size());
		for(int i=0;i<recs.size();i++)
		{
			for(int j=i+1;j<recs.size();j++)
			{
			//	InputGetter.consoleOutPut(Double.parseDouble(recs.get(j).getElementValueAtIndex(index)));
				if(Double.parseDouble(recs.get(j).getElementValueAtIndex(index))
						<Double.parseDouble(recs.get(i).getElementValueAtIndex(index))
						)
				{
					tem = recs.get(j);
					recs.set(j, recs.get(i));
					recs.set(i, tem);
					
				}
			}
		}
	}
	
	
	public void sortRulesBasedonGeneralizationError(RuleSet ruleSet)
	{
	 
		List<Rules> temp = ruleSet.getRulesList();
		
		for(int i=0;i<temp.size();i++)
		{
			for(int j=i;j<temp.size();j++)
			{
				if(temp.get(i).getgError()>temp.get(j).getgError())
				{
					Collections.swap(temp, i, j);
					
				}
			}
		}
		
		
	}
	
	
	
	/**
	 * @param table
	 * @param attributeIndex
	 * @return splitted values for continuous attribute
	 */
	public List<Double> fillSplitList(DataTable table,int attributeIndex)
	{
		List<Records> temp = table.getRecords();
		List<Double> splits = new ArrayList<Double>();
		
		for(int i=0;i<temp.size();i++)
		{
			if(i==0)
			{
				splits.add(Double.parseDouble(
						temp.get(i).getElementValueAtIndex(attributeIndex))
						*0.8);
				
			}
			else
			{
				splits.add(
						 ((
								 Double.parseDouble(
								 temp.get(i).getElementValueAtIndex(attributeIndex))+
								 Double.parseDouble(
								 temp.get(i-1).getElementValueAtIndex(attributeIndex))
								 )/2.0)
						);
			}
		}
		splits.add(Double.parseDouble((temp.get
				(temp.size()-1).
				getElementValueAtIndex(attributeIndex)))
				*1.2
				);
		
		return splits;
	}
	 
	/**
	 * @param table
	 * @return list of positions where split can occur
	 */
	public List<Integer> splitPostition(DataTable table)
	{
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=1;i<table.sizeOfRecords();i++)
		{
			if(!(
			table.getRecordAtIndex(i).getClassAttribute().equals
			(table.getRecordAtIndex(i-1).getClassAttribute()
					)))
			{
				list.add(i);
			}
		}
		
		return list;
	}
	
	/**
	 * finds the best rule from the list of rule condition and returns the best rule condition
	 * @param List of rule conditions
	 * @return best rule condition
	 */
	public RuleCondition bestAttributeFromErrorModel(List<RuleCondition> rules) throws IndexOutOfBoundsException
	{
		RuleCondition index = null;
		
		if(Notations.STRICTLY_LAPLACE==false)
		{
			TrainingLog.accuracyLogs.info("Inside strictly_laplace false block");
		for(RuleCondition rule:rules)
		{
		
			if(rule.getNoOfCorrectClass()>0)
			{
				index = rule;
				break;
			}
		}
		try
		{
		TrainingLog.accuracyLogs.info("Rule chosen in strictly_laplace false after for loop "+index.getCondition());
		}catch(NullPointerException ne)
		{
			TrainingLog.accuracyLogs.info("Null pointer exception index not assigned any value");
		}
		}
		
		else
		{
			TrainingLog.accuracyLogs.info("Inside strictly_laplace true block");
	//		Double laplace = rules.get(0).getError();
			index = rules.get(0);
			TrainingLog.accuracyLogs.info("Rule chosen in else"+index.getCondition());
		}
	
		for(RuleCondition rule:rules)
		{
		
		
			if(index.getError()<rule.getError()
					)
			{
				index = rule;
			}
		}
		
		
		TrainingLog.accuracyLogs.info("Rule chosen "+index.getCondition());
		
		return index;
		
		}
	
	/**
	 * @param table
	 * @return if it belongs to a leaf node
	 */
	public boolean isleafNode(DataTable input)
	{
		ChoosingAttributes cr = new ChoosingAttributes();
		Double error = cr.calculateErrorForTable(input);
//		System.out.println("Error for leaf node " + error);
		
		if(error == 0.0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @param map of classes and counts
	 * @return best class
	 */
	public String bestClassFromMap(Map<String,Integer> input)
	{
		String index = null;
	//	Double error=1.0;
		Integer count = 0;
		
		Set<String> keys = input.keySet();
	
		for(String key:keys)
		{
			if(count<input.get(key))
				{
			
				count=input.get(key);
				index = key;
				
				}
		}
					
		return index;
		
	}
	
	/**
	 * @param table
	 * @param error
	 * @return finds the maximum occurred class
	 */
	public String findMaxClass(DataTable inputRecords,Double error)
	{
		String classSelected;
		CommonLogics cl = new CommonLogics();
		Map<String,Integer> map = cl.classAndCounts(inputRecords);
		
		classSelected = cl.bestClassFromMap(map);
		
//		if(error == 0.0)
//		{
//		System.out.println("Pure Class selected "+classSelected);
//		}
//		else
//		{
//		System.out.println("Impure Class selected "+classSelected);
//		}
		
		return classSelected;
	}
	
	/**
	 * @param map of attributes and their split errors
	 * @return best attribute
	 */
	public String bestAttributeFromMap(Map<String,Double> input)
	{
		String index = null;
		Double error=1.0;
		
		Set<String> keys = input.keySet();
		
	
		
		for(String key:keys)
		{
			if(error>input.get(key))
				{
			
				error=input.get(key);
				index = key;
				
				}
		}
		
			
		return index;
		
	}

	/**
	 * @param Node condition
	 * @return Node value
	 */
	public String getNodeValueFromCondition(String str)
	{
		String temp;
		if(str.contains(" "))
		{
			int index = str.indexOf(" ");
			temp = str.substring(index+1, str.length());
		}
		else if(str.contains("<"))
		{
			int index = str.indexOf("<");
			temp = str.substring(index+1, str.length());
		}
		else if(str.contains(">="))
		{
			int index = str.indexOf(">=");
			temp = str.substring(index+2, str.length());
		}
		else
		{
			temp = Notations.ERROR_IN_COND;
		}
		return temp;
	}
	
	/**
	 * @param Node condition
	 * @return node name
	 */
	public String getNodeNameFromCondition(String str)
	{
		String temp;
		if(str.contains(" "))
		{
			int index = str.indexOf(" ");
			temp = str.substring(0,index);
		}
		else if(str.contains("<"))
		{
			int index = str.indexOf("<");
			temp = str.substring(0,index);
		}
		else if(str.contains(">="))
		{
			int index = str.indexOf(">=");
			temp = str.substring(0,index);
		}
		else
		{
			temp = Notations.ERROR_IN_COND;
		}
		return temp;
	}
	
	
	
	
	
	
	/**
	 * based on this value the records are assigned to the children
	 * @param node condition of parent
	 * @return child split information
	 */
	public String getDecisionForChildRecordSender(String str)
	{
		String temp;
		if(str.contains(" "))
		{
			int index = str.indexOf(" ");
			temp = Notations.DISCRETE_EQUAL;
		}
		else if(str.contains("<"))
		{
			int index = str.indexOf("<");
			temp = Notations.CNTS_LEFT;
		}
		else if(str.contains(">="))
		{
			int index = str.indexOf(">=");
			temp = Notations.CNTS_RIGHT;
		}
		else
		{
			temp = Notations.ERROR_IN_COND;
		}
		return temp;
	}

	
	/**
	 * If true is given condition chosen in less than and if false is given condition chosen is greater than
	 * @param attributeName
	 * @param value
	 * @param true or false
	 * @return rule condition
	 */
	public String conditionGeneratorCnts(String attributeName, Double str,
			boolean b) {
		// TODO Auto-generated method stub
		if(b==false)
		{
			return attributeName+Notations.CNTNS_GREATER_THAN+str;
		}
		else
		{
			return attributeName+Notations.CNTNS_LESS_THAN+str;
		}
		
	}
}
