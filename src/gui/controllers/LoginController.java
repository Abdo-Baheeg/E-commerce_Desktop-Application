package src.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.DAO.AdminDAO;
import src.DAO.CustomerDAO;
import src.gui.MainApp;
import src.service.AdminService;
import src.service.CustomerService;

import java.awt.*;
import java.io.IOException;

public class LoginController {
    AdminDAO adminDAO = new AdminDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    AdminService adminService = new AdminService();
    CustomerService customerService = new CustomerService();

    // login.fxml Controls: ///
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink registerButton;
    @FXML
    private Hyperlink adminLogin;
    // end of login.fxml Controls

    // Register.fxml Controls:  //
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField registerUsername;
    @FXML
    private TextField registerPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label registerMessage;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
    @FXML
    private Button reset;
    // end of Register.fxml Controls //

    // adminLogin.fxml Controls //
    @FXML
    private Button adminBackBTN;

    @FXML
    private Button adminLoginBTN;

    @FXML
    private Label adminLoginMessage;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private TextField adminUsername;
    ///// end of adminLogin Controls



    // Event Handling________________::

    @FXML
    public void login(ActionEvent event) throws IOException {
    String user = username.getText();
    String pass = password.getText();

    if(customerService.login(user,pass)){
      //  MainApp.currentCustomer = customerDAO.read(user);
      //  messageLabel.setText("Hello," + customerDAO.read(user).getName() +" <3");

    }
    else{
        messageLabel.setText("Username or password is incorrect, please try again");
    }
    }

    @FXML
    private void handleRegisterButton(ActiveEvent event) {
        try {
            // Load the Register FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Register.fxml"));
            Parent registerRoot = loader.load();

            // Create a new Stage (window) for the Register Form
            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with the login screen
            registerStage.setResizable(false);
            registerStage.setScene(new Scene(registerRoot));

            // Show the Register Form
            registerStage.showAndWait(); // Wait until the Register window is closed
        } catch (IOException e) {
            e.printStackTrace(); // Handle the error (e.g., file not found)
        }

    }

    @FXML
     private void handleAdminLogin(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("src/gui/adminLogin.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("CCCCCCCCCCCCCCCCCCCCCccc");
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(root));
        loginStage.showAndWait();
    }

    @FXML
    private void adminLogin(ActionEvent event) throws IOException {
        String user = adminUsername.getText();
    }
}
