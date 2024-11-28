package src.DAO.Implement;

import src.DAO.CRUD;
import src.entities.Product;

import java.util.List;

public class ProductDAO implements CRUD<Product> {


    @Override
    public void create(Product entity) {

    }

    @Override
    public Product read(Object id) {
        return null;
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
