package src.DAO.Implement;

import src.DAO.CustomerDAO;
import src.entities.Customer;
import src.database.Database;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public void registerCustomer(Customer customer) {
        Database.Customers.add(customer);
    }

    @Override
    public Customer getCustomer(String username) {
        for (Customer customer : Database.Customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // If not found
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

    @Override
    public List<Customer> getAllCustomers() {
        return Database.Customers;
    }
}
