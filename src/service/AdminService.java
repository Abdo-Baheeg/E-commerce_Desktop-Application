package src.service;

import src.DAO.AdminDAO;
import src.DAO.CategoryDAO;
import src.DAO.ProductDAO;
import src.entities.Admin;
import src.entities.Category;
import src.entities.Product;

import java.util.ArrayList;

public class AdminService {
    private static final AdminDAO adminDAO = new AdminDAO();
    private static Admin currentAdmin;
    private static final ProductDAO productDAO = new ProductDAO();
    private static final CategoryDAO categoryDAO = new CategoryDAO();



    public static boolean login(String username, String password) {
        Admin admin = adminDAO.read(username);
        if (admin != null ) {
            if(admin.getPassword().equals(password)){
                currentAdmin = admin;
                return true;
            }
            return false;
        }
        return false;
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public static void CreateCategory(String categoryName, String description) {
        Category category = new Category(categoryName, description);
        categoryDAO.create(category);
    }

    public static ArrayList<Category> getAllCategories() {
        return categoryDAO.getAll();
    }

    public static boolean deleteCategory(Category category) {
        if (category != null) {
            categoryDAO.delete(category);
            return true;
        }
        return false;
    }

    public static ArrayList<Category> getCategories() {
        return categoryDAO.getAll();
    }

    public static void update(Admin admin) {
        adminDAO.update(admin);
    }

    public static boolean createAdmin(String name, String role, String username, String password) {
        Admin existingAdmin = adminDAO.read(username);
        if (existingAdmin != null) {
            return false;
        }
        Admin admin = new Admin(name, role, username, password);
        adminDAO.create(admin);
        return true;
    }
    public boolean updatePassword(String newPassword) {
        if(AdminDAO.getCurrentAdmin() != null){
            return adminDAO.updatePassword(newPassword);
        }
        return false;
    }
    public boolean updateUsername(String newUsername) {
        if(AdminDAO.getCurrentAdmin() != null){
           return adminDAO.updateUsername(newUsername);
        }
        return false;
    }
    public static boolean createProduct(String name, String description, float price, int quantity, Category value, String image) {
        if(productDAO.read(name) != null){
            return false;
        } else if (ProductDAO.validName(name)&&ProductDAO.validDescription(description)&& price >0 && quantity>0) {
            Product product = new Product(name, description, price, quantity,value,image);
            productDAO.create(product);
            return true;
        }
        return false;
    }
    public boolean updateProduct(int id, Product product) {
        Product existingProduct = productDAO.read(id);
        if (existingProduct == null) {
            return false;
        }
        productDAO.update(product);
        return true;
    }
    public static boolean deleteProduct(int id) {
        Product product = productDAO.read(id);
        if (product == null) {
            return false;
        }
        productDAO.delete(id);
        return true;
    }
    public ArrayList<Admin> getAllAdmins() {
        return adminDAO.getAll();
    }
    public static ArrayList<Product> getAllProducts() {
        return productDAO.getAll();
    }
    public Product readProduct(String name) {
        return productDAO.read(name);
    }
    public Product readProduct(int id) {
        return productDAO.read(id);
    }

}