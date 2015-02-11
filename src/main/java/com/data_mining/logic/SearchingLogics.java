package com.data_mining.logic;

import java.util.List;

import com.data_mining.constants.NodeConstants;
import com.data_mining.constants.Notations;
import com.data_mining.model.attributes_records.AttributesSpecifications;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.Records;

/**
 * @author Janakiraman
 * Class used for searching and refining attributes
 *
 */
public class SearchingLogics {

	/**
	 * @param temp
	 * @param name
	 * @return attribute obj
	 */
	public AttributesSpecifications searchingAttribute(List<AttributesSpecifications> temp,String name)
	{
		AttributesSpecifications result = null;
		
		for(AttributesSpecifications attribute:temp)
		{
			if(attribute.getName().equals(name))
			{
				result = attribute;
				break;
			}
		}
		
		return result;
	}
	
	public Integer getAttributeIndex(List<AttributesSpecifications> temp,String name)
	{
		Integer result = 0;
		
		
		for(AttributesSpecifications attribute:temp)
		{
			if(attribute.getName().equals(name))
			{
				break;
			}
			else
			{
				result++;
			}
		}
		
		return result;
	}
	
	
	public DataTable refiningSetDiscrete(DataTable table,int attributeIndex,String attrValue)
	{
		DataTable newSet = new DataTable();
		addAttributeContentsDiscrete(newSet, table, attributeIndex);
		recordRefinment(newSet, table, attributeIndex, attrValue);
		return newSet;
		
	}
	
	public DataTable refiningSetContinuousLeft(DataTable table,int attributeIndex,Double attrValue)
	{
		DataTable newSet = new DataTable();
		addAttributeContentsContinuous(newSet, table, attributeIndex);
		recordRefinmentCntnsLeft(newSet, table, attributeIndex, attrValue);
		return newSet;
		
	}

	public DataTable refiningSetContinuousRight(DataTable table,int attributeIndex,Double attrValue)
	{
		DataTable newSet = new DataTable();
		addAttributeContentsContinuous(newSet, table, attributeIndex);
		recordRefinmentCntnsRight(newSet, table, attributeIndex, attrValue);
		return newSet;
		
	}
	
	private void recordRefinment(DataTable newSet,DataTable oldSet,int attrbIndex,String attrbValue)
	{
		CommonLogics cl = new CommonLogics();
		
		for(int i=0;i<oldSet.sizeOfRecords();i++)
		{
			if(oldSet.searchByRowAndColumn(i, attrbIndex).equals(attrbValue))
			{
				newSet.addRecord(
								cl.removeElementFromRecordDiscrete(
								oldSet.getRecordAtIndex(i)
										, attrbIndex)
								);
			}
		}
	}
	
	private void recordRefinmentCntnsLeft(DataTable newSet,DataTable oldSet,int attrbIndex,Double attrbValue)
	{
		CommonLogics cl = new CommonLogics();
		
		for(int i=0;i<oldSet.sizeOfRecords();i++)
		{
			if(Double.parseDouble(oldSet.searchByRowAndColumn(i, attrbIndex))
					<(attrbValue))
			{
//				newSet.addRecord(
//								cl.removeElementFromRecordDiscrete(
//								oldSet.getRecordAtIndex(i)
//										, attrbIndex)
//								);
				
				newSet.addRecord(
						new Records(oldSet.getRecordAtIndex(i))
						);
			}
		}
	}
	
	private void recordRefinmentCntnsRight(DataTable newSet,DataTable oldSet,int attrbIndex,Double attrbValue)
	{
		CommonLogics cl = new CommonLogics();
		
		for(int i=0;i<oldSet.sizeOfRecords();i++)
		{
			if(Double.parseDouble(oldSet.searchByRowAndColumn(i, attrbIndex))
					>=(attrbValue))
			{
//				newSet.addRecord(
//								cl.removeElementFromRecordDiscrete(
//								oldSet.getRecordAtIndex(i)
//										, attrbIndex)
//								);
				newSet.addRecord(new Records(oldSet.getRecordAtIndex(i)));
			}
		}
	}
	
	public void addAttributeContentsDiscrete(DataTable newSet,DataTable oldSet, int attrbIndex)
	{
		for(int i=0;i<oldSet.totColumns();i++)
		{
			if(i!=attrbIndex)
			{
				newSet.addAttribute(oldSet.getAttributes().get(i).getName(),
						oldSet.getAttributes().get(i).getType(),
						oldSet.getAttributes().get(i).getValues()
						);
			}
			
			
		}
		
	}
	public void addAttributeContentsContinuous(DataTable newSet,DataTable oldSet, int attrbIndex)
		{
			for(int i=0;i<oldSet.totColumns();i++)
			{
						newSet.addAttribute(oldSet.getAttributes().get(i).getName(),
							oldSet.getAttributes().get(i).getType(),
							oldSet.getAttributes().get(i).getValues()
							);
				
				
				
			}
		
	}
	
	public DataTable extractDataBasedOnCondition(DataTable input,String nodeCondition)
	{
		DataTable temp = new DataTable();
		CommonLogics cl = new CommonLogics();
		if(nodeCondition.equals(NodeConstants.ROOT_NODE))
		{
			temp = input;
			
		}
		else if(cl.getDecisionForChildRecordSender(nodeCondition)==Notations.DISCRETE_EQUAL)
		{
			SearchingLogics sl = new SearchingLogics();
			temp = sl.refiningSetDiscrete(input, input.getAttributeIndex(
					cl.getNodeNameFromCondition(nodeCondition)),
					cl.getNodeValueFromCondition(nodeCondition));
		}
		else if(cl.getDecisionForChildRecordSender(nodeCondition)==Notations.CNTS_LEFT)
		{
			SearchingLogics sl = new SearchingLogics();
			temp = sl.refiningSetContinuousLeft(input, input.getAttributeIndex(
					cl.getNodeNameFromCondition(nodeCondition)),
					Double.parseDouble(
					cl.getNodeValueFromCondition(nodeCondition)
					));
		}
		else if(cl.getDecisionForChildRecordSender(nodeCondition)==Notations.CNTS_RIGHT)
		{
			SearchingLogics sl = new SearchingLogics();
			temp = sl.refiningSetContinuousRight(input, input.getAttributeIndex(
					cl.getNodeNameFromCondition(nodeCondition)),
					Double.parseDouble(
					cl.getNodeValueFromCondition(nodeCondition)
					));
		}else
		{
			temp = null;
		}
		
		return temp;
	}
	
	
}
