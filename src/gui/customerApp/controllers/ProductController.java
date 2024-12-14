package src.gui.customerApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Product;
import src.service.CustomerService;

import java.io.IOException;
import java.util.Objects;

public class ProductController {
    CustomerService customerService = new CustomerService();
    ProductDAO productDAO = new ProductDAO();
    Product product;
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
    private Spinner<Integer> quantity;

    @FXML
    void back(ActionEvent event) {

    }




    //////// product card /////////

    @FXML
    public Label name;
    @FXML
    public Label price;
    @FXML
    public Button addToInterests;
    @FXML
    public Button addToCart;
    @FXML
    public ImageView img;
    @FXML
    public Spinner<Integer> q;
    @FXML
    public Button removeFromCart;

    public BorderPane createCard(Product product) throws IOException {
        this.product = product;
        BorderPane card = new BorderPane();
        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(false);
        imageView.setMouseTransparent(true);
        imageView.setImage(product.getImg());
        card.setCenter(imageView);
        card.setBottom();
        HBox Hbox = new HBox();
        Hbox.setSpacing(10);
        Hbox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();

        name.setText(product.getName());
        price.setText(product.getPriceString());
        return card;
    }
    @FXML
    public void addToCart(ActionEvent actionEvent) {
        if(product != null && quantity.getValue() > 0) {
            CustomerService.addToCart(product, quantity.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Product added to Cart");
            alert.showAndWait();
        }
    }

    @FXML
    public void removeFromCart(ActionEvent actionEvent) {
        if(product != null && CustomerService.getCurrentCustomer().getCart().getProducts().contains(product)) {
            CustomerService.removeFromCart(product, quantity.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Product removed from Cart");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Something went wrong!");
        alert.showAndWait();
    }

    @FXML
    public void addToInterests(ActionEvent actionEvent) {
        if(product != null) {
            CustomerService.addToInterests(product);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Product added to Interests");
            alert.showAndWait();
        }
    }
    @FXML
    public void removeFromInterests(ActionEvent actionEvent) {
        if(product != null) {
            CustomerService.removeFromInterests(product);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Product removed from Interests");
            alert.showAndWait();
        }
    }
    @FXML
    public void goToDetails(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/detailedProductCard.fxml"));
        loader.load();

    }

}
