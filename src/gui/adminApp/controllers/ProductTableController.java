package src.gui.adminApp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.entities.Product;

import java.util.ArrayList;

public class ProductTableController {

    @FXML
    private TableColumn<Product, String>  actionCol;

    @FXML
    private TableColumn<Product, String>  inStockCol;

    @FXML
    private TableColumn<Product, String>  photoCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<?, ?> productPriceCol;

    @FXML
    private TableView<ArrayList<Product>> productsTable;

}
