package tester;

import java.util.Map;

import com.data_mining.constants.Notations;
import com.data_mining.controller.MainController;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.view.console.Outputs;

public class Tester {

	public static void main(String args[])
	{
		Notations.VALIDATION_ON = true;
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
		mc.fillRuleSet();
		
		mc.output();
		mc.testDataAccuracy();
		
		
	}
}
