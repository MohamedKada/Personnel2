package com.example.personnel;

/**
 * Classe lanceur pour l'application exécutable JAR.
 * Cette classe est nécessaire car JavaFX ne peut pas être lancé directement 
 * depuis un JAR avec sa classe principale héritant de Application.
 */
public class MainLauncher {
    public static void main(String[] args) {
        Main.main(args);
    }
} 