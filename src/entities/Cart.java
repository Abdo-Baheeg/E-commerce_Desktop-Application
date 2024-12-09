package src.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private int id;
    private static int idCounter=0;
    private ArrayList<Product> products;
    private float totalPrice;
    public Cart() {}
    public Cart(Product product) {
        this.products.add(product);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public void addProduct(Product product) {
        this.products.add(product);
    }

}
