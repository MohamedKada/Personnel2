package com.example.personnel.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe LoginViewController.
 * Note: Ces tests sont principalement structurels et ne testent pas la fonctionnalité complète
 * car JavaFX n'est pas disponible dans un environnement de test JUnit standard sans configuration spéciale.
 */
public class LoginViewControllerTest {

    /**
     * Test simple pour vérifier que l'on peut instancier le contrôleur.
     */
    @Test
    public void testLoginViewControllerInstantiation() {
        // Vérifier que la classe peut être instanciée sans exception
        LoginViewController controller = new LoginViewController();
        assertNotNull(controller, "Le contrôleur LoginViewController devrait pouvoir être instancié");
    }
    
    /**
     * Ce test est désactivé car il nécessite JavaFX.
     * Dans un environnement de test réel avec JavaFX, on pourrait tester ces champs.
     */
    @Test
    @Disabled("Ce test nécessite JavaFX et une injection des composants UI")
    public void testLoginFieldsInitialized() {
        // Dans un environnement JavaFX, on pourrait tester que les champs sont initialisés
        LoginViewController controller = new LoginViewController();
        assertNull(controller.mailTextField, "Le champ mailTextField devrait être null avant l'initialisation par JavaFX");
        assertNull(controller.passwordTextField, "Le champ passwordTextField devrait être null avant l'initialisation par JavaFX");
    }
} 