package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Employe;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.EmployeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierEmployeController implements Initializable {
    private static Employe selectedEmploye;
    public static void setSelectedEmploye(Employe employe) {
        selectedEmploye = employe;
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
    @FXML
    public TextField ligueTextField;

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
                mailTextField.getText(),
                Integer.parseInt(ligueTextField.getText())
        );

        if (EmployeRepository.editEmploye(employe, selectedEmploye.getId())) {
            SceneController.showAlert(Alert.AlertType.CONFIRMATION, "Validé", "L'employé à été modifié");
            SceneController sceneController = new SceneController();
            sceneController.switchView("gerer-employes-view.fxml", actionEvent);
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "la modif a échouée");
        }
    }

    @FXML
    public void loadBack(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchView("gerer-employes-view.fxml", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(selectedEmploye);
        nomTextField.setText(selectedEmploye.getNom());
        prenomTextField.setText(selectedEmploye.getPrenom());
        mailTextField.setText(selectedEmploye.getMail());
        mdpTextField.setText(selectedEmploye.getPassword());
        departDatePicker.setValue(selectedEmploye.getDateDepart());
        arriveeDatePicker.setValue(selectedEmploye.getDateArrive());
        adminRadioButton.setSelected(!(selectedEmploye.getAdmin() == 0));
        ligueTextField.setText(String.valueOf(selectedEmploye.getligueID()));
    }
}
