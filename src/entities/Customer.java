package src.entities;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Person {
    private double balance = 5000;
    private Cart cart;
    private ArrayList<Order> orders=new ArrayList<>();
    private ArrayList<Product> interests = new ArrayList<>();

    public Customer(String name, Gender genderEnum, String address, String phone, String email, String username, String password, LocalDate dob, Image img) {
        super( name,  genderEnum,  address,  phone, email,username,password,dob);
        cart = new Cart();
        this.cart=new Cart();
        this.balance=1000;
        this.orders=new ArrayList<Order>();
    }

    public Customer(String user, String pass) {
        super(user, pass);
    }


    public double getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public ArrayList<Product> getInterests() {
        return interests;
    }
    public void setInterests(ArrayList<Product> interests) {
        this.interests = interests;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrder(Order order) {
        this.orders.add(order);
    }
    public void deleteOrder(Order order) {
        orders.remove(order);
    }
    public Cart getCart() {
        return cart;
    }
}
