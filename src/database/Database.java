package src.database;

import src.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Database(){}

    public static void initDummyData(){
        // initialize admins:
        for (int i = 0; i < 5; i++) {
            admins.add(new Admin("Admin"+i,34, Person.Gender.MALE ,"Address"+i ,"0107823323"+i , "admin" , "admin"));
        }

        // initialize customers:

        for (int i = 0; i < 20; i++) {
            Random rand = new Random();
            int age = rand.nextInt(100);
            customers.add(new Customer("Customer"+i, age, Person.Gender.MALE, "Address"+i,null,null ));
        }
        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            int quantity = rand.nextInt(10);
            float price = rand.nextFloat(10000);
            products.add(new Product("product"+i, "description"+i, price,quantity));
        }
    }
    public void saveData(){}

    public void loadData(){}

    public String generateSalesReport(){return null;}

    public List<Product> getTopSellingProducts(){
        ArrayList<Product> products = new ArrayList<>();

        return products;
    }

}
