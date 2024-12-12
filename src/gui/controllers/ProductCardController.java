package src.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addToCart;

    @FXML
    private ImageView productImg;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Spinner<?> quantity;

    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void setQuantity(MouseEvent event) {

    }

    @FXML
    void initialize() {
//        assert addToCart != null : "fx:id=\"addToCart\" was not injected: check your FXML file 'productCard.fxml'.";
//        assert productImg != null : "fx:id=\"productImg\" was not injected: check your FXML file 'productCard.fxml'.";
//        assert productName != null : "fx:id=\"productName\" was not injected: check your FXML file 'productCard.fxml'.";
//        assert productPrice != null : "fx:id=\"productPrice\" was not injected: check your FXML file 'productCard.fxml'.";
//        assert quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'productCard.fxml'.";

    }

}
