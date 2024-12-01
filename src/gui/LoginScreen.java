package src.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import src.entities.Customer;
import src.service.AdminService;
import src.service.CustomerService;

public class LoginScreen {
    private final GridPane view;
    private final AdminService adminService = new AdminService();
    private final CustomerService customerService = new CustomerService();

    public LoginScreen(Stage primaryStage) {
        view = new GridPane();
        view.setPadding(new Insets(10));
        view.setHgap(10);
        view.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        Label roleLabel = new Label("Role:");
        ChoiceBox<String> roleBox = new ChoiceBox<>();
        roleBox.getItems().addAll("Admin", "Customer");
        roleBox.setValue("Customer");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleBox.getValue();

            boolean success = false;
            if ("Admin".equals(role)) {
                success = adminService.login(username, password);
            } else if ("Customer".equals(role)) {
                success = customerService.login(username, password);
            }

            if (success) {
                messageLabel.setText("Login successful!");
//                MainDashboard dashboard = new MainDashboard(primaryStage, role, username);
//                primaryStage.setScene(new Scene(dashboard.getView(), 600, 400));
            } else {
                messageLabel.setText("Invalid credentials.");
            }
        });
        registerButton.setOnAction(e -> {
            String role = roleBox.getValue();
            String password = passwordField.getText();
            String username = usernameField.getText();
            boolean success = false;
            if ("Customer".equals(role)) {
                Customer customer = new Customer(username,password);
                success = customerService.register(customer);
            }

            if (success) {
                messageLabel.setText("Login successful!");
//                MainDashboard dashboard = new MainDashboard(primaryStage, role, username);
//                primaryStage.setScene(new Scene(dashboard.getView(), 600, 400));

            }
        });

        view.add(usernameLabel, 0, 0);
        view.add(usernameField, 1, 0);
        view.add(passwordLabel, 0, 1);
        view.add(passwordField, 1, 1);
        view.add(roleLabel, 0, 2);
        view.add(roleBox, 1, 2);
        view.add(loginButton, 0, 3);
        view.add(registerButton, 1, 3);
        view.add(messageLabel, 0, 4, 2, 1);
    }

    public GridPane getView() {
        return view;
    }
}
