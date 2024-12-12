package src.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.service.CustomerService;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public Hyperlink gitHub;
    public Hyperlink youtube;
    public Hyperlink facebook;
    public Hyperlink whatsapp;

    CustomerService customerService = new CustomerService();

    @FXML
    private Button aboutUsBtn;

    @FXML
    private Button contactUsBtn;

    @FXML
    private Button goToLoginBtn;

    @FXML
    private Button goToRegisterBtn;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private BorderPane loginPane;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    public void initialize() {
        genderToggleGroup = new ToggleGroup();
        femaleRadio.setToggleGroup(genderToggleGroup);
        maleRadio.setToggleGroup(genderToggleGroup);
    }

    @FXML
    void goToAboutUs(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/about.fxml")));
        loginPane.setCenter(anchorPane);
    }

    @FXML
    void goToContactUs(ActionEvent event) throws IOException {
        try{
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/contactUs.fxml")));
            loginPane.setCenter(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void goToLogin() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/loginPane.fxml")));
        loginPane.setCenter(anchorPane);
    }
    @FXML
    void goToRegister(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/register.fxml")));
        loginPane.setCenter(anchorPane);
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid username/password");
            alert.showAndWait();
            return;
        }
        if(!customerService.login(username, password)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Credentials");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Credentials");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(" You are Logged in successfully");
        alert.showAndWait();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/dashboard.fxml")));
        Scene newScene = new Scene(newRoot);
        // Get the current stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

}
