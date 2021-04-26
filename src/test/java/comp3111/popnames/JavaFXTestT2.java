package comp3111.popnames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaFXTestT2 extends ApplicationTest {

	private Scene s;
	private TextArea t;
	private TextField yearstart;
	private TextField yearend;
	private TextField k;

	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Popular Names");
   		stage.show();
   		s = scene;
		t = (TextArea)s.lookup("#textAreaConsole");
		yearstart = (TextField) s.lookup("#textfieldYearStart");
		yearend = (TextField) s.lookup("#textfieldYearEnd");
		k = (TextField) s.lookup("#textfieldK");
	}

	@Test
	public void testTextAreaUpdateSummaryT2() {
		clickOn("#tabReport2");
		yearstart.setText("1960");
		yearend.setText("1980");
		k.setText("1");
		clickOn("#buttonSummaryT2");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonSummaryT2");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateSummaryT2Invalid() {
		clickOn("#tabReport2");
		yearstart.setText("1960");
		yearend.setText("1980");
		k.setText("1");
		clickOn("#buttonSummaryT2");
		sleep(500);
		String s1 = t.getText();
		k.setText("2");
		clickOn("#buttonSummaryT2");
		sleep(500);
		String s2 = t.getText();
		assertFalse(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateDataTableT2() {
		clickOn("#tabReport2");
		yearstart.setText("1900");
		yearend.setText("1980");
		k.setText("1");
		clickOn("#buttonDataTableT2");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonDataTableT2");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateBarChartT2() {
		clickOn("#tabReport2");
		yearstart.setText("1900");
		yearend.setText("1980");
		k.setText("1");
		clickOn("#buttonBarChartT2");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonBarChartT2");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdatePieChartT2() {
		clickOn("#tabReport2");
		yearstart.setText("1900");
		yearend.setText("1980");
		k.setText("1");
		clickOn("#buttonPieChartT2");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonPieChartT2");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

}
