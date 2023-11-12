package BusBooking.code;

import java.util.ArrayList;

import BusBooking.sqlqueries.driverQueries;

public class driver extends passenger {
    private int salary;
    private int experience;
    static ArrayList<driver> driverBook = new ArrayList<>();

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public driver(String name, int age, String gender, String mobileNo, int salary, int experience) {
        super(name, age, gender, mobileNo);
        this.salary = salary;
        this.experience = experience;
    }

    public static void AddDriver(String line) {
        String values[] = line.split(",");
        driver d = new driver(values[0], Integer.valueOf(values[1]), values[2], values[3], Integer.valueOf(values[4]),
                Integer.valueOf(values[5]));
        driverBook.add(d);
        driverQueries.insertion(d);
    }

    public static void PrintDriverBook() {
        driverBook.stream().filter(x -> x != null).forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("name: %s,age: %d,gender: %s,mobile no: %s,salary: %d,experience: %s", this.getName(),
                this.getAge(), this.getGender(), this.getMobileNo(), this.getSalary(), this.getExperience());
    }
}
