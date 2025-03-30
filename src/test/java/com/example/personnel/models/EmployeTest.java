package com.example.personnel.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testEmployeConstructorAndGetters() {
        // Arrange
        int id = 1;
        String nom = "Dupont";
        String prenom = "Jean";
        String password = "motdepasse";
        LocalDate dateArrive = LocalDate.of(2020, 1, 1);
        LocalDate dateDepart = LocalDate.of(2025, 1, 1);
        int admin = 1;
        String mail = "jean.dupont@example.com";
        
        // Act
        Employe employe = new Employe(id, nom, prenom, password, dateArrive, dateDepart, admin, mail);
        
        // Assert
        assertEquals(id, employe.getId());
        assertEquals(nom, employe.getNom());
        assertEquals(prenom, employe.getPrenom());
        assertEquals(password, employe.getPassword());
        assertEquals(dateArrive, employe.getDateArrive());
        assertEquals(dateDepart, employe.getDateDepart());
        assertEquals(admin, employe.getAdmin());
        assertEquals(mail, employe.getMail());
    }
    
    @Test
    public void testEmployeConstructorWithLigueIDAndGetters() {
        // Arrange
        int id = 2;
        String nom = "Martin";
        String prenom = "Marie";
        String password = "secure123";
        LocalDate dateArrive = LocalDate.of(2021, 3, 15);
        LocalDate dateDepart = LocalDate.of(2026, 3, 15);
        int admin = 0;
        String mail = "marie.martin@example.com";
        int ligueID = 5;
        
        // Act
        Employe employe = new Employe(id, nom, prenom, password, dateArrive, dateDepart, admin, mail, ligueID);
        
        // Assert
        assertEquals(id, employe.getId());
        assertEquals(nom, employe.getNom());
        assertEquals(prenom, employe.getPrenom());
        assertEquals(password, employe.getPassword());
        assertEquals(dateArrive, employe.getDateArrive());
        assertEquals(dateDepart, employe.getDateDepart());
        assertEquals(admin, employe.getAdmin());
        assertEquals(mail, employe.getMail());
        assertEquals(ligueID, employe.getligueID());
    }
    
    @Test
    public void testEmployeSetters() {
        // Arrange
        Employe employe = new Employe(0, "", "", "", LocalDate.now(), LocalDate.now(), 0, "");
        
        int id = 3;
        String nom = "Durand";
        String prenom = "Pierre";
        String password = "pass456";
        LocalDate dateArrive = LocalDate.of(2022, 5, 10);
        LocalDate dateDepart = LocalDate.of(2027, 5, 10);
        int admin = 2;
        String mail = "pierre.durand@example.com";
        
        // Act
        employe.setId(id);
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setPassword(password);
        employe.setDateArrive(dateArrive);
        employe.setDateDepart(dateDepart);
        employe.setAdmin(admin);
        employe.setMail(mail);
        
        // Assert
        assertEquals(id, employe.getId());
        assertEquals(nom, employe.getNom());
        assertEquals(prenom, employe.getPrenom());
        assertEquals(password, employe.getPassword());
        assertEquals(dateArrive, employe.getDateArrive());
        assertEquals(dateDepart, employe.getDateDepart());
        assertEquals(admin, employe.getAdmin());
        assertEquals(mail, employe.getMail());
    }
    
    @Test
    public void testToString() {
        // Arrange
        int id = 4;
        String nom = "Lefevre";
        String prenom = "Sophie";
        String password = "pwd789";
        LocalDate dateArrive = LocalDate.of(2023, 7, 20);
        LocalDate dateDepart = LocalDate.of(2028, 7, 20);
        int admin = 1;
        String mail = "sophie.lefevre@example.com";
        
        Employe employe = new Employe(id, nom, prenom, password, dateArrive, dateDepart, admin, mail);
        
        // Act
        String result = employe.toString();
        
        // Assert
        assertTrue(result.contains(String.valueOf(id)));
        assertTrue(result.contains(nom));
        assertTrue(result.contains(prenom));
        assertTrue(result.contains(password));
        assertTrue(result.contains(mail));
    }
} 