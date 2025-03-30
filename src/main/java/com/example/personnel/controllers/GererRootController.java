package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.repositories.EmployeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.personnel.repositories.EmployeRepository.editRoot;

public class GererRootController {
    @FXML
    public TextField nomTextField;
    @FXML
    public TextField mailTextField;
    @FXML
    public TextField mdpTextField;
    @FXML
    public void loadOk(ActionEvent actionEvent) throws SQLException {
        if(EmployeRepository.editRoot(nomTextField.getText(), mailTextField.getText(), mdpTextField.getText())) {
            SceneController.showAlert(Alert.AlertType.CONFIRMATION,"Validé","Le compte root a bien été modifié");
            SceneController sceneController = new SceneController();
            sceneController.loadStages("home-view.fxml");
            Stage stage = (Stage) nomTextField.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    public void loadBack(ActionEvent event) throws SQLException {
        SceneController sceneController = new SceneController();
        sceneController.loadStages("home-view.fxml");
        Stage stage = (Stage) nomTextField.getScene().getWindow();
        stage.close();
    }
}
