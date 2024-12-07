package src.entities;

import java.io.Serializable;

public class Product implements Serializable {
    private final int id;
    private static int idCounter=1;
    private String name;
    private String description;
    private float price;
    private int stock;
    private Category category;
    private int soldItems=0;

    public Product(String name, String description, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = idCounter++;
    }

    public Product(String name, String description, float price, int stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.id = idCounter++;
    }

    public Product(String productName, float price, int stock, Category category) {
        this.name = productName;
        this.price = price;
        this.stock = stock;
        this.id = idCounter++;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category.setName(category);
    }
    public String getCategory() {
        return category.toString();
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }


    public int getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(int soldItems) {
        this.soldItems = soldItems;
    }
}