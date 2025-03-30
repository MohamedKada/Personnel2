package com.example.personnel.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    /**
     * Test désactivé par défaut car il nécessite une base de données active
     * pour fonctionner correctement. À activer manuellement si la base de données est configurée.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testDatabaseConnection() {
        try {
            Connection connection = Database.connectDatabase();
            assertNotNull(connection, "La connexion à la base de données ne devrait pas être null");
            assertFalse(connection.isClosed(), "La connexion à la base de données ne devrait pas être fermée");
            
            // Vérifier les métadonnées de connexion
            assertNotNull(connection.getMetaData(), "Les métadonnées de connexion ne devraient pas être null");
            
            // Fermer la connexion après le test
            connection.close();
            assertTrue(connection.isClosed(), "La connexion devrait être fermée après l'appel à close()");
        } catch (SQLException e) {
            fail("Exception lors de la connexion à la base de données: " + e.getMessage());
        }
    }
    
    @Test
    public void testDatabaseClassExists() {
        // Test simple pour vérifier que la classe existe
        assertNotNull(Database.class, "La classe Database devrait exister");
    }
} 