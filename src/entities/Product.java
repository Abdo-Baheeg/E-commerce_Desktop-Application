package src.entities;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Product implements Serializable {
    private final int id;
    private static int idCounter=1;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Category category;
    private int soldItems=0;
    private Image img = new Image("C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg");

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = idCounter++;
    }
    public Product(String name, String description, double price, int stock , Category category, String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.id = idCounter++;
        this.img = new Image(url);
    }

    public Product(String name, String description, double price, int stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.id = idCounter++;
    }

    public Product(String productName, double price, int stock, Category category) {
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

    public double getPrice() {
        return price;
    }
    public String getPriceString() {
        return String.format("$%.2f", price);
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}