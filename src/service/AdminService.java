package src.service;

import src.DAO.Implement.CustomerDAO;
import src.DAO.Implement.AdminDAO;
import src.DAO.Implement.OrderDAO;
import src.DAO.Implement.ProductDAO;
import src.entities.Admin;
import src.entities.Customer;
import src.entities.Product;
import src.entities.Order;

import java.util.List;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    OrderDAO orderDAO = new OrderDAO();
    ProductDAO productDAO = new ProductDAO();

    public boolean login(String username, String password) {
       return adminDAO.read(username) == adminDAO.read(password);
    }
}
