package src.gui.customerApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import src.DAO.AdminDAO;
import src.DAO.CustomerDAO;
import src.DAO.ProductDAO;
import src.database.Database;
import src.entities.Product;

import java.io.IOException;
import java.util.ArrayList;
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
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/Profile.fxml")));
        mainBorderPane.setCenter(pane);
    }

    @FXML
    void logout(ActionEvent event) {
      Stage o= (Stage) logoutBtn.getScene().getWindow();
      o.close();
    }

    @FXML
    void search(ActionEvent event) {

    }
        @FXML
        private Button goToHomeBtn;
        private final AnchorPane productCard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/productCard.fxml")));
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
                productGrid.add(p.createCard(products.get(i)),i%4,i/4);
            }
        }
    }

