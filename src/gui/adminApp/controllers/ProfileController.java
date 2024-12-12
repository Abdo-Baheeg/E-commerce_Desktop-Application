package src.gui.adminApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import src.DAO.CustomerDAO;
import src.service.CustomerService;

public class ProfileController {
    CustomerService customerService = new CustomerService();
    CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    private Label address;

    @FXML
    private Label age;

    @FXML
    private Button backBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Label email;

    @FXML
    private Label gender;

    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label username;

    @FXML
    void backToDashboard(ActionEvent event) {

    }

    @FXML
    void goToEdit(ActionEvent event) {

    }

    @FXML
    public void initialize(){
        name.setText(customerDAO.getCurrentCustomer().getName());
        username.setText(customerDAO.getCurrentCustomer().getUsername());
        email.setText(customerDAO.getCurrentCustomer().getEmail());
        address.setText(customerDAO.getCurrentCustomer().getAddress());
        gender.setText(customerDAO.getCurrentCustomer().getGender().toString());
        img.setImage(customerDAO.getCurrentCustomer().getImage());
    }
}
