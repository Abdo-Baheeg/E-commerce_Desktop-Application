package source.people;

import java.util.Date;
import java.util.Calendar;

public abstract class Person {
    private String name;
    private String username;
    private String password;
    private int age;
    private Date dateOfBirth;
    public enum Gender {
        MALE, FEMALE
    }
    private Gender gender;
    private String address;
    private String phone;
    private String email;
    public Person(String name, int age, Gender gender, String address, String phone, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge() {
        Date today = new Date();

        // Convert both dates to Calendar objects
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(this.dateOfBirth);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(today);

        // Calculate age
        this.age = todayCal.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);

        // Adjust if the birthday hasn't occurred yet this year
        if (todayCal.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
    }
}
