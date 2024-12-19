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
        name.setText(CustomerService.getCurrentCustomer().getName());
        username.setText(CustomerService.getCurrentCustomer().getUsername());
        email.setText(CustomerService.getCurrentCustomer().getEmail());
        address.setText(CustomerService.getCurrentCustomer().getAddress());
        gender.setText(CustomerService.getCurrentCustomer().getGender().toString());
        img.setImage(CustomerService.getCurrentCustomer().getImage());
    }
}
