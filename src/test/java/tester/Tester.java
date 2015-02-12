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
		mc.fillRuleSet();
	
		ChoosingAttributes choosAttrb = new ChoosingAttributes(mc.getTrainAttributes());
		choosAttrb.addRule(mc.getMainTable(), "yes", 0);
	}
}
