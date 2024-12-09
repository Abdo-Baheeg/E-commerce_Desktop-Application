package src.DAO;

import src.database.Database;
import src.entities.Cart;
import src.entities.Category;
import src.entities.Order;
import src.entities.Product;
import src.gui.MainApp;

import java.util.ArrayList;
import java.util.List;

import static src.database.Database.*;


public class ProductDAO implements CRUD<Product> {

    // validators : ............//
    public static boolean validName(String name) {
        return name != null &&
                name.matches("^[A-Za-z\\s]{2,50}$");
    }
    public static boolean validDescription(String description) {
        return description != null &&
                description.matches("^[A-Za-z\\s]{2,250}$");
    }

     @Override
     public void create(Product product) {
        products.add(product);
     }

     public void create(String name, String description, float price, int quantity, Category category) {
        Product product = new Product(name, description, price, quantity, category);
        products.add(product);
     }

     @Override
     public Product read(Product object) {
        if (products.contains(object)) {
            return object;
        }
        else
         return null;
     }

     @Override
     public Product read(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
         return null;
     }

     @Override
     public Product read(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
         return null;
     }

     @Override
     public void update(Product entity) {
        products.set(products.indexOf(entity), entity);
     }

     @Override
     public void delete(Product entity) {
        products.remove(entity);
     }

     @Override
     public List<Product> getAll() {
         return products;
     }
     public void addToCart(int id) {
        Product p = this.read(id);
        currentCustomer.getCart().addProduct(p);
     }
     public void removeFromCart(int id) {
         currentCustomer.getCart().getProducts().removeIf(p -> p.getId() == id);
     }
     public void updateName(int id, String name) {
        Product p = this.read(id);
        p.setName(name);
     }
     public void updateDescription(int id, String description) {
        Product p = this.read(id);
        p.setDescription(description);
     }
     public void updatePrice(int id, float price) {
        Product p = this.read(id);
     }
     public void updateQuantity(int id, int quantity) {
        Product p = this.read(id);

     }
     public void updateCategory(int id, Category category) {
        Product p = this.read(id);
        p.setCategory(category);

     }

     public void delete(int id) {
        Product p = this.read(id);
        products.remove(p);
     }

     public static List<Product> search(String name) {
        List<Product> products = new ArrayList<>();
        for (Product product : Database.products) {
            if (product.getName().equals(name)) {
                products.add(product);
            }
        }
        return products;
     }

     public boolean placeOrder(Order.payMethod payMethod) {
        if (!currentCustomer.getCart().getProducts().isEmpty()) {
            Order order = new Order(currentCustomer.getCart().getProducts());
            order.setStatus(Order.Status.PENDING);
            order.setPayMethod(payMethod);
            currentCustomer.setOrder(order);
            return true;
        }
        return false;
     }



 }
