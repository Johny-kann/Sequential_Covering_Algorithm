package com.data_mining.model.rules;

public class RuleCondition
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