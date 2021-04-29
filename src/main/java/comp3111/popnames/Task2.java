package comp3111.popnames;

import java.util.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.print.PrinterJob;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.csv.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
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

/**
 * Task 2 - Generate a report in response to the queries on the K-th popular names over a given period.
 * @author  Li Ho Yin
 */
public class Task2 {


	/**
	 * Task 2 - Convert Cardinal value to ordinal value e.g. from 1 to 1-st. Used for console text output.
	 * @param Cardinal the value of the cardinal value
	 * @return Number in Ordinal value
	 * @author  Li Ho Yin
	 */
	public static String ordinalConversion(int Cardinal){
		String result = "";
		if (Cardinal <= 0){
			return null;
		}
		switch(Cardinal) {
			case 1:
				result = "1-st";
				break;
			case 2:
				result = "2-nd";
				break;
			case 3:
				result = "3-rd";
				break;
			default:
				result = Cardinal + "-th";
		}
		return result;
	}
	/**
	 * Task 2 - Convert Gender from short form "M/F" to "Male/Female" . Used for console text output.
	 * @param gender the short form of the gender
	 * @return The full string of the gender.
	 * @author  Li Ho Yin
	 */
	public static String Gender(String gender){
		if (gender.equals("M")){
			return "Male";
		}else if (gender.equals("F")){
			return "Female";
		}
		return null;
	}

	/**
	 * Task 2 - Convert Gender from short form "M/F" to "boys/girls" . Used for console text output.
	 * @param gender the short form of the gender
	 * @return The full string of the gender.
	 * @author  Li Ho Yin
	 */
	public static String Genderbaby(String gender){
		if (gender.equals("M")){
			return "boys";
		}else if (gender.equals("F")){
			return "girls";
		}
		return null;
	}

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}

	/**
	 * Task 2 - Display a PopUp Message displaying the error.
	 * @param error The error field
	 * @param extra The extra error message
	 * @author  Li Ho Yin
	 */
	public static void popupMessage(String error,String extra) {
		JOptionPane.showMessageDialog(null,
			    "Please enter a valid " + error + "! " + extra,
			    "Input Error",
			    JOptionPane.WARNING_MESSAGE);
	}


	/**
	 * Task 2 - Ensure that the input is valid by checking their type and range.
	 * @param yearendstring String inputted for Ending Year
	 * @param yearstartstring String inputted for Starting Year
	 * @param kstring String inputted for Rank K
	 * @param gender String inputted for gender
	 * @return A boolean value showing the input is valid or not
	 * @author  Li Ho Yin
	 */
	public static boolean checkinputvalid(String yearstartstring, String yearendstring, String kstring, String gender) {
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

	/**
	 * Task 2 - Search for the people in the databases base on the year, gender and rank.
	 * @param year The year wanted to check
	 * @param gender Gender value
	 * @param rank Rank K value
	 * @return The people with the rank and gender as requested
	 * @author  Li Ho Yin
	 */
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
		//System.out.println(year);
		if (found)
			return people;
		else
		{
			JOptionPane.showMessageDialog(null,
					"Please try to search for another value. " + "No name can be found in rank "+ rank + " in year " + year + ".",
					"Database Error",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}

	/**
	 * Task 2 - Sort the people list by the reverse order of occurrences and if two names have the same occurrences, they will be sorted by their name alphabetically.
	 * @param peoplelist an unsorted people list
	 * @return A sorted people list with extra info e.g. total occurrences, number of unique person
	 * @author  Li Ho Yin
	 */
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


	/**
	 * Task 2 -  Generate the output used for displaying charts and data tables.
	 * @param yearstart Starting Year
	 * @param yearend Ending Year
	 * @param k Rank K
	 * @param gender Gender
	 * @return A sorted people list with extra info e.g. total occurrences, number of unique person
	 * @author  Li Ho Yin
	 */
	public static SortedPeopleList generateOutput(int yearstart, int yearend, int k, String gender){
		People[] peoplelist = new People[yearend-yearstart+1];
		for (int i = 0; i < yearend-yearstart+1 ;i++){
			People temp = getPeople(yearstart+i,k,gender);
			if (temp != null){
				peoplelist[i] = temp;
			}else{
				return null;
			}
		}
		SortedPeopleList output = sortPeople(peoplelist);
		return output;
	}

	/**
	 * Task 2 - A temporarily person class used for sorting
	 * @author  Li Ho Yin
	 */
	public static class Person {

		/**
		 * Task 2 - The name of the temporarily person
		 */
		public final SimpleStringProperty Name;

		/**
		 * Task 2 - The frequency of the temporarily person
		 */
		public final SimpleStringProperty Freq;

		/**
		 * Task 2 - The occurrences of the temporarily person
		 */
		public final SimpleStringProperty Occurrences;

		/**
		 * Task 2 - The percentage of the temporarily person existed
		 */
		public final SimpleStringProperty Percentage;


		/**
		 * Task 2 - Constructor for the temporarily person
		 * @param Name The name of the temporarily person
		 * @param freq The frequency of the temporarily person
		 * @param occurrences The occurrences of the temporarily person
		 * @param percentage The percentage of the temporarily person
		 */
		public Person(String Name, int freq, int occurrences, String percentage) {
			this.Name = new SimpleStringProperty(Name);
			this.Freq = new SimpleStringProperty(String.valueOf(freq));
			this.Occurrences = new SimpleStringProperty(String.valueOf(occurrences));
			this.Percentage = new SimpleStringProperty(percentage+"%");
		}

		/**
		 * Task 2 - Get the name of the temporarily person
		 * @return  The name of the person
		 * @author  Li Ho Yin
		 */
		public String getName() {
			return Name.get();
		}

		/**
		 * Task 2 - Set the name of the temporarily person
		 * @param Name The name wanted to set
		 * @author  Li Ho Yin
		 */
		public void setName(String Name) {
			this.Name.set(Name);
		}

		/**
		 * Task 2 - Get the frequency of the temporarily person
		 * @return  The frequency of the person existed
		 * @author  Li Ho Yin
		 */
		public String getFreq() {
			return Freq.get();
		}

		/**
		 * Task 2 - Set the frequency of the temporarily person
		 * @param frequency The frequency wanted to set
		 * @author  Li Ho Yin
		 */
		public void setFreq(String frequency) {
			this.Freq.set(frequency);
		}

		/**
		 * Task 2 - Get the occurrences of the temporarily person
		 * @return  The occurrences of the person
		 * @author  Li Ho Yin
		 */
		public String getOccurrences() {
			return Occurrences.get();
		}

		/**
		 * Task 2 - Set the occurrences of the temporarily person
		 * @param Occurrences The occurrences wanted to set
		 * @author  Li Ho Yin
		 */
		public void setOccurrences(String Occurrences) {
			this.Occurrences.set(Occurrences);
		}

		/**
		 * Task 2 - Get the percentage of the temporarily person
		 * @return  The percentage of the person existed
		 * @author  Li Ho Yin
		 */
		public String getPercentage() {
			return Percentage.get();
		}

		/**
		 * Task 2 - Set the percentage of the temporarily person
		 * @param percentage The percentage wanted to set
		 * @author  Li Ho Yin
		 */
		public void setPercentage(String percentage) {
			this.Percentage.set(percentage);
		}

	}

	/**
	 * Task 2 - Compute the result of the k-th popular name over the year in a data table.
	 * @param yearendstring String inputted for Ending Year
	 * @param yearstartstring String inputted for Starting Year
	 * @param kstring String inputted for Rank K
	 * @param gender String inputted for gender
	 * @return Console String and a PopUp window of a data table
	 * @author  Li Ho Yin
	 */
	public static String getDataTableT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		String oReport = "";
		if (checkinputvalid(yearstartstring,yearendstring, kstring, gender)) {
			int yearstart = Integer.parseInt(yearstartstring);
			int yearend = Integer.parseInt(yearendstring);
			int k = Integer.parseInt(kstring);
			SortedPeopleList result = generateOutput(yearstart, yearend, k, gender);
			if (result == null){
				return "Error. The result cannot be generated.";
			}

			TableView<Person> table = new TableView<Person>();

			ObservableList<Person> data = FXCollections.observableArrayList();
			for (People ppl : result.sortedpeoplelist) {
				data.add(new Person(ppl.name, ppl.freq, ppl.occurrence, String.format("%.1f",(double)ppl.occurrence/result.totaloccurrence * 100 )));
			}

			Stage stage = new Stage();
			Scene scene = new Scene(new Group());
			stage.setTitle("Data table");
			stage.setWidth(450);
			stage.setHeight(550);

			final Label label = new Label(ordinalConversion(k) + " Popular " + Gender(gender) + " Names between " + yearstartstring + " to " + yearendstring);
			label.setFont(new Font("Arial", 18));

			table.setEditable(false);

			TableColumn NameCol = new TableColumn("Name");
			NameCol.setMinWidth(100);
			NameCol.setStyle( "-fx-alignment: CENTER;");
			NameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));

			TableColumn FreqCol = new TableColumn("Frequency");
			FreqCol.setMinWidth(100);
			FreqCol.setStyle( "-fx-alignment: CENTER;");
			FreqCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Freq"));

			TableColumn OccurCol = new TableColumn("Occurrences");
			OccurCol.setMinWidth(100);
			OccurCol.setStyle( "-fx-alignment: CENTER;");
			OccurCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Occurrences"));

			TableColumn PercentCol = new TableColumn("Percentage");
			PercentCol.setMinWidth(100);
			PercentCol.setStyle( "-fx-alignment: CENTER;");
			PercentCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Percentage"));

			table.setItems(data);
			table.getColumns().addAll(NameCol,FreqCol,OccurCol,PercentCol);

			final VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.setPadding(new Insets(10, 0, 0, 10));

			final Label total = new Label("Total:");
			total.setFont(new Font("Arial", 20));
			total.setTranslateX(35);
			total.setTranslateY(440);

			Label totalfreq = new Label(String.valueOf(result.totalfreq));
			totalfreq.setFont(new Font("Arial", 20));
			totalfreq.setTranslateX(155);
			totalfreq.setTranslateY(440);

			Label totaloccur = new Label(String.valueOf(result.totaloccurrence));
			totaloccur.setFont(new Font("Arial", 20));
			totaloccur.setTranslateX(220);
			totaloccur.setTranslateY(440);

			Label totalpercent = new Label("100.0%");
			totalpercent.setFont(new Font("Arial", 20));
			totalpercent.setTranslateX(330);
			totalpercent.setTranslateY(440);

			vbox.getChildren().addAll(label, table);
			Button btn = new Button("Print/Export to PDF");
			btn.setStyle("-fx-font: 18 arial; -fx-base: #b6e7c9;");
			btn.setLayoutX(125);
			btn.setLayoutY(470);
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("To Printer!");
					PrinterJob job = PrinterJob.createPrinterJob();
					if(job != null){
						job.showPrintDialog(stage);
						job.printPage(vbox);
						job.endJob();
					}
				}
			});
			((Group) scene.getRoot()).getChildren().addAll(vbox,total,totalfreq,totaloccur,totalpercent,btn);

			stage.setScene(scene);
			stage.show();
			oReport = "A PopUp window is displayed showing a data table of the " + label.getText() + ".\nYou can click on the header of different column to arrange the data differently. \nDefault arragement: Arraged by the reverse order of occurrences. \nIf names with the occurrences are tied, they are sorted and presented in alphabetical order.";
		}
		 return oReport;
	 }


	/**
	 * Task 2 - Compute the result of the k-th popular name over the year in a Bar Chart.
	 * @param yearendstring String inputted for Ending Year
	 * @param yearstartstring String inputted for Starting Year
	 * @param kstring String inputted for Rank K
	 * @param gender String inputted for gender
	 * @return Console String and a PopUp window of a Bar Chart
	 * @author  Li Ho Yin
	 */
	public static String getBarChartT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		String oReport = "";
		if (checkinputvalid(yearstartstring,yearendstring, kstring, gender)) {
			int yearstart = Integer.parseInt(yearstartstring);
			int yearend = Integer.parseInt(yearendstring);
			int k = Integer.parseInt(kstring);
			SortedPeopleList result = generateOutput(yearstart, yearend, k, gender);
			if (result == null){
				return "Error";
			}

			Stage stage = new Stage();
			stage.setTitle("Bar Chart");
			final CategoryAxis xAxis = new CategoryAxis();
			final NumberAxis yAxis = new NumberAxis();
			final BarChart<String, Number> bc =
					new BarChart<String, Number>(xAxis, yAxis);
			bc.setTitle(ordinalConversion(k) + " Popular " + Gender(gender) + " Names between " + yearstartstring + " to " + yearendstring);
			xAxis.setLabel("Name");
			yAxis.setLabel("Occurrences");
			XYChart.Series series1 = new XYChart.Series();
			series1.setName(yearstartstring + " to " + yearendstring);

			final Label caption = new Label("");
			caption.setTextFill(Color.BLACK);
			caption.setStyle("-fx-font: 24 arial;");
			for (People ppl : result.sortedpeoplelist) {
				series1.getData().add(new XYChart.Data(ppl.name, ppl.occurrence));
			}

			//for better UI
			if (result.unipeople == 1){
				bc.setCategoryGap(300);
			} else if(result.unipeople == 2){
				bc.setCategoryGap(50);
			}

			Label reminder = new Label("*You can click on the corresponding bar \nand the exact value will be displayed.*");
			reminder.setTextFill(Color.GRAY);
			reminder.setStyle("-fx-font: 16 arial;");
			reminder.setTranslateX(20);
			reminder.setTranslateY(400);

			Group root = new Group();
			Button btn = new Button("Print/Export to PDF");
			btn.setStyle("-fx-font: 18 arial; -fx-base: #b6e7c9;");
			btn.setLayoutX(140);
			btn.setLayoutY(460);
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("To Printer!");
					PrinterJob job = PrinterJob.createPrinterJob();
					if(job != null){
						job.showPrintDialog(stage);
						job.printPage(root);
						job.endJob();
					}
				}
			});
			root.getChildren().addAll(bc,caption,reminder,btn);

			Scene scene = new Scene(root, 500, 500);

			bc.getData().addAll(series1);

			for (XYChart.Series<String,Number> serie: bc.getData()){
				for (XYChart.Data<String, Number> item: serie.getData()){
					item.getNode().setOnMousePressed((MouseEvent event) -> {
						//System.out.println(item.toString());
						caption.setTranslateX(event.getSceneX());
						caption.setTranslateY(event.getSceneY());
						caption.setText(String.valueOf(item.getYValue()));
					});
				}
			}

			stage.setScene(scene);
			stage.show();
			oReport = "A PopUp window is displayed showing a bar chart of the " + bc.getTitle() + ". \nEach name is associated with a bar representing the frequency of having hold the "+ ordinalConversion(k) +" rank. \nThe longer the bar, the more often the name has been ranked at "+ ordinalConversion(k)+".\nYou can click on the corresponding bar to display the exact value of the bar.";

		}
		return oReport;
	 }

	/**
	 * Task 2 - Compute the result of the k-th popular name over the year in a Pie Chart.
	 * @param yearendstring String inputted for Ending Year
	 * @param yearstartstring String inputted for Starting Year
	 * @param kstring String inputted for Rank K
	 * @param gender String inputted for gender
	 * @return Console String and a PopUp window of a Pie Chart
	 * @author  Li Ho Yin
	 */
	public static String getPieChartT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		String oReport = "";
		if (checkinputvalid(yearstartstring,yearendstring, kstring, gender)) {
			int yearstart = Integer.parseInt(yearstartstring);
			int yearend = Integer.parseInt(yearendstring);
			int k = Integer.parseInt(kstring);
			SortedPeopleList result = generateOutput(yearstart, yearend, k, gender);
			if (result == null){
				return "Error";
			}
			Stage stage = new Stage();
			Scene scene = new Scene(new Group());
			stage.setTitle("Pie Chart");
			stage.setWidth(500);
			stage.setHeight(550);
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
			for (People ppl : result.sortedpeoplelist) {
				pieChartData.add(new PieChart.Data(ppl.name,ppl.occurrence));
			}

			final PieChart chart = new PieChart(pieChartData);
			chart.setTitle(ordinalConversion(k) + " Popular " + Gender(gender) + " Names between " + yearstartstring + " to " + yearendstring);
			final Label caption = new Label("");
			caption.setTextFill(Color.BLACK);
			caption.setStyle("-fx-font: 24 arial;");

			for (final PieChart.Data data : chart.getData()) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
						new EventHandler<MouseEvent>() {
							@Override public void handle(MouseEvent e) {
								caption.setTranslateX(e.getSceneX());
								caption.setTranslateY(e.getSceneY());
								caption.setText(String.format("%.2f",(double)data.getPieValue()/result.totaloccurrence*100)
										+ "%");
							}
						});
			}
			Label reminder = new Label("*You can click on the corresponding slice \nand the value will be displayed.(Rounded down to 2 d.p.)*");
			reminder.setTextFill(Color.GRAY);
			reminder.setStyle("-fx-font: 16 arial;");
			reminder.setTranslateX(20);
			reminder.setTranslateY(400);

			Button btn = new Button("Print/Export to PDF");
			btn.setStyle("-fx-font: 18 arial; -fx-base: #b6e7c9;");
			btn.setLayoutX(137);
			btn.setLayoutY(470);
			btn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.out.println("To Printer!");
					PrinterJob job = PrinterJob.createPrinterJob();
					if(job != null){
						job.showPrintDialog(stage);
						job.printPage(scene.getRoot());
						job.endJob();
					}
				}
			});

			((Group) scene.getRoot()).getChildren().addAll(chart, caption,reminder,btn);
			stage.setScene(scene);
			stage.show();

			oReport = "A PopUp window is displayed showing a pie chart of the " + chart.getTitle() + ". \nEach name is shown as a sector in the chart representing the associated frequencty of having hold the "+ ordinalConversion(k) +" rank. \nThe bigger the sector, the more often the name has been ranked at "+ ordinalConversion(k)+".\nYou can click on the corresponding slice to display the value of the slice.";

		}
		 return oReport;
	 }

	/**
	 * Task 2 - Compute the result of the k-th popular name over the year in text form.
	 * @param yearendstring String inputted for Ending Year
	 * @param yearstartstring String inputted for Starting Year
	 * @param kstring String inputted for Rank K
	 * @param gender String inputted for gender
	 * @return Console String
	 * @author  Li Ho Yin
	 */
	 public static String getSummaryT2(String yearstartstring, String yearendstring, String kstring, String gender) {
		 String oReport = "";
		 if (checkinputvalid(yearstartstring,yearendstring, kstring, gender)) {
		 	int yearstart = Integer.parseInt(yearstartstring);
		 	int yearend = Integer.parseInt(yearendstring);
		 	int k = Integer.parseInt(kstring);
		 	SortedPeopleList result = generateOutput(yearstart,yearend,k,gender);
		 	if (result == null){
		 		return "Error";
			}
			People toppeople = result.sortedpeoplelist[0];
			oReport = toppeople.name + " has hold the " + ordinalConversion(k) + " rank most often for a total of " + toppeople.freq + " times among \nnames registered for baby " + Genderbaby(gender) + " born in the period from " + yearstartstring + " to " + yearendstring + ".\n";
			oReport += "The total number of occurrences of " + toppeople.name + " is " + toppeople.occurrence + ", which represents \n" + String.format("%.2f",(double)toppeople.occurrence/result.totaloccurrence * 100) + "% of total " + Gender(gender) + " births at the " + ordinalConversion(k) + " rank in the period from " + yearstartstring + " to " + yearendstring + ".";
			
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