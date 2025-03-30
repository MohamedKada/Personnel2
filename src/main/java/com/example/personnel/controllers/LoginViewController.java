package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Employe;
import com.example.personnel.repositories.EmployeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginViewController {
    @FXML
    public TextField mailTextField;
    @FXML
    public TextField passwordTextField;

    @FXML
    public void loginAction(ActionEvent event) throws IOException, SQLException {
        if (mailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled!");
            return;
        }

        Employe employe = EmployeRepository.login(mailTextField.getText(), passwordTextField.getText());
        if (employe != null) {
            EmployeRepository.rootUser = employe;
            SceneController sceneController = new SceneController();
            sceneController.switchView("home-view.fxml", event);
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR, "Error", "Login failed!");
        }
    }
}
