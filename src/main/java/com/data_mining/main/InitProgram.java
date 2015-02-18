package com.data_mining.main;

import java.util.ArrayList;
import java.util.List;

import com.data_mining.controller.MainController;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.ErrorsAndGain;
import com.data_mining.view.console.Outputs;


public class InitProgram {

	public static void main(String[] args) {
		
	CommonLogics.assignInitValues(args);
		
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
		mc.fillRuleSet();
		mc.output();
		
		
	
	}

}
