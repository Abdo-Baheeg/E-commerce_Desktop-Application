package src.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.database.Database;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen(primaryStage);
        primaryStage.setTitle("E-Commerce System");
        primaryStage.setScene(new Scene(loginScreen.getView(), 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Database.initDummyData();
        launch(args);
    }
}
