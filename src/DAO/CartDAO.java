package src.DAO;

import src.entities.Cart;

import java.util.List;

public class CartDAO implements CRUD<Cart> {


    @Override
    public void create(Cart entity) {

    }

    @Override
    public Cart read(Object id) {
        return null;
    }

    @Override
    public Cart readByID(int id) {
        return null;
    }

    @Override
    public void update(Cart entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Cart> getAll() {
        return List.of();
    }
}
