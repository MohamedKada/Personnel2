package com.example.personnel.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LigueTest {

    @Test
    public void testLigueConstructorAndGetters() {
        // Arrange
        int id = 1;
        String nom = "Ligue de Football";
        
        // Act
        Ligue ligue = new Ligue(id, nom);
        
        // Assert
        assertEquals(id, ligue.getId());
        assertEquals(nom, ligue.getNom());
    }
    
    @Test
    public void testLigueSetters() {
        // Arrange
        Ligue ligue = new Ligue(0, "");
        int id = 2;
        String nom = "Ligue de Basketball";
        
        // Act
        ligue.setId(id);
        ligue.setNom(nom);
        
        // Assert
        assertEquals(id, ligue.getId());
        assertEquals(nom, ligue.getNom());
    }
    
    @Test
    public void testLigueEquals() {
        // Arrange
        Ligue ligue1 = new Ligue(1, "Ligue A");
        Ligue ligue2 = new Ligue(1, "Ligue B"); // Même ID
        Ligue ligue3 = new Ligue(2, "Ligue A"); // Même nom
        
        // Act & Assert
        assertEquals(ligue1, ligue1); // Réflexivité
        assertEquals(ligue1, ligue2); // Même ID, nom différent
        assertNotEquals(ligue1, ligue3); // ID différent, même nom
        assertNotEquals(ligue1, null); // Pas égal à null
        assertNotEquals(ligue1, new Object()); // Pas égal à un autre type
    }
    
    @Test
    public void testLigueHashCode() {
        // Arrange
        Ligue ligue1 = new Ligue(1, "Ligue A");
        Ligue ligue2 = new Ligue(1, "Ligue B"); // Même ID
        
        // Act & Assert
        assertEquals(ligue1.hashCode(), ligue2.hashCode()); // Même hashcode pour même ID
    }
} 