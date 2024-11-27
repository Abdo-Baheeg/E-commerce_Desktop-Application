package src.service;



import src.DAO.AdminDAO;
import src.DAO.OrderDAO;
import src.DAO.Implement.AdminDAOImpl;
import src.DAO.Implement.OrderDAOImpl;
import src.entities.Admin;
import src.entities.Product;
import src.entities.Order;

import java.util.List;

public class AdminService {
    private final AdminDAO adminDAO = new AdminDAOImpl();  // AdminDAO includes product operations
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();  // Added CustomerDAO dependency

    // PRODUCT MANAGEMENT
    public void addProduct(Product product) {
        adminDAO.addProduct(product);
    }

    public void removeProduct(int productId) {
        adminDAO.deleteProduct(productId);
    }

    public void updateProduct(Product product) {
        adminDAO.updateProduct(product);
    }

    public List<Product> viewAllProducts() {
        return adminDAO.getAllProducts();
    }

    // ORDER MANAGEMENT
    public List<Order> viewOrders() {
        return orderDAO.getAllOrders();
    }

    // CUSTOMER MANAGEMENT
    public List<Customer> viewAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public Customer viewCustomer(String username) {
        return customerDAO.getCustomer(username);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(String username) {
        customerDAO.deleteCustomer(username);
    }

    // ADMIN PROFILE MANAGEMENT
    public Admin viewProfile(String username) {
        return adminDAO.getAdmin(username);
    }
}
