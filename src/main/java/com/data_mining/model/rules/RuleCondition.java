package com.data_mining.model.rules;

public class RuleCondition
{
	private String name;
	private String value;
	private String condition;
	private Double error;
	private String split;
	
	public RuleCondition(String nam,String val,String con,Double error,String split)
	{
		name = nam;
		value = val;
		condition = con;
		this.error = error;
		this.split = split;
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
	
	public Double getError()
	{
		return this.error;
	}
	
	public String getSplit()
	{
		return this.split;
	}
			
}