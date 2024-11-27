package src.DAO.Implement;

import src.DAO.AdminDAO;
import src.entities.Admin;
import src.entities.Customer;
import src.entities.Product;
import src.dataBase.DataBase;

import java.util.ArrayList;
import java.util.List;


public class AdminDAOImpl implements AdminDAO {

    // Product Management
    @Override
    public void addProduct(Product product) {
        DataBase.Products.add(product);
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : DataBase.Products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < DataBase.Products.size(); i++) {
            if (DataBase.Products.get(i).getId() == product.getId()) {
                DataBase.Products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        DataBase.Products.removeIf(product -> product.getId() == productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return DataBase.Products;
    }

    // Admin Profile Management
    @Override
    public Admin getAdmin(String username) {
        for (Admin admin : DataBase.Admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    // Customer Management
    @Override
    public ArrayList<Customer> getAllCustomers() {
        return DataBase.Customers;
    }

    @Override
    public Customer getCustomer(String username) {
        for (Customer customer : DataBase.Customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        for (int i = 0; i < DataBase.Customers.size(); i++) {
            if (DataBase.Customers.get(i).getUsername().equals(customer.getUsername())) {
                DataBase.Customers.set(i, customer);
                return;
            }
        }
    }

    @Override
    public void deleteCustomer(String username) {
        DataBase.Customers.removeIf(customer -> customer.getUsername().equals(username));
    }
}
