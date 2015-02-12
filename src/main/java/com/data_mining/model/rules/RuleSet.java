package com.data_mining.model.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleSet {

	private List<Rules> rules;
	
	public RuleSet()
	{
		rules = new ArrayList<Rules>();
		
	}
	
	public Integer sizeOfRuleSet()
	{
		return rules.size();
	}
}
