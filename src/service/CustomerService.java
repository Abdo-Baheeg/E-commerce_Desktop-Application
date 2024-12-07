package src.service;

import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Product;

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

    public void addToCart(Customer customer, Cart cart) {

    }

    public void getAllProduct() {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
//    public void searchProduct(String productName) {
//        List<Product> products = productDAO.getByName(productName);
//    }

}