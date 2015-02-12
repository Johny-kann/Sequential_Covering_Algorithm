package com.data_mining.model.rules;

import java.util.List;

public class Rules
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
