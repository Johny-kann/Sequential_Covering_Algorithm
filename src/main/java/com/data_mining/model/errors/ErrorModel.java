package com.data_mining.model.errors;

import java.util.ArrayList;
import java.util.List;

import com.data_mining.constants.Notations;

/**
 * Error model class is useful to store the error related info on the object
 * @author Janakiraman
 *
 */
public class ErrorModel {

	private String attrbName;
	private Integer attrIndex;
	private String attrbTye;
	private List<String> attrbValues;
	private List<Double> errors;
	private List<Double> gainRatio;
	private List<String> conds;
	
	public ErrorModel(String name,Integer Index,String type,List<String> values,List<Double> errors,List<Double> gainRatio, List<String> conds)
	{
	attrbName = name;
	attrIndex = Index;
	attrbTye = type;
	attrbValues = values;
	
	
	this.errors = errors;
	this.conds = conds;
	this.gainRatio = gainRatio;
	}
	
	public ErrorModel(String name,Integer Index,String type,List<String> values,Double errors,Double gainRatio)
	{
	attrbName = name;
	attrIndex = Index;
	attrbTye = type;
	attrbValues = values;
	
	this.errors = new ArrayList<Double>();
	this.errors.add(errors);
	
	this.gainRatio = new ArrayList<Double>();
	this.gainRatio.add(gainRatio);
	}

	
	public String getAttrbName() {
		return attrbName;
	}

	public String getAttrbTye() {
		return attrbTye;
	}

	public List<String> getAttrbValues() {
		return attrbValues;
	}

	public List<Double> getErrors() {
		return errors;
	}

	public List<String> getConds() {
		return conds;
	}

	public void setConds(List<String> conds) {
		this.conds = conds;
	}

	public Integer getAttrIndex() {
		return attrIndex;
	}

	public List<Double> getgainRatio() {
		return gainRatio;
	}
	
	
}
