package src.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private final int id;
    private static int idCounter=1;
    private ArrayList<Product> products = new ArrayList<>();
    private payMethod payMethod;
    private Status status;
    private float totalPrice;


    public Order() {
        this.id = idCounter++;
        this.totalPrice = 0.0f;
        this.products = new ArrayList<>();
        this.status=Status.PENDING;
    }
    public Order(ArrayList<Product> products) {
        this.products = products;
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

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public enum Status{
        PENDING, CONFIRMED, DELIVERED
    }


    public enum payMethod{
        card, cash, wallet
    }
    public boolean card(String cardNumber, String expireDate,int cvv){
        this.payMethod = payMethod.card;
        return true;
    }
    public boolean wallet(String number, String password){
        this.payMethod = payMethod.wallet;
        return true;
    }

}
