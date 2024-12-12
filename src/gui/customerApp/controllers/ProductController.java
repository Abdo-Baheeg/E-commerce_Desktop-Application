package src.gui.customerApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductController {

    @FXML
    private Label ProductName;

    @FXML
    private Button addToCartBtn;

    @FXML
    private Button addToInterestsBtn;

    @FXML
    private Button backBtn;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label productCategory;

    @FXML
    private Label productDescription;

    @FXML
    private ImageView productImg;

    @FXML
    private Label productPrice;

    @FXML
    private Label productStock;

    @FXML
    private Spinner<?> quantity;

    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void addToInterests(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {

    }

}
