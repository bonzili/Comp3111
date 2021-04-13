package comp3111.popnames;

import org.apache.commons.csv.*;
import edu.duke.*;

public class Task2 {

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
	
	 public static String getSummaryT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "Success";
    	if (!(yearstartstring.matches("^[0-9]*$") && yearendstring.matches("^[0-9]*$") && kstring.matches("^[0-9]*$"))) {
    		oReport = "Type Error";
    		return oReport;
    	} 
	 	int yearstart = Integer.parseInt(yearstartstring);
	    int yearend = Integer.parseInt(yearendstring);
	    int k = Integer.parseInt(kstring);
		 
		 if (yearstart < 1880 || yearstart > 2019) {
			 oReport = "Error with input range";
		 }
		 if (yearend < 1880 || yearend > 2019) {
			 oReport = "Error with input range";
		 }
		 if (k < 1 || k > 1000) {
			 oReport = "Error with input range";
		 }
		 return oReport;
	 }
 
}