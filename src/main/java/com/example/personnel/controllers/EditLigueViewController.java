package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.LigueRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditLigueViewController implements Initializable {
    private static Ligue selectedLigue;
    @FXML
    public TextField ligueNameTextField;

    public static void setSelectedLigue(Ligue ligue) {
        selectedLigue = ligue;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ligueNameTextField.setText(selectedLigue.getNom());
    }

    public void ValiderEdit(ActionEvent Event) throws SQLException {
        String ligueName = ligueNameTextField.getText();

        if (ligueName.isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "La ligue " + selectedLigue.getNom() + " n'existe pas");
            return;
        }
        selectedLigue.setNom(ligueName);
        if (LigueRepository.UpdateLigue(selectedLigue)){
            SceneController.showAlert(Alert.AlertType.CONFIRMATION,"Validé", "La ligue " + selectedLigue.getNom() + "à bien été modifié");
            SceneController sceneController = new SceneController();
            sceneController.loadStages("home-view.fxml");
            Stage stage = (Stage) ligueNameTextField.getScene().getWindow();
            stage.close();
        }
        else{
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "La mise a jour de la ligue a échoué");
        }
    }
    @FXML
    public void loadBack(ActionEvent event) throws SQLException {
        SceneController sceneController = new SceneController();
        sceneController.loadStages("home-view.fxml");
        Stage stage = (Stage) ligueNameTextField.getScene().getWindow();
        stage.close();
    }
}
