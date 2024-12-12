package src.gui.adminApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {

    @FXML
    private Label balanceLabel;

    @FXML
    private Button cartBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button interestsBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button logoutBtn1;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    void addCredits(ActionEvent event) {

    }

    @FXML
    void goToCart(ActionEvent event) {

    }

    @FXML
    void goToHome(ActionEvent event) {

    }

    @FXML
    void goToInterests(ActionEvent event) {

    }

    @FXML
    void goToOrders(ActionEvent event) {

    }

    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/customerApp/Profile.fxml")));
        mainBorderPane.setCenter(pane);
    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

}
