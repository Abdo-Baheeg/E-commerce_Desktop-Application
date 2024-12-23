package src.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private int id;
    private static int idCounter=0;
    private ArrayList<CartItem> products;
    private float totalPrice;

    public Cart() {
        this.products = new ArrayList<>();
        this.id = idCounter++;
        this.totalPrice = 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CartItem> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<CartItem> products) {
        this.products = products;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

}
