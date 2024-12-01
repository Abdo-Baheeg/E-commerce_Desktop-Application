package src.entities;

import java.util.ArrayList;

public class Customer extends Person {
    private float balance;
    private ArrayList<Category> interests = new ArrayList<>();


    public Customer(String name, int age, Gender gender, String address, String phone, String email){
        super(name, age, gender, address, phone, email);
    }

    public Customer(String username, String password, String dateOfBirth, double balance, String address, String gender) {
        super();
    }

    public Customer(String username, String password) {
        super(username , password);
    }

    public Customer(String s) {

    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public ArrayList<Category> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Category> interests) {
        this.interests = interests;
    }
}
