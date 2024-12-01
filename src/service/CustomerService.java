package src.service;

import src.DAO.CartDAO;
import src.DAO.CustomerDAO;
import src.DAO.OrderDAO;
import src.DAO.ProductDAO;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Product;

import java.util.List;

public class CustomerService {

    CustomerDAO customerDAO = new CustomerDAO();
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    CartDAO cartDAO = new CartDAO();
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
}