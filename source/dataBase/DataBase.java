package source.dataBase;

import source.Products.Category;
import source.Products.Product;
import source.people.Admin;
import source.people.Customer;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static ArrayList<Customer> Customers = new ArrayList<>();
    static ArrayList<Admin> Admins = new ArrayList<>();
    static ArrayList<Product> Products = new ArrayList<>();
    static ArrayList<Product> AvailableProducts = new ArrayList<>();
    static ArrayList<Product> Carts = new ArrayList<>();
    static ArrayList<Product> Orders = new ArrayList<>();
    static ArrayList<Category> Categories = new ArrayList<>();
    static ArrayList<String> Coupons = new ArrayList<>();

    public DataBase(){}

    public void initDummyData(){

    }
    public void saveData(){}

    public String generateSalesReport(){return ;}

    public List<Product> getTopSellingProducts(){
        return Products;
    }




}
