package tester;

import java.util.Map;

import com.data_mining.controller.MainController;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.view.console.Outputs;

public class Tester {

	public static void main(String args[])
	{
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
		mc.fillRuleSet();
		ChoosingAttributes choosAttrb = new ChoosingAttributes(mc.getMainTable());
		
		RuleSet rule = new RuleSet();
		
		rule = choosAttrb.fillRuleSet(mc.getMainTable(), mc.getSortedClassSet(),mc.getTestAttributes());
		System.out.println("The Rules are ");
		System.out.println(mc.getSortedClassSet().getClassesAlone());
		System.out.println(new Outputs().outputRuleSet(rule));
		new CommonLogics().sortRulesBasedonGeneralizationError(rule);
		System.out.println(new Outputs().outputRuleSet(rule));
		
	}
}
