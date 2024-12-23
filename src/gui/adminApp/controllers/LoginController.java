package src.gui.adminApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import src.DAO.CustomerDAO;
import src.service.AdminService;
import src.service.CustomerService;

import java.io.IOException;
import java.util.Objects;

public class LoginController{

    @FXML public TextField username;
    @FXML public Button loginButton;
    @FXML public PasswordField password;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Notifications.create().text("Please Enter Username and Password!").showError();
            return;
        }
        if(AdminService.login(username, password)){
            System.out.println("Login Successful");
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/AdminDashboard.fxml")));
            Scene newScene = new Scene(newRoot);
            // Get the current stage
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(newScene);
            stage.show();
            return;
        }
        Notifications.create().text("Invalid Username or Password!").showError();
    }


}
