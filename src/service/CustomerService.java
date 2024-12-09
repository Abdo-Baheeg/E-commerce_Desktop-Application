package src.service;

import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerDAO customerDAO = new CustomerDAO();
    ProductDAO productDAO = new ProductDAO();
    Cart cart = new Cart();

    public boolean login(String username, String password) {
        return customerDAO.read(username).equals(customerDAO.read(password));
    }
    public boolean register(Customer customer) {
        customerDAO.create(customer);
        return true;

    }

    public boolean addToCart(Product p, int quantity) {
        if(p.getStock() <= quantity) {
        Database.currentCustomer.getCart().getProducts().add(p);
        return true;
        }
        else {
            return false;
        }
    }

    public void getAllProduct() {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public List<Product> searchProduct(String productName) {
        return productDAO.search(productName);

    }

}