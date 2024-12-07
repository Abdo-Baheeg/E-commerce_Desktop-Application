package src.DAO;

import src.database.Database;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Order;
import src.entities.Product;

import java.util.List;
import static src.database.Database.*;

public  class CustomerDAO implements CRUD<Customer> {

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
        boolean exist= false;
        for(Customer c : customers) {
            if (c.getUsername().equals(username)) {
                exist = true;
                break;
            }
        }
        return username != null &&
                username.matches("^[A-Za-z0-9_]{4,20}$") &&
                !username.matches("^\\d+$")&& !exist; // Prevent all-numeric usernames
    }

    @Override
    public void create(Customer entity) {
        customers.add(entity);
    }

    @Override
    public Customer read(Customer object) {
        if(customers.contains(object)) {
            return object;
        }
        return null;
    }

    @Override
    public Customer read(int id) {
        for(Customer c : customers) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Customer read(String username) {
        for(Customer c : customers) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }


    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
    public boolean updateName(Customer entity, String name) {
        if(validName(name)) {
            entity.setName(name);
            return true;
        }
        else
            return false;
    }
    public boolean updateEmail(Customer entity, String email) {
        if(validEmail(email)) {
            entity.setEmail(email);
            return true;
        }
        else
            return false;
    }
    public boolean updatePassword(Customer entity, String password) {
        if(validPassword(password)) {
            entity.setPassword(password);
            return true;
        }
        else
            return false;
    }
    public boolean updatePhone(Customer entity, String phone) {
        if(validPhone(phone)) {
            entity.setPhone(phone);
            return true;
        }
        else
            return false;
    }
    public boolean updateAddress(Customer entity, String address) {
        if(validAddress(address)) {
            entity.setAddress(address);
            return true;
        }
        else
            return false;
    }
    public boolean updateUsername(Customer entity, String username) {
        if(validUsername(username)) {
            entity.setUsername(username);
            return true;
        }
        else
            return false;
    }

    public void calcTotalPrice() {
        float total =0;
        for(Product p :  currentCustomer.getCart().getProducts()){
            total += p.getPrice();
        }
            currentCustomer.getCart().setTotalPrice(total);
    }
    private boolean validateOrder(Cart cart) {
        if(cart == null ) {
            return false;
        }
        return true;
    }
    public void deleteCart() {
        currentCustomer.getCart().setProducts(null);
    }
    public boolean placeOrder() {
        if(currentCustomer.getCart() != null) {
            currentCustomer.addOrder(currentCustomer.getCart());
            return true;
        }
        return false;
    }
}
