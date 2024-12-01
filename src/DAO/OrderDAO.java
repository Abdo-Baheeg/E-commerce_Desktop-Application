package src.DAO;

import src.entities.Order;

import java.util.List;

public class OrderDAO implements CRUD<Order> {

    @Override
    public void create(Order entity) {

    }

    @Override
    public Order read(Object id) {
        return null;
    }

    @Override
    public Order readByID(int id) {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }



}
