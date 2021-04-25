package comp3111.popnames;

import edu.duke.FileResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task5 {

    public static CSVParser getFileParser(int year) {
        FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
        return fr.getCSVParser(false);
    }

    public static boolean checkinputvalid(String name, String yearstring, String preferredInitial) {
        boolean valid = true;
        String integeronly = "It only accept an integer value.";
        if (!(name.matches("^[a-zA-Z]*$"))){
            Task2.popupMessage("Name", "It only accept English letters.");
            valid = false;
        }
        if (!(preferredInitial.matches("^[A-Z]$"))){
            Task2.popupMessage("Preferred Inital", "It only accept an uppercase English letter.");
            valid = false;
        }
        if (!(yearstring.matches("^[0-9]*$"))){
            Task2.popupMessage("Year of birth",integeronly);
            valid = false;
        }
        if (valid) {
            int year = Integer.parseInt(yearstring);
            if (year < 1880 || year > 2019) {
                Task2.popupMessage("Year of Birth", "Its range can only be 1880-2019.");
                valid = false;
            }
        }
        return valid;
    }

    public static String doFindT5X1(String namestring, String yearstring, String gender, String preferredInital, String preferredGender, String preferredAge){
        String oReport = "";
        if (checkinputvalid(namestring,yearstring,preferredInital)) {
            int year = Integer.parseInt(yearstring);
            People result = getMostPopularPersonByInitial(year, preferredGender, null);
            int num_names = 0;
            int total_occurances = 0;
            for (CSVRecord rec : getFileParser(year)) {
                if (rec.get(1).equals(gender)) {
                    num_names++;
                    total_occurances += Integer.parseInt(rec.get(2));
                }
            }
            oReport = "After calculating the most popular name of your preferred gender in your year of birth, \nwe are glad to tell you that the person with the name: " + result.name + " are more likely to become your soulmate!\nBase on our databases, "+ total_occurances + " of baby "+ Task2.Genderbaby(preferredGender) + " with " + num_names + " different names"  + " were born in year " + yearstring +" and " + result.occurrence + " of them are called " + result.name + "."+"\nSo there are " + String.format("%.2f",(double)result.occurrence/total_occurances * 100) + "% you will meet your soulmate! \nGood Luck!";
            //Jessica has hold the 8-th rank most often for a total of 4 timesamong names registered for baby girls born in the period from 2000 to 2010.
            //The total number of occurrences of Jessica is 1592, which represents 36.4% of total female births at the 8-th rank in the period from 2000 to 2010.
        }
        return oReport;
    }

    public static String doFindT5X2(String namestring, String yearstring, String gender, String preferredInital, String preferredGender, String preferredAge){
        String oReport = "";
        if (checkinputvalid(namestring,yearstring,preferredInital)){
            int year = Integer.parseInt(yearstring);
            SortedPeopleList result = null;
            System.out.println(namestring + yearstring +gender+preferredInital+preferredGender+preferredAge);
            if (preferredAge.equals("Younger")) {
                result = generateOutput(year,2019,preferredGender,preferredInital);
            }else if (preferredAge.equals("Older")){
                result = generateOutput(1880,year,preferredGender,preferredInital);
            }
            if (result != null){
                oReport = result.sortedpeoplelist[0].name;
            }
        }
        return oReport;
    }


    public static People getMostPopularPersonByInitial(int year, String gender, String Initial) {
        boolean found = false;
        People people= new People("",0,0);

        // For every name entry in the CSV file
        for (CSVRecord rec : getFileParser(year)) {
            // Get its rank if gender matches param
            if (rec.get(1).equals(gender)) {
                // Get the name whose rank matches param
                if (Initial != null) {
                    if (String.valueOf(rec.get(0).charAt(0)).equals(Initial)) {
                        found = true;
                        people.setName(rec.get(0));
                        people.setRank(1);
                        people.setOccurrence(Integer.parseInt(rec.get(2)));
                        break;
                    }
                } else {
                    found = true;
                    people.setName(rec.get(0));
                    people.setRank(1);
                    people.setOccurrence(Integer.parseInt(rec.get(2)));
                    break;
                }
            }
        }
            //System.out.println(year);
        if (found)
            return people;
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Please try to search for another value. " + "No name can be found in year " + year + ".",
                    "Database Error",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }


    public static SortedPeopleList generateOutput(int yearstart, int yearend, String gender, String initial){
        People[] peoplelist = new People[yearend-yearstart+1];
        for (int i = 0; i < yearend-yearstart+1 ;i++){
            People temp = getMostPopularPersonByInitial(yearstart+i,gender, initial);
            if (temp != null){
                peoplelist[i] = temp;
            }else{
                return null;
            }
        }
        SortedPeopleList output = Task2.sortPeople(peoplelist);
        return output;
    }

}
