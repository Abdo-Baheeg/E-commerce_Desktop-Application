package src.database;

import javafx.scene.image.Image;
import src.entities.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Database {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();
    public static ArrayList<Product> availableProducts = new ArrayList<>();
    public static ArrayList<Product> soldProducts = new ArrayList<>();

    public Database(){}

    public static void initData() throws MalformedURLException {
        //customers.add(new Customer("Abdo", Person.Gender.MALE,"Cairo, Egypt", "01016042121","3bdobahig@gmail.com","abdo","1234"));
            admins.add(new Admin("Abdo Bahig","CEO","abdo","1234"));
            customers.add(new Customer("abdo","1234"));
            // Initialize Categories
//            Category laptops = new Category("Laptops", "High-performance computing devices for professional and personal use");
//            Category smartphones = new Category("Smartphones", "Mobile devices with advanced computing capabilities");
//            Category tablets = new Category("Tablets", "Portable touchscreen computing devices");
//            Category accessories = new Category("Accessories", "Supporting devices and add-ons for electronics");
//
//            // Add categories to the list
//            categories.add(laptops);
//            categories.add(smartphones);
//            categories.add(tablets);
//            categories.add(accessories);
//
//            // Initialize Products
//            // Laptops
//            Product dellLatitude = new Product(
//                    "Dell Latitude 5555",
//                    "Intel Core i7 Processor, 32 GB DDR5 Ram, 1 TB SSD, 15.6 inches, Color: Black",
//                    999.99,
//                    3,
//                    laptops,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product macBookPro = new Product(
//                    "MacBook Pro 16\"",
//                    "Apple M2 Pro Chip, 16 GB RAM, 512 GB SSD, 16-inch Retina Display, Space Gray",
//                    2499.99,
//                    5,
//                    laptops,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product lenovoThinkPad = new Product(
//                    "Lenovo ThinkPad X1 Carbon",
//                    "Intel Core i7, 16 GB RAM, 512 GB SSD, 14-inch, Carbon Fiber Construction",
//                    1799.99,
//                    4,
//                    laptops,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//
//            // Smartphones
//            Product iPhone14 = new Product(
//                    "iPhone 14 Pro",
//                    "6.1-inch Super Retina XDR Display, A16 Bionic Chip, 128GB, Deep Purple",
//                    999.99,
//                    6,
//                    smartphones,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product samsungS23 = new Product(
//                    "Samsung Galaxy S23 Ultra",
//                    "6.8-inch Dynamic AMOLED, 200MP Camera, 512GB, Phantom Black",
//                    1199.99,
//                    5,
//                    smartphones,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product googlePixel7 = new Product(
//                    "Google Pixel 7",
//                    "6.3-inch OLED, Google Tensor G2, 128GB, Obsidian",
//                    599.99,
//                    4,
//                    smartphones,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//
//            // Tablets
//            Product iPadPro = new Product(
//                    "iPad Pro 12.9\"",
//                    "M2 Chip, 128GB, 12.9-inch Liquid Retina XDR Display, Space Gray",
//                    1099.99,
//                    5,
//                    tablets,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product samsungTabS8 = new Product(
//                    "Samsung Galaxy Tab S8 Ultra",
//                    "14.6-inch Super AMOLED, 8GB RAM, 128GB, Graphite",
//                    899.99,
//                    4,
//                    tablets,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product microsoftSurface = new Product(
//                    "Microsoft Surface Pro 9",
//                    "Intel Evo Platform, 16GB RAM, 512GB SSD, Graphite",
//                    1599.99,
//                    3,
//                    tablets,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//
//            // Accessories
//            Product appleAirPods = new Product(
//                    "Apple AirPods Pro (2nd Generation)",
//                    "Active Noise Cancellation, Wireless Charging Case, White",
//                    249.99,
//                    7,
//                    accessories,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product sonyHeadphones = new Product(
//                    "Sony WH-1000XM5 Wireless Noise Cancelling Headphones",
//                    "Bluetooth, Touch Sensor Controls, Black",
//                    399.99,
//                    4,
//                    accessories,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product ankerPowerBank = new Product(
//                    "Anker 737 Power Bank (PowerIQ 3.0)",
//                    "24000mAh, 140W Fast Charging, Compatible with Laptops and Smartphones",
//                    99.99,
//                    6,
//                    accessories,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product logitechMX = new Product(
//                    "Logitech MX Master 3S Wireless Mouse",
//                    "Ergonomic Design, Silent Clicks, Bluetooth/USB, Gray",
//                    99.99,
//                    5,
//                    accessories,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//            Product usbcHub = new Product(
//                    "Anker USB-C Hub",
//                    "7-in-1, 4K HDMI, SD/microSD Card Reader, 2 USB-A Ports",
//                    49.99,
//                    8,
//                    accessories,
//                    "C:\\Users\\Abdelrahman Bahig\\Pictures\\EGYPT\\2686479_389815-PCH2OE-340.jpg"
//            );
//
//            // Add products to the list
//            products.add(dellLatitude);
//            products.add(macBookPro);
//            products.add(lenovoThinkPad);
//            products.add(iPhone14);
//            products.add(samsungS23);
//            products.add(googlePixel7);
//            products.add(iPadPro);
//            products.add(samsungTabS8);
//            products.add(microsoftSurface);
//            products.add(appleAirPods);
//            products.add(sonyHeadphones);
//            products.add(ankerPowerBank);
//            products.add(logitechMX);
//            products.add(usbcHub);


            // Initialize Categories
            Category laptops = new Category("Laptops", "High-performance computing devices for professional and personal use");
            Category smartphones = new Category("Smartphones", "Mobile devices with advanced computing capabilities");
            Category tablets = new Category("Tablets", "Portable touchscreen computing devices");
            Category accessories = new Category("Accessories", "Supporting devices and add-ons for electronics");

            // Add categories to the list
            categories.add(laptops);
            categories.add(smartphones);
            categories.add(tablets);
            categories.add(accessories);

            // Initialize Products
            // Laptops
            Product dellLatitude = new Product("Dell Latitude 5555",
                    "Intel Core i7 Processor, 32 GB DDR5 Ram, 1 TB SSD, 15.6 inches, Color: Black",
                    999.99, 3, laptops);
            Product macBookPro = new Product("MacBook Pro 16\"",
                    "Apple M2 Pro Chip, 16 GB RAM, 512 GB SSD, 16-inch Retina Display, Space Gray",
                    2499.99, 5, laptops);
            Product lenovoThinkPad = new Product("Lenovo ThinkPad X1 Carbon",
                    "Intel Core i7, 16 GB RAM, 512 GB SSD, 14-inch, Carbon Fiber Construction",
                    1799.99, 4, laptops);

            // Smartphones
            Product iPhone14 = new Product("iPhone 14 Pro",
                    "6.1-inch Super Retina XDR Display, A16 Bionic Chip, 128GB, Deep Purple",
                    999.99, 6, smartphones);
            Product samsungS23 = new Product("Samsung Galaxy S23 Ultra",
                    "6.8-inch Dynamic AMOLED, 200MP Camera, 512GB, Phantom Black",
                    1199.99, 5, smartphones);
            Product googlePixel7 = new Product("Google Pixel 7",
                    "6.3-inch OLED, Google Tensor G2, 128GB, Obsidian",
                    599.99, 4, smartphones);

            // Tablets
            Product iPadPro = new Product("iPad Pro 12.9\"",
                    "M2 Chip, 128GB, 12.9-inch Liquid Retina XDR Display, Space Gray",
                    1099.99, 5, tablets);
            Product samsungTabS8 = new Product("Samsung Galaxy Tab S8 Ultra",
                    "14.6-inch Super AMOLED, 8GB RAM, 128GB, Graphite",
                    899.99, 4, tablets);
            Product microsoftSurface = new Product("Microsoft Surface Pro 9",
                    "Intel Evo Platform, 16GB RAM, 512GB SSD, Graphite",
                    1599.99, 3, tablets);

            // Accessories
            Product appleAirPods = new Product("Apple AirPods Pro (2nd Generation)",
                    "Active Noise Cancellation, Wireless Charging Case, White",
                    249.99, 7, accessories);
            Product sonyHeadphones = new Product("Sony WH-1000XM5 Wireless Noise Cancelling Headphones",
                    "Bluetooth, Touch Sensor Controls, Black",
                    399.99, 4, accessories);
            Product ankerPowerBank = new Product("Anker 737 Power Bank (PowerIQ 3.0)",
                    "24000mAh, 140W Fast Charging, Compatible with Laptops and Smartphones",
                    99.99, 6, accessories);
            Product logitechMX = new Product("Logitech MX Master 3S Wireless Mouse",
                    "Ergonomic Design, Silent Clicks, Bluetooth/USB, Gray",
                    99.99, 5, accessories);
            Product usbcHub = new Product("Anker USB-C Hub",
                    "7-in-1, 4K HDMI, SD/microSD Card Reader, 2 USB-A Ports",
                    49.99, 8, accessories);

            // Add products to the list
            products.add(dellLatitude);
            products.add(macBookPro);
            products.add(lenovoThinkPad);
            products.add(iPhone14);
            products.add(samsungS23);
            products.add(googlePixel7);
            products.add(iPadPro);
            products.add(samsungTabS8);
            products.add(microsoftSurface);
            products.add(appleAirPods);
            products.add(sonyHeadphones);
            products.add(ankerPowerBank);
            products.add(logitechMX);
            products.add(usbcHub);
        }



    public static void saveDatabase() throws IOException {
        saveAdmins();
        saveCustomers();
        saveProducts();
        saveCategories();
        System.out.println("Database saved");
    }

    public static void loadDatabase() throws IOException, ClassNotFoundException {
        loadAdmins();
        loadCustomers();
        loadProducts();
        loadCategories();
    }


    private static void saveProducts() throws IOException {
        File file = new File("src/database/products.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(products);
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
    private static void saveCategories() throws IOException {
        File file = new File("src/database/categories.csv");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(categories);
    }

    private static void loadCategories() throws IOException, ClassNotFoundException {
        File file = new File("src/database/categories.csv");
        if (file.exists() && file.length() > 0) {
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                categories = (ArrayList<Category>) ois.readObject();
            }
            catch (Exception e){e.printStackTrace();}}
        else {
            System.out.println("Categories file is empty. Initializing with default values.");
        }
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


    public String generateSalesReport(){
        StringBuilder salesReport= new StringBuilder();
        if(!soldProducts.isEmpty()){
            salesReport = new StringBuilder("Sold Products:\n");
            for(Product p : soldProducts){
                salesReport.append(p.getId()).append(" ,").append(p.getName()).append(" , sold items: ").append(p.getSoldItems());
            }
        } else {
         return "No sold products";
        }
        return salesReport.toString();
    }

    public List<Product> getTopSellingProducts(){
        ArrayList<Product> topProducts = new ArrayList<>();

        return products;
    }
    public void setAvailableProducts(){
        for (Product p : products) {
            if(p.getStock() > 0){
                availableProducts.add(p);
            }
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // testing
//        //Database.initData();
//       // Database.saveDatabase();
//        System.out.println(admins);
//        Database.loadDatabase();
//        for (Admin admin : admins) {
//            System.out.println(admin.getName() +" "+admin.getAge() +"  "+admin.getGender());
//        }
//        System.out.println(orders);
    }

}
