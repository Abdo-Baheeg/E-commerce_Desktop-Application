package src.gui.customerApp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import src.entities.Product;
import src.service.CustomerService;

import javax.management.Notification;

public class ProductController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblProductCategory;

    @FXML
    private Label lblProductPrice;

    @FXML
    private Label lblProductStock;

    @FXML
    private ImageView imgProductDetail;

    @FXML
    private Label lblProductDescription;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAddToInterests;

    @FXML
    private Spinner<Integer> spinnerQuantity;

    @FXML
    private Button btnAddToCart;

    // Reference to the associated product
    private Product product;

//    // Initialize the product details in the card
//    public void setProductDetails(Product product) {
//        this.product = product;
//
//        // Set the product's information in the respective UI components
//        lblProductName.setText(product.getName());
//        lblProductCategory.setText(product.getCategory());
//        lblProductPrice.setText(product.getPriceString());
//        lblProductStock.setText(product.getStock() > 0 ? "In Stock" : "Out of Stock");
//        lblProductDescription.setText(product.getDescription());
//
//        // Set the product image
//        if (product.getImg() != null) {
//            imgProductDetail.setImage(product.getImg());
//        } else {
//            imgProductDetail.setImage(new Image("default-image.png")); // Default image
//        }
//
//        // Initialize the spinner for quantity
//        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getStock(), 1);
//        spinnerQuantity.setValueFactory(valueFactory);
//    }

    // Handle the Back button click
    @FXML
    private void back() {
        System.out.println("Back button clicked");
        // Logic for navigating back (e.g., to the product list view)
    }

    // Handle the Add to Interests button click
    @FXML
    private void addToInterests() {
        if (product != null) {
            System.out.println("Added to interests: " + product.getName());
            // Add the product to the user's interests
            // Example: InterestsService.addToInterests(product);
        }
    }




    /////////////////////////////////////////////////////////////////////////////
    //product card
    ////////////////////////////////////////////////////////////////////////////



    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Button addToCartButton;

    // Method to initialize the card with product details
    public void setProductDetails(Product product) {
        this.product = product;

        // Set product details on the card
        productName.setText(product.getName());
        productPrice.setText(product.getPriceString());
        if (product.getImg() != null) {
            productImage.setImage(product.getImg());
        } else {
            // Set a default image if none is provided
            productImage.setImage(new Image("default-image.png"));
        }
    }

    // Event handler for the "Add to Cart" button
    @FXML
    private void addToCart() {
        if (product != null) {
            CustomerService.getCurrentCustomer().getCart().getProducts().add(product);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add to cart");
            alert.setHeaderText(null);
            alert.setContentText("Added to cart successfully");
            alert.showAndWait();

            Notifications.create()
                    .title("Product Added")
                    .text("The product has been added to your cart.")
                    .position(Pos.TOP_RIGHT) // Position on screen
                    .showInformation();

        }
    }
}
