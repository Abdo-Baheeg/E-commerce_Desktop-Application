package src.database;

import src.entities.*;

import java.io.*;
import java.util.*;

public class Database {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> availableProducts = new ArrayList<>();
    public static ArrayList<Product> carts = new ArrayList<>();
    public static ArrayList<Product> orders = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<String> coupons = new ArrayList<>();
    public static ArrayList<Product> soldProducts = new ArrayList<>();
    public static PriorityQueue<Map.Entry< Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
    );

    public Database(){}

    public static void initDummyData(){
        // initialize 5 admins:
        for (int i = 0; i < 5; i++) {
            admins.add(new Admin("Admin"+i,34, Person.Gender.MALE ,"Address"+i ,"0107823323"+i , "admin" , "admin"));
        }

        // initialize 10 customers:

        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int age = rand.nextInt(100);
            customers.add(new Customer("Customer"+i, age, Person.Gender.MALE, "Address"+i,null,null ));
        }

        // initialize 15 Products
        for (int i = 0; i < 15; i++) {
            Random rand = new Random();
            int quantity = rand.nextInt(10);
            float price = rand.nextFloat(10000);
            products.add(new Product("product"+i, "description"+i, price,quantity));
        }
    }
    public void saveData(){
    }
    public void loadData(){
    }

    public String generateSalesReport(){
        return null;}

    public List<Product> getTopSellingProducts(){
        ArrayList<Product> topProducts = new ArrayList<>();

        return products;
    }

}
