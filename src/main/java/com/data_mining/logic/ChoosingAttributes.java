package com.data_mining.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.chart.PieChart.Data;

import com.data_mining.constants.Notations;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.errors.ErrorModel;
import com.data_mining.model.errors.ErrorModelList;
import com.data_mining.model.errors.PassingAttribute;
import com.data_mining.model.nodes.RootTreeNode;
import com.data_mining.model.nodes.TreeNodes;
import com.data_mining.view.console.Outputs;

/**
 * @author Janakiraman
 *
 *Class with methods to choose the best attributes
 */
public class ChoosingAttributes {

	private DataTable inputRecords;
	private Integer numOfAttributes;
	private String attributeName;
	private List<String> attributeValues;
	
	public ChoosingAttributes(DataTable input)
	{
		this.inputRecords = input;
		numOfAttributes= input.numberOfAttributes();
	}
	
	
	/**
	 * Calculates the error for the table
	 * @param table
	 * @return error value
	 * 
	 */
	public double calculateErrorForTable(DataTable table)
	{
		List<String> values = table.getClassValues();
		
		CommonLogics cl = new CommonLogics();
		
		Map<String, Integer> categor =  
				cl.classAndCounts(table);
	//			new LinkedHashMap<String, Integer>();
		
		ErrorsAndGain eg = new ErrorsAndGain();
		
		return eg.giniError(categor);
		
	}
	
	/**
	 * finds the best attribute based on the split and returns the an object containing the details
	 * @param table
	 * @param error
	 * @return
	 */
	public PassingAttribute findBestAttribute(DataTable input,Double error)
	{
	//	Map<String,Double> attrbErrorMap = new LinkedHashMap<String, Double>();
				
		ErrorModelList errorList = new ErrorModelList();
		
		for(int i=0;i<input.numberOfAttributes();i++)
		{
			if(input.getAttributes().get(i).getType().equals(Notations.DISCRETE_ATTRB))
			{
				errorList.addErrorModel(findErrorForDiscrete(input, i, error));
			}
			
			if(input.getAttributes().get(i).getType().equals(Notations.CNTS_ATTRB))
			{
				errorList.addErrorModel(findErrorForContinuous(input, i,error));
				
			}
		}
		
		CommonLogics cl = new CommonLogics();

		return cl.bestAttributeFromErrorModel(errorList);
		
		
	}
	
	
	/**
	 * Finds error for discrete attributes
	 * @param input
	 * @param index
	 * @param pError
	 * @return error model object
	 */
	private ErrorModel findErrorForDiscrete(DataTable input,int index,Double pError)
	{
		List<String> values =  input.getAttributes().get(index).getValues();
		DataTable temp;
		
		String attrbName = input.getAttributes().get(index).getName();
		
		String attrbType = Notations.FULL_SPLIT;
		
		
		List<Double> errors = new ArrayList<Double>();
		List<Integer> records = new ArrayList<Integer>();
		
		String condChose = null;
		
		for(String str:values)
		{
			SearchingLogics sl = new SearchingLogics();
			temp = sl.refiningSetDiscrete(input, index, str);
			errors.add(calculateErrorForTable(temp));
			CommonLogics cl = new CommonLogics();
			records.add(temp.sizeOfRecords());
//			Outputs out = new Outputs();
//			out.outPutTable(temp);
		 
			
		}
		
//		System.out.println(errors);
		ErrorsAndGain er = new ErrorsAndGain();
		Double error = er.errorSplit(errors, records, input.sizeOfRecords());
		
		
		
		Double gainRatio = er.gainRatio(records, input.sizeOfRecords(), pError,error);
		return new ErrorModel(attrbName,index, attrbType, null, error,gainRatio);
	}
	


	/**
	 * finds errors for continuous attributes
	 * @param table
	 * @param attribute index
	 * @param parent error
	 * @return object of error model
	 */
	private ErrorModel findErrorForContinuous(DataTable input,int index,Double pError)
	{
		List<Double> values = findValuesForContinuousAttributes(input, index);
		DataTable temp,temp2;
		
		String attrbName = input.getAttributes().get(index).getName();
//		System.out.println(attrbName);
		String attrbType = Notations.SEMI_SPLIT;
		
		List<Double> errorSplit = new ArrayList<Double>();
		List<Double> gainRatio = new ArrayList<Double>();
		
		List<String> strChose = new ArrayList<String>();
		
		List<String> condChose = new ArrayList<String>();
		
//		System.out.println(values);
//		new Outputs().outPutTable(input);
		
		for(Double str:values)
		{
			List<Integer> records = new ArrayList<Integer>();
			List<Double> errors = new ArrayList<Double>();
			SearchingLogics sl = new SearchingLogics();
			String name = input.getAttributes().get(index).getName();
			String Cond = name+"<"+str;
			condChose.add(Cond);
			temp = sl.refiningSetContinuousLeft(input, index, str);
			
//			 System.out.println("\n\nRefined Table 1 "+str);
			
			 
			errors.add(calculateErrorForTable(temp));
			
			CommonLogics cl = new CommonLogics();
			records.add(temp.sizeOfRecords());
//			Outputs out = new Outputs();
//			out.outPutTable(temp);
			
			String Cond2 = name+">="+str;
			condChose.add(Cond2);
			temp2 = sl.refiningSetContinuousRight(input, index, str);
//			System.out.println("\n\nRefined Table 2 "+str);
			 
			errors.add(calculateErrorForTable(temp2));
			
			records.add(temp2.sizeOfRecords());
			
//			out.outPutTable(temp2);
			
			strChose.add(str.toString());
			
			ErrorsAndGain er = new ErrorsAndGain();
		
			Double error = er.errorSplit(errors,
					records, 
					input.sizeOfRecords());
			
			Double gainR = er.gainRatio(records, 
					input.sizeOfRecords(), pError,error);
			
			errorSplit.add(error);
			gainRatio.add(gainR);
			
		}
		
		
		
		ErrorModel err = new ErrorModel(attrbName,index, attrbType, strChose, errorSplit,gainRatio,condChose);
//		
		return err;
	}
	
	/**
	 * @param table
	 * @param attribute index
	 * @return possible values of continuous attributes
	 */
	private List<Double> findValuesForContinuousAttributes(DataTable input, int index)
	{
		CommonLogics cl = new CommonLogics();
		
		cl.sort(input, index);
		
		List<Double> splits = cl.fillSplitList(input, index);
		List<Integer> pos = cl.splitPostition(input);
		
		List<Double> values = new ArrayList<Double>();
		for(int i=0;i<pos.size();i++)
		{
			values.add(splits.get(pos.get(i)));
		}
		
		return values;
	}
	
	/**
	 * @param categories
	 * @return error gain
	 */
	public Double error(Map<String,Integer> categories)
	{
		ErrorsAndGain errGain = new ErrorsAndGain();
		return errGain.classificationError(categories);
	}
	
	
	public Map<String,Integer> matchValues(int attrbIndex,String attrbvalue)
	{
		Map<String,Integer> categories = new LinkedHashMap<String, Integer>();
		
		for(int i=0;i<inputRecords.getClassValues().size();i++)
		{
		categories.put(inputRecords.getClassValues().get(i), 0);
		}
		
		for(int i=1;i<inputRecords.sizeOfRecords();i++)
		{
				if(inputRecords.
						getRecordAtIndex(i).
						getElementValueAtIndex(attrbIndex).equals(attrbvalue)
						)
				{
					String categor = inputRecords.getRecordAtIndex(i).getClassAttribute();

					int count = categories.get(categor);
					categories.replace(categor, count+1);

				}
		}
		
		return categories;
	}
}
