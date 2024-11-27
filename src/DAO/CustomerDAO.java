package src.DAO;
import src.entities.Customer;
import java.util.List;

public interface CustomerDAO {
    void registerCustomer(Customer customer); // Create
    Customer getCustomer(String username);   // Read
    void updateCustomer(Customer customer);  // Update
    void deleteCustomer(String username);    // Delete
    List<Customer> getAllCustomers();        // Retrieve all customers

}
