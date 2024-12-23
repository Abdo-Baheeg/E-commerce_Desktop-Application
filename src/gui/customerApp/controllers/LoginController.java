package src.gui.customerApp.controllers;


import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.Notifications;
import src.DAO.CustomerDAO;
import src.entities.Person;
import src.service.CustomerService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

       @FXML public ImageView img;
    public AnchorPane registerPane;

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
        private PasswordField password;
        @FXML
        private TextField username;


        @FXML
        void goToContactUs(ActionEvent event) throws IOException {


            try{
                goToRegisterBtn.setDisable(false);
                contactUsBtn.setDisable(true);
                goToLoginBtn.setDisable(false);
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/aboutUs.fxml")));
                loginPane.setCenter(anchorPane);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    @FXML
    private void goToView(String fxmlPath, Button activeButton) throws IOException {
        try {
            // Enable all buttons first
            goToRegisterBtn.setDisable(false);
            contactUsBtn.setDisable(false);
            goToLoginBtn.setDisable(false);

            // Disable the currently active button
            activeButton.setDisable(true);

            // Load the specified view
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            loginPane.setCenter(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void goToLogin(ActionEvent event) throws IOException {
        goToView("../views/loginPane.fxml", goToLoginBtn);
    }

    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        goToView("../views/register.fxml", goToRegisterBtn);
    }


        @FXML
        void login(ActionEvent event) throws IOException {
            String username = this.username.getText();
            String password = this.password.getText();

            if (username.isEmpty() || password.isEmpty()) {
                Notifications.create().text("Please Enter username and password").darkStyle().position(Pos.CENTER).showError();
                return;
            }
            if(CustomerService.login(username, password)){
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/gif/got-it.gif")));
                img.setImage(image);
                Notifications.create().darkStyle().position(Pos.CENTER).title("Logged in Successfully").text("Welcome, "+CustomerService.getCurrentCustomer().getName()).showInformation();
                Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/dashboard.fxml")));
                Scene newScene = new Scene(newRoot);
                // Get the current stage
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(newScene);
                stage.setTitle("EcoMARKET");
                stage.show();
                return;
            }
            Notifications.create().darkStyle().position(Pos.CENTER).title("Invalid Credentials").showError();
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
        private void setDefault(MouseEvent event) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../utils/icon/login.png")));
            img.setImage(image);
        }



        // register Handling

        String imgPath;

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
        @FXML private TextField registerPhone;

        @FXML
        private TextField registerName;

        @FXML
        private PasswordField registerPassword;

        @FXML
        private TextField registerUsername;

        @FXML
        private Button submitBtn;

        Image image;

        @FXML
        void choosePhoto(ActionEvent event) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");

            // Set file extension filters
            FileChooser.ExtensionFilter imageFilter =
                    new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif");
            fileChooser.getExtensionFilters().add(imageFilter);

            // Show the file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                imgPathLbl.setText(selectedFile.getName());
                imgPath = selectedFile.getAbsolutePath();
            }
        }

    @FXML
    void register(ActionEvent event) throws IOException {
            String name = registerName.getText();
            String email = registerEmail.getText();
            String address = registerAddress.getText();
            String phone = registerPhone.getText();
            String password = registerPassword.getText();
            String username = this.registerUsername.getText();
            Person.Gender gender = Person.Gender.MALE;
            LocalDate dob = datePicker.getValue();
            if (!CustomerService.validName(name)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Enter Your name Correctly").showError();
                return;
            }
            if (!CustomerService.validEmail(email)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Enter Your Email Correctly").showError();
                return;
            }
            if (!CustomerService.validAddress(address)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Enter Your Address Correctly").showError();
                return;
            }
            if (!CustomerService.validUsername(username)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Enter unique Username").showError();
                return;
            }
            if (!CustomerService.validPassword(password)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Enter Password Contains: (A-Z, a-z, 0-9 and Special character)").showError();
                return;
            }
            if (!CustomerService.validPhone(phone)) {
                Notifications.create().text("Please Enter Phone Number").darkStyle().showError();
                return;
            }
            if (!Male.isSelected() || Female.isSelected()) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Select your Gender").showError();
                return;
            }
            if (!CustomerService.validDateOfBirth(dob)) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Age warning").text("check for your birthday, if it is correct, You are not eligible for using our Site").showError();
                return;
            }
            if (imgPath == null) {
                Notifications.create().darkStyle().position(Pos.CENTER).text("Please Choose a photo").showError();
                return;
            }
            if(Female.isSelected()){
                gender = Person.Gender.FEMALE;
            }
            if(CustomerService.register(name,email,address,username,password,phone,gender,dob,imgPath)) {
                Notifications.create().darkStyle().position(Pos.BOTTOM_LEFT).title("Registered Successfully").text("Welcome, " + name).show();

                Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/dashboard.fxml")));
                Scene newScene = new Scene(newRoot);
                // Get the current stage
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(newScene);
                stage.setTitle("EcoMARKET");
                stage.show();
            }
    }

}


