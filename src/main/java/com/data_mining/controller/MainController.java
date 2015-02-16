package com.data_mining.controller;

import java.util.Map;

import org.hibernate.type.OrderedSetType;

import com.data_mining.constants.FilesList;
import com.data_mining.constants.Notations;
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

		
	//	System.out.println(classes.getOrderedClasses());
	ChoosingAttributes choose = new ChoosingAttributes();
	if(Notations.PRUNING_ON)
	{
		sortedClassSet = new OrderedClassSet(
				cl.sortMapValues
				(cl.classAndCounts(trainData))
				);
	mainRuleSet = choose.fillRuleSet(trainData, sortedClassSet,testData);
	}else
	{
	//	System.out.println(mainAttributes.sizeOfRecords()+","+trainData.sizeOfRecords()+","+testData.sizeOfRecords());
		Map<String,Integer> map = cl.classAndCounts(mainAttributes);
		
	
		sortedClassSet = new OrderedClassSet(
				cl.sortMapValues
				(map)
				);
		
		Outputs.printToConsole("Order of the classes "+ sortedClassSet.getClassesAlone()
				);
		mainRuleSet = choose.fillRuleSet(mainAttributes, sortedClassSet,testData);
	}
	
	}
	
	public void orderBasedOnGeneralError()
	{
		new CommonLogics().sortRulesBasedonGeneralizationError(mainRuleSet);
		
	}
	
	public void output()
	{
		
		Outputs.printToConsole(new Outputs().outputRuleSet(mainRuleSet));
		Outputs.printToConsole("Train Data");
		
		if(Notations.PRUNING_ON)
		{
			
			Outputs.printToConsole(new Outputs().outPutTable(trainData));
			Outputs.printToConsole("Accuracy "+
					new ChoosingAttributes().AccuracyForTableByRuleSet(trainData, mainRuleSet)
							);
		}
		else
		{
			Outputs.printToConsole(new Outputs().outPutTable(mainAttributes));
		Outputs.printToConsole("Accuracy "+
		new ChoosingAttributes().AccuracyForTableByRuleSet(mainAttributes, mainRuleSet)
				);
		}
		
		
	}
	
	
	public void testDataAccuracy()
	{
		Outputs.printToConsole("Test Data");
		Outputs.printToConsole(
		new Outputs().outPutTable(testData)
		);
		Outputs.printToConsole("Accuracy "+
				new ChoosingAttributes().AccuracyForTableByRuleSet(testData, mainRuleSet)
				);
		
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

	public OrderedClassSet getSortedClassSet() {
		return sortedClassSet;
	}
	
	
}
