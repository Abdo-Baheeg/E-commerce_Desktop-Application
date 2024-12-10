package src.entities;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class Category implements Serializable {
    private  String description;
    private String name;
    private final int id;
    private static int idCounter=0;
    private ArrayList<Product> products;

    public Category(String name,String description) {
        this.name = name;
        this.id = idCounter++;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}



