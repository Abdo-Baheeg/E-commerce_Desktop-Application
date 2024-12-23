package src.gui.customerApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.database.Database;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Set the Stage
            primaryStage.setTitle("Login & Registration");
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../utils/icon/icon.png"))));
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(primaryStage);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to exit?");
                alert.showAndWait();
                event.consume();
                if (alert.getResult() == ButtonType.OK){
                    primaryStage.close();
                    try {
                        Database.saveDatabase();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
      Database.loadDatabase();
        launch(args);
    }
}
