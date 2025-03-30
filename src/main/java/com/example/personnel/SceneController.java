package com.example.personnel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneController {
    public void switchView(String fxmlPath, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        
        // Définir des tailles minimales
        if (fxmlPath.equals("home-view.fxml")) {
            stage.setMinWidth(750);
            stage.setMinHeight(500);
        }
        
        stage.show();
    }

    public void loadStages(String fxmlPath) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);
            
            // Définir des tailles appropriées selon le type de fenêtre
            if (fxmlPath.equals("home-view.fxml")) {
                stage.setMinWidth(750);
                stage.setMinHeight(500);
            } else if (fxmlPath.equals("edit-ligue-view.fxml")) {
                stage.setMinWidth(420);
                stage.setMinHeight(400);
            } else if (fxmlPath.equals("ajouter_ligue-view.fxml")) {
                stage.setMinWidth(350);
                stage.setMinHeight(350);
            } else if (fxmlPath.equals("supp-ligue-view.fxml")) {
                stage.setMinWidth(300);
                stage.setMinHeight(270);
            } else if (fxmlPath.equals("gerer-employes-view.fxml")) {
                stage.setMinWidth(600);
                stage.setMinHeight(450);
            } else if (fxmlPath.equals("ajouter-employes-view.fxml") || fxmlPath.equals("modifier-employes-view.fxml")) {
                stage.setMinWidth(650);
                stage.setMinHeight(450);
            } else if (fxmlPath.equals("gerer-root-view.fxml")) {
                stage.setMinWidth(400);
                stage.setMinHeight(400);
            }
            
            stage.show();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(SceneController.class.getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }
}
