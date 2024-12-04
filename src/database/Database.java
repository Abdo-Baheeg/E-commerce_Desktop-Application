package src.database;

import src.entities.*;

import java.io.*;
import java.util.*;

public class Database {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Product> availableProducts = new ArrayList<>();
    public static ArrayList<Cart> carts = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
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
    public static void saveDatabase() throws IOException {
        saveAdmins();
        saveCustomers();
        saveOrders();
        saveCategories();
        saveProducts();
        saveCarts();
    }

    private static void saveCarts() throws IOException {
        File file = new File("src/database/carts.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(carts);
    }

    private static void saveProducts() throws IOException {
        File file = new File("src/database/products.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(products);
    }

    private static void saveCategories() throws IOException {
        File file = new File("src/database/categories.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(categories);
    }

    private static void saveOrders() throws IOException {
        File file = new File("src/database/orders.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(orders);
    }

    private static void saveCustomers() throws IOException {
        File file = new File("src/database/customers.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(customers);
    }
    private static void saveAdmins() throws IOException {
        File file = new File("src/database/admins.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(admins);
    }
    public static void loadDatabase() throws IOException, ClassNotFoundException {
        loadAdmins();
        loadCustomers();
        loadOrders();
        loadCategories();
        loadProducts();
        loadCarts();
    }

    private static void loadAdmins() throws IOException, ClassNotFoundException {
        File file = new File("src/database/admins.csv");
        if (file.exists() && file.length() > 0) {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            admins = (ArrayList<Admin>) ois.readObject();
        }
        catch (Exception e){e.printStackTrace();}}
        else {
        System.out.println("Admins file is empty. Initializing with default values.");
    }
    }

    private static void loadCustomers() throws IOException, ClassNotFoundException {
        File file = new File("src/database/customers.csv");
        if (file.exists() && file.length() > 0) {
            try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        customers = (ArrayList<Customer>) ois.readObject();}
            catch (Exception e){e.printStackTrace();}}
         else {
            System.out.println("Customers file is empty. Initializing with default values.");
    }
    }

    private static void loadOrders() throws IOException, ClassNotFoundException {
        File file = new File("src/database/orders.csv");
        if (file.exists() && file.length() > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/database/orders.csv"));
                orders = (ArrayList<Order>) ois.readObject();
            }catch (Exception e){e.printStackTrace();}
        } else {
            System.out.println("Orders file is empty. Initializing with default values.");
        }

    }

    private static void loadCategories() throws IOException, ClassNotFoundException {
        File file = new File("src/database/categories.csv");
        if (file.exists() && file.length() > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/database/categories.csv"));
                categories = (ArrayList<Category>) ois.readObject();
            }catch (Exception e){e.printStackTrace();}
        } else {
            System.out.println("Category file is empty. Initializing with default values.");
        }

    }

    private static void loadProducts() throws IOException, ClassNotFoundException {
        File file = new File("src/database/products.csv");
        if (file.exists() && file.length() > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/database/products.csv"));
                products = (ArrayList<Product>) ois.readObject();
            }catch (Exception e){e.printStackTrace();}
        } else {
            System.out.println("Products file is empty. Initializing with default values.");
        }

    }

    private static void loadCarts() throws IOException, ClassNotFoundException {
        File file = new File("src/database/carts.csv");
        if (file.exists() && file.length() > 0) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/database/carts.csv"));
                carts =(ArrayList<Cart>) ois.readObject();
            }catch (Exception e){e.printStackTrace();}
        } else {
            System.out.println("Carts file is empty. Initializing with default values.");
        }

    }

    public String generateSalesReport(){
        return null;}

    public List<Product> getTopSellingProducts(){
        ArrayList<Product> topProducts = new ArrayList<>();

        return products;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // testing
//        //Database.initDummyData();
//       // Database.saveDatabase();
//        System.out.println(admins);
//        Database.loadDatabase();
//        for (Admin admin : admins) {
//            System.out.println(admin.getName() +" "+admin.getAge() +"  "+admin.getGender());
//        }
//        System.out.println(orders);
    }

}
