package src.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public abstract class Person implements Serializable {
    private int id;
    private static int idCounter = 1;
    private String name;
    private String username;
    private String password;
    private Gender gender;
    private String address;
    private String phone;
    private String email;
    private int age;
    private Date dateOfBirth;

    public Person(String name, String username, String password) {
        this.password = password;
        this.id = idCounter++;
        this.name =name;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = idCounter++;
        this.name =username;
    }

    public Person(String name, Gender genderEnum, String address, String phone, String email, String username, String password , Date dateOfBirth) {
    this.name = name;
    this.gender = genderEnum;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.username = username;
    this.password = password;
    this.id = idCounter++;
    this.dateOfBirth = dateOfBirth;
    this.setAge();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE
    }
    public Person(String name, int age, Gender gender, String address, String phone, String username, String password) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.address = address;
    this.phone = phone;
    this.username = username;
    this.password = password;
    this.id = idCounter;
    idCounter++;
    }
    public Person(String name, int age, Gender gender, String address, String phone, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = idCounter;
        idCounter++;
    }

    public Person() {
        this.id = idCounter;
        idCounter++;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public int getID() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
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

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getAge() {
        return age;
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
