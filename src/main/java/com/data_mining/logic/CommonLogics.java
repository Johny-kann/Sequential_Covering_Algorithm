package com.data_mining.logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;









import com.data_mining.constants.Notations;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.Records;
import com.data_mining.model.errors.ErrorModelList;
import com.data_mining.model.errors.PassingAttribute;

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
	 * finds the best attribute and returns the passing attribute obj
	 * @param errorModel
	 * @return best attribute obj
	 */
	public PassingAttribute bestAttributeFromErrorModel(ErrorModelList input)
	{
		PassingAttribute index = null;
		Double error=1.0;

		for(int i=0;i<input.getErrors().size();i++)
		{
			if(input.getErrors().get(i).getAttrbTye().equals(Notations.FULL_SPLIT))
			{

				
				if(error>input.getErrorModel(i).getErrors().get(0))
				{
			
				error=input.getErrorModel(i).getErrors().get(0);
				index = new PassingAttribute(
						input.getErrorModel(i).getAttrbName(),
						input.getErrorModel(i).getAttrIndex(),
						Notations.FULL_SPLIT, 
						Notations.FULL_SPLIT, 
						input.getErrorModel(i).getErrors().get(0),
						input.getErrorModel(i).getgainRatio().get(0)
						);
			
				}
				else if(error.equals(input.getErrorModel(i).getErrors().get(0)))
				{
					
					if(index.getGainRatio()<input.getErrorModel(i).getgainRatio().get(0))
					{
						index = new PassingAttribute(
								input.getErrorModel(i).getAttrbName(),
								input.getErrorModel(i).getAttrIndex(),
								Notations.FULL_SPLIT, 
								Notations.FULL_SPLIT, 
								input.getErrorModel(i).getErrors().get(0),
								input.getErrorModel(i).getgainRatio().get(0)
								);
					}
				}
			}
			else
			{
				for(int j=0;j<input.
						getErrorModel(i).
						getAttrbValues().
						size();j++)
				{

					
					
					if(error>input.getErrorModel(i).getErrors().get(j))
					{
				
					error=input.getErrorModel(i).getErrors().get(j);
					index = new PassingAttribute(
							input.getErrorModel(i).getAttrbName(),
							input.getErrorModel(i).getAttrIndex(),
							Notations.SEMI_SPLIT, 
							input.getErrorModel(i).getConds().get(j), 
							input.getErrorModel(i).getErrors().get(j),
							input.getErrorModel(i).getgainRatio().get(j),
							Double.parseDouble(
							input.getErrorModel(i).getAttrbValues().get(j))
							
							);
//					
				
					
					}
					
					else if(error.equals(input.getErrorModel(i).getErrors().get(j)))
					{
						
						if(index.getGainRatio()<input.getErrorModel(i).getgainRatio().get(j))
						{
						
							index = new PassingAttribute(
									input.getErrorModel(i).getAttrbName(),
									input.getErrorModel(i).getAttrIndex(),
									Notations.SEMI_SPLIT, 
									input.getErrorModel(i).getConds().get(j), 
									input.getErrorModel(i).getErrors().get(j),
									input.getErrorModel(i).getgainRatio().get(j),
									Double.parseDouble(
									input.getErrorModel(i).getAttrbValues().get(j))
									
									); 
						}
					}
				}
			}
		}
		return index;
	}
	
	/**
	 * @param table
	 * @return if it belongs to a leaf node
	 */
	public boolean isleafNode(DataTable input)
	{
		ChoosingAttributes cr = new ChoosingAttributes(input);
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
}
