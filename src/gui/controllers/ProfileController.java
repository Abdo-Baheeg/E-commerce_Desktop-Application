package src.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private void editProfile() {
        System.out.println("Editing profile...");
    }

    public void setProfileDetails(String name, String email, String phone, String address) {
        nameLabel.setText("Name: " + name);
        emailLabel.setText("Email: " + email);
        phoneLabel.setText("Phone: " + phone);
        addressLabel.setText("Address: " + address);
    }
}
