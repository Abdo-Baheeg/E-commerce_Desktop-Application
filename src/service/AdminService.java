package src.service;

import src.DAO.CustomerDAO;
import src.DAO.AdminDAO;
import src.DAO.OrderDAO;
import src.DAO.ProductDAO;
import src.entities.Admin;
import src.entities.Product;

import java.util.List;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    OrderDAO orderDAO = new OrderDAO();
    ProductDAO productDAO = new ProductDAO();

    public boolean login(String username, String password) {
        try {
       return adminDAO.read(username).equals(adminDAO.read(password)) ;

        }
        catch (Exception e) {
            return false;
        }
    }
    public boolean register(String username, String password) {
        return adminDAO.read(username).equals(adminDAO.read(password)) ;
    }

    public void updatePassword(String username, String password) {}

    public void updateUsername(String username, String newUsername) {}

    public void updateProduct( int id ,Product product) {}

    public void createAdmin(Admin admin) {}

    public void createProduct(Product product) {
        productDAO.create(product);
    }

    public void deleteAdmin(int id) {
        adminDAO.delete(id);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }

    public void readProduct(int id) {
        productDAO.read(id);
    }

    public void getAllAdmin() {
        List<Admin> admins = adminDAO.getAll();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    public void getAllProduct() {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
