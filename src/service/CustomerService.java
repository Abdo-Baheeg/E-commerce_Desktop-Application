package src.service;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.entities.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import static src.database.Database.customers;

public class CustomerService {

    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final ProductDAO productDAO = new ProductDAO();
    private static Customer currentCustomer;

    // validators : ............//
    public static boolean validName(String name) {
        return name != null &&
                name.matches("^[A-Za-z\\s]{2,50}$");
    }
    public static boolean validEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null &&
                email.matches(regex) &&
                email.length() <= 100;
    }
    public static boolean validPassword(String password) {
        return password != null &&
                password.length() >= 6 &&
                password.length() <= 20 &&
                password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    }
    public static boolean validPhone(String phone) {
        return phone != null &&
                phone.matches("^\\d{10}$");
    }
    public static boolean validAddress(String address) {
        return address != null &&
                address.length() >= 10 &&
                address.length() <= 200 &&
                address.matches("^[A-Za-z0-9\\s,.-]+$");
    }
    public static boolean validUsername(String username) {
        for(Customer c : customers) {
            if (c.getUsername().equals(username)) {
              return false;
            }
        }
        return username != null &&
                username.matches("^[A-Za-z0-9_]{4,20}$") &&
                !username.matches("^\\d+$"); // Prevent all-numeric usernames
    }
    public static boolean validOrder() {
        return !customers.isEmpty() ;
    }
    public static boolean validDateOfBirth(LocalDate dateOfBirth) {
        LocalDate allowedAge = LocalDate.of(2006, Month.DECEMBER,20);
        if(dateOfBirth == null) {
            return false;
        } else
            return !dateOfBirth.isAfter(allowedAge);
    }




    public static boolean login(String username, String password) {
        Customer customer = customerDAO.read(username);
        if (customer != null) {
            currentCustomer = customer;
        }
        return customer != null && customer.getPassword().equals(password);
    }

    public static boolean register(String name, String email, String address, String username, String password, String phone, Person.Gender gender, LocalDate dob, Image img) {
        if (customerDAO.read(username) != null) {
            return false;
        } else if (validName(username) && validEmail(email) && validAddress(address) && validPhone(phone) ) {
            Customer c = new Customer(name,gender,address,phone,email,username,password,dob,img);
            customerDAO.create(c);
            currentCustomer = c;
            return true;
        }
        return false;
    }

    public static boolean addToCart(@org.jetbrains.annotations.NotNull Product product, int quantity) {
        if (product.getStock() >= quantity) {
            currentCustomer.getCart().getProducts().add(product);
            updateTotalPrice(product, quantity);
        }
        return false;
    }

    public static boolean removeFromCart(Product product, int quantity) {
        if (product != null) {
            updateTotalPrice(product, -quantity);
            currentCustomer.getCart().getProducts().remove(product);
            return true;
        }
        return false;
    }

    public static boolean addToInterests(Product product) {
        if(product != null) {
            currentCustomer.getInterests().add(product);
            return true;
        }
        return false;
    }
    public static boolean removeFromInterests(Product product) {
        if(product != null) {
            currentCustomer.getInterests().remove(product);
            return true;
        }
        return false;
    }

    public static ArrayList<Product> searchProduct(String productName) {
        return ProductDAO.search(productName);
    }

    public static ArrayList<Product> viewCart() {
        return currentCustomer.getCart().getProducts();
    }

    public static boolean checkout() {
        Cart cart = currentCustomer.getCart();
        if (cart != null){
            if (currentCustomer.getBalance() < cart.getTotalPrice()|| cart.getProducts().isEmpty()) {
            return false;
            }

            if (placeOrder()) {
                currentCustomer.getCart().getProducts().removeAll(currentCustomer.getCart().getProducts());
                return true;
            }
            }

        return false;
    }

//    public static boolean resetPassword(String email, String newPassword) {
//        for (Customer customer : Database.customers) {
//            if (customer.getEmail().equals(email)) {
//                if (validPassword(newPassword)) {
//                    customer.setPassword(newPassword);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public static boolean updateName(String name) {
        if(validName(name)) {
            currentCustomer.setName(name);
            return true;
        }
        else
            return false;
    }
    public static boolean updateEmail(String email) {
        if(validEmail(email)) {
            currentCustomer.setEmail(email);
            return true;
        }
        else
            return false;
    }
    public static boolean updatePassword(String password) {
        if(validPassword(password)) {
            currentCustomer.setPassword(password);
            return true;
        }
        else
            return false;
    }
    public static boolean updatePhone(String phone) {
        if(validPhone(phone)) {
            currentCustomer.setPhone(phone);
            return true;
        }
        else
            return false;
    }
    public static boolean updateAddress( String address) {
        if(validAddress(address)) {
            currentCustomer.setAddress(address);
            return true;
        }
        else
            return false;
    }
    public static boolean updateUsername( String username) {
        if(validUsername(username)) {
            currentCustomer.setUsername(username);
            return true;
        }
        else
            return false;
    }

    public static void updateTotalPrice(@NotNull Product product, int quantity) {
        float total =currentCustomer.getCart().getTotalPrice();
        total += (float) (quantity*product.getPrice());
        currentCustomer.getCart().setTotalPrice(total);
    }
    public static void deleteCart() {
        currentCustomer.getCart().setProducts(new ArrayList<>());
    }
    public static boolean placeOrder() {
        if(currentCustomer.getCart() != null) {
            if(currentCustomer.getCart().getTotalPrice() > currentCustomer.getBalance()){
                return false;
            }
            ArrayList<Product> p =currentCustomer.getCart().getProducts();
            Order o = new Order(p);
            o.setStatus(Order.Status.PENDING);
            o.setTotalPrice(currentCustomer.getCart().getTotalPrice());
            currentCustomer.setOrder(o);
            return true;
        }
        return false;
    }

    public static boolean cancelOrder(Order order) {
        if (order != null && order.getStatus().equals(Order.Status.PENDING)) {
            currentCustomer.deleteOrder(order);
            return true;
        }
        return false;
    }


    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }
}
