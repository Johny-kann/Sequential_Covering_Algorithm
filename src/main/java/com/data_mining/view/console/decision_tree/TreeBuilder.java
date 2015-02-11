package com.data_mining.view.console.decision_tree;

import java.io.BufferedWriter;

import com.data_mining.constants.NodeConstants;
import com.data_mining.constants.Notations;
import com.data_mining.exceptions.LeafNodeException;
import com.data_mining.model.errors.Accuracy;
import com.data_mining.model.nodes.TreeNodes;

/**
 * @author Janakiraman
 * 
 * Builds the decision tree
 *
 */
public class TreeBuilder {

	private TreeNodes rootNode;
	private Accuracy accuracy;
	
	public TreeBuilder(TreeNodes node)
	{
		rootNode = node;
		accuracy = new Accuracy();
	}
	
	public String printTree(TreeNodes node,String str)
	{
		
		if(node.getType()==NodeConstants.LEAF_NODE)
		{
			System.out.println(getSpaces(node)+node.getNodeTitle()+" Class "+node.getMaxClass()
					+" correct "+node.getCorrectClassCount()
					+" wrong "+node.getWrongClassCount());
			
			str+=getSpaces(node)+node.getNodeTitle()+" Class "+node.getMaxClass()
					+" correct "+node.getCorrectClassCount()
					+" wrong "+node.getWrongClassCount();
		
			str+=System.lineSeparator();
			
			accuracy.addRightClass(node.getCorrectClassCount());
			accuracy.addWrongClass(node.getWrongClassCount());
		}
		else
		{
			System.out.println(getSpaces(node)+node.getNodeTitle());
			
			str += getSpaces(node)+node.getNodeTitle();
			str+=System.lineSeparator();
			
		}
	//	InputGetter.getEnter();
		if(node.getType()!=NodeConstants.LEAF_NODE)
		try {
			for(TreeNodes child:node.getChildren())
			{
				str=printTree(child,str);
				
			}
		} catch (LeafNodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	public String getSpaces(TreeNodes node)
	{
		String space="";
		for(int i=1;i<node.getDecisionTreeLevel();i++)
		{
			space+="|\t";
		}
		return space;
	}
	
	public Double getAccuracy()
	{
		return accuracy.getAccuracy();
	}
}
