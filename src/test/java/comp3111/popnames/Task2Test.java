package comp3111.popnames;

import javafx.scene.input.KeyCode;
import org.junit.Test;
import javax.swing.*;
import static org.junit.Assert.*;




public class Task2Test {

    @Test
    public void testordinalConversion1() {
        Task2 a = new Task2();
        String output = a.ordinalConversion(1);
        assertTrue(output.equals("1-st"));
    }

    @Test
    public void testordinalConversion2() {
        Task2 a = new Task2();
        String output = a.ordinalConversion(2);
        assertTrue(output.equals("2-nd"));
    }

    @Test
    public void testordinalConversion3() {
        Task2 a = new Task2();
        String output = a.ordinalConversion(3);
        assertTrue(output.equals("3-rd"));
    }

    @Test
    public void testordinalConversionbiggerthan3() {
        Task2 a = new Task2();
        String output = a.ordinalConversion(55);
        assertTrue(output.equals("55-th"));
    }

    @Test
    public void testordinalConversioninvalid() {
        Task2 a = new Task2();
        String output = a.ordinalConversion(0);
        assertTrue(output == null);
    }

    @Test
    public void testordinalConversionGenderM() {
        Task2 a = new Task2();
        String output = a.Gender("M");
        assertTrue(output.equals("Male"));
    }

    @Test
    public void testordinalConversionGenderF() {
        Task2 a = new Task2();
        String output = a.Gender("F");
        assertTrue(output.equals("Female"));
    }

    @Test
    public void testordinalConversionGenderinvalid() {
        Task2 a = new Task2();
        String output = a.Gender("C");
        assertTrue(output == null);
    }


    @Test
    public void testordinalConversionGenderbabyM() {
        Task2 a = new Task2();
        String output = a.Genderbaby("M");
        assertTrue(output.equals("boys"));
    }

    @Test
    public void testordinalConversionGenderbabyF() {
        Task2 a = new Task2();
        String output = a.Genderbaby("F");
        assertTrue(output.equals("girls"));
    }

    @Test
    public void testordinalConversionGenderbabyinvalid() {
        Task2 a = new Task2();
        String output = a.Genderbaby("Q");
        assertTrue(output == null);
    }

    @Test
    public void checkinputvalid() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","1890","5","M");
        assertTrue(output);
    }

    @Test
    public void checkinputvalid_boundaryk() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","2000","1000","M");
        assertTrue(output);
    }

    @Test
    public void checkinputvalid_boundaryk2() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","1890","0","F");
        //JOptionPane.OK_OPTION
        assertFalse(output);

    }

    @Test
    public void checkinputvalid_invalidyearstart() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("18w80","1890","3","F");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_invalidyearend() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","q00","2","F");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_invalidk() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","1890","C","F");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_outofrange_yearstart() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1234","1890","2","F");
        assertFalse(output);
    }
    @Test
    public void checkinputvalid_outofrange_yearend() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1880","2022","1","F");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_yearendsmallerthanyearstart() {
        Task2 a = new Task2();
        Boolean output = a.checkinputvalid("1990","1989","12","M");
        assertFalse(output);
    }

    @Test
    public void checkgetPeopleFemale() {
        Task2 a = new Task2();
        People output = a.getPeople(2000,1,"F");
        assertEquals(output.name,"Emily");
        assertEquals(output.occurrence,25952);
        assertEquals(output.rank,1);
    }

    @Test
    public void checkgetPeopleMale() {
        Task2 a = new Task2();
        People output = a.getPeople(1880,5,"M");
        assertEquals(output.name,"George");
        assertEquals(output.occurrence,5126);
        assertEquals(output.rank,5);
    }

    @Test
    public void checkgetPeoplecantfind() {
        Task2 a = new Task2();
        People output = a.getPeople(1880,950,"F");
        assertTrue(output == null);
    }

    @Test
    public void sortPeople_arrangebyOccurrence() {
        Task2 a = new Task2();
        People[] test = new People[5];
        test[0] = new People("A",1,100);
        test[1] = new People("B",1,108);
        test[2] = new People("C",1,102);
        test[3] = new People("D",1,120);
        test[4] = new People("E",1,101);
        SortedPeopleList output = a.sortPeople(test);
        assertEquals(output.sortedpeoplelist[0].name,"D");
        assertEquals(output.sortedpeoplelist[1].name,"B");
        assertEquals(output.sortedpeoplelist[2].name,"C");
        assertEquals(output.sortedpeoplelist[3].name,"E");
        assertEquals(output.sortedpeoplelist[4].name,"A");
    }

    @Test
    public void sortPeople_arrangebyOccurrenceandfollowedbyname() {
        Task2 a = new Task2();
        People[] test = new People[5];
        test[0] = new People("A",1,100);
        test[1] = new People("B",1,108);
        test[2] = new People("E",1,102);
        test[3] = new People("D",1,120);
        test[4] = new People("C",1,102);
        SortedPeopleList output = a.sortPeople(test);
        assertEquals(output.sortedpeoplelist[0].name,"D");
        assertEquals(output.sortedpeoplelist[1].name,"B");
        assertEquals(output.sortedpeoplelist[2].name,"C");
        assertEquals(output.sortedpeoplelist[3].name,"E");
        assertEquals(output.sortedpeoplelist[4].name,"A");
    }

    @Test
    public void sortPeople_outputprop() {
        Task2 a = new Task2();
        People[] test = new People[5];
        test[0] = new People("D",1,100);
        test[1] = new People("B",1,108);
        test[2] = new People("E",1,102);
        test[3] = new People("D",1,120);
        test[4] = new People("E",1,101);
        SortedPeopleList output = a.sortPeople(test);
        assertEquals(output.totaloccurrence,531);
        assertEquals(output.unipeople,3);
        assertEquals(output.totalfreq,5);
    }

    @Test
    public void generateOutput() {
        Task2 a = new Task2();
        SortedPeopleList output = a.generateOutput(2000, 2000, 10, "F");
        assertEquals(output.sortedpeoplelist[0].name, "Taylor");
        assertEquals(output.sortedpeoplelist[0].occurrence, 15078);
        assertEquals(output.unipeople, 1);
        assertEquals(output.totalfreq, 1);
    }

    @Test
    public void generateOutput2() {
        Task2 a = new Task2();
        SortedPeopleList output = a.generateOutput(1960, 1970, 1, "M");
        assertEquals(output.sortedpeoplelist[0].name, "Michael");
        assertEquals(output.sortedpeoplelist[0].occurrence, 834502);
        assertEquals(output.sortedpeoplelist[1].name, "David");
        assertEquals(output.sortedpeoplelist[1].occurrence, 85931);
        assertEquals(output.unipeople, 2);
        assertEquals(output.totalfreq, 11);
    }

    @Test
    public void generateOutputCannotfind() {
        Task2 a = new Task2();
        SortedPeopleList output = a.generateOutput(1880, 1880, 970, "F");
        assertEquals(output,null);
    }


    /*
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
*/

}
