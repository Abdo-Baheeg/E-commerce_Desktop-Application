package src.entities;

import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private payMethod payMethod;
    private String status;

    public Order(Order.payMethod payMethod, String status) {
        this.payMethod = payMethod;
        this.status = status;
    }


    public Object getStatus() {
        return null;
    }

    public void setStatus(String canceled) {
    }

    public List<Product> getProductList() {
        return null;
    }

    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
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
}
