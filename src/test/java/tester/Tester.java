package tester;

import java.util.Map;

import com.data_mining.controller.MainController;
import com.data_mining.logic.ChoosingAttributes;
import com.data_mining.logic.CommonLogics;
import com.data_mining.logic.SearchingLogics;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.attributes_records.OrderedClassSet;
import com.data_mining.model.attributes_records.RuleSet;
import com.data_mining.view.console.Outputs;

public class Tester {

	public static void main(String args[])
	{
		MainController mc = new MainController();
		mc.loadAttributesAndRecords();
		DataTable records= mc.getMainTable();
	//	new Outputs().outPutTable(records);
		System.out.println(records.sizeOfRecords());
		
		
		DataTable valTable;
		try {
			valTable = new SearchingLogics().giveValidationSet(records);
			System.out.println(valTable.sizeOfRecords());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommonLogics cl = new CommonLogics();
		
		Map<String, Integer> classAndCounts = cl.classAndCounts(records);
		
		OrderedClassSet classSet = new OrderedClassSet(
				cl.sortMapValues(classAndCounts));
		
		RuleSet ruleSet = new RuleSet();
		System.out.println(ruleSet.sizeOfRuleSet());
		
		System.out.println(new ChoosingAttributes(records).laplaceForTable(records, "no"));
		
		
	}
}
