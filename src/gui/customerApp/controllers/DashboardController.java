package src.gui.customerApp.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.*;
import src.service.CustomerService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;

public class DashboardController implements Initializable {

    @FXML public  Button addCreditsBtn;
    @FXML public GridPane productsGrid;

    @FXML
    private Label balanceLabel;

    @FXML
    private  Button cartBtn;

    @FXML
    public  Button homeBtn;

    @FXML
    private  Button interestsBtn;

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


    @FXML
    void addCredits(ActionEvent event) throws IOException {
        goToView("../views/addCredits.fxml",addCreditsBtn);

    }

    @FXML
    void goToHome(ActionEvent event) {
        homeBtn.setDisable(true);
        interestsBtn.setDisable(false);
        profileBtn.setDisable(false);
        cartBtn.setDisable(false);
        ordersBtn.setDisable(false);
        addCreditsBtn.setDisable(false);
        populateGridPane();
        ScrollPane pane = new ScrollPane(productsGrid);
        mainBorderPane.setCenter(pane);
    }

    @FXML
    void goToInterests(ActionEvent event) {
        // Disable the interests button and enable others
        interestsBtn.setDisable(true);
        homeBtn.setDisable(false);
        profileBtn.setDisable(false);
        cartBtn.setDisable(false);
        ordersBtn.setDisable(false);
        addCreditsBtn.setDisable(false);

        // Create the AnchorPane and TableView
        AnchorPane pane = new AnchorPane();
        TableView<Product> tableView = new TableView<>();
        tableView.setPrefWidth(1100);

        // Get customer interests
        ObservableList<Product> interests = FXCollections.observableArrayList(CustomerService.getCurrentCustomer().getInterests());
        tableView.setItems(interests);

        // Define columns
        TableColumn<Product, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        nameColumn.setPrefWidth(300);

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPrice()));
        priceColumn.setPrefWidth(200);

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStock()));
        stockCol.setPrefWidth(200);

        TableColumn<Product, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory().getName()));

        // Action column with Remove button
        TableColumn<Product, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setPrefWidth(200);
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Remove");

            {
                // Button styling
                button.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                button.setOnAction(e -> {
                    Product product = getTableView().getItems().get(getIndex());
                    interests.remove(product); // Remove the product from the list
                    CustomerService.getCurrentCustomer().getInterests().remove(product); // Update the backend list
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

        // Add columns to TableView
        tableView.getColumns().addAll(nameColumn, categoryColumn, priceColumn, stockCol, actionColumn);

        // Add TableView to pane and set it in the center of BorderPane
        pane.getChildren().add(tableView);
        AnchorPane.setTopAnchor(tableView, 10.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setRightAnchor(tableView, 10.0);
        AnchorPane.setBottomAnchor(tableView, 10.0);
        mainBorderPane.setCenter(pane);
    }


    @FXML
    void goToOrders(ActionEvent event) throws IOException {
        interestsBtn.setDisable(false);
        homeBtn.setDisable(false);
        profileBtn.setDisable(false);
        cartBtn.setDisable(false);
        ordersBtn.setDisable(true);
        addCreditsBtn.setDisable(false);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox ordersContainer = new VBox(15); // Container for the cards
        ordersContainer.setPadding(new Insets(20));
        ordersContainer.setAlignment(Pos.TOP_CENTER);

        ArrayList<Order> orders = CustomerService.getCurrentCustomer().getOrders();

        if (orders.isEmpty()) {
            Label emptyLabel = new Label("No Orders to Display");
            emptyLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #555555;");
            ordersContainer.getChildren().add(emptyLabel);
        } else {
            for (Order order : orders) {
                AnchorPane orderCard = createOrderCard(order);
                ordersContainer.getChildren().add(orderCard);
            }
        }

        scrollPane.setContent(ordersContainer);
        mainBorderPane.setCenter(scrollPane);
    }

    private AnchorPane createOrderCard(Order order) {
        AnchorPane orderCard = new AnchorPane();
        orderCard.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");
        orderCard.setPrefSize(800, 200);

        // Order Header (Total Price, Date, Status)
        Label totalPriceLabel = new Label("Total Price: $" + order.getTotalPrice());
        totalPriceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label dateLabel = new Label("Date: " + order.getDate());
        dateLabel.setStyle("-fx-font-size: 14px;");

        Label statusLabel = new Label(order.getStatus().name());
        statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: " + getStatusColor(order.getStatus()) + ";");

        HBox headerBox = new HBox(15, totalPriceLabel, dateLabel, statusLabel);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        // Product List
        VBox productList = new VBox(5);
        productList.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9; -fx-border-radius: 5;");
        for (CartItem product : order.getItems()) {
            Label productLabel = new Label("- " + product.getProduct().getName() + " ($" + product.getProduct().getPrice() + ") x"+ product.getQuantity());
            productLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
            productList.getChildren().add(productLabel);
        }

        // Layout
        VBox cardContent = new VBox(10, headerBox, productList);
        cardContent.setPadding(new Insets(10));
        cardContent.setAlignment(Pos.TOP_LEFT);

        AnchorPane.setTopAnchor(cardContent, 0.0);
        AnchorPane.setLeftAnchor(cardContent, 0.0);
        AnchorPane.setRightAnchor(cardContent, 0.0);
        AnchorPane.setBottomAnchor(cardContent, 0.0);
        orderCard.getChildren().add(cardContent);

        return orderCard;
    }

    private String getStatusColor(Order.Status status) {
        switch (status) {
            case PENDING: return "#ff9800";  // Orange
            case CONFIRMED: return "#4caf50"; // Green
            case DELIVERED: return "#2196f3"; // Blue
            default: return "#555555"; // Default gray
        }
    }



    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        cartBtn.setDisable(false);
        interestsBtn.setDisable(false);
        ordersBtn.setDisable(false);
        addCreditsBtn.setDisable(false);
        profileBtn.setDisable(true);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        VBox profileContainer = new VBox(30);
        profileContainer.setPadding(new Insets(30));
        profileContainer.setAlignment(Pos.TOP_CENTER);

        Customer currentCustomer = CustomerService.getCurrentCustomer();

        // Photo Section
        ImageView profilePhoto = new ImageView();
        if(CustomerService.getCurrentCustomer().getImgPath() != null){
           Image img =new Image("file:///" + currentCustomer.getImgPath().replace("\\", "/"));
           profilePhoto.setImage(img);
        }
        profilePhoto.setFitHeight(400);
        profilePhoto.setFitWidth(600);
        profilePhoto.setPreserveRatio(true);
        profilePhoto.setStyle("-fx-border-color: #4caf50; -fx-border-width: 2px; -fx-border-radius: 100px; -fx-background-radius: 100px;");

        Button changePhotoBtn = new Button("Change Photo");
        changePhotoBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-border-radius: 20px; -fx-background-radius: 20px;");
        changePhotoBtn.setOnAction(event1 -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                currentCustomer.setImgPath(selectedFile.getAbsolutePath());
                profilePhoto.setImage(new Image("file:" + currentCustomer.getImgPath()));
            }
        });

        VBox photoSection = new VBox(10, profilePhoto, changePhotoBtn);
        photoSection.setAlignment(Pos.CENTER);

        // Editable Fields
        TextField nameField = createStyledTextField(currentCustomer.getName(), "Name");
        DatePicker dobPicker = new DatePicker(currentCustomer.getDateOfBirth());
        dobPicker.setPromptText("Date of Birth");
        dobPicker.setStyle("-fx-font-size: 16px; -fx-padding: 8px; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setValue(currentCustomer.getGender().name());
        genderBox.setStyle("-fx-font-size: 16px; -fx-padding: 8px; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        TextField addressField = createStyledTextField(currentCustomer.getAddress(), "Address");
        TextField phoneField = createStyledTextField(currentCustomer.getPhone(), "Phone Number");
        TextField emailField = createStyledTextField(currentCustomer.getEmail(), "Email");

        PasswordField passwordField = createStyledPasswordField("Password");
        PasswordField confirmPasswordField = createStyledPasswordField("Confirm Password");

        VBox fieldsSection = new VBox(15,
                createLabeledField("Name:", nameField),
                createLabeledField("Date of Birth:", dobPicker),
                createLabeledField("Gender:", genderBox),
                createLabeledField("Address:", addressField),
                createLabeledField("Phone Number:", phoneField),
                createLabeledField("Email:", emailField),
                createLabeledField("Password:", passwordField),
                createLabeledField("Confirm Password:", confirmPasswordField)
        );

// Save Button
        Button saveBtn = new Button("Save Changes");
        saveBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-size: 16px; -fx-border-radius: 20px; -fx-background-radius: 20px;");

        saveBtn.setOnAction(event2 -> {
            // Validate fields
            if (!validateField(nameField.getText(), "Invalid Name", "Please enter a valid name.", CustomerService::validName)) {
                nameField.setStyle("-fx-border-color: red;");
                return;
            }
            if (!validateField(dobPicker.getValue(), "Invalid Date of Birth", "You must be older than 16 years!", CustomerService::validDateOfBirth)) {
                dobPicker.setStyle("-fx-border-color: red;");
                return;
            }
            if (!validateField(addressField.getText(), "Invalid Address", "Please try again with a valid address.", CustomerService::validAddress)) {
                addressField.setStyle("-fx-border-color: red;");
                return;
            }
            if (!validateField(phoneField.getText(), "Invalid Phone Number", "Please enter a valid phone number.", CustomerService::validPhone)) {
                phoneField.setStyle("-fx-border-color: red;");
                return;
            }
            if (!validateField(emailField.getText(), "Invalid Email", "Please enter a valid email address.", CustomerService::validEmail)) {
                emailField.setStyle("-fx-border-color: red;");
                return;
            }
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                showAlert(Alert.AlertType.ERROR, "Password Mismatch", "Passwords do not match!");
                passwordField.setStyle("-fx-border-color: red;");
                confirmPasswordField.setStyle("-fx-border-color: red;");
                return;
            }
            if (!validateField(passwordField.getText(), "Weak Password", "Password must contain at least 6 characters, including uppercase, lowercase, numbers, and special characters.", CustomerService::validPassword)) {
                passwordField.setStyle("-fx-border-color: red;");
                return;
            }

            // Reset invalid styles
            resetFieldStyles(nameField, dobPicker, addressField, phoneField, emailField, passwordField, confirmPasswordField);

            // Update customer details
            currentCustomer.setName(nameField.getText());
            currentCustomer.setDateOfBirth(dobPicker.getValue());
            currentCustomer.setGender(Person.Gender.valueOf(genderBox.getValue().toUpperCase()));
            currentCustomer.setAddress(addressField.getText());
            currentCustomer.setPhone(phoneField.getText());
            currentCustomer.setEmail(emailField.getText());
            currentCustomer.setPassword(passwordField.getText());

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Profile Updated", "Your profile has been updated successfully!");
        });


        // Layout
        profileContainer.getChildren().addAll(photoSection, fieldsSection, saveBtn);
        scrollPane.setContent(profileContainer);
        mainBorderPane.setCenter(scrollPane);
    }
// Utility Methods

        private <T> boolean validateField(T fieldValue, String errorTitle, String errorMessage, Function<T, Boolean> validator) {
            if (!validator.apply(fieldValue)) {
                showAlert(Alert.AlertType.ERROR, errorTitle, errorMessage);
                return false;
            }
            return true;
        }

        private void resetFieldStyles(Control... fields) {
            for (Control field : fields) {
                field.setStyle(null);
            }
        }


    private HBox createLabeledField(String labelText, Node inputField) {
        Label label = new Label(labelText);
        label.setPrefWidth(150);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        HBox fieldContainer = new HBox(10, label, inputField);
        fieldContainer.setAlignment(Pos.CENTER_LEFT);
        return fieldContainer;
    }

    private TextField createStyledTextField(String value, String promptText) {
        TextField textField = new TextField(value);
        textField.setPromptText(promptText);
        textField.setStyle("-fx-font-size: 16px; -fx-padding: 8px; -fx-border-color: #cccccc; -fx-border-radius: 5px;");
        return textField;
    }

    private PasswordField createStyledPasswordField(String promptText) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        passwordField.setStyle("-fx-font-size: 16px; -fx-padding: 8px; -fx-border-color: #cccccc; -fx-border-radius: 5px;");
        return passwordField;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        homeBtn.setDisable(false);
        String search = searchField.getText();
        ProductDAO productDAO = new ProductDAO();
        productsGrid.getChildren().clear();
        productsGrid.setHgap(10);
        productsGrid.setVgap(10);

        ArrayList<Product> found = ProductDAO.search(search);
        if (found.isEmpty()) {
            productsGrid.getChildren().add(new Label("No products found"));
        }
        else {
            ObservableList<Product> foundProducts = FXCollections.observableArrayList(found);
            int col = 0;
            int row = 0;

            try {
                for (Product product : foundProducts) {
                    // Load the FXML for the product card
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/product card.fxml"));
                    AnchorPane productCard = loader.load();

                    // Get the controller from the loader
                    ProductController controller = loader.getController();

                    // Set the product in the controller
                    controller.setProduct(product);

                    // Add the product card to the grid pane
                    productsGrid.add(productCard, col, row);

                    // Update column and row indices for the grid
                    col++;
                    if (col == 4) { // Adjust for your desired number of columns
                        col = 0;
                        row++;
                    }
                    searchField.clear();
                    ScrollPane pane = new ScrollPane(productsGrid);
                    mainBorderPane.setCenter(pane);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static final ArrayList<Product> products = Database.products;

    private void populateGridPane() {
        ObservableList<Product> p = FXCollections.observableList(products);
        int col = 0;
        int row = 0;

        try {
            for (Product product : p) {
                // Load the FXML for the product card
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/product card.fxml"));
                AnchorPane productCard = loader.load();

                // Get the controller from the loader
                ProductController controller = loader.getController();

                // Set the product in the controller
                controller.setProduct(product);

                // Add the product card to the grid pane
                productsGrid.add(productCard, col, row);

                // Update column and row indices for the grid
                col++;
                if (col == 4) { // Adjust for your desired number of columns
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToView(String fxmlPath, Button activeButton) throws IOException {
        try {
            // Enable all buttons first
           // homeBtn.setDisable(false);
            cartBtn.setDisable(false);
            interestsBtn.setDisable(false);
            ordersBtn.setDisable(false);
            addCreditsBtn.setDisable(false);
            profileBtn.setDisable(false);

            // Disable the currently active button
            activeButton.setDisable(true);

            // Load the specified view
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(DashboardController.class.getResource(fxmlPath)));
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
       populateGridPane();
       balanceLabel.setText(String.valueOf(CustomerService.getCurrentCustomer().getBalance()));

    }

    ////////////////////////////cart table ////

    Label totalPriceLabel = new Label("Total: $0.00");
    @FXML
    void goToCart(ActionEvent event) {
        interestsBtn.setDisable(false);
        homeBtn.setDisable(false);
        profileBtn.setDisable(false);
        cartBtn.setDisable(true);
        ordersBtn.setDisable(false);
        addCreditsBtn.setDisable(false);

        ObservableList<CartItem> cartItems = FXCollections.observableArrayList(CustomerService.getCurrentCustomer().getCart().getProducts());

        // Create TableView
        TableView<CartItem> tableView = new TableView<>(cartItems);

        // Product Name Column
        TableColumn<CartItem, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProduct().getName()));
        productNameColumn.setPrefWidth(200);

        // Category Column
        TableColumn<CartItem, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProduct().getCategory().getName()));
        categoryColumn.setPrefWidth(150);

        // Price Column
        TableColumn<CartItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getProduct().getPrice()).asObject());
        priceColumn.setPrefWidth(100);

        // Quantity Column
        TableColumn<CartItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setPrefWidth(100);

        // Action Column (Remove Button)
        TableColumn<CartItem, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setPrefWidth(120);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                removeButton.setOnAction(event -> {
                    CartItem currentItem = getTableView().getItems().get(getIndex());
                    cartItems.remove(currentItem); // Remove item from the list
                    CustomerService.getCurrentCustomer().getCart().getProducts().remove(currentItem);
                    updateTotalPrice(cartItems); // Update the total price
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(5, removeButton));
                }
            }
        });

        // Add Columns to TableView
        tableView.getColumns().addAll(productNameColumn, categoryColumn, priceColumn, quantityColumn, actionColumn);

        // Set TableView Properties
        tableView.setPrefSize(600, 400);
        tableView.setStyle("-fx-font-size: 14px; -fx-background-color: #f9f9f9; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        // Create Total Price Label
        totalPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        updateTotalPrice(cartItems); // Initial total price

        // Create Buttons
        Button checkOutBtn = new Button("Check out");
        checkOutBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        checkOutBtn.setOnAction(_ -> {
            if (CustomerService.checkout()){
                Notifications.create()
                        .title("Success")
                        .text("You have successfully checked out!\nYour Order is under Processing")
                        .showInformation();
                cartItems.clear(); // Clear the cart after checkout
                updateTotalPrice(cartItems);
                balanceLabel.setText(String.valueOf(CustomerService.getCurrentCustomer().getBalance()));
            }
            else {
                Notifications.create()
                        .title("Invalid Order")
                        .text("check your balance and try again!")
                        .showInformation();
            }
        });

        Button clearCartBtn = new Button("Clear Cart");
        clearCartBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        clearCartBtn.setOnAction(_ -> {
            CustomerService.deleteCart();
            cartItems.clear(); // Clear the cart
            updateTotalPrice(cartItems);
        });

        // Layout for Buttons
        HBox buttonBox = new HBox(20, checkOutBtn, clearCartBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPrefHeight(50);

        // Layout for Total Price
        HBox totalPriceBox = new HBox(totalPriceLabel);
        totalPriceBox.setAlignment(Pos.CENTER_RIGHT);
        totalPriceBox.setPadding(new Insets(10, 20, 10, 20));

        // Main Layout
        VBox mainBox = new VBox(10, tableView, totalPriceBox, buttonBox);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 10px;");

        // Add to BorderPane
        mainBorderPane.setCenter(mainBox);
    }

    // Method to Update Total Price in cart page
    private void updateTotalPrice(ObservableList<CartItem> cartItems) {
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        totalPriceLabel.setText(String.format("Total: $%.2f", totalPrice));
    }

}
