package src.database;

import src.entities.Category;
import src.entities.Product;
import src.entities.Admin;
import src.entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static ArrayList<Customer> Customers = new ArrayList<>();
    public static ArrayList<Admin> Admins = new ArrayList<>();
    public static ArrayList<Product> Products = new ArrayList<>();
    public static ArrayList<Product> AvailableProducts = new ArrayList<>();
    public static ArrayList<Product> Carts = new ArrayList<>();
    public static ArrayList<Product> Orders = new ArrayList<>();
    public static ArrayList<Category> Categories = new ArrayList<>();
    public static ArrayList<String> Coupons = new ArrayList<>();

    public Database(){}

    public void initDummyData(){

    }
    public void saveData(){}

    public String generateSalesReport(){return ;}

    public List<Product> getTopSellingProducts(){
        return Products;
    }




}
