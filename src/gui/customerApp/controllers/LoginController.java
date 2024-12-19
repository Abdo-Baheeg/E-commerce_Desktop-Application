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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

        public ImageView img;
    public AnchorPane loginAnchorPane;

    CustomerService customerService = new CustomerService();

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
            Image image = new Image(getClass().getResourceAsStream("../../utils/gif/got-it.gif"));
            img.setImage(image);
            CustomerDAO customerDAO = new CustomerDAO();
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
                 image = new Image(selectedFile.toURI().toString());
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid name");
                alert.showAndWait();
                return;
            }
            if (!CustomerService.validEmail(email)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid email");
                alert.showAndWait();
                return;
            }
            if (!CustomerService.validAddress(address)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid address");
                alert.showAndWait();
                return;
            }
            if (!CustomerService.validUsername(username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid username");
                alert.showAndWait();
                return;
            }
            if (!CustomerService.validPassword(password)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid password");
                alert.showAndWait();
                return;
            }
            if (!Male.isSelected() || Female.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a gender");
                alert.showAndWait();
                return;
            }
            if (!CustomerService.validDateOfBirth(dob)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid date of birth");
                alert.showAndWait();
                return;
            }
            if (image == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid image");
                alert.showAndWait();
                return;
            }
            if(Female.isSelected()){
                gender = Person.Gender.FEMALE;
            }
            System.out.println("HHHHHHHHHHHHHHHHHHH");
            CustomerService.register(name,email,address,username,password,phone,gender,dob,image);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Successfully registered");
                alert.showAndWait();

                Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/dashboard.fxml")));
                Scene newScene = new Scene(newRoot);
                // Get the current stage
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(newScene);
                stage.setTitle("EcoMARKET");
                stage.show();




    }

}


