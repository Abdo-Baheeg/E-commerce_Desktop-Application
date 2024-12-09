package src.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.xml.validation.Validator;
import java.util.Date;

public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField name;
    @FXML
    private DatePicker birthday;
    @FXML
    private RadioButton Male;
    @FXML
    private RadioButton Female;
    @FXML
    private Button submit;
    @FXML
    private Button reset;
    @FXML
    private Button back;
    @FXML
    private Label messageLabel;


    @FXML
    public void reset(ActionEvent event) {
        username.setText("");
        password.setText("");
        confirmPassword.setText("");
        email.setText("");
        phone.setText("");
        address.setText("");
        name.setText("");
        birthday.setValue(null);
        Male.setSelected(false);
        Female.setSelected(false);
        submit.setDisable(false);
        reset.setDisable(false);
        back.setDisable(false);
    }

    void isValidName() {
        if(name.getText().equals("")) {
            messageLabel.setText("Name is required");
        } else if (name.getText().length() < 5) {
            messageLabel.setText("Name is too short");
        }
    }
    void isValidPassword() {
        if(password.getText().equals("")) {
            messageLabel.setText("Password is required");
        }
        else if (password.getText().length() < 6) {
            messageLabel.setText("Password is too short");
        }
    }
    void isValidEmail() {
        if(email.getText().equals("")) {
            messageLabel.setText("Email is required");
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
       if( !email.getText().matches(regex)){
           messageLabel.setText("Email is not valid");
       }
    }
    void isValidPhone() {
        if(phone.getText().equals("")) {
            messageLabel.setText("Phone is required");
        }
        else if (phone.getText().length() < 11) {
            messageLabel.setText("Phone is too short");
        }
    }
    void isValidAddress() {
        if(address.getText().equals("")) {
            messageLabel.setText("Address is required");
        }
        else if (address.getText().length() < 5) {
            messageLabel.setText("Address is too short");
        }
    }
    void isValidBirthday() {
        Date date = new Date();
        date.setYear(2008);
        if(birthday.getValue() == null) {
            messageLabel.setText("Birthday is required");
        }
        else if (birthday.getValue().getYear() > date.getYear()  ) {
            messageLabel.setText("You are not Eligible to use the site");
        }
    }
    void isValidMale() {
        if(!Male.isSelected() && !Female.isSelected()) {
            messageLabel.setText("Gender is required");
        }

    }
    void isValidConfirmedPassword() {
        if(confirmPassword.getText().equals("")) {
            messageLabel.setText("Password Confirmation is required");
        }
        else if (confirmPassword.getText().length() < 6) {
            messageLabel.setText("Password Confirmation is too short");
        }
        else if (!confirmPassword.getText().equals( password.getText())) {
            messageLabel.setText("Password Confirmation is incorrect");
        }
    }


}
