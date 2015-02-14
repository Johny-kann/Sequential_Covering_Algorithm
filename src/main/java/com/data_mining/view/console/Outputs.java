package com.data_mining.view.console;

import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.model.rules.RuleCondition;
import com.data_mining.model.rules.RuleSet;
import com.data_mining.model.rules.Rules;

public class Outputs {

	/**
	 * prints the table
	 * @param table
	 * @return gives the string
	 */
	public String outPutTable(DataTable table)
	{
		String str = "";
		Integer row = table.sizeOfRecords();
		Integer col = table.numberOfAttributes();
		
		for(int i=0;i<=col;i++)
		{
			if(i<col)
			{
			System.out.print(
					table.getAttributes().get(i).getName()+"\t\t"
					);
			str+="\t\t";
			}
			else
			{
				System.out.println(table.getClassName());
				str+=table.getClassName();
				str+=System.lineSeparator();
			}
		}
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<=col;j++)
			{
				if(j<col)
				{
				System.out.print(
						table.getRecordAtIndex(i).getElementValueAtIndex(j)+"\t\t"
						);
				str+=table.getRecordAtIndex(i).getElementValueAtIndex(j)+"\t\t";
				}
				else
				{
					System.out.println(table.getRecordAtIndex(i).getClassAttribute());
					str+=table.getRecordAtIndex(i).getClassAttribute();
					str+=System.lineSeparator();
					
				}
			}
		}
		return str;
	}
	
	public String outputRuleSet(RuleSet ruleSet)
	{
		StringBuffer str = new StringBuffer();
		for(int i=0;i<ruleSet.getRulesList().size();i++)
		{
			try
			{
				str.append(outputRule(ruleSet.getRulesList().get(i)));
				str.append(" --> "+ruleSet.getRulesList().get(i).getCategory()+" error "+ruleSet.getRulesList().get(i).getgError());
			}catch(IndexOutOfBoundsException ie)
			{
			//	System.out.println("Index out of bound");
				str.append("{}");
				str.append(" --> "+ruleSet.getRulesList().get(i).getCategory()+" error "+ruleSet.getRulesList().get(i).getgError());
			}
		  str.append(System.lineSeparator());
		}
		
		return str.toString();
	}
	
	public String outputRule(Rules rule)
	{
		StringBuffer str = new StringBuffer();
		for(RuleCondition temp:rule.getRules())
		{
			str.append(temp.getCondition()+"/\\");
		}
		return str.substring(0, str.length()-2).toString();
	}
}
