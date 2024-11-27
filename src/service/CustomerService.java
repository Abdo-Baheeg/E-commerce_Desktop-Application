package src.service;


import src.DAO.CustomerDAO;
import src.DAO.Implement.CustomerDAOImpl;
import src.entities.Customer;

public class CustomerService {
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    // Method to register a new customer
    public boolean register(String username, String password, String dateOfBirth, double balance, String address, String gender, String interests) {
        if (customerDAO.getCustomer(username) == null) {
            Customer customer = new Customer( username, password, dateOfBirth, balance, address, gender);
            customerDAO.registerCustomer(customer);
            return true;
        }
        return false; // Username already exists
    }

    // Method to login a customer
    public boolean login(String username, String password) {
        Customer customer = customerDAO.getCustomer(username);
        return customer != null && customer.getPassword().equals(password);
    }

    // Method to view a customer's profile
    public Customer viewProfile(String username) {
        return customerDAO.getCustomer(username);
    }

    // Method to update a customer's profile
    public void updateProfile(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
}