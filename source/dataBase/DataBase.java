package source.dataBase;
import source.Products.Category;
import source.Products.Product;
import source.people.Admin;
import source.people.Customer;
import source.people.Person;

import java.util.ArrayList;

import  source.Products.Category.Categories;

public class DataBase {
    static ArrayList<Customer> Users = new ArrayList<>();
    static ArrayList<Admin> Admins = new ArrayList<>();
    static ArrayList<Product> Products = new ArrayList<>();
    static ArrayList<Product> AvailableProducts = new ArrayList<>();
    static ArrayList<Product> Cart = new ArrayList<>();
    static ArrayList<Product> Order = new ArrayList<>();
    public static ArrayList<Category> Categories = new ArrayList<>();

    public void initialize() {
        // Add dummy admins
        Admins.add(new Admin( "Admin1", 45, Person.Gender.MALE,"","","admin1@example.com", "adminpass1"));
        Admins.add(new Admin( "Admin2", 32, Person.Gender.FEMALE,"","","admin2@example.com", "adminpass2"));

        // Add categories
        Categories.add(new Category(1, "Electronics"));
        Categories.add(new Category(2, "Books"));
        Categories.add(new Category(3, "Clothing"));
        Categories.add(new Category(4, "Home Appliances"));
        Categories.add(new Category(5,"FOOD_AND_BEVERAGES"));

        // Generate 100 customers
        for (int i = 1; i <= 100; i++) {
            Users.add(new Customer("Customer" + i, i + 23, Person.Gender.MALE , "address" + i,"019999999"+i,"customer" + i + "@example.com"));
        }

        // Generate 200 unique products
        for (int i = 1; i <= 200; i++) {
            String productName = "Product" + i;
            double price = Math.round((10 + Math.random() * 990) * 100.0) / 100.0; // Random price between $10 and $1000
            int quantity = (int) (1 + Math.random() * 50); // Random stock quantity between 1 and 50
            Category category = Categories.get(i % Categories.size()); // Assign a category in a round-robin fashion
            Products.add(new Product(i, productName, price, quantity, category));
        }

        // Set AvailableProducts to include all products
        AvailableProducts.addAll(Products);

        // Dummy cart and orders
        Cart.add(Products.get(0)); // Add first product to cart
        Cart.add(Products.get(1)); // Add second product to cart

        Order.add(Products.get(2)); // Add third product to order
        Order.add(Products.get(3)); // Add fourth product to order

        System.out.println("Database initialized with 100 customers and 200 products.");
    }

}
