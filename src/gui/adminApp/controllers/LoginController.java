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
import src.DAO.CustomerDAO;
import src.service.CustomerService;

import java.io.IOException;
import java.util.Objects;

public class LoginController{
    public Hyperlink gitHub;
    public Hyperlink youtube;
    public Hyperlink facebook;
    public Hyperlink whatsapp;
    public ImageView img;
    public AnchorPane loginAnchorPane;
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
    private RadioButton femaleRadio;  // Ensure this is annotated with @FXML

    @FXML
    private RadioButton maleRadio;    // Make sure this is also declared

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    public void initialize() throws IOException {
    }

    @FXML
    void goToAboutUs(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/about.fxml")));
        loginPane.setCenter(anchorPane);
    }

    @FXML
    void goToContactUs(ActionEvent event) throws IOException {
        try{
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/contactUs.fxml")));
            loginPane.setCenter(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void goToLogin() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/loginPane.fxml")));
        loginPane.setCenter(anchorPane);
    }
    @FXML
    void goToRegister(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/register.fxml")));
        if (anchorPane != null && !anchorPane.equals(loginPane.getCenter())) {
        loginPane.setCenter(anchorPane);
        return;
        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            setAngry();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid username/password");
            alert.showAndWait();
            return;
        }
        if(!customerService.login(username, password)){
            setAngry();
            for (int i = 0; i < 1000; i++) {
            }
            setDefualt();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Credentials");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Credentials");
            alert.showAndWait();
            return;
        }
        setCorrect();
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setCurrentCustomer(customerDAO.read(username));
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

    private void setDefualt() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/icon/login.png")));
        img.setImage(image);
    }

    @FXML
    public void setMonkeyClose(MouseEvent event) {
        Image monkey_close = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/gif/monkey-close.gif")));
        img.setImage(monkey_close);
    }
    @FXML
    public void setMonkeyOpen(MouseEvent event) {
        Image monkey_open = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/gif/monkey-open.gif")));
        img.setImage(monkey_open);
    }

    private void setAngry(){
        Image image = new Image(getClass().getResourceAsStream("../../utils/gif/angry.gif"));
        img.setImage(image);

    }
    private void setCorrect(){
        Image image = new Image(getClass().getResourceAsStream("../../utils/gif/got-it.png"));
        img.setImage(image);
    }
}
