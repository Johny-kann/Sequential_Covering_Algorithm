package com.data_mining.model.errors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Janakiraman
 *
 *Deals with list of error model objs
 */
public class ErrorModelList {

	private List<ErrorModel> errors;
	
	public ErrorModelList()
	{
		errors = new ArrayList<ErrorModel>();
	}

	public List<ErrorModel> getErrors() {
		return errors;
	}
	
	public void addErrorModel(ErrorModel a)
	{
		errors.add(a);
	}
	
	public ErrorModel getErrorModel(int index)
	{
		return errors.get(index);
	}
	
	public List<Double> getErrorValues(String attrbName)
	{
		List<Double> list = null;
		for(ErrorModel model:errors)
		{
			if(
			model.getAttrbName().equals(attrbName))
			{
				list = model.getErrors();
				break;
			}
		}
		return list;
	}
	
	
	public List<String> getAttrbValues(String attrbName)
	{
		List<String> list = null;
		for(ErrorModel model:errors)
		{
			if(
			model.getAttrbName().equals(attrbName))
			{
				list = model.getAttrbValues();
				break;
			}
		}
		return list;
	}
}
