package com.data_mining.model.rules;

import java.util.ArrayList;
import java.util.List;

public class Rules implements Cloneable
{
	private Integer ruleNumber;
	private List<RuleCondition> rules;
	private String category;
	private Double gError;
	
	public Rules(int index,String clas)
	{
		ruleNumber = index;
		category = clas;
		rules = new ArrayList<RuleCondition>();
	}
	
	public Rules(int index,String clas,Double gError)
	{
		ruleNumber = index;
		category = clas;
		rules = new ArrayList<RuleCondition>();
		this.gError = gError;
	}
	
	@Override
	public Rules clone() throws CloneNotSupportedException
	{
		Rules clone = new Rules(ruleNumber, category);
		List<RuleCondition> rule = new ArrayList<RuleCondition>();
		rule.addAll(rules);
		clone.getRules().addAll(rule);
		return clone;
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

	public Double getgError() {
		return gError;
	}

	public void setgError(Double gError) {
		this.gError = gError;
	}
	
	
}
