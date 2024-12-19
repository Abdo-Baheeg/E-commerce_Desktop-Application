package src.gui.customerApp.controllers;

import com.sun.javafx.stage.WindowCloseRequestHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import src.DAO.AdminDAO;
import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Product;
import src.service.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Button addCreditsBtn;
    public GridPane productsGrid;
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
    public BorderPane mainBorderPane;

    @FXML
    private Button ordersBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    public DashboardController() throws IOException {
    }


    @FXML
    void addCredits(ActionEvent event) throws IOException {
        goToView("../views/addCredits.fxml",addCreditsBtn);
    }

    @FXML
    void goToCart(ActionEvent event) {
        cartBtn.setDisable(true);
        TableView<Product> tableView = new TableView<>();
        ObservableList<Product> products = (ObservableList<Product>) CustomerService.getCurrentCustomer().getCart();

        // Name Column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        // Price Column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPrice()));

        // Quantity Column
        TableColumn<Product, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStock()));

        // Button Column
        TableColumn<Product, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Add to Cart");

            {
                button.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    System.out.println("Added to cart: " + product.getName());
                    // Perform your action here (e.g., add the product to a cart)
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });

        tableView.getColumns().addAll(nameColumn, priceColumn, actionColumn);
        tableView.setItems(products);
        mainBorderPane.setCenter(tableView);

    }

    @FXML
    void goToHome(ActionEvent event) {
        initProductsGrid();
    }

    @FXML
    void goToInterests(ActionEvent event) {

    }

    @FXML
    void goToOrders(ActionEvent event) throws IOException {
        goToView("../views/profile.fxml",profileBtn);
    }

    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        goToView("../views/profile.fxml",profileBtn);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Database.saveDatabase();
           Stage stage= (Stage) logoutBtn.getScene().getWindow();
           stage.close();
        }
    }


    @FXML
    void search(ActionEvent event) {
        String search = searchField.getText();
        ProductDAO productDAO = new ProductDAO();
        productsGrid.getChildren().clear();
        ArrayList<Product> found = ProductDAO.search(search);
        if (found.isEmpty()) {
            productsGrid.getChildren().add(new Label("No products found"));
        }
        else {
                initProductsGrid();

        }
    }

    private void initProductsGrid() {
        for (Product product : products) {

        }
    }

    @FXML
        private Button goToHomeBtn;
        private final BorderPane productCard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/product card.fxml")));
        private final AdminDAO dao = new AdminDAO();
        private final CustomerDAO cdao = new CustomerDAO();
        private final ProductDAO pdao = new ProductDAO();
        private static final ArrayList<Product> products = Database.products;
        static GridPane productGrid = new GridPane();



        public static void initialize() throws IOException {
            productGrid.setHgap(10);
            productGrid.setVgap(10);
            for(int i = 0; i < products.size(); i++){
                ProductController p = new ProductController();
              //  productGrid.add(p.createCard(products.get(i)),i%4,i/4);
            }
        }

    @FXML
    private void goToView(String fxmlPath, Button activeButton) throws IOException {
        try {
            // Enable all buttons first
            homeBtn.setDisable(false);
            cartBtn.setDisable(false);
            interestsBtn.setDisable(false);
            ordersBtn.setDisable(false);
            addCreditsBtn.setDisable(false);
            profileBtn.setDisable(false);

            // Disable the currently active button
            activeButton.setDisable(true);

            // Load the specified view
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            mainBorderPane.setCenter(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initProductsGrid();
        balanceLabel.setText(String.valueOf(CustomerService.getCurrentCustomer().getBalance()));

    }
}





