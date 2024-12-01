package src.DAO;

import src.database.Database;
import src.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CRUD<Product> {


    @Override
    public void create(Product entity) {
        Database.products.add(entity);
    }

    @Override
    public Product read(int id) {

    }

    @Override
    public Product readByID(int id) {
        return null;
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Object id) {
        for (Product p : Database.products){
            if (p.getId().equals(id)){
                Database.products.remove(p);
            }
        }
    }

    @Override
    public List<Product> getAll() {
        return Database.products;
    }

    public List<Product> getByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product p : Database.products) {
            if (p.getName().equals(name)) {
                products.add(p);
            }
        }
        return products;
    }
    public List<Product> getByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product p : Database.products) {
            if (p.getCategory().equals(category)) {
                products.add(p);
            }
        }
        return products;
    }
}
