package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.LigueRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterLigueController {
    @FXML
    public TextField txtLigue;
    @FXML
    public void AjouterLigue(ActionEvent event) throws SQLException {
        if (txtLigue.getText().isEmpty()){
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Aucune ligue n'a été saisi");
            return ;
        }
        Ligue ligue = new Ligue(0, txtLigue.getText());
        if (LigueRepository.AjouterLigue(ligue)){
            SceneController.showAlert(Alert.AlertType.CONFIRMATION, "Validé", "la ligue" + txtLigue.getText() + "a bien été ajouté");
            SceneController sceneController = new SceneController();
            sceneController.loadStages("home-view.fxml");
            Stage stage = (Stage) txtLigue.getScene().getWindow();
            stage.close();
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Cette ligue existe déja");
        }
    }
    @FXML
    public void loadBack(ActionEvent event){
        SceneController sceneController = new SceneController();
        sceneController.loadStages("home-view.fxml");
        Stage stage = (Stage) txtLigue.getScene().getWindow();
        stage.close();
    }
}
