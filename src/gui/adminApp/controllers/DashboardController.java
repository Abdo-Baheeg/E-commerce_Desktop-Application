package src.gui.adminApp.controllers;

import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.Notifications;
import org.jetbrains.annotations.NotNull;
import src.DAO.CategoryDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.*;
import src.service.AdminService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javafx.util.converter.DoubleStringConverter;

import static src.database.Database.*;


public class DashboardController implements Initializable {

    @FXML
    public Button goToProfileBtn;
    @FXML
    public Button homeBtn;
    @FXML
    public Button addProductBtn;
    @FXML
    public Button modifyBtn;
    @FXML
    public Button viewCustomersBtn;
    @FXML
    public Button logoutBtn;
    @FXML
    public Label helloLabel;
    @FXML
    public GridPane gridPane;
    @FXML
    public BorderPane mainPane;
    @FXML public Button addCategoryBtn;
    @FXML public Button modifyCategoryBtn;
    @FXML public Button addAdminBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       helloLabel.setText("Hello, " + AdminService.getCurrentAdmin().getName() + "!");
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               Home();
           }
       });
    }

    private void Home() {
        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1100, 600);
        anchorPane.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20px;");

        // Title
        Label title = new Label("Admin Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setAlignment(Pos.TOP_CENTER);

        // Pending Orders Section
        VBox pendingOrdersSection = createPendingOrdersSection();
        mainContent.getChildren().add(pendingOrdersSection);

        // Notifications Section
        VBox notificationsSection = createNotificationsSection();
        mainContent.getChildren().add(notificationsSection);

        // Statistics Section
        VBox statisticsSection = createStatisticsSection();
        mainContent.getChildren().add(statisticsSection);

        // Add main content to AnchorPane
        AnchorPane.setTopAnchor(mainContent, 10.0);
        AnchorPane.setLeftAnchor(mainContent, 10.0);
        AnchorPane.setRightAnchor(mainContent, 10.0);
        AnchorPane.setBottomAnchor(mainContent, 10.0);
        anchorPane.getChildren().add(mainContent);

        mainPane.setCenter(anchorPane);
        disableButton(homeBtn);
    }
    private VBox createPendingOrdersSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(20));
        section.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Label sectionTitle = new Label("Pending Orders");
        sectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        TableView<Order> pendingOrdersTable = new TableView<>();
        pendingOrdersTable.setItems(FXCollections.observableArrayList(pendingOrders));
        pendingOrdersTable.setPrefHeight(200);

        TableColumn<Order, String> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        orderIdColumn.setPrefWidth(200);

        TableColumn<Order, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>((double) cellData.getValue().getTotalPrice()));
        totalPriceColumn.setPrefWidth(150);

        TableColumn<Order, Void> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");
            private final Button refuseButton = new Button("Refuse");

            {
                acceptButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");
                refuseButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    pendingOrders.remove(order);
                    confirmedOrders.add(order);
                    order.setStatus(Order.Status.CONFIRMED);
                    refreshTable(pendingOrdersTable, pendingOrders);
                });

                refuseButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    pendingOrders.remove(order);
                    order.setStatus(Order.Status.CANCELLED);
                    refreshTable(pendingOrdersTable, pendingOrders);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(10, acceptButton, refuseButton));
                }
            }
        });
        actionsColumn.setPrefWidth(200);

        pendingOrdersTable.getColumns().addAll(orderIdColumn, totalPriceColumn, actionsColumn);
        section.getChildren().addAll(sectionTitle, pendingOrdersTable);

        return section;
    }

    private VBox createNotificationsSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(20));
        section.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Label sectionTitle = new Label("Notifications");
        sectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Out of Stock
        Label outOfStockLabel = new Label("Out of Stock Products:");
        List<Product> outOfStockProducts = products.stream()
                .filter(product -> product.getStock() == 0)
                .toList();
        outOfStockProducts.forEach(product -> section.getChildren().add(new Label("- " + product.getName())));

        // Top-Selling
        Label topSellingLabel = new Label("Top-Selling Products:");
        products.stream()
                .sorted(Comparator.comparingInt(Product::getSoldItems).reversed())
                .limit(3)
                .forEach(product -> section.getChildren().add(new Label("- " + product.getName() + " (Sold: " + product.getSoldItems() + ")")));

        section.getChildren().addAll(sectionTitle, outOfStockLabel, topSellingLabel);
        return section;
    }

    private VBox createStatisticsSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(20));
        section.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Label sectionTitle = new Label("Statistics");
        sectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        int totalOrders = pendingOrders.size() + confirmedOrders.size() + deliveredOrders.size();
        double totalRevenue = confirmedOrders.stream().mapToDouble(Order::getTotalPrice).sum()
                + deliveredOrders.stream().mapToDouble(Order::getTotalPrice).sum();

        section.getChildren().addAll(
                sectionTitle,
                new Label("Total Orders: " + totalOrders),
                new Label("Total Revenue: $" + String.format("%.2f", totalRevenue))
        );

        return section;
    }


    @FXML
    public void goToHome(ActionEvent event) throws IOException {
         Home();
    }
    private static <T> void refreshTable(TableView<T> tableView, List<T> list) {
        // Validate inputs
        if (tableView == null || list == null) {
            throw new IllegalArgumentException("TableView and List cannot be null.");
        }

        // Update the existing items
        ObservableList<T> observableList = tableView.getItems();
        observableList.clear();
        observableList.addAll(list);
    }




    @FXML
    public void goToAddProduct(ActionEvent event) throws IOException {
        goToView("../views/addNewProduct.fxml");
        disableButton(addProductBtn);

    }

    @FXML private void goToModifyProduct(ActionEvent event) throws IOException {
        mainPane.setCenter(createProductTable());
        disableButton(modifyBtn);
    }

    @FXML private void goToAddCategory(ActionEvent event) throws IOException {
        goToView("../views/addCategory.fxml");
        disableButton(addCategoryBtn);
    }

    private void goToView(String path) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            mainPane.setCenter(pane);
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Error");
        }
    }

/////////////////////////////////////////////////
    // add Category Controller
/////////////////////////////////////////////////

    @FXML
    public TextField categoryName;
    @FXML
    public TextArea categoryDescription;
    @FXML
    public Button addCategorySubmit;

    @FXML
    private void categorySubmission() {
        String category = categoryName.getText();
        String description = categoryDescription.getText();
        if (!CategoryDAO.validName(category)) {
            Notifications.create().position(Pos.CENTER).text("Invalid Category Name!").showError();
            return;
        }
        if (!CategoryDAO.validDescription(description)) {
            Notifications.create().position(Pos.CENTER).text("Invalid Description!").showError();
            return;
        }
        AdminService.CreateCategory(category, description);
        Notifications.create().position(Pos.CENTER).text("Category Added!").showInformation();
    }

    //////////////////////////////////////////////////////////////////////
    //// add product controller//////////////////////////
    ////////////////////////////////////////////
    @FXML public TextField newProductName;
    @FXML public TextArea newProductDescription;
    @FXML public TextField newProductPrice;
    @FXML public TextField newProductQuantity;
    @FXML public Button choosePhotoBtn;
    @FXML public Label imgPathLbl;
    @FXML public Button submitNewProduct;
    private String imgPath;
    @FXML public ChoiceBox<Category> newProductCategory;

    @FXML public void populateChoiceBox(MouseEvent contextMenuEvent) {
        if (newProductCategory.getItems().isEmpty()) {
            ObservableList<Category> categories = FXCollections.observableArrayList(AdminService.getCategories());
            // Populate the ChoiceBox
            newProductCategory.setItems(categories);
        }
    }

    @FXML
    public void choosePhoto(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Set file extension filters
        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(((Node) actionEvent.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try {

                File targetDir = new File("../../utils/app_Img/");
                if (!targetDir.exists()) {
                    targetDir.mkdirs(); // Create directories if they don't exist
                }

                // Copy the file to the target directory
                File targetFile = new File(targetDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Update the image path label and set the Image object
                imgPathLbl.setText(targetFile.getAbsolutePath());
                imgPath = targetFile.getAbsolutePath();

            } catch (IOException e) {
                e.printStackTrace();
                // Show an alert if the file copying fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to copy the image to the application directory.");
                alert.showAndWait();
            }
        }
    }

    @FXML public void addProduct(ActionEvent event) throws IOException {
        String name = newProductName.getText();
        String description = newProductDescription.getText();
        int quantity = Integer.parseInt(newProductQuantity.getText());
        double price = Double.parseDouble(newProductPrice.getText());
        if (!ProductDAO.validName(name)){
            Notifications.create().position(Pos.CENTER).text("Invalid Product Name!").showError();
            return;
        }
        if (!ProductDAO.validDescription(description)){
            Notifications.create().position(Pos.CENTER).text("Invalid Description!").showError();
            return;
        }
        if (!(price >0) && !(quantity > 0)){
            Notifications.create().position(Pos.CENTER).text("Invalid Product Price or Quantity!").showError();
            return;
        }
        if (newProductCategory.getValue() == null){
            Notifications.create()
                    .text("Select Category!").showWarning();
            return;
        }
        AdminService.createProduct(name,description, (float) price,quantity,newProductCategory.getValue(),imgPath);
        Notifications.create().position(Pos.CENTER).text("Product Added!").showInformation();
        newProductName.clear();
        newProductDescription.clear();
        newProductPrice.clear();
        newProductQuantity.clear();
        imgPathLbl.setText("");

    }


    @FXML public void viewCustomers(ActionEvent actionEvent) {

            disableButton(viewCustomersBtn);

            ObservableList<Customer> customers = FXCollections.observableArrayList(Database.customers);
            // Create TableView
            TableView<Customer> tableView = new TableView<>(customers);

            // Create Columns
            TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setPrefWidth(250);

            TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            addressColumn.setPrefWidth(200);

            TableColumn<Customer, Double> balanceColumn = new TableColumn<>("Balance");
            balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
            balanceColumn.setPrefWidth(100);

            TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone Number");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            phoneColumn.setPrefWidth(200);

            TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            emailColumn.setPrefWidth(250);

            TableColumn<Customer, Person.Gender> genderColumn = new TableColumn<>("Gender");
            genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            genderColumn.setPrefWidth(200);


            // Add Columns to TableView
            tableView.getColumns().addAll(nameColumn, addressColumn, balanceColumn, phoneColumn, emailColumn, genderColumn);

            // Set TableView Properties
            tableView.setPrefSize(600, 400);
            tableView.setStyle("-fx-font-size: 14px; -fx-background-color: #f9f9f9; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

            // Create AnchorPane and Add TableView
            AnchorPane anchorPane = new AnchorPane();
            AnchorPane.setTopAnchor(tableView, 10.0);
            AnchorPane.setLeftAnchor(tableView, 10.0);
            AnchorPane.setRightAnchor(tableView, 10.0);
            AnchorPane.setBottomAnchor(tableView, 10.0);
            anchorPane.getChildren().add(tableView);

            mainPane.setCenter(anchorPane);
    }

    @FXML public void logout(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
         Stage s = (Stage)logoutBtn.getScene().getWindow();
         s.close();
            Database.saveDatabase();
        }
    }

    @FXML public void goToProfile(ActionEvent actionEvent) {
        disableButton(goToProfileBtn);

        Admin admin = AdminService.getCurrentAdmin();
        // Create AnchorPane
        AnchorPane profilePane = new AnchorPane();


        // Create ImageView for Photo
        ImageView photo = new ImageView();
        if (admin.getImgPath() != null) {
            File imgFile = new File(admin.getImgPath());
            if (imgFile.exists()) {
                photo.setImage(new Image(imgFile.toURI().toString()));
            }
        }
        photo.setFitHeight(150);
        photo.setFitWidth(150);
        photo.setPreserveRatio(true);
        photo.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 4, 4);");

        // Create Labels for Admin Info
        Label nameLabel = new Label("Name: " + admin.getName());
        nameLabel.setFont(new Font("Arial", 18));
        nameLabel.setStyle("-fx-text-fill: #00796b;");

        Label roleLabel = new Label("Role: " + admin.getRole());
        roleLabel.setFont(new Font("Arial", 16));
        roleLabel.setStyle("-fx-text-fill: #004d40;");

        Label workingHoursLabel = new Label("Working Hours: " + admin.getWorkingHours());
        workingHoursLabel.setFont(new Font("Arial", 16));
        workingHoursLabel.setStyle("-fx-text-fill: #004d40;");

        // Create VBox for Layout
        VBox contentBox = new VBox(10, photo, nameLabel, roleLabel, workingHoursLabel);
        contentBox.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-background-color: #f0f4c3; -fx-border-color: #8bc34a; -fx-border-radius: 10;");
        contentBox.setPrefWidth(300);

        // Add VBox to AnchorPane
        AnchorPane.setTopAnchor(contentBox, 20.0);
        AnchorPane.setLeftAnchor(contentBox, 20.0);
        AnchorPane.setRightAnchor(contentBox, 20.0);
        profilePane.getChildren().add(contentBox);

        mainPane.setCenter(profilePane);

    }

    public static AnchorPane createProductTable() {
        AnchorPane root = new AnchorPane();

        // TableView and its data
        TableView<Product> productTable = new TableView<>();
        ObservableList<Product> productData = FXCollections.observableArrayList(AdminService.getAllProducts());

        // Columns
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setPrefWidth(200);
        nameColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setName(event.getNewValue());
        });

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setPrefWidth(300);
        descriptionColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setDescription(event.getNewValue());
        });

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(150);
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setPrice(event.getNewValue());
        });

        TableColumn<Product, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockColumn.setPrefWidth(150);
        stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setStock(event.getNewValue());
        });

        // Category Column
        ObservableList<Category> categoryData = FXCollections.observableArrayList(Database.categories);
        TableColumn<Product, Category> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(categoryData));
        categoryColumn.setPrefWidth(200);
        categoryColumn.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setCategory(event.getNewValue());
        });

        // Delete Button Column
        TableColumn<Product, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setPrefWidth(150);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    productData.remove(product);
                    AdminService.deleteProduct(product.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        productTable.getColumns().addAll(idColumn, nameColumn, descriptionColumn, priceColumn, stockColumn,categoryColumn, actionColumn);
        productTable.setItems(productData);
        productTable.setEditable(true);

        root.getChildren().add(productTable);
        AnchorPane.setTopAnchor(productTable, 0.0);
        AnchorPane.setRightAnchor(productTable, 0.0);
        AnchorPane.setBottomAnchor(productTable, 0.0);
        AnchorPane.setLeftAnchor(productTable, 0.0);

        return root;
    }
    public static AnchorPane createCategoryTable() {
        AnchorPane root = new AnchorPane();

        // TableView and its data
        TableView<Category> categoryTable = new TableView<>();
        ObservableList<Category> categoryData = FXCollections.observableArrayList(AdminService.getAllCategories());

        // Columns
        TableColumn<Category, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Category, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setPrefWidth(300);
        nameColumn.setOnEditCommit(event -> {
            Category category = event.getRowValue();
            category.setName(event.getNewValue());
        });

        TableColumn<Category, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setPrefWidth(500);
        descriptionColumn.setOnEditCommit(event -> {
            Category category = event.getRowValue();
            category.setDescription(event.getNewValue());
        });

        // Delete Button Column
        TableColumn<Category, Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setPrefWidth(150);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Category category = getTableView().getItems().get(getIndex());
                    categoryData.remove(category);
                    AdminService.deleteCategory(category); // Remove from static list
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        categoryTable.getColumns().addAll(idColumn, nameColumn, descriptionColumn, actionColumn);
        categoryTable.setItems(categoryData);
        categoryTable.setEditable(true);

        root.getChildren().add(categoryTable);
        AnchorPane.setTopAnchor(categoryTable, 0.0);
        AnchorPane.setRightAnchor(categoryTable, 0.0);
        AnchorPane.setBottomAnchor(categoryTable, 0.0);
        AnchorPane.setLeftAnchor(categoryTable, 0.0);

        return root;
    }


   @FXML public void goToCategories(ActionEvent actionEvent) {
        mainPane.setCenter(createCategoryTable());
        disableButton(modifyCategoryBtn);
    }

    private void disableButton(Button btn) {
        homeBtn.setDisable(false);
        addProductBtn.setDisable(false);
        modifyBtn.setDisable(false);
        viewCustomersBtn.setDisable(false);
        goToProfileBtn.setDisable(false);
        addCategoryBtn.setDisable(false);
        modifyCategoryBtn.setDisable(false);
        addAdminBtn.setDisable(false);

        btn.setDisable(true);
    }

    @FXML public void goToAddAdmin(ActionEvent actionEvent) {

        disableButton(addAdminBtn);

        // Create AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 400);
        anchorPane.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 20px;");

        // Create GridPane for form layout
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setStyle("-fx-background-color: #ffffff; -fx-padding: 20px; -fx-border-color: #cccccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Name field
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter admin name");

        // Role field
        Label roleLabel = new Label("Role:");
        TextField roleField = new TextField();
        roleField.setPromptText("Enter role");

        // Working Hours field
        Label hoursLabel = new Label("Working Hours:");
        Spinner<Integer> hoursSpinner = new Spinner<>(0, 24, 8);
        hoursSpinner.setEditable(true);

        // Username field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        // Password field
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        // Add components to GridPane
        formGrid.addRow(0, nameLabel, nameField);
        formGrid.addRow(1, roleLabel, roleField);
        formGrid.addRow(2, hoursLabel, hoursSpinner);
        formGrid.addRow(3, usernameLabel, usernameField);
        formGrid.addRow(4, passwordLabel, passwordField);

        // Style labels and inputs
        formGrid.getChildren().forEach(node -> {
            if (node instanceof Label) {
                ((Label) node).setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            }
            if (node instanceof TextField || node instanceof PasswordField || node instanceof Spinner) {
                node.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-border-color: #cccccc; -fx-background-color: #ffffff;");
            }
        });

        // Buttons
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-padding: 8px 15px;");
        submitButton.setOnAction(event -> {
            // Validation example
            if (nameField.getText().isEmpty() || roleField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert("Validation Error", "Please fill all required fields", Alert.AlertType.ERROR);
                return;
            }
            // Replace with actual validation methods
            System.out.println("Admin added: " + nameField.getText());
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 8px 15px;");
        cancelButton.setOnAction(event -> {
            // Clear fields
            nameField.clear();
            roleField.clear();
            hoursSpinner.getValueFactory().setValue(8);
            usernameField.clear();
            passwordField.clear();
        });

        HBox buttonsBox = new HBox(15, submitButton, cancelButton);
        buttonsBox.setAlignment(Pos.CENTER);

        // Create VBox to hold the form
        VBox vbox = new VBox(20, formGrid, buttonsBox);
        vbox.setAlignment(Pos.CENTER);

        // Add VBox to AnchorPane
        AnchorPane.setTopAnchor(vbox, 10.0);
        AnchorPane.setLeftAnchor(vbox, 10.0);
        AnchorPane.setRightAnchor(vbox, 10.0);
        AnchorPane.setBottomAnchor(vbox, 10.0);
        anchorPane.getChildren().add(vbox);

        mainPane.setCenter(anchorPane);
    }

    private static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }



}