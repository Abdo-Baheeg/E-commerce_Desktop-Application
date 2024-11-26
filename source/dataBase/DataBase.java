package source.dataBase;

import source.Products.Category;
import source.Products.Product;
import source.people.Admin;
import source.people.Customer;
import java.util.ArrayList;

public class DataBase {
    static ArrayList<Customer> Users = new ArrayList<>();
    static ArrayList<Admin> Admins = new ArrayList<>();
    static ArrayList<Product> Products = new ArrayList<>();
    static ArrayList<Product> AvailableProducts = new ArrayList<>();
    static ArrayList<Product> Cart = new ArrayList<>();
    static ArrayList<Product> Order = new ArrayList<>();

}
