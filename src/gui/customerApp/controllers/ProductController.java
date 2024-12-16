package src.gui.customerApp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.entities.Product;

import java.io.IOException;
import java.util.List;

public class ProductController {
    public Button btnAddToCart;
    public Spinner spinnerQuantity;
    public Button btnAddToInterests;
    public Button btnBack;
    public Label lblProductDescription;
    public ImageView imgProductDetail;
    public Label lblProductStock;
    public Label lblProductPrice;
    public Label lblProductCategory;
    public Label lblProductName;
    public AnchorPane rootPane;
    // Product Card View Fields
    @FXML private Label name;
    @FXML private Label price;
    @FXML private ImageView img;
    @FXML private Button addToInterests;
    @FXML private Button removeFromCart;
    @FXML private Button addToCart;
    @FXML private Spinner<Integer> quantity;

    // Product Detail View Fields
    @FXML private AnchorPane mainPane;
    @FXML private Label ProductName;
    @FXML private Label productCategory;
    @FXML private Label productPrice;
    @FXML private Label productStock;
    @FXML private Label productDescription;
    @FXML private ImageView productImg;
    @FXML private Button backBtn;
    @FXML private Button addToInterestsBtn;
    @FXML private Button addToCartBtn;
    @FXML private Spinner<Integer> detailQuantity;

    // Current product
    private Product currentProduct;

    // Initialize method called by JavaFX after FXML is loaded
    @FXML
    public void initialize() {
        // Setup quantity spinners
        setupQuantitySpinner(quantity);
        setupQuantitySpinner(detailQuantity);
    }

    // Utility method to setup quantity spinner
    private void setupQuantitySpinner(Spinner<Integer> spinner) {
        if (spinner != null) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
            spinner.setValueFactory(valueFactory);
        }
    }

    // Method to create a product card as an AnchorPane
    public static AnchorPane createProductCard(Product product) {
        try {
            // Load the FXML for the product card
            FXMLLoader loader = new FXMLLoader(ProductController.class.getResource("/path/to/your/ProductCard.fxml"));
            AnchorPane productCard = loader.load();

            // Get the controller and set the product data
            ProductController controller = loader.getController();
            controller.setProductForCard(product);

            return productCard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Set product data to the card
    public void setProductForCard(Product product) {
        this.currentProduct = product;

        // Set name and price
        name.setText(product.getName());
        price.setText(String.format("$%.2f", product.getPrice()));

        // Set product image
        if (product.getImgPath() != null) {
            img.setImage(new Image(product.getImgPath()));
        }
    }

    // Method to open detailed product view popup
    @FXML
    public void goToDetails() {
        try {
            // Load the detailed product view FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/your/ProductDetailView.fxml"));
            Parent detailView = loader.load();

            // Get the controller for the detail view
            ProductController detailController = loader.getController();
            detailController.setProductForDetailView(currentProduct);

            // Create and configure the popup stage
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Product Details");

            // Create scene and show
            Scene scene = new Scene(detailView);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Set product details in the detail view
    public void setProductForDetailView(Product product) {
        this.currentProduct = product;

        // Set all labels and image
        ProductName.setText(product.getName());
        productCategory.setText(product.getCategory());
        productPrice.setText(String.format("$%.2f", product.getPrice()));
        productStock.setText(product.getStock() + " in stock");
        productDescription.setText(product.getDescription());

        // Set product image
        if (product.getImgPath() != null) {
            productImg.setImage(new Image(product.getImgPath()));
        }
    }

    // Method to handle back button in detail view
    @FXML
    public void back() {
        // Close the current stage (popup window)
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    // Method to add product to interests (works for both card and detail views)
    @FXML
    public void addToInterests() {
        if (currentProduct != null) {
            // Implement logic to add product to user's interests
            System.out.println("Added to interests: " + currentProduct.getName());
            // You might want to call a service or update a model here
        }
    }

    // Method to remove from cart (for product card)
    @FXML
    public void removeFromCart() {
        if (currentProduct != null) {
            // Implement logic to remove product from cart
            System.out.println("Removed from cart: " + currentProduct.getName());
        }
    }

    // Method to add product to cart (works for both card and detail views)
    @FXML
    public void addToCart() {
        if (currentProduct != null) {
            // Get selected quantity (check which spinner is available)
            Spinner<Integer> activeQuantitySpinner = quantity != null ? quantity : detailQuantity;
            int selectedQuantity = activeQuantitySpinner.getValue();

            // Implement logic to add product to cart
            System.out.println("Added to cart: " + currentProduct.getName()
                    + ", Quantity: " + selectedQuantity);
            // You might want to call a cart service or update a cart model here
        }
    }

}