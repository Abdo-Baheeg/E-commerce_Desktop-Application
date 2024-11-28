package src.entities;

import java.util.List;
import src.database.*;

public class Order {
    private int index;
    private Customer customer;
    private payMethod payMethod;
    private Status status;
    private static int numberOfOrders=0;

    public Order(Customer customer , Order.payMethod payMethod) {
        this.payMethod = payMethod;
        this.customer = customer;
        this.status = Status.PENDING;
        this.index = numberOfOrders+1;
        numberOfOrders++;
    }

    public static int getNumberOfOrders() {
        return numberOfOrders;
    }


    public static void setNumberOfOrders(int numberOfOrders) {
        Order.numberOfOrders = numberOfOrders;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Product> getProductList() {
        return Database.products;
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

    public enum Status{
        PENDING, CONFIRMED, DELIVERED
    }
    public enum payMethod{
        CARD, CASH, DIGITAL_WALLET
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public boolean isValid(Order order) {
        return order != null ;
    }
}
