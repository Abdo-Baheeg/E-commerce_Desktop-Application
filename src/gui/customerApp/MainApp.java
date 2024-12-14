package src.gui.customerApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import src.database.Database;

import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Try using an absolute path from the project root
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
            Parent root = loader.load();

            // Create a Scene using the loaded root
            Scene scene = new Scene(root);

            // Set the Stage
            primaryStage.setTitle("Login & Registration");
            primaryStage.setScene(scene);

            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../utils/icon/icon.png"))));
            primaryStage.setMaxWidth(1280);
            primaryStage.setMaxHeight(680);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Database.initData();
        launch(args);
    }
}
