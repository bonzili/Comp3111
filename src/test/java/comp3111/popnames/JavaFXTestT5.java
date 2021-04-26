package comp3111.popnames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaFXTestT5 extends ApplicationTest {

	private Scene s;
	private TextArea t;
	private TextField name;
	private TextField year;
	private TextField initial;
	private RadioButton T5X2;
	private RadioButton T5X1;
	private RadioButton age_younger;
	private RadioButton age_older;

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

		name = (TextField) s.lookup("#textfieldNameT5");
		year = (TextField) s.lookup("#textfieldYearT5");
		initial = (TextField) s.lookup("#textfieldNameItitialT5");
		T5X2 = (RadioButton) s.lookup("#T5X2");
		T5X1 = (RadioButton) s.lookup("#T5X1");
		age_older = (RadioButton) s.lookup("#Older");
		age_younger = (RadioButton) s.lookup("#Younger");

	}

	@Test
	public void testTextAreaUpdateT5X1T2withemptyinitial() {
		clickOn("#tabApp2");
		name.setText("Peter");
		year.setText("2000");
		initial.setText("");
		T5X1.setSelected(true);
		T5X2.setSelected(false);
		age_older.setSelected(true);
		age_younger.setSelected(false);
		clickOn("#buttonFindT5");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonFindT5");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateT5X1() {
		clickOn("#tabApp2");
		name.setText("Peter");
		year.setText("2000");
		initial.setText("A");
		T5X1.setSelected(true);
		T5X2.setSelected(false);
		age_older.setSelected(true);
		age_younger.setSelected(false);
		clickOn("#buttonFindT5");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonFindT5");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateT5X2T2withemptyinitial() {
		clickOn("#tabApp2");
		name.setText("Peter");
		year.setText("2000");
		initial.setText("");
		T5X1.setSelected(false);
		T5X2.setSelected(true);
		age_older.setSelected(true);
		age_younger.setSelected(false);
		clickOn("#buttonFindT5");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonFindT5");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

	@Test
	public void testTextAreaUpdateT5X2() {
		clickOn("#tabApp2");
		name.setText("Peter");
		year.setText("2000");
		initial.setText("A");
		T5X1.setSelected(false);
		T5X2.setSelected(true);
		age_older.setSelected(true);
		age_younger.setSelected(false);
		clickOn("#buttonFindT5");
		sleep(500);
		String s1 = t.getText();
		clickOn("#buttonFindT5");
		sleep(500);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}

}
