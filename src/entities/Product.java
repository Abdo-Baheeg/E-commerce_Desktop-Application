package src.entities;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.nio.file.Path;

public class Product implements Serializable {
    private final int id;
    private static int idCounter=1;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private Category category;

    public Product(String name, String description, float price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.id = idCounter++;
    }

    public Product(String name, String description, float price, int quantity, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.id = idCounter++;
    }

    public Product(String productName, float price, int quantity, Category category) {
        this.name = productName;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }


}