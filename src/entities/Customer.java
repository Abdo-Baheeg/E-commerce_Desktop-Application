package src.entities;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {
    private float balance;
    private Cart cart;
    private ArrayList<Order> orders=new ArrayList<>();
    private ArrayList<Product> interests = new ArrayList<>();

    public Customer(String name, Gender genderEnum, String address, String phone, String email, String username, String password, Date dob) {
        super( name,  genderEnum,  address,  phone, email,username,password,dob);
    }

    public float getBalance() {
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
    public void setCart(Cart cart) {
        this.cart = cart;
    }


}
