package src.DAO;

import src.database.Database;
import src.entities.Cart;
import src.entities.Customer;
import src.entities.Order;
import src.entities.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static src.database.Database.*;

public  class CustomerDAO implements CRUD<Customer> {

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
        customers.remove(entity);
    }

    @Override
    public ArrayList<Customer> getAll() {
        return customers;
    }

}
