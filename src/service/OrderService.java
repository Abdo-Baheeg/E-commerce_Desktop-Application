package src.service;


import src.DAO.OrderDAO;
import src.DAO.Implement.OrderDAOImpl;
import src.entities.Order;
import src.entities.Product;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAOImpl();

    // Method to place an order
    public boolean placeOrder(Order order) {
        if ((order != null) && (order.getProductList().size() > 0)) {
            orderDAO.saveOrder(order);
            return true;
        }
        return false; // Invalid order (empty product list)
    }

    // Method to cancel an order
    public boolean cancelOrder(int orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if (order != null && order.getStatus().equals("Pending")) {
            order.setStatus("Canceled");
            orderDAO.saveOrder(order); // Updating the order status
            return true;
        }
        return false; // Cannot cancel an order that's already processed or shipped
    }

    // Method to calculate the total amount of an order
    public double calculateTotal(Order order) {
        double total = 0;
        for (Product product : order.getProductList()) {
            total += product.getPrice();
        }
        return total;
    }

    // Method to get order details by ID
    public Order getOrderDetails(int orderId) {
        return orderDAO.getOrderById(orderId);
    }
}
