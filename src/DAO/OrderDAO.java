package src.DAO;

import src.entities.Order;
import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order);            // Create
    Order getOrderById(int orderId);        // Read
    List<Order> getAllOrders();             // Retrieve all orders
}
