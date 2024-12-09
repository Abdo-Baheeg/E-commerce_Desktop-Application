//package src.service;
//
//import src.DAO.CustomerDAO;
//import src.DAO.AdminDAO;
//import src.DAO.OrderDAO;
//import src.DAO.ProductDAO;
//import src.entities.Admin;
//import src.entities.Product;
//
//import java.util.List;
//
//public class AdminService {
//    AdminDAO adminDAO = new AdminDAO();
//    CustomerDAO customerDAO = new CustomerDAO();
//    OrderDAO orderDAO = new OrderDAO();
//    ProductDAO productDAO = new ProductDAO();
//
//    public boolean login(String username, String password) {
//        try {
//       return adminDAO.read(username).equals(adminDAO.read(password)) ;
//
//        }
//        catch (Exception e) {
//            return false;
//        }
//    }
//    public boolean register(String username, String password) {
//        return adminDAO.read(username).equals(adminDAO.read(password)) ;
//    }
//
//    public void updatePassword(String username, String password) {}
//
//    public void updateUsername(String username, String newUsername) {}
//
//    public void updateProduct( int id ,Product product) {}
//
//    public void createAdmin(Admin admin) {}
//
//    public void createProduct(Product product) {
//        productDAO.create(product);
//    }
//
//    public void deleteAdmin(int id) {
//        adminDAO.delete(id);
//    }
//
//    public void deleteProduct(int id) {
//        productDAO.delete(id);
//    }
//
//    public void readProduct(int id) {
//        productDAO.read(id);
//    }
//
//    public void getAllAdmin() {
//        List<Admin> admins = adminDAO.getAll();
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }
//    }
//
//    public void getAllProduct() {
//        List<Product> products = productDAO.getAll();
//        for (Product product : products) {
//            System.out.println(product);
//        }
//    }
//}


package src.service;

import src.DAO.AdminDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Admin;
import src.entities.Product;

import java.util.List;

import static src.database.Database.currentAdmin;

public class AdminService {
    private final AdminDAO adminDAO = new AdminDAO();
    private final ProductDAO productDAO = new ProductDAO();

    // Login method
    public   String login(String username, String password) {
        Admin admin = adminDAO.read(username);
        if (admin == null) {
            return "Admin not found.";
        }
        if (!admin.getPassword().equals(password)) {
            return "Invalid password.";
        }
        return "Login successful.";
    }

    // Register admin
//    public String register(String username, String password) {
//        Admin existingAdmin = adminDAO.read(username);
//        if (existingAdmin != null) {
//            return "Username already exists.";
//        }
//        Admin admin = new Admin(username, password);
//        adminDAO.create(admin);
//        return "Registration successful.";
//    }

    // Update admin password
    public String updatePassword(String username, String newPassword) {
        Admin admin = adminDAO.read(username);
        if (admin == null) {
            return "Admin not found.";
        }
        admin.setPassword(newPassword);
        adminDAO.update(admin);
        return "Password updated successfully.";
    }

    // Update admin username
    public boolean updateUsername( String newUsername) {
        return adminDAO.updateUsername(currentAdmin, newUsername);
    }

    // Create a new product
    public boolean createProduct(Product product) {
        if(Database.products.contains(product)) {
            return false;
        };
        if (product.getName() == null || product.getPrice() < 0) {

            return false;
        }
        productDAO.create(product);
        return true;
    }

    // Update product details
//    public String updateProduct(int id, Product product) {
//        Product existingProduct = productDAO.read(id);
//        if (existingProduct == null) {
//            return "Product not found.";
//        }
//        product.setId(id);
//        productDAO.update(product);
//        return "Product updated successfully.";
//    }

    // Delete a product
//    public String deleteProduct(int id) {
//        Product product = productDAO.read(id);
//        if (product == null) {
//            return "Product not found.";
//        }
//        productDAO.delete(id);
//        return "Product deleted successfully.";
//    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        return adminDAO.getAll();
    }


    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }


//    public Product readProduct(int id) {
//        return productDAO.read(id);
//    }


}
