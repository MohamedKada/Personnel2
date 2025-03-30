package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.LigueRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuppLigueViewController implements Initializable {
    private static Ligue selectedLigue;

    public static void setSelectedLigue(Ligue ligue) {
        selectedLigue = ligue;
    }
    public void loadOui (ActionEvent actionEvent) throws SQLException {
        String ligueName = selectedLigue.getNom();
        if (ligueName.isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "La ligue " + selectedLigue.getNom() + " n'existe pas");
            return;
        }

        selectedLigue.setNom(selectedLigue.getNom());
        if (LigueRepository.SuppLigue(selectedLigue)) {
            SceneController.showAlert(Alert.AlertType.CONFIRMATION,"Validé", "La ligue " + selectedLigue.getNom() + " à bien été supprimé");
            SceneController sceneController = new SceneController();
            sceneController.loadStages("home-view.fxml");
            Stage stage = (Stage) ((TextField) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR,"Erreur", "La ligue n'a pas pu être supprimé");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
