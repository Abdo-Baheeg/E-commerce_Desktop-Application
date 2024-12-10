package src.service;

import src.DAO.AdminDAO;
import src.DAO.ProductDAO;
import src.entities.Admin;
import src.entities.Product;

import java.util.List;

public class AdminService {
    private final AdminDAO adminDAO = new AdminDAO();
    private final ProductDAO productDAO = new ProductDAO();


    public boolean login(String username, String password) {
        Admin admin = adminDAO.read(username);
        if (admin != null ) {
            if(admin.getPassword().equals(password)){
                AdminDAO.setCurrentAdmin(admin);
                return true;
            }
            return false;
        }
        return false;
    }


    public boolean createAdmin(String name, String role, String username, String password) {
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


    public boolean createProduct(String name, String description, float price, int quantity) {
        if(productDAO.read(name) != null){
            return false;
        } else if (ProductDAO.validName(name)&&ProductDAO.validDescription(description)&& price >0 && quantity>0) {
            Product product = new Product(name, description, price, quantity);
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


    public boolean deleteProduct(int id) {
        Product product = productDAO.read(id);
        if (product == null) {
            return false;
        }
        productDAO.delete(id);
        return true;
    }


    public List<Admin> getAllAdmins() {
        return adminDAO.getAll();
    }


    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }


    public Product readProduct(String name) {
        return productDAO.read(name);
    }
    public Product readProduct(int id) {
        return productDAO.read(id);
    }

}