package src.entities;
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String image;
    private Category category;
    private  int soldItems = 0;
    public Product(String name ,String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String name, String description, double price, int quantity, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(int id, String productName, double price, int quantity, Category category) {
        this.id = id;
        this.name = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setCategory(String category) {
        this.category.name= category;
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
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCategory() {
        return category.toString();
    }
    public void setCategory() {
        this.category = category;
    }

    public int getSoldItems() {
        return soldItems;
    }

    public void setSoldItems(int soldItems) {
        this.soldItems = soldItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
