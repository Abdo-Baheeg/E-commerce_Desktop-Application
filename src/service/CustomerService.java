package src.service;

import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Person;
import src.entities.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final ProductDAO productDAO = new ProductDAO();

    public static boolean login(String username, String password) {
        Customer customer = customerDAO.read(username);
        return customer != null && customer.getPassword().equals(password);
    }

    public static boolean register(String name, String email, String address, String username, String password, String phone, Person.Gender gender, Date dob) {
        if (customerDAO.read(username) != null) {
            return false;
        } else if (CustomerDAO.validName(username) && CustomerDAO.validEmail(email) && CustomerDAO.validAddress(address) && CustomerDAO.validPhone(phone) ) {
            customerDAO.create(new Customer(name,gender,address,phone,email,username,password,dob));
            return true;
        }
        return false;
    }

    public static boolean addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            customerDAO.getCurrentCustomer().getCart().getProducts().add(product);
        }
        return false;
    }

    public static boolean removeFromCart(Product product, int quantity) {
        if (product != null) {
            customerDAO.getCurrentCustomer().getCart().getProducts().remove(product);
            return true;
        }
        return false;
    }

    public static boolean addToInterests(Product product) {
        if(product != null) {
            customerDAO.getCurrentCustomer().getInterests().add(product);
            return true;
        }
        return false;
    }
    public static boolean removeFromInterests(Product product) {
        if(product != null) {
            customerDAO.getCurrentCustomer().getInterests().remove(product);
            return true;
        }
        return false;
    }

    public static ArrayList<Product> searchProduct(String productName) {
        return ProductDAO.search(productName);
    }

    public static ArrayList<Product> viewCart() {
        return customerDAO.getCurrentCustomer().getCart().getProducts();
    }

    public static boolean checkout() {
        Cart cart = customerDAO.getCurrentCustomer().getCart();
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

    public static boolean resetPassword(String email, String newPassword) {
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

    public static Customer getCurrentCustomer() {
        return customerDAO.getCurrentCustomer() ;
    }
}
