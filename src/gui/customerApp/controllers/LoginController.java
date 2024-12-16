package src.gui.customerApp.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.WindowEvent;
import src.DAO.CustomerDAO;
import src.service.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Hyperlink gitHub;
    public Hyperlink youtube;
    public Hyperlink facebook;
    public Hyperlink whatsapp;
    public ImageView img;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goToLoginBtn.setDisable(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/loginPane.fxml"));
        try {
            AnchorPane pane = fxmlLoader.load();
            loginPane.setCenter(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goToAboutUs(ActionEvent event) throws IOException {
        goToRegisterBtn.setDisable(false);
        aboutUsBtn.setDisable(true);
        contactUsBtn.setDisable(false);
        goToLoginBtn.setDisable(false);

        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/about.fxml")));
        loginPane.setCenter(anchorPane);
    }

    @FXML
    void goToContactUs(ActionEvent event) throws IOException {
        goToRegisterBtn.setDisable(false);
        aboutUsBtn.setDisable(false);
        contactUsBtn.setDisable(true);
        goToLoginBtn.setDisable(false);

        try{
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/contactUs.fxml")));
            loginPane.setCenter(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void goToLogin() throws IOException {
        goToRegisterBtn.setDisable(false);
        aboutUsBtn.setDisable(false);
        contactUsBtn.setDisable(false);
        goToLoginBtn.setDisable(true);

        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/loginPane.fxml")));
        loginPane.setCenter(anchorPane);
    }
    @FXML
    void goToRegister(ActionEvent event) throws IOException {
        goToRegisterBtn.setDisable(true);
        aboutUsBtn.setDisable(false);
        contactUsBtn.setDisable(false);
        goToLoginBtn.setDisable(false);

        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/register.fxml")));
        if (!anchorPane.equals(loginPane.getCenter())) {
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Credentials");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Credentials");
            alert.showAndWait();
            return;
        }
        Image image = new Image(getClass().getResourceAsStream("../../utils/gif/got-it.gif"));
        img.setImage(image);
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setCurrentCustomer(customerDAO.read(username));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(" You are Logged in successfully");
        alert.showAndWait();
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/dashboard.fxml")));
        Scene newScene = new Scene(newRoot);
        // Get the current stage
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.setTitle("EcoMARKET");
        stage.show();
        DashboardController.initialize();
    }
    @FXML
    public void setMonkeyClose(MouseEvent actionEvent) {
        Image monkey_close = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/gif/monkey-close.gif")));
        img.setImage(monkey_close);
    }
    @FXML
    public void setMonkeyOpen(MouseEvent event) {
        Image monkey_open = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/gif/monkey-open.gif")));
        img.setImage(monkey_open);
    }
    @FXML
    private void setDefualt(MouseEvent event) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/icon/login.png")));
        img.setImage(image);
    }
    private void setAngry(){
        Image image = new Image(getClass().getResourceAsStream("../../utils/gif/angry.gif"));
        img.setImage(image);
    }


    // register Handling
    @FXML
    private RadioButton Female;

    @FXML
    private RadioButton Male;

    @FXML
    private Button choosePhoto;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    private Label imgPathLbl;

    @FXML
    private TextField registerAddress;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerName;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerUsername;

    @FXML
    private Button submitBtn;


}
