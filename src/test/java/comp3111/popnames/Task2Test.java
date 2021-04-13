package comp3111.popnames;

import org.junit.Test;
import static org.junit.Assert.*;

public class Task2Test {
	
    @Test 
    public void testYearStartMin1() {
    	Task2 a = new Task2();
    	String output = a.getSummaryT2("1880","1890","1","M");
		assertTrue(output.equals("Success"));
    }
    
    @Test 
    public void testYearStartMin2() {
    	Task2 a = new Task2();
    	String output = a.getSummaryT2("1879","1880","1","M");
		assertTrue(output.equals("Error with input range"));
    }
    
    @Test 
    public void testYearStartType() {
    	Task2 a = new Task2();
    	String output = a.getSummaryT2("200k","1880","1","M");
		assertTrue(output.equals("Type Error"));
    }


}
