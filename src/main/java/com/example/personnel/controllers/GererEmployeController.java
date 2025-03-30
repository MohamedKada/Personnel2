package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Employe;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.EmployeRepository;
import com.example.personnel.repositories.LigueRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GererEmployeController implements Initializable {
    private static Ligue selectedLigue;
    public static void setSelectedLigue(Ligue ligue) {
        selectedLigue = ligue;
    }

    @FXML
    public TableColumn<Employe, Integer> idColumn;
    @FXML
    public TableColumn<Employe, String> nomColumn;
    @FXML
    public TableColumn<Employe, String> prenomColumn;
    @FXML
    public TableView<Employe> employeTableView;
    private ObservableList<Employe> Employes = FXCollections.observableArrayList();
    private Employe selectedEmploye;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Configuration du tableau pour afficher correctement les données
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            // Récupération des données
            this.Employes = EmployeRepository.getEmploye(selectedLigue.getId());
            this.employeTableView.setItems(Employes);

            // S'assurer que le tableau prend toute la largeur disponible
            employeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAdd(ActionEvent event) throws IOException {
        AjouterEmployeController.setSelectedLigue(selectedLigue);
        SceneController sceneController = new SceneController();
        sceneController.switchView("ajouter-employes-view.fxml", event);

    }

    public void loadEdit(ActionEvent event) throws IOException {
        selectedEmploye = employeTableView.getSelectionModel().getSelectedItem();
        ModifierEmployeController.setSelectedEmploye(selectedEmploye);
        SceneController sceneController = new SceneController();
        sceneController.switchView("modifier-employes-view.fxml", event);
    }
    public void loadSuppL(ActionEvent actionEvent) throws SQLException {
        this.selectedEmploye = employeTableView.getSelectionModel().getSelectedItem();
        boolean result = EmployeRepository.deleteToLigue(selectedEmploye.getId());
        if (result) {
            Employes.remove(selectedEmploye);
            SceneController.showAlert(Alert.AlertType.CONFIRMATION, "Succes", "User deleted to ligue");
        }
        else {
            SceneController.showAlert(Alert.AlertType.ERROR, "Error", "error for deleted to ligue");
        }
    }
    public void loadSuppU(ActionEvent actionEvent) throws SQLException {
        this.selectedEmploye = employeTableView.getSelectionModel().getSelectedItem();
        EmployeRepository.deleteEmploye(selectedEmploye.getId());
        Employes.remove(selectedEmploye);
        SceneController.showAlert(Alert.AlertType.CONFIRMATION, "Succes", "User deleted");
    }

    public void loadBack(ActionEvent actionEvent) {
        SceneController sceneController = new SceneController();
        sceneController.loadStages("home-view.fxml");
        Stage stage = (Stage) employeTableView.getScene().getWindow();
        stage.close();
    }

}
