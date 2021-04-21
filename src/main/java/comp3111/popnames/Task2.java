package comp3111.popnames;

import org.apache.commons.csv.*;
import edu.duke.*;
import javax.swing.JOptionPane;

public class Task2 {

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
	
	private static void popupMessage(String error,String extra) {
		JOptionPane.showMessageDialog(null,
			    "Please enter a valid " + error + "! " + extra,
			    "Type Error",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	private static boolean checkinputvalid(String yearstartstring, String yearendstring, String kstring, String gender) {
		boolean valid = true;
		String integeronly = "It only accept an integer value.";
		if (!(yearstartstring.matches("^[0-9]*$"))){
			popupMessage("Starting Year",integeronly);
			valid = false;
		}
		if (!(yearendstring.matches("^[0-9]*$"))){
			popupMessage("Ending Year",integeronly);
			valid = false;
		}
		if (!(kstring.matches("^[0-9]*$"))){
			popupMessage("Rank K",integeronly);
			valid = false;
		}
		if (valid) {
		 	int yearstart = Integer.parseInt(yearstartstring);
		    int yearend = Integer.parseInt(yearendstring);
		    int k = Integer.parseInt(kstring);
			 
			 if (yearstart < 1880 || yearstart > 2019) {
				popupMessage("Starting Year","Its range can only be 1880-2019.");
				valid = false;
			 }
			 if (yearend < 1880 || yearend > 2019) {
				popupMessage("Ending Year","Its range can only be 1880-2019.");
				valid = false;
			 }
			 if (yearend < yearstart) {
				popupMessage("Starting Year and Ending Year","Ending Year must be larger or equal to the Starting Year.");
				valid = false;
			 }
			 if (k < 1 || k > 1000) {
				popupMessage("Rank K","Its range can only be 1-1000.");
				valid = false;
			 }
			if (!(gender.matches("^[MF]$") || gender == null)){
				popupMessage("Gender","Please choose the value 'M' or 'F' in the selection box.");
				valid = false;
			}
		}
		return valid;
	}
	
	public static String getDataTableT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "Success";
		 return oReport;
	 }
	
	public static String getBarChartT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "Success";
		 return oReport;
	 }
	public static String getPieChartT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "Success";
		 return oReport;
	 }
	
	 public static String getSummaryT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "";
		 if (checkinputvalid(yearstartstring,yearendstring, kstring, gender)) {
			 oReport = gender;
		 }
		 
		 return oReport;
	 }
 
}