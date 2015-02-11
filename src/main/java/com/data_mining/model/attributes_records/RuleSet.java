package com.data_mining.model.attributes_records;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

	private class Rules
	{
		Integer ruleNumber;
		List<RuleCondition> rules;
		String category;
		
		public Rules(int index,String clas)
		{
			ruleNumber = index;
			category = clas;
		}
		
		
		public Integer getRuleNumber() {
			return ruleNumber;
		}
		public void setRuleNumber(Integer ruleNumber) {
			this.ruleNumber = ruleNumber;
		}
		public List<RuleCondition> getRules() {
			return rules;
		}
		
		public void setRules(List<RuleCondition> rules) {
			this.rules = rules;
		}
		
		public void addRule(RuleCondition rule)
		{
			this.rules.add(rule);
		}
		
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
		
	}
	
	private class RuleCondition
	{
		String name;
		String value;
		String condition;
		
		public RuleCondition(String nam,String val,String con)
		{
			name = nam;
			value = val;
			condition = con;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
				
	}
	
	
	private List<Rules> rules;
	
	public RuleSet()
	{
		rules = new ArrayList<RuleSet.Rules>();
		
	}
	
	public Integer sizeOfRuleSet()
	{
		return rules.size();
	}
}
