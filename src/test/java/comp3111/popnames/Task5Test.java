package comp3111.popnames;

import org.junit.Test;

import static org.junit.Assert.*;


public class Task5Test {

    @Test
    public void checkinputvalid() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Mary","1880","F");
        assertTrue(output);
    }

    @Test
    public void checkinputvalid_boundaryyear() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Peter","2019","F");
        assertTrue(output);
    }

    @Test
    public void checkinputvalid_boundaryk2() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Harry","1879","M");
        assertFalse(output);

    }

    @Test
    public void checkinputvalid_invalidinitial() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Mary","1880","CC");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_invalidinitaltype() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Mary","1880","4");
        assertFalse(output);
    }

    @Test
    public void checkinputvalid_nametype() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Mar2y","1880","M");
        assertFalse(output);
    }
    @Test
    public void checkinputvalid_name() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("bOnZi","1980","F");
        assertTrue(output);
    }

    @Test
    public void checkinputvalid_yeartype() {
        Task5 a = new Task5();
        Boolean output = a.checkinputvalid("Mary","188a0","F");
        assertFalse(output);
    }

    @Test
    public void getMostPopularPersonByInitial() {
        Task5 a = new Task5();
        People output = a.getMostPopularPersonByInitial(1905,"F","H");
        assertEquals(output.name,"Helen");
        assertEquals(output.occurrence,6811);
    }




    @Test
    public void generateOutput() {
        Task5 a = new Task5();
        SortedPeopleList output = a.generateOutput(1923, 1923, "F", "B");
        assertEquals(output.sortedpeoplelist[0].name, "Betty");
        assertEquals(output.sortedpeoplelist[0].occurrence, 25990);
        assertEquals(output.unipeople, 1);
        assertEquals(output.totalfreq, 1);
    }

    @Test
    public void generateOutput2() {
        Task5 a = new Task5();
        SortedPeopleList output = a.generateOutput(1923, 1924, "F", "M");
        assertEquals(output.sortedpeoplelist[0].name, "Mary");
        assertEquals(output.sortedpeoplelist[0].occurrence, 145161);
        assertEquals(output.unipeople, 1);
        assertEquals(output.totalfreq, 2);
    }

}
