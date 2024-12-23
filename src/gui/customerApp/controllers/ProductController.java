//package src.gui.customerApp.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.geometry.Pos;
//import org.controlsfx.control.Notifications;
//import javafx.scene.control.*;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import src.entities.Product;
//import src.service.CustomerService;
//
//public class ProductController {
//
//
//    Product product;
//
//    SpinnerValueFactory<Integer> valueFactory;
//
//    @FXML
//    private Button btnAddToCart;
//
//    @FXML
//    private Button btnAddToInterests;
//
//    @FXML
//    private Button btnBack;
//
//    @FXML
//    private ImageView imgProductDetail;
//
//    @FXML
//    private Label lblProductCategory;
//
//    @FXML
//    private Label lblProductDescription;
//
//    @FXML
//    private Label lblProductName;
//
//    @FXML
//    private Label lblProductPrice;
//
//    @FXML
//    private Label lblProductStock;
//
//    @FXML
//    private AnchorPane detailedCard;
//
//    @FXML
//    private Spinner<Integer> spinnerQuantity;
//
//
//    @FXML
//    void back(ActionEvent event) {
//        DashboardController.goToHome(event);
//    }
//
//    @FXML
//    AnchorPane detailedCard(){
//        if (this.product != null) {
//            lblProductCategory.setText(this.product.getCategory().getName());
//            lblProductDescription.setText(this.product.getDescription());
//            lblProductName.setText(this.product.getName());
//            lblProductPrice.setText(String.valueOf(this.product.getPrice()));
//            lblProductStock.setText(String.valueOf(this.product.getStock()));
//            imgProductDetail.setImgPath(product.getImg());
//            spinnerQuantity.setValueFactory(valueFactory);
//            return detailedCard;
//        }
//        return detailedCard;
//    }
//
//
///////////////////////////////////////////////////////////////////////////////
//    //product card
//////////////////////////////////////////////////////////////////////////////
//
//    @FXML public AnchorPane productCard;
//    @FXML
//    private Button addToCart;
//
//    @FXML
//    private Button handleInterests;
//
//    @FXML
//    private Button expand;
//
//    @FXML
//    private ImageView img;
//
//    @FXML
//    private Label name;
//
//    @FXML
//    private Label price;
//
//    @FXML
//    private Spinner<Integer> quantity;
//
//    AnchorPane create(Product product) {
//        this.product = product;
//        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,product.getStock(),1);
//        name.setText(this.product.getName());
//        price.setText(String.valueOf(this.product.getPrice()));
//        img.setImgPath(this.product.getImg());
//        quantity.setValueFactory(valueFactory);
//        return this.productCard;
//
//    }
//
//    @FXML
//    void addToCart(ActionEvent event) {
//        CustomerService.getCurrentCustomer().getCart().getItems().add(product);
//        int s = this.product.getStock();
//        if (event.getSource() == spinnerQuantity) {
//        product.setStock(s-spinnerQuantity.getValue());
//        Notifications.create().text("Added to Cart").position(Pos.BOTTOM_RIGHT).showInformation();
//        return;
//        }
//        product.setStock(s-quantity.getValue());
//        Notifications.create().text("Added to Cart").position(Pos.BOTTOM_RIGHT).showInformation();
//    }
//
//    @FXML
//    void handleInterests(ActionEvent event) {
//        CustomerService.getCurrentCustomer().getInterests().add(product);
//        Notifications.create().text("Added to Interests").position(Pos.BOTTOM_RIGHT).showInformation();
//    }
//
//    @FXML
//    AnchorPane expand(ActionEvent event) {
//        DashboardController.homeBtn.setDisable(false);
//       return detailedCard();
//    }
//
////////////////////////////////////////////////
//
//
//}



package src.gui.customerApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import src.entities.Product;
import src.service.CustomerService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

public class ProductController {

    @FXML public ImageView interested;
    @FXML public ImageView productCardInterest;
    private Product product;

    private SpinnerValueFactory<Integer> valueFactory;
    private Stage detailedProductStage = new Stage();
    Image interest = new Image(Objects.requireNonNull(getClass().getResource("../../utils/icon/star.png")).toExternalForm());
    Image notInterested = new Image(getClass().getResource("../../utils/icon/favorite.png").toExternalForm());

    @FXML private Button btnAddToCart;
    @FXML private Button btnAddToInterests;
    @FXML private Button btnBack;
    @FXML private ImageView imgProductDetail;
    @FXML private Label lblProductCategory;
    @FXML private Label lblProductDescription;
    @FXML private Label lblProductName;
    @FXML private Label lblProductPrice;
    @FXML private Label lblProductStock;
    @FXML public AnchorPane detailedCard;
    @FXML private Spinner<Integer> spinnerQuantity;

    @FXML public AnchorPane productCard;
    @FXML private Button addToCart;
    @FXML private Button addToInterests;
    @FXML private Button expand;
    @FXML private ImageView img;
    @FXML private Label name;
    @FXML private Label price;
    @FXML private Spinner<Integer> quantity;

    public ProductController() throws MalformedURLException {
    }

    @FXML
    public void initialize() {

    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            name.setText(product.getName());
            price.setText(String.format("$%.2f", product.getPrice()));
            if(product.getImgPath() != null)
                img.setImage(new Image("file:///" + product.getImgPath().replace("\\", "/")));
            valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getStock(),1);
            quantity.setValueFactory(valueFactory);
            if (CustomerService.getCurrentCustomer().getInterests().contains(product)){
                productCardInterest.setImage(interest);}
            else
                productCardInterest.setImage(notInterested);
        }
    }
    public void setDetailedProduct(Product product) throws IOException {
        this.product = product;

        // Update UI elements with product details
        if (product != null) {
            lblProductCategory.setWrapText(true);
            lblProductCategory.setText("Category: "+product.getCategory().getName());
            lblProductDescription.setText(product.getDescription());
            lblProductName.setText(product.getName());
            lblProductPrice.setWrapText(true);
            lblProductPrice.setText("Price: "+String.format("$%.2f", product.getPrice()));
            lblProductStock.setText("in Stock "+ product.getStock());
            imgProductDetail.setImage( new Image("file:///" + product.getImgPath().replace("\\", "/")));
            valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getStock(), 1);
            spinnerQuantity.setValueFactory(valueFactory);
            if (CustomerService.getCurrentCustomer().getInterests().contains(product)){
                interested.setImage(interest);}
            else
                interested.setImage(notInterested);
        }
    }

    @FXML
    void addToCart(ActionEvent event) {
        if (event.getSource() == btnAddToCart) {
            if (product.getStock() <= 0){
                Notifications.create()
                        .text(product.getName()+" out of stock!").position(Pos.BOTTOM_RIGHT).showWarning();
                return;
            }
            CustomerService.addToCart(product, spinnerQuantity.getValue());
            product.setStock(product.getStock() - spinnerQuantity.getValue());
            lblProductStock.setText(String.valueOf(product.getStock()));
            Notifications.create()
                    .text(product.getName() + " added to cart!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
            return;
        }
        if (event.getSource() == addToCart) {
            if (product.getStock() <= 0){
                Notifications.create()
                        .text(product.getName()+" out of stock!").position(Pos.BOTTOM_RIGHT).showWarning();
                return;
            }
            CustomerService.addToCart(product, quantity.getValue());
            product.setStock(product.getStock() - quantity.getValue());
            Notifications.create()
                    .text(product.getName() + " added to cart!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
        }

    }

    @FXML
    void handleInterests(ActionEvent event) {
        if (interested != null) {
            if (interested.getImage().equals(notInterested )) {
                CustomerService.addToInterests(product);
                Notifications.create()
                        .text(product.getName() + " added to interests!")
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                interested.setImage(interest);
            }
            else {
                CustomerService.removeFromInterests(product);
                Notifications.create()
                        .text(product.getName() + " removed from interests!")
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                interested.setImage(notInterested);
            }
            return;

        }
        if (productCardInterest.getImage().equals(notInterested)) {
            CustomerService.addToInterests(product);
            Notifications.create()
                    .text(product.getName() + " added to interests!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
            productCardInterest.setImage(interest);
        }
        else {
            CustomerService.removeFromInterests(product);
            Notifications.create()
                    .text(product.getName() + " removed from interests!")
                    .position(Pos.BOTTOM_RIGHT)
                    .showInformation();
            productCardInterest.setImage(notInterested);
        }
    }
    @FXML void expand(ActionEvent event) throws IOException {
        if (this.product != null) {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../views/detailedProductCard.fxml")));
            AnchorPane anchorPane = loader.load();
            ProductController controller =loader.getController();
            controller.setDetailedProduct(this.product);

            detailedProductStage.setScene(new Scene(anchorPane));
            detailedProductStage.initModality(Modality.APPLICATION_MODAL);
            detailedProductStage.show();
            detailedProductStage.centerOnScreen();
            detailedProductStage.setTitle(this.product.getName());
            btnBack.setOnAction(event1 -> detailedProductStage.close());

        }

    }
}

