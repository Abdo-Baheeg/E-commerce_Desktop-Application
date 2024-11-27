package src.DAO.Implement;

import src.DAO.CustomerDAO;
import src.entities.Customer;
import src.dataBase.DataBase;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public void registerCustomer(Customer customer) {
        DataBase.Customers.add(customer);
    }

    @Override
    public Customer getCustomer(String username) {
        for (Customer customer : DataBase.Customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // If not found
    }

    @Override
    public void updateCustomer(Customer customer) {
        for (int i = 0; i < DataBase.Customers.size(); i++) {
            if (DataBase.Customers.get(i).getUsername().equals(customer.getUsername())) {
                DataBase.Customers.set(i, customer);
                return;
            }
        }
    }

    @Override
    public void deleteCustomer(String username) {
        DataBase.Customers.removeIf(customer -> customer.getUsername().equals(username));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return DataBase.Customers;
    }
}
