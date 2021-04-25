package comp3111.popnames;

import edu.duke.FileResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
            //System.out.println(namestring + yearstring +gender+preferredInital+preferredGender+preferredAge);
            String yearstartstring = "";
            String yearendstring = "";
            if (preferredAge.equals("Younger")) {
                result = generateOutput(year,2019,preferredGender,preferredInital);
                yearstartstring = String.valueOf(year);
                yearendstring = String.valueOf(2019);
            }else if (preferredAge.equals("Older")){
                result = generateOutput(1880,year,preferredGender,preferredInital);
                yearstartstring = String.valueOf(1880);
                yearendstring = String.valueOf(year);
            }
            if (result != null){
                Stage imagestage = new Stage();
                Image image = new Image("name_initials_effect.png");
                //Creating the image view
                ImageView imageView = new ImageView();
                //Setting image to the image view
                imageView.setImage(image);
                //Setting the image view parameters
                imageView.setX(10);
                imageView.setY(10);
                imageView.setFitWidth(575);
                imageView.setPreserveRatio(true);
                //Setting the Scene object
                Group root = new Group(imageView);
                Scene sceneimage = new Scene(root, 650, 500);
                imagestage.setTitle("Research study for name-letter effect");
                imagestage.setScene(sceneimage);


                Stage stage = new Stage();
                Scene scene = new Scene(new Group());
                stage.setTitle("Pie Chart");
                stage.setWidth(500);
                stage.setHeight(500);
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                for (People ppl : result.sortedpeoplelist) {
                    pieChartData.add(new PieChart.Data(ppl.name,ppl.occurrence));
                }

                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Popular " + Task2.Gender(gender) + " Names with initial " + preferredInital + " between " + yearstartstring + " to " + yearendstring);
                final Label caption = new Label("");
                caption.setTextFill(Color.BLACK);
                caption.setStyle("-fx-font: 22 arial;");

                for (final PieChart.Data data : chart.getData()) {
                    SortedPeopleList finalResult = result;
                    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                            new EventHandler<MouseEvent>() {
                                @Override public void handle(MouseEvent e) {
                                    caption.setTranslateX(e.getSceneX());
                                    caption.setTranslateY(e.getSceneY());
                                    caption.setText(String.format("%.2f",(double)data.getPieValue()/ finalResult.totaloccurrence*100)
                                            + "%");
                                }
                            });
                }
                Label reminder = new Label("*You can click on the corresponding slice \nand the value will be displayed.(Rounded down to 2 d.p.)*");
                reminder.setTextFill(Color.GRAY);
                reminder.setStyle("-fx-font: 16 arial;");
                reminder.setTranslateX(20);
                reminder.setTranslateY(400);

                ((Group) scene.getRoot()).getChildren().addAll(chart, caption,reminder);
                stage.setScene(scene);
                stage.show();

                String initialtext = "";
                if (preferredInital.equals(String.valueOf(namestring.charAt(0)))){
                    initialtext = "your own initial";
                }else{
                    initialtext = "your preferred initial";
                }
                String yearrange = "";
                if (preferredAge.equals("Younger")){
                    yearrange = " from " + yearstring + " to 2019";
                }else{
                    yearrange = " from 1880 to " + yearstring;
                }

                oReport = "After calculating the most popular name of your preferred gender with " + initialtext + yearrange + ",\nwe are glad to tell you that the person with the name: "+ result.sortedpeoplelist[0].name + " are more likely to become your soulmate! \n" + "According to the name-letter effect discovered in 1985 by the Belgian psychologist Jozef Nuttin,\npeople tends to prefer the letters in their name especially for their name initial over other letters in the alphabet.\nThis is because most people like themselves and the name is associated with the self. Hence the letters of the name are preferred.\n";
                if (preferredInital.equals(String.valueOf(namestring.charAt(0)))){
                    imagestage.show();
                    oReport += "As you can see from the PopUp bar chart, groups with shared initials have higher group performance, collective efficacy and adaptive conflict ability.\nSince " + result.sortedpeoplelist[0].name + " shared the same name initial " + preferredInital + " with you, you two are born to become soulmate!\n";
                }else{
                    oReport += "Although you flavor other initial than your own initial, you can try to fill in your own initial in the preferred initial field or leave it blank.\n";
                }
                oReport += "Moreover, base on our databases, "+ result.totaloccurrence + " of baby "+ Task2.Genderbaby(preferredGender) + " with the initial " + preferredInital + " were born in year from " + yearstartstring +" to " + yearendstring + ".\nAnd "+ result.sortedpeoplelist[0].occurrence + " of them are called " + result.sortedpeoplelist[0].name + "."+ " So as you can see from the PopUp chart, there are " + String.format("%.2f",(double)result.sortedpeoplelist[0].occurrence/result.totaloccurrence * 100) + "% you will meet your soulmate! \nGood Luck!";

                //oReport = result.sortedpeoplelist[0].name;
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
