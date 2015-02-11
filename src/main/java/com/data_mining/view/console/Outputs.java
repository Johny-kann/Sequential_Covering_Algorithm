package com.data_mining.view.console;

import com.data_mining.model.attributes_records.DataTable;

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
}
