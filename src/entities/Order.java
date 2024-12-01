package src.entities;

import java.util.ArrayList;
import java.util.List;
import src.database.*;

public class Order {
    private final int id;
    private static int idCounter=1;
    private Customer customer;
    private ArrayList<Product>product = new ArrayList<>();
    private payMethod payMethod;
    private Status status;


    public Order(Customer customer , Order.payMethod payMethod) {
        this.payMethod = payMethod;
        this.customer = customer;
        this.status = Status.PENDING;
        this.id = idCounter++;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order.payMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Order.payMethod payMethod) {
        this.payMethod = payMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public enum Status{
        PENDING, CONFIRMED, DELIVERED
    }
    public enum payMethod{
        CARD, CASH, DIGITAL_WALLET
    }
}
