package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.repositories.EmployeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.personnel.repositories.EmployeRepository.editRoot;

public class GererRootController implements Initializable {
    @FXML
    public TextField nomTextField;
    @FXML
    public TextField mailTextField;
    @FXML
    public TextField mdpTextField;
    @FXML
    public void loadOk(ActionEvent actionEvent) throws SQLException {
        System.out.println(EmployeRepository.editRoot(nomTextField.getText(), mailTextField.getText(), mdpTextField.getText()));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomTextField.setText(EmployeRepository.rootUser.getNom());
        mailTextField.setText(EmployeRepository.rootUser.getMail());
        mdpTextField.setText(EmployeRepository.rootUser.getPassword());
    }
}
