package src.entities;

import javafx.scene.image.Image;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private String imgPath;

    public Person(String name, String username, String password,String imgPath) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = idCounter++;
        this.imgPath = imgPath;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = idCounter++;
        this.name =username;
    }

    public Person(String name, Gender genderEnum, String address, String phone, String email, String username, String password, LocalDate dob, String img) {
    this.name = name;
    this.gender = genderEnum;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.username = username;
    this.password = password;
    this.id = idCounter++;
    this.dateOfBirth = dob;
    this.imgPath = img;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getAge() {
        return age;
    }

}
