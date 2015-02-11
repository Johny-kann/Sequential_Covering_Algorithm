package com.data_mining.model.nodes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.data_mining.constants.NodeConstants;
import com.data_mining.constants.Notations;
import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;
import com.data_mining.model.attributes_records.DataTable;

/**
 * @author Janakiraman
 * 
 * Leaf node model
 *
 */
public class LeafNode implements TreeNodes,Cloneable{
	
	private DataTable inputRecords;
	private TreeNodes parentNode;
	private String nodeTitle;
	private String nodeSplit;
	private String classSelected;
	private String nodeType;
	private Double error;
	private String nodeAttribute;
	private String nodeCondition;
	private Integer decisionTreeLevel;
	private Integer countOfCorrectClass;
	private Integer countOfWrongClass;
	
	public LeafNode(DataTable input,String title,int level)
	{
//		System.out.println("Leaf node "+title+" "+level);
		
		
		nodeType = NodeConstants.LEAF_NODE;
		inputRecords = input;
		nodeTitle = title;
		decisionTreeLevel = level;
		
		try {
			start();
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("\n\n\nLeaf Node  Reached");
//		InputGetter.getEnter();
	}
	
	@Override
	public TreeNodes clone() throws CloneNotSupportedException {
	      TreeNodes clonedCustomer = (TreeNodes) super.clone();
	 
	      // Clone the object referenced objects
	      
	      return clonedCustomer;
	   }
	
	@Override
	public void testData(DataTable table)
	{
		SearchingLogics sl = new SearchingLogics();
		inputRecords = sl.extractDataBasedOnCondition(table, nodeTitle);
		calculateError();
		
	/*	try {
			sendToChildNodes();
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
	}
	
	@Override
	public void sendToChildNodes() throws LeafNodeException {
		// TODO Auto-generated method stub
		throw new LeafNodeException();
	}
	
	public TreeNodes getParentNode()
	{
		return parentNode;
	}

	
	@Override
	public Double getError() {
		// TODO Auto-generated method stub
		return error;
	}

	@Override
	public void calculateError() {
		// TODO Auto-generated method stub
		ChoosingAttributes err = new ChoosingAttributes(inputRecords);
		error = err.calculateErrorForTable(inputRecords);
		assignClassAndCounts();
		
	}

	
	
	@Override
	public DataTable getInputRecords() {
		// TODO Auto-generated method stub
		return inputRecords;
	}

	@Override
	public void setInputRecords(DataTable inputRecords) {
		// TODO Auto-generated method stub
		this.inputRecords = inputRecords;
		
	}

	@Override
	public List<TreeNodes> getChildren() throws LeafNodeException{
		// TODO Auto-generated method stub
		
		throw new LeafNodeException();
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return nodeType;
	}

	@Override
	public void setAllChildren(List<TreeNodes> childs) throws LeafNodeException {
		// TODO Auto-generated method stub
		throw new LeafNodeException();
	}
	@Override
	public String getChosenNodeAttribute() {
		// TODO Auto-generated method stub
		return nodeAttribute;
	}


	@Override
	public String getChosenNodeCondition() {
		// TODO Auto-generated method stub
		return nodeCondition;
	}


	@Override
	public void setNodeAttribute(String name) {
		// TODO Auto-generated method stub
		nodeAttribute = name;
	}


	@Override
	public void setNodeCondition(String cond) {
		// TODO Auto-generated method stub
		nodeCondition = cond;
	}

	@Override
	public void findBestAttribute() throws LeafNodeException {
		// TODO Auto-generated method stub
		throw new LeafNodeException();
	}

	@Override
	public void start() throws LeafNodeException {
		// TODO Auto-generated method stub
		calculateError();
		
		CommonLogics cl = new CommonLogics();
		classSelected = cl.findMaxClass(inputRecords, error);
		
		
		if(classSelected == null)
		{
		
			classSelected = Notations.DEFAULT_CLASS;
		
		}
		
		
	}

	@Override
	public void findNodeCondition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double getErrorSplit() throws LeafNodeException {
		// TODO Auto-generated method stub
		throw new LeafNodeException();
	}

	@Override
	public void initiateChildNodes() throws LeafNodeException {
		// TODO Auto-generated method stub
		throw new LeafNodeException();
	}

	@Override
	public String getNodeTitle() {
		// TODO Auto-generated method stub
		return nodeTitle;
	}

	@Override
	public Integer getDecisionTreeLevel() {
		// TODO Auto-generated method stub
		return decisionTreeLevel;
	}

	@Override
	public String getMaxClass() {
		// TODO Auto-generated method stub
		CommonLogics cl = new CommonLogics();
		classSelected = cl.findMaxClass(inputRecords, error);
		if(classSelected == null)
		{
		
			classSelected = Notations.DEFAULT_CLASS;
		
		}
		return classSelected;
		
	}

	@Override
	public void assignClassAndCounts() {
		// TODO Auto-generated method stub
				CommonLogics cl = new CommonLogics();
				Map<String,Integer> map = cl.classAndCounts(inputRecords);
				countOfCorrectClass = 0;
				countOfWrongClass = 0;
				
				Set<String> keys = map.keySet();
				
				for(String str:keys)
				{
					if(str.equals(getMaxClass()))
					{
						countOfCorrectClass = map.get(str);
					}
					else
					{
						countOfWrongClass += map.get(str);
					}
				}
				
		
	}
	
	@Override
	public Integer getCorrectClassCount() {
		// TODO Auto-generated method stub
		return countOfCorrectClass;
	}

	@Override
	public Integer getWrongClassCount() {
		// TODO Auto-generated method stub
		return countOfWrongClass;
	}


}
