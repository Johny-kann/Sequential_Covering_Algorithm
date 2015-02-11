package com.data_mining.main;

import java.util.ArrayList;
import java.util.List;

import com.data_mining.controller.MainController;
import com.data_mining.logic.ErrorsAndGain;
import com.data_mining.model.nodes.RootTreeNode;
import com.data_mining.model.nodes.TreeNodes;
import com.data_mining.view.console.Outputs;
import com.data_mining.view.console.decision_tree.TreeBuilder;

public class InitProgram {

	public static void main(String[] args) {
		
		MainController mc = new MainController();
		
		TreeNodes t1 = mc.trainTree();
		
		mc.testTree(t1);
	}

}
