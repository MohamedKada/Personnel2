package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Employe;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.EmployeRepository;
import com.example.personnel.repositories.LigueRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AjouterEmployeController {
    private static Ligue selectedLigue;
    public static void setSelectedLigue(Ligue ligue) {
        selectedLigue = ligue;
    }

    @FXML
    public TextField nomTextField;
    @FXML
    public TextField prenomTextField;
    @FXML
    public TextField mailTextField;
    @FXML
    public TextField mdpTextField;
    @FXML
    public DatePicker departDatePicker;
    @FXML
    public DatePicker arriveeDatePicker;
    @FXML
    public RadioButton adminRadioButton;

    public void loadValider(ActionEvent actionEvent) throws SQLException, IOException {
        if (nomTextField.getText().isEmpty() ||
                prenomTextField.getText().isEmpty() ||
                mailTextField.getText().isEmpty() ||
                mdpTextField.getText().isEmpty() ||
                departDatePicker.getValue() == null ||
                arriveeDatePicker.getValue() == null) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Tous les champs doivent être remplis");
            return;
        }

        // Validation des dates
        if (departDatePicker.getValue().isBefore(arriveeDatePicker.getValue())) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "La date de départ ne peut pas être antérieure à la date d'arrivée");
            return;
        }

        int adminInput;
        if (adminRadioButton.isSelected()) {
            adminInput = 1;
        }
        else {
            adminInput = 0;
        }

        Employe employe = new Employe(
                0,
                nomTextField.getText(),
                prenomTextField.getText(),
                mdpTextField.getText(),
                arriveeDatePicker.getValue(),
                departDatePicker.getValue(),
                adminInput,
                mailTextField.getText()
        );

        if (EmployeRepository.addEmploye(employe, selectedLigue)) {
            SceneController.showAlert(Alert.AlertType.CONFIRMATION, "Validé", "L'employé à été ajouté");
            SceneController sceneController = new SceneController();
            sceneController.switchView("gerer-employes-view.fxml", actionEvent);
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Cet employe existe déja");
        }
    }

    @FXML
    public void loadBack(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchView("gerer-employes-view.fxml", event);
    }
}
