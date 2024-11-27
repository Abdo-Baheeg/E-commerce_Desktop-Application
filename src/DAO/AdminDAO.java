package src.DAO;

import src.entities.Admin;
import src.entities.Customer;
import src.entities.Product;

import java.util.ArrayList;
import java.util.List;

public interface AdminDAO {
    // Product Management
    void addProduct(Product product);
    Product getProductById(int productId);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    List<Product> getAllProducts();

    // Admin Profile Management
    Admin getAdmin(String username);

    // Customer Management (Optional for AdminDAO if used via CustomerDAO)
    ArrayList<Customer> getAllCustomers();
    Customer getCustomer(String username);
    void updateCustomer(Customer customer);
    void deleteCustomer(String username);
}
