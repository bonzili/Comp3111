/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;
    
    @FXML
    private TextField textfieldYearStart;
    
    @FXML
    private TextField textfieldYearEnd;
    
    @FXML
    private TextField textfieldK;

    @FXML
    private TextField textfieldGender;
    
    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;

    @FXML
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    public TextArea textAreaConsole;
    
    @FXML
    private ComboBox<String> comboboxGender;

    @FXML
    private Button help_buttonT2;

    @FXML
    private Button buttonSummaryT2;

    @FXML
    private Button buttonDataTableT2;

    @FXML
    private Button buttonBarChartT2;

    @FXML
    private Button buttonPieChartT2;

    @FXML
    private Button help_buttonT5;

    @FXML
    private Button help_buttonT5X1;

    @FXML
    private Button help_buttonT5X2;

    @FXML
    private Button buttonFindT5;

    @FXML
    private TextField textfieldNameT5;

    @FXML
    private TextField textfieldYearT5;

    @FXML
    private ComboBox<String> comboboxGenderT5;

    @FXML
    private ComboBox<String> comboboxPreferredGenderT5;

    @FXML
    public ToggleGroup age;

    @FXML
    public ToggleGroup algo;

    @FXML
    public RadioButton T5X2;

    @FXML
    public RadioButton T5X1;

    @FXML
    public RadioButton Younger;

    @FXML
    public RadioButton Older;

    @FXML
    public TextField textfieldNameItitialT5;

    @FXML
    private Slider consolefontslider;


    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }


    void Emptywarning(String error){
        JOptionPane.showMessageDialog(null,
                "Please enter a valid " + error + "! It is empty right now.",
                "Input Error",
                JOptionPane.WARNING_MESSAGE);
    }

    boolean checkEmptyT2(){
        boolean empty = false;
        if (textfieldYearStart.getText().equals("")){
            Emptywarning("Starting Year");
            empty = true;
        }
        if (textfieldYearEnd.getText().equals("")){
            Emptywarning("Ending Year");
            empty = true;
        }
        if (textfieldK.getText().equals("")){
            Emptywarning("Rank K");
            empty = true;
        }
        return empty;
    }
    /**
     *  Task Two
     *  
     *  
     */
    @FXML
    void doSummaryT2() {
    	String yearstartstring = textfieldYearStart.getText();
    	String yearendstring = textfieldYearEnd.getText();
    	String rankstring = textfieldK.getText();
    	String gender = comboboxGender.getValue();
    	String oReport = "";
    	if (!checkEmptyT2()){
    	    oReport = Task2.getSummaryT2(yearstartstring, yearendstring, rankstring, gender);
        }
    	textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }
    
    /**
     *  Task Two
     *  
     *  
     */
    @FXML
    void doDataTableT2() {
    	String yearstartstring = textfieldYearStart.getText();
    	String yearendstring = textfieldYearEnd.getText();
    	String rankstring = textfieldK.getText();
        String gender = comboboxGender.getValue();
        String oReport = "";
        if (!checkEmptyT2()){
            oReport = Task2.getDataTableT2(yearstartstring, yearendstring, rankstring, gender);
        }
    	textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }
    
    /**
     *  Task Two
     *  
     *  
     */
    @FXML
    void doBarChartT2() {
    	String yearstartstring = textfieldYearStart.getText();
    	String yearendstring = textfieldYearEnd.getText();
    	String rankstring = textfieldK.getText();
        String gender = comboboxGender.getValue();
        String oReport = "";
        if (!checkEmptyT2()){
            oReport = Task2.getBarChartT2(yearstartstring, yearendstring, rankstring, gender);
        }
    	textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }
    
    /**
     *  Task Two
     *  
     *  
     */
    @FXML
    void doPieChartT2() {
    	String yearstartstring = textfieldYearStart.getText();
    	String yearendstring = textfieldYearEnd.getText();
    	String rankstring = textfieldK.getText();
        String gender = comboboxGender.getValue();
        String oReport = "";
        if (!checkEmptyT2()){
            oReport = Task2.getPieChartT2(yearstartstring, yearendstring, rankstring, gender);
        }
    	textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }

    /**
     *  Task Two
     *
     *
     */
    @FXML
    void displayHelpT2() {
        JOptionPane.showMessageDialog(null, "Generate a report in response to the queries on the K-th popular names over a given period.","Help Message for T2",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *  Task Five
     *
     *
     */
    @FXML
    void displayHelpT5() {
        JOptionPane.showMessageDialog(null, "Seeking advices on identifying the name of a person who would become your soulmate? \n Enter the following information and let's find your soulmate!","Help Message for T5",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *  Task Five
     *
     *
     */
    @FXML
    void displayHelpT5X1() {
        JOptionPane.showMessageDialog(null, "An algorithm that compute the name of your potential soulmate which is equal to \n the most popular name in your preferred gender in your year of birth.","Help Message for Algorithm T5X1",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *  Task Five
     *
     *
     */
    @FXML
    void displayHelpT5X2() {
        JOptionPane.showMessageDialog(null, "An algorithm that compute the name of your potential soulmate which is equal to \n most popular name in your preferred gender with your preferred initial (or the same initial as your initial if it is empty) \n from 1880 to your year of birth or from your year of birth to 2019 (determined by your preferred age).","Help Message for Algorithm T5X2",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *  Task Five
     *
     *
     */
    @FXML
    void doFindT5() {
        String name = textfieldNameT5.getText();
        String year = textfieldYearT5.getText();
        String gender = comboboxGenderT5.getValue();
        String preferredGender = comboboxPreferredGenderT5.getValue();
        String preferredInitial = textfieldNameItitialT5.getText();
        String preferredAge = "";
        String Algo = "";
        String oReport = "";
        if (Older.isSelected()){
            preferredAge = "Older";
        }else if (Younger.isSelected()){
            preferredAge = "Younger";
        }
        if (T5X1.isSelected()){
            Algo = "T5X1";
        }else if (T5X2.isSelected()){
            Algo = "T5X2";
        }
        boolean empty = false;
        if (name.equals("")){
            Emptywarning("Name");
            empty = true;
        }
        if (year.equals("")){
            Emptywarning("Year of Birth");
            empty = true;
        }
        if (preferredAge.equals("")){
            Emptywarning("Preferred Age");
            empty = true;
        }
        if (Algo.equals("")){
            Emptywarning("Algorithm");
            empty = true;
        }
        if (!name.equals("")){
            name = name.toLowerCase();
            StringBuilder nameString = new StringBuilder(name);
            nameString.setCharAt(0, Character.toUpperCase(name.charAt(0)));
            name = nameString.toString();
        }
        if (preferredInitial.equals("")){
            if (!name.equals("")) {
                preferredInitial = String.valueOf(name.charAt(0));
            }
        }else{
            preferredInitial = preferredInitial.toUpperCase();
        }
        if (!empty){
            if (Algo.equals("T5X1")) {
                oReport = Task5.doFindT5X1(name, year, gender, preferredInitial, preferredGender, preferredAge);
            }else if (Algo.equals("T5X2")){
                oReport = Task5.doFindT5X2(name, year, gender, preferredInitial, preferredGender, preferredAge);
            }
        }
        //System.out.println(Older.isSelected());
        textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }

    @FXML
    void doConsolefontChanged(){
        int size = (int) consolefontslider.getValue();
        String style = "-fx-font-size: " + size;
        textAreaConsole.setStyle(style);
    }


}

