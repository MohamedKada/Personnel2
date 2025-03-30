package com.example.personnel.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe HomeViewController.
 * Note: Ces tests sont principalement structurels et ne testent pas la fonctionnalité complète
 * car JavaFX n'est pas disponible dans un environnement de test JUnit standard sans configuration spéciale.
 */
public class HomeViewControllerTest {

    /**
     * Test simple pour vérifier que l'on peut instancier le contrôleur.
     */
    @Test
    public void testHomeViewControllerInstantiation() {
        // Vérifier que la classe peut être instanciée sans exception
        HomeViewController controller = new HomeViewController();
        assertNotNull(controller, "Le contrôleur HomeViewController devrait pouvoir être instancié");
    }
    
    /**
     * Ce test est désactivé car il nécessite JavaFX.
     * Dans un environnement de test réel avec JavaFX, on pourrait tester l'initialisation.
     */
    @Test
    @Disabled("Ce test nécessite JavaFX et une injection des composants UI")
    public void testHomeViewTableInitialized() {
        // Dans un environnement JavaFX, on pourrait tester que les champs sont initialisés
        HomeViewController controller = new HomeViewController();
        assertNull(controller.ligueTableView, "Le tableau ligueTableView devrait être null avant l'initialisation par JavaFX");
        assertNull(controller.idColumn, "La colonne idColumn devrait être null avant l'initialisation par JavaFX");
        assertNull(controller.nameColumn, "La colonne nameColumn devrait être null avant l'initialisation par JavaFX");
    }
} 