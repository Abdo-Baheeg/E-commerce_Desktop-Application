package src.database;

import src.DAO.Implement.CustomerDAOImpl;
import src.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.*;

public class Database {
    public static ArrayList<Customer> Customers = new ArrayList<>();
    public static ArrayList<Admin> Admins = new ArrayList<>();
    public static ArrayList<Product> Products = new ArrayList<>();
    public static ArrayList<Product> AvailableProducts = new ArrayList<>();
    public static ArrayList<Product> Carts = new ArrayList<>();
    public static ArrayList<Product> Orders = new ArrayList<>();
    public static ArrayList<Category> Categories = new ArrayList<>();
    public static ArrayList<String> Coupons = new ArrayList<>();
    public static ArrayList<Product> soldProducts = new ArrayList<>();

    public Database(){}

    public void initDummyData(){
        // initialize Admins:
        for (int i = 0; i < 5; i++) {
            Admins.add(new Admin("Admin"+i,34, Person.Gender.MALE ,"Address"+i ,"0107823323"+i , "admin" , "admin"));
        }

        // initialize customers:

        for (int i = 0; i < 20; i++) {
            Random rand = new Random();
            int age = rand.nextInt(100);
            Customers.add(new Customer("Customer"+i, age, Person.Gender.MALE, "Address"+i,null,null ));
        }
        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            int quantity = rand.nextInt(10);
            float price = rand.nextFloat(10000);
            Products.add(new Product("product"+i, "description"+i, price,quantity));
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
