package com.data_mining.model.nodes;

import java.util.List;

import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.exceptions.ParentNodeException;
import com.data_mining.model.attributes_records.DataTable;

/**
 * @author Main node which has three sub division root, internal, leaf
 *
 */
public interface TreeNodes{
	
	
	
	/**
	 * @return error of the node
	 */
	public Double getError();
	
	/**
	 * @return type of the node example, discrete, cntns
	 */
	public String getType();
	
	/**
	 * calculates error
	 */
	public void calculateError();
	
	/**
	 * clone so that data is not referenced 
	 * @return tree node obj
	 * @throws CloneNotSupportedException
	 */
	public TreeNodes clone() throws CloneNotSupportedException;
	
	/**
	 * get the records of that node
	 * @return
	 */
	public DataTable getInputRecords();
	
	public void setInputRecords(DataTable inputRecords);
	
	/**
	 * @return nodes of the child
	 * @throws LeafNodeException
	 */
	public List<TreeNodes> getChildren() throws LeafNodeException;
	
	public TreeNodes getParentNode() throws ParentNodeException;
	
	public void setAllChildren(List<TreeNodes> childs) throws LeafNodeException;
	
	/**
	 * @return chosen attribute for this node
	 */
	public String getChosenNodeAttribute();
	
	/**
	 * based on this the records are assigned in this node
	 * @return chosen condition 
	 */
	public String getChosenNodeCondition();
	
	/**
	 * @return max category in the node
	 */
	public String getMaxClass();
	
	/**
	 * @return level in which the node is placed
	 */
	public Integer getDecisionTreeLevel();
	
	public void setNodeAttribute(String name);
	
	public void setNodeCondition(String cond);
	
	/**
	 * finds the best attribute
	 * @throws LeafNodeException
	 */
	public void findBestAttribute() throws LeafNodeException;
	
	/**
	 * starts to train the for the record
	 * @throws LeafNodeException
	 */
	public void start() throws LeafNodeException;
	
	public void findNodeCondition();
	
	/**
	 * @return split error
	 * @throws LeafNodeException
	 */
	public Double getErrorSplit() throws LeafNodeException;
	
	public void initiateChildNodes() throws LeafNodeException;;
	
	public String getNodeTitle();
	
	/**
	 * assigns the number of classes and their counts
	 */
	public void assignClassAndCounts();
	
	/**
	 * @param table
	 */
	public void testData(DataTable table);
	
	/**
	 * send to child nodes
	 * @throws LeafNodeException
	 */
	public void sendToChildNodes() throws LeafNodeException;
	
	/**
	 * gives number of major class
	 * @return
	 */
	public Integer getCorrectClassCount();
	
	/**
	 * @return number of minor class
	 */
	public Integer getWrongClassCount();
	
		

}
