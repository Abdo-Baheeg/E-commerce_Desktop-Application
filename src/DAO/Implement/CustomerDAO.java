package src.DAO.Implement;

import src.DAO.CRUD;
import src.entities.Customer;

import java.util.List;

public class CustomerDAO implements CRUD<Customer> {

    @Override
    public void create(Customer entity) {

    }

    @Override
    public Customer read(Object id) {
        return null;
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
