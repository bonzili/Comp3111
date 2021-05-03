package comp3111.popnames;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import org.apache.commons.csv.*;
import edu.duke.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import javafx.scene.control.Label;

public class Task1 {
	public static void doPieChart() {
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
	}
	public static int totalM(int year) {
		int totalBoys = 0;
		
		for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			}
			
		}
		return totalBoys;
	    
	}
	public static int totalF(int year) {
		int totalGirls = 0;
		
		for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (rec.get(1).equals("F")) {
				totalGirls += numBorn;
			}
			
		}
		return totalGirls;
	    
	}
	public static int getNumforName(int year, int rank, String gender) {
	 	boolean found = false;
	     int onum=0;
	     int currentRank = 0;
	     
	     // For every name entry in the CSV file
	     for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	currentRank++;
	            if (currentRank == rank) {
	             	found = true;
	             	onum = Integer.parseInt(rec.get(2));
	                break;
	            }
	         }
	     }     
	     if (found)
	     	return onum;
	     else
	     	return -1;
	 }
	/*for table */
	public static void TableT1M(int year,int topN, String gender) {
		TableView<Person> table = new TableView();
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setWidth(300);
        stage.setHeight(500);
        stage.setTitle("Table of Male");
        
        int rank = 1;
                
        List<Person> list=new ArrayList<>();
        for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	list.add(new Person(rec.get(0),Integer.parseInt(rec.get(2)),Double.parseDouble(rec.get(2))/totalM(year) ));
	         	rank+=1;
	         	if (rank > topN) {break;}
	         	}
        }
        ObservableList<Person> data =
                FXCollections.observableArrayList(list);
        table.setEditable(true);
        
        TableColumn firstNameCol = new TableColumn("Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Name"));
 
        TableColumn lastNameCol = new TableColumn("Occurrences");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Occurrences"));
 
        TableColumn emailCol = new TableColumn("Percentage");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Percentage"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
 
        
 
       
	}
	public static void TableT1F(int year,int topN, String gender) {
		TableView<Person> table = new TableView();
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setWidth(300);
        stage.setHeight(500);
        stage.setTitle("Table of Male");
        
        int rank = 1;
                
        List<Person> list=new ArrayList<>();
        for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	list.add(new Person(rec.get(0),Integer.parseInt(rec.get(2)),Double.parseDouble(rec.get(2))/totalF(year) ));
	         	rank+=1;
	         	if (rank > topN) {break;}
	         	}
        }
        ObservableList<Person> data =
                FXCollections.observableArrayList(list);
        table.setEditable(true);
        
        TableColumn firstNameCol = new TableColumn("Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Name"));
 
        TableColumn lastNameCol = new TableColumn("Occurrences");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Occurrences"));
 
        TableColumn emailCol = new TableColumn("Percentage");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Percentage"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
	}
	public static void BarT1M(int year, int topN, String gender) {
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setWidth(300);
        stage.setHeight(500);
        stage.setTitle("Table for Male");
        
        VBox root = new VBox(); 
        
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        BarChart bc = new BarChart(xAxis, yAxis);
        xAxis.setLabel("Name");
        yAxis.setLabel("Occurances");
        yAxis.setTickLabelRotation(90);
        
        int rank=1;
        XYChart.Series series = new XYChart.Series();
        for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	        	 XYChart.Data data = new XYChart.Data(rec.get(0),Integer.parseInt(rec.get(2)) );
	        	 data.setNode(new Label(rec.get(2)));
	        	 series.getData().add(data);
	         	rank+=1;
	         	if (rank > topN) {break;}
	         	}
       }
        
        bc.getData().addAll(series);
        root.getChildren().addAll(bc);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
	}
	public static void BarT1F(int year, int topN, String gender) {
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setWidth(300);
        stage.setHeight(500);
        stage.setTitle("Bar Chart for Female");
        
        VBox root = new VBox(); 
        
        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        BarChart bc = new BarChart(xAxis, yAxis);
        xAxis.setLabel("Name");
        yAxis.setLabel("Occurances");
        yAxis.setTickLabelRotation(90);
        
        int rank=1;
        XYChart.Series series = new XYChart.Series();
        for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	        	 XYChart.Data data = new XYChart.Data(rec.get(0),Integer.parseInt(rec.get(2)) );
	        	 data.setNode(new Label(rec.get(2)));
	        	 series.getData().add(data);
	         	rank+=1;
	         	if (rank > topN) {break;}
	         	}
       }
        
        bc.getData().addAll(series);
        root.getChildren().addAll(bc);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
	}
	public static void PieT1(int year, int topN, String gender) {
		Stage stage = new Stage();
		Scene scene = new Scene(new Group());
		stage.setWidth(700);
        stage.setHeight(500);
        stage.setTitle("Pie Chart for Male");
        
        int rank = 1;
        List<PieChart.Data> list=new ArrayList<>();
        for (CSVRecord rec : AnalyzeNames.getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	list.add(new PieChart.Data(rec.get(0), Integer.parseInt(rec.get(2))));
	         	rank+=1;
	         	if (rank > topN) {break;}
	         	}
       }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(list);
        final PieChart chart = new PieChart(pieChartData);

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
	}

}

