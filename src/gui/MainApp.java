package src.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import src.database.Database;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/customerApp/login.fxml"));
            Parent root = loader.load();

            // Create a Scene using the loaded root
            Scene scene = new Scene(root);

            // Set the Stage
            primaryStage.setTitle("Login & Registration");
            primaryStage.setScene(scene);

            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("utils/icons/icon.png"))));
            primaryStage.setMaxWidth(1280);
            primaryStage.setMaxHeight(680);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Errorrrrrrrrr");
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Database.initDummyData();
        launch(args);
    }
}
