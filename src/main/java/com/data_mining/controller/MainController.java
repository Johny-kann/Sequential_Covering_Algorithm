package com.data_mining.controller;

import com.data_mining.constants.FilesList;
import com.data_mining.file_readers.TextFileWriter;
import com.data_mining.logic.AttributeAndRecordLoaders;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.nodes.RootTreeNode;
import com.data_mining.model.nodes.TreeNodes;
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
	DataTable testAttributes;
	
	public MainController()
	{
		mainAttributes = new DataTable();
		testAttributes = new DataTable();
	}
	
	public void loadAttributesAndRecords()
	{
		
		AttributeAndRecordLoaders.loadAttributeFromFile(mainAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(mainAttributes, FilesList.RECORD_FILES);
	}
	
	public void testData()
	{
		AttributeAndRecordLoaders.loadAttributeFromFile(testAttributes, FilesList.ATTRIBUTES_FILES, FilesList.ATTRIBUTES_FILES);
		
		AttributeAndRecordLoaders.loadRecordsFromFile(testAttributes, FilesList.TEST_RECORD_FILES);
		
	}
	
	
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
	
	/**
	 * @return Root note
	 * Trains the tree
	 */
	public TreeNodes trainTree()
	{
		loadAttributesAndRecords();
		TextFileWriter write = new TextFileWriter();
		
		TreeNodes tn = new RootTreeNode(getMainTable());
	
		System.out.println("Train Data");
		String tes=
		new Outputs().outPutTable(tn.getInputRecords());
			
		TreeBuilder tb = new TreeBuilder(tn);
		
		
		String str="";
		tes += tb.printTree(tn,str)+System.lineSeparator()+"Accuracy "+tb.getAccuracy();
		write.writeFile(tes,FilesList.WRITE_TRAIN_RESULT);
	//	System.out.println(tes);
		System.out.println("Accuracy "+tb.getAccuracy());
		
		return tn;
	}
	
	
	/**
	 * @param Trained root node
	 * Tests the tree with the data
	 */
	public void testTree(TreeNodes tn)
	{
		TreeBuilder tb2 = new TreeBuilder(tn);
		System.out.println("Test Data");
		
		try {
			
			TreeNodes tn2 = (TreeNodes)tn.clone();
			
			testData();
			tn2.testData(getTestAttributes());
			String tes=
			new Outputs().outPutTable(tn2.getInputRecords());
			
			TextFileWriter write = new TextFileWriter();
			String str="";
		
			String str2="";
			tes += tb2.printTree(tn,str2)+System.lineSeparator()+"Accuracy "+tb2.getAccuracy();
			write.writeFile(tes, FilesList.WRITE_TEST_RESULT);
	//		tb2.printTree(tn2);
			
			System.out.println("Accuracy "+tb2.getAccuracy());
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataTable getTestAttributes() {
		return testAttributes;
	}
	
	
}
