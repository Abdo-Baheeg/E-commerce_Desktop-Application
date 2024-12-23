package src.gui.adminApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import src.database.Database;

import java.io.IOException;
import java.util.Objects;

public class AdminApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/adminLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Set the Stage
            primaryStage.setTitle("Admin Login");
            primaryStage.setScene(scene);

            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../utils/icon/icon.png"))));
            primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to exit?");
                alert.showAndWait();
                event.consume();
                if (alert.getResult() == ButtonType.OK){
                    Notifications.create().text("Good Bye").position(Pos.CENTER).show();
                    try {
                        Database.saveDatabase();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    primaryStage.close();
                }
            });

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Database.loadDatabase();
        launch(args);
    }
}
