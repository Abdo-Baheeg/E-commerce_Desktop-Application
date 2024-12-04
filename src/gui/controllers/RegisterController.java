package src.gui.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import src.entities.Customer;
import src.entities.Person;
import src.service.CustomerService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RegisterController {
    CustomerService customerService = new CustomerService();

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ToggleGroup genderToggleGroup;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private Button registerButton;

    @FXML
    private Hyperlink loginLink;

    @FXML
    void handleRegister(MouseEvent event) {
        // Collect data from fields
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String username = usernameField.getText();
        LocalDate dobLocalDate = dobPicker.getValue();
        Date dob = null;
        if (dobLocalDate != null) {
            // Convert LocalDate to Date
            dob = Date.from(dobLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        RadioButton selectedGender = (RadioButton) genderToggleGroup.getSelectedToggle();
        String gender = selectedGender != null ? selectedGender.getText() : "Unspecified";
        Person.Gender genderEnum = Person.Gender.valueOf(gender);

        // Validate fields (example)
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all required fields.");
            return;
        }

        // Register logic
        System.out.println("Registering user: " + name);
        Customer c = new Customer(name ,genderEnum,address,phone,email,username,password,dob);
        customerService.register(c);
    }

    @FXML
    void goToLogin(MouseEvent event) {
        System.out.println("Redirecting to login screen...");

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
