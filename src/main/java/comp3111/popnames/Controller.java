/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

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
    private TextArea textAreaConsole;
    
    @FXML
    private ComboBox<String> comboboxGender;
 
    
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
    	String oReport = Task2.getSummaryT2(yearstartstring, yearendstring, rankstring, gender);
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
    	String oReport = Task2.getDataTableT2(yearstartstring, yearendstring, rankstring, gender);
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
    	String oReport = Task2.getBarChartT2(yearstartstring, yearendstring, rankstring, gender);
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
    	String oReport = Task2.getPieChartT2(yearstartstring, yearendstring, rankstring, gender);
    	textAreaConsole.setText(oReport);
        textAreaConsole.setEditable(false);
    }
}

