package src.DAO.Implement;

import src.DAO.AdminDAO;
import src.entities.Admin;
import src.entities.Customer;
import src.entities.Product;
import src.database.Database;

import java.util.ArrayList;
import java.util.List;


public class AdminDAOImpl implements AdminDAO {

    // Product Management
    @Override
    public void addProduct(Product product) {
        Database.Products.add(product);
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : Database.Products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < Database.Products.size(); i++) {
            if (Database.Products.get(i).getId() == product.getId()) {
                Database.Products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Database.Products.removeIf(product -> product.getId() == productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return Database.Products;
    }

    // Admin Profile Management
    @Override
    public Admin getAdmin(String username) {
        for (Admin admin : Database.Admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    // Customer Management
    @Override
    public ArrayList<Customer> getAllCustomers() {
        return Database.Customers;
    }

    @Override
    public Customer getCustomer(String username) {
        for (Customer customer : Database.Customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        for (int i = 0; i < Database.Customers.size(); i++) {
            if (Database.Customers.get(i).getUsername().equals(customer.getUsername())) {
                Database.Customers.set(i, customer);
                return;
            }
        }
    }

    @Override
    public void deleteCustomer(String username) {
        Database.Customers.removeIf(customer -> customer.getUsername().equals(username));
    }
}
