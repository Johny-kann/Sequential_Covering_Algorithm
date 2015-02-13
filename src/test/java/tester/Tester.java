package tester;

import java.util.Map;

import com.data_mining.controller.MainController;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.OrderedClassSet;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.view.console.Outputs;

public class Tester {

	public static void main(String args[])
	{
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
	//	DataTable records= mc.getMainTable();
	
		ChoosingAttributes choosAttrb = new ChoosingAttributes(mc.getMainTable());
		
//		RuleSet set = choosAttrb.fillRuleSet(mc.getMainTable(), sortclasses);
		RuleSet rule = new RuleSet();
		choosAttrb.extractRule(mc.getMainTable(), rule, "yes", 0);
	//	String str = new Outputs().outputRuleSet(rule);
		
	//	System.out.println(str);
		
	}
}
