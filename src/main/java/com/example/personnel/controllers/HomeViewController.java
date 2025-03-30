package com.example.personnel.controllers;

import com.example.personnel.SceneController;
import com.example.personnel.models.Ligue;
import com.example.personnel.repositories.LigueRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    @FXML
    public TableColumn<Ligue, Integer> idColumn;
    @FXML
    public TableColumn<Ligue, String> nameColumn;
    @FXML
    public TableView<Ligue> ligueTableView;
    private ObservableList<Ligue> ligues = FXCollections.observableArrayList();

    private Ligue selectedLigue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Configuration du tableau pour afficher correctement les données
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
            // Récupération des données
            this.ligues = LigueRepository.getLigues();
            this.ligueTableView.setItems(ligues);
            
            // S'assurer que le tableau prend toute la largeur disponible
            ligueTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loadAddLigue(ActionEvent event) {
        SceneController sceneController = new SceneController();
        sceneController.loadStages("ajouter_ligue-view.fxml");
        Stage stage = (Stage) ligueTableView.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loadEditLigue(ActionEvent event) {
        if (this.ligueTableView.getSelectionModel().getSelectedItems().isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Vous devez sélectionner une ligue");
            return;
        }

        this.selectedLigue = this.ligueTableView.getSelectionModel().getSelectedItem();
        EditLigueViewController.setSelectedLigue(this.selectedLigue);
        SceneController sceneController = new SceneController();
        sceneController.loadStages("edit-ligue-view.fxml");
        Stage stage = (Stage) ligueTableView.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void loadSuppLigue(ActionEvent event) {
        if (this.ligueTableView.getSelectionModel().getSelectedItems().isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Vous devez sélectionner une ligue");
            return;
        }

        this.selectedLigue = this.ligueTableView.getSelectionModel().getSelectedItem();
        SuppLigueViewController.setSelectedLigue(this.selectedLigue);
        SceneController sceneController = new SceneController();
        sceneController.loadStages("supp-ligue-view.fxml");
        Stage stage = (Stage) ligueTableView.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void loadRoot(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchView("gerer-root-view.fxml", event);
    }
    
    @FXML
    public void loadEmploye(ActionEvent event) throws IOException {
        if (this.ligueTableView.getSelectionModel().getSelectedItems().isEmpty()) {
            SceneController.showAlert(Alert.AlertType.ERROR, "Erreur", "Vous devez sélectionner une ligue");
            return;
        }
        this.selectedLigue = this.ligueTableView.getSelectionModel().getSelectedItem();
        GererEmployeController.setSelectedLigue(this.selectedLigue);
        SceneController sceneController = new SceneController();
        sceneController.switchView("gerer-employes-view.fxml", event);
    }
    @FXML
    public void loadQuit(ActionEvent event) {
        Stage stage = (Stage) ligueTableView.getScene().getWindow();
        stage.close();
    }
}
