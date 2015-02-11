package com.data_mining.model.attributes_records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;

/**
 * Class that contains the attributes and records used for calculations
 * @author Janakiraman
 *
 */
public class DataTable implements Cloneable {

	
//	private Map<String, String> attributeNameAndType;
	private List<AttributesSpecifications> attributes;
	
	private Integer numberOfRecords;
	private Integer numberOfAttributes;
	
	
	private List<Records> records;
	
	public DataTable()
	{
	//	attributeNameAndType = new LinkedHashMap<String, String>();
		attributes = new ArrayList<AttributesSpecifications>();
		numberOfAttributes = 0;
		numberOfRecords = 0;
		records = new ArrayList<Records>();
		
	}
	
	@Override
	public DataTable clone() throws CloneNotSupportedException {
	      DataTable clonedCustomer = (DataTable) super.clone();
	 
	      // Clone the object referenced objects
	      
	      return clonedCustomer;
	   }
	
	/**
	 * Gives number of Records
	 * @return no of records
	 */
	public Integer sizeOfRecords()
	{
		return numberOfRecords;
	}
	
	/**
	 * Gives Number of attributes
	 * @return integer
	 */
	public Integer numberOfAttributes()
	{
		return numberOfAttributes-1;
	}
	
	public Integer totColumns()
	{
		return numberOfAttributes;
	}
	
	/**
	 * Adds the attribute name and attribute type and possible values
	 * @param Attribute name
	 * @param Attribute type
	 */
	public void addAttribute(String name,String type,List<String> values)
	{
		//attributeNameAndType.put(name, type);
		attributes.add(new AttributesSpecifications(name, type, values));
		numberOfAttributes++;
	}
	
	
	/**
	 * Returns Attributes
	 * @return attribute list of name and type
	 */
	public List<AttributesSpecifications> getAttributes() {
		return attributes;
	}
	
	
	public String getAttributeType(String name)
	{
		SearchingLogics sl = new SearchingLogics();
		return sl.searchingAttribute(attributes, name).getType();
	}
	
	public List<String> getAttributeValues(String name)
	{
		SearchingLogics sl = new SearchingLogics();
		return sl.searchingAttribute(attributes, name).getValues();
	}
	
	public Integer getAttributeIndex(String name)
	{
		SearchingLogics sl = new SearchingLogics();
		return sl.getAttributeIndex(attributes, name);
	}
	
	/**
	 * @return
	 */
	public String getClassName() {
		return attributes.get(numberOfAttributes-1).getName();
	}


	/**
	 * 
	 * @return Gives class type
	 */
	public String getClassType() {
		return attributes.get(numberOfAttributes-1).getType();
	}

	public List<String> getClassValues()
	{
		return attributes.get(attributes.size()-1).getValues();
	}
	
	
	/**
	 * @return gives a list of Records
	 */
	public List<Records> getRecords() {
		return records;
	}
	
	/**
	 * @return gives a Record at Index
	 */
	public Records getRecordAtIndex(int i) {
		return records.get(i);
	}
	/**
	 * gives the record at the index
	 * @param Index
	 * @return List of elements
	 */
	public List<String> getRecordElementsAtIndex(Integer i)
	{
		return records.get(i).getElements();
	}
	
	/**
	 * gives the category of training set at the index
	 * @param index
	 * @return category
	 */
	public String getClassAtIndex(Integer i)
	{
		return records.get(i).getClassAttribute();
	}
	
	/**
	 * Adds the record
	 * @param record
	 */
	public void addRecord(Records record)
	{
		
		records.add(record);
		numberOfRecords++;
	}

	/**
	 * Adds the record from the list and category
	 * @param list of attribute elements
	 * @param category
	 */
	public void addRecord(List<String> elems,String category)
	{
		Records record = new Records(elems, category);
		records.add(record);
		numberOfRecords++;
	}

	public String searchByRowAndColumn(int row,int col)
	{
		return getRecordAtIndex(row).getElementValueAtIndex(col);
	}
	
	
	public Integer getCountOfAClassValues(String value)
	{
		CommonLogics cl = new CommonLogics();
		return cl.getCountOfClassValue(this, value);
		
	}
	
}



