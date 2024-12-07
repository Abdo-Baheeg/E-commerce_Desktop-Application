package src.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import src.database.Database;
import src.entities.Admin;
import src.entities.Customer;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Load the FXML file for the registration screen
           FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            // Set up the scene
            Scene scene = new Scene(root);
            // Configure the stage
            stage.setTitle("EcoMARKET - Registration");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
