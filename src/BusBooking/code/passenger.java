package BusBooking.code;

import java.util.HashMap;

public class passenger {
    private String name;
    private int age;
    private String gender;
    private String mobileNo;
    static HashMap<String,passenger> passengerBook = new HashMap<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public passenger(String name, int age, String gender, String mobileNo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobileNo = mobileNo;
    }

    public int duplicateFind(passenger p){
        if(passengerBook.containsKey(p.mobileNo)){
            passengerBook.put(p.mobileNo, p);
            return 1;
        } else {
            passengerBook.put(p.mobileNo, p);
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "  Age: " + age + "  Gender: " + gender + "  MobileNo: " + mobileNo;
    }

}
