package src.DAO;

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
    public Customer readByID(int id) {
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
