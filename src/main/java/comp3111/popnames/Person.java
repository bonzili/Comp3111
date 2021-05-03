package comp3111.popnames;

public class Person {
	public String Name;
    public int Occurrences;
    public double Percentage;
    
    public Person(String fName, int lName, double email) {
        this.Name = fName;
        this.Occurrences = lName;
        this.Percentage = email;
    }
    public String getName() {
        return this.Name;
    }


    public int getOccurrences() {
        return this.Occurrences;
    }
    public double getPercentage() {
        return this.Percentage;
    }
}
