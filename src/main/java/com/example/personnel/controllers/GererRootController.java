package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.repositories.EmployeRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*import static com.example.personnel.repositories.EmployeRepository.editRoot;

public class GererRootController {
    public void loadOk(ActionEvent actionEvent) throws SQLException {
        if(EmployeRepository.editRoot()){
            SceneController.showAlert(Alert.AlertType.CONFIRMATION,"Validé","Le compte root a bien été modifié");
            SceneController sceneController = new SceneController();
            sceneController.loadStages("home-view.fxml");
            Stage stage = (Stage) ligueNameTextField.getScene().getWindow();
            stage.close();
        }
    }
}*/
