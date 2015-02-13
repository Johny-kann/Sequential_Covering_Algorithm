package com.data_mining.controller;

import org.hibernate.type.OrderedSetType;

import com.data_mining.constants.FilesList;
import com.data_mining.file_readers.TextFileWriter;
import com.data_mining.logic.AttributeAndRecordLoaders;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.OrderedClassSet;
import com.data_mining.model.nodes.RootTreeNode;
import com.data_mining.model.nodes.TreeNodes;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.view.console.Outputs;
import com.data_mining.view.console.decision_tree.TreeBuilder;

/**
 * @author Janakiraman 
 * 
 * Main Controller for loading test data and train data and calling functions
 *
 */
public class MainController {

	DataTable mainAttributes;
	DataTable trainData;
	DataTable testData;
	RuleSet mainRuleSet;
	OrderedClassSet sortedClassSet;
	
	public MainController()
	{
		mainAttributes = new DataTable();
		mainRuleSet = new RuleSet();
		
		
	}
	
	public void loadAttributesAndRecords()
	{
		
		AttributeAndRecordLoaders.loadAttributeFromFile(mainAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(mainAttributes, FilesList.RECORD_FILES);
		
		SearchingLogics sl = new SearchingLogics();
		trainData = sl.getTrainingSet(mainAttributes);
		testData = sl.getValidationSet(mainAttributes);
	}
	

	public void fillRuleSet()
	{
		CommonLogics cl = new CommonLogics();

		sortedClassSet = new OrderedClassSet(
				cl.sortMapValues
				(cl.classAndCounts(trainData))
				);
	//	System.out.println(classes.getOrderedClasses());
	ChoosingAttributes choose = new ChoosingAttributes(trainData);
	choose.fillRuleSet(trainData, sortedClassSet);
	
	}
	
	/*public void testData()
	{
		AttributeAndRecordLoaders.loadAttributeFromFile(testAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(testAttributes, FilesList.TEST_RECORD_FILES);
		
	} */
	
    
	
	public void output()
	{
		System.out.println(mainAttributes.getClassName());
		System.out.println(mainAttributes.sizeOfRecords());
		System.out.println(mainAttributes.numberOfAttributes());
	}
	
	public DataTable getMainTable()
	{
		return mainAttributes;
	}
	
	public DataTable getTrainAttributes()
	{
		return trainData;
	}
	
	public DataTable getTestAttributes() {
		return testData;
	}
	
	
}
