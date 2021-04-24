package comp3111.popnames;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;

import edu.duke.*;
import javax.swing.JOptionPane;


class SortedPeopleList {
	public int totaloccurrence;
	public int totalfreq;
	public int unipeople;
	public People[] sortedpeoplelist;

	public SortedPeopleList(){
		this.totaloccurrence = 0;
		this.totalfreq = 0;
		this.unipeople = 0;
	}
}

class People{
	public String name;
	public int rank;
	public int occurrence;
	public int freq;

	public People(String name, int rank, int occurrence) {
		this.name = name;
		this.rank = rank;
		this.occurrence = occurrence;
		this.freq = 1;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccurrence(int occurrence) {
		this.occurrence = occurrence;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public static int compareOccurrence(People a , People b)
	{
		if(a.occurrence < b.occurrence)
			return 1;    //swap b before a
		else if (a.occurrence == b.occurrence)
			return a.name.compareTo(b.name); //check name instead
		return -1 ;
		//return b.occurrence - a.occurrence;
		//return a.name.compareTo(b.name);
	}
}

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

	public static People getPeople(int year, int rank, String gender) {
		boolean found = false;
		People people= new People("",0,0);
		int currentRank = 0;

		// For every name entry in the CSV file
		for (CSVRecord rec : getFileParser(year)) {
			// Get its rank if gender matches param
			if (rec.get(1).equals(gender)) {
				// Get the name whose rank matches param
				currentRank++;
				if (currentRank == rank) {
					found = true;
					people.setName(rec.get(0));
					people.setRank(currentRank);
					people.setOccurrence(Integer.parseInt(rec.get(2)));
					break;
				}
			}
		}
		if (found)
			return people;
		else
			popupMessage("input","No name can be found in rank "+ rank + " in year " + year + ".");
			return null;
	}

	public static SortedPeopleList sortPeople(People[] peoplelist){
		SortedPeopleList result = new SortedPeopleList();
		result.totalfreq = peoplelist.length;
		People[] templist = new People[peoplelist.length];
		Set<String> listname = new HashSet<>();
		for (int i = 0; i < peoplelist.length; i++){
			if (!listname.contains(peoplelist[i].name)){
				templist[result.unipeople] = peoplelist[i];
				listname.add(peoplelist[i].name);
				result.unipeople ++;
			}else{
				for (int k = 0; k < result.unipeople; k++){
					if (templist[k].name.equals(peoplelist[i].name)){
						templist[k].occurrence += peoplelist[i].occurrence;
						templist[k].freq += 1;
					}
				}
			}
			result.totaloccurrence += peoplelist[i].occurrence;
		}
		People[] realArray = new People[result.unipeople];
		System.arraycopy(templist, 0, realArray, 0, result.unipeople);
		result.sortedpeoplelist = realArray;
		Arrays.sort(result.sortedpeoplelist, People::compareOccurrence);
		return result;
	}

	public static SortedPeopleList generateOutput(int yearstart, int yearend, int k, String gender){
		People[] peoplelist = new People[yearend-yearstart+1];
		for (int i = 0; i < yearend-yearstart+1 ;i++){
			People temp = getPeople(yearstart+i,k,gender);
			if (temp != null){
				peoplelist[i] = temp;
			}
		}
		SortedPeopleList output = sortPeople(peoplelist);
		return output;
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
		 	int yearstart = Integer.parseInt(yearstartstring);
		 	int yearend = Integer.parseInt(yearendstring);
		 	int k = Integer.parseInt(kstring);
		 	SortedPeopleList result = generateOutput(yearstart,yearend,k,gender);
			People toppeople = result.sortedpeoplelist[0];
			oReport = toppeople.name + " has hold the " + kstring + "-th rank most often for a total of " + toppeople.freq + " timesamong names registered for baby " + "girls" + " born in the period from " + yearstartstring + " to " + yearendstring + ".\n";
			oReport += "The total number of occurrences of " + toppeople.name + " is " + toppeople.occurrence + ", which represents " + (double)toppeople.occurrence/result.totaloccurrence * 100 + "% of total " + "female" + " births at the " + kstring + "-th rank in the period from " + yearstartstring + " to " + yearendstring + ".";
			
			 //Jessica has hold the 8-th rank most often for a total of 4 timesamong names registered for baby girls born in the period from 2000 to 2010.
			 //The total number of occurrences of Jessica is 1592, which represents 36.4% of total female births at the 8-th rank in the period from 2000 to 2010.
		 	// for (int i = 0; i < result.unipeople; i++){
		 	// 	oReport = oReport + result.sortedpeoplelist[i].name + ":" + result.sortedpeoplelist[i].occurrence + ":" + result.sortedpeoplelist[i].freq + "\n" ;
			// }
		 	// oReport += result.unipeople + "/" + result.totalfreq + "/" + result.totaloccurrence;
		 }
		 return oReport;
	 }
 
}