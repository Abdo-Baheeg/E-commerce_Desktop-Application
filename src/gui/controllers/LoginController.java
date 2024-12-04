package src.gui.controllers;

import javafx.fxml.FXML;
import src.service.AdminService;
import src.service.CustomerService;

import java.awt.*;

public class LoginController {

    @FXML
    TextField username;
    @FXML
    TextField password;


    public void login() {
    String user = username.getText();
    String pass = password.getText();
    AdminService adminService = new AdminService();
    CustomerService customerService = new CustomerService();

    if(customerService.login(user,pass)){

    }
    else{}
    }
}
