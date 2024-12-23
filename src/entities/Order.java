package src.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {
    private final int id;
    private static int idCounter=1;
    private ArrayList<CartItem> Items = new ArrayList<>();
    private payMethod payMethod;
    private Status status;
    private float totalPrice;
    private LocalDate date;


    public Order() {
        this.id = idCounter++;
        this.totalPrice = 0.0f;
        this.Items = new ArrayList<>();
        this.status=Status.PENDING;
    }
    public Order(ArrayList<CartItem> Items) {
        this.Items = Items;
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

    public ArrayList<CartItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.Items = items;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
