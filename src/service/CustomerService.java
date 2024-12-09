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

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();

    public boolean login(String username, String password) {
        Customer customer = customerDAO.read(username);
        return customer != null && customer.getPassword().equals(password);
    }

    public boolean register(Customer customer) {
        if (customerDAO.read(customer.getUsername()) == null) {
            customerDAO.create(customer);
            return true;
        }
        return false;
    }

    public boolean addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            Cart cart = Database.currentCustomer.getCart();
            if (cart == null) {
                cart = new Cart();
                Database.currentCustomer.setCart(cart);
            }

            for (int i = 0; i < quantity; i++) {
                cart.addProduct(product);
            }
            product.setStock(product.getStock() - quantity);
            return true;
        }
        return false;
    }

    public void getAllProducts() {
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> searchProduct(String productName) {
        return productDAO.search(productName);
    }

    public Cart viewCart() {
        return Database.currentCustomer.getCart();
    }

    public boolean checkout() {
        Cart cart = Database.currentCustomer.getCart();
        if (cart != null && !cart.getProducts().isEmpty()) {
            customerDAO.calcTotalPrice();
            boolean orderPlaced = customerDAO.placeOrder();
            if (orderPlaced) {
                customerDAO.deleteCart();
                return true;
            }
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        for (Customer customer : Database.customers) {
            if (customer.getEmail().equals(email)) {
                if (CustomerDAO.validPassword(newPassword)) {
                    customer.setPassword(newPassword);
                    return true;
                }
            }
        }
        return false;
    }
}
