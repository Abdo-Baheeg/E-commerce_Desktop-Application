package src.DAO.Implement;


import src.DAO.OrderDAO;
import src.entities.Order;
import src.database.Database;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public void saveOrder(Order order) {
        Database.Orders.add(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        for (Order order : Database.Orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return Database.Orders;
    }
}
