package tester;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.data_mining.constants.Notations;
import com.data_mining.controller.MainController;
import com.data_mining.logic.CommonLogics;
import com.data_mining.model.attributes_records.DataTable;
import com.data_mining.view.console.Outputs;

public class SmallClass {
		
	public static void main(String[] args) 
	{
		Logger log = Logger.getLogger("TestLogger");

		FileHandler fh;  

		    try {  

		        // This block configure the logger with handler and formatter  
		     //   fh = new FileHandler("/Hello.log");  
//		        URL url= FileHandler.class.getResource("/Hello.log");
		        fh = new FileHandler("Hello.log");
		        log.addHandler(fh);
		        log.setUseParentHandlers(false);
		        
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		        // the following statement is used to log any messages  
		        log.info("My first log");  

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  

		log.log(Level.INFO,"List");
	//	System.out.println(log);
	}
}
