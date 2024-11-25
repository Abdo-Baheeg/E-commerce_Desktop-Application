package source.people;

import java.util.ArrayList;

public class Customer extends Person {
    private float balance;
    protected static int numberOfUsers=0;
    public Customer(String name, int age, Gender gender, String address, String phone, String email){
        super(name, age, gender, address, phone, email);
        numberOfUsers++ ;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public int getNumberOfUsers() {
        return numberOfUsers;
    }
    public void showAllUsers() {
        for (int i = 0; i < numberOfUsers; i++) {
        System.out.println("Name: " + getName());}
    }
}
