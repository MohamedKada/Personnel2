package com.example.personnel.repositories;

import com.example.personnel.models.Employe;
import com.example.personnel.models.Ligue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

/**
 * Tests unitaires pour la classe EmployeRepository.
 * Note: Ces tests sont désactivés par défaut car ils nécessitent une connexion à une base de données.
 * Pour les exécuter, retirez l'annotation @Disabled et assurez-vous que la base de données est configurée correctement.
 */
public class EmployeRepositoryTest {

    /**
     * Test pour la méthode login() - vérifie qu'elle peut être appelée sans exception.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testLogin() {
        try {
            // Note: Ces identifiants doivent exister dans la base de données pour que le test réussisse
            Employe employe = EmployeRepository.login("admin@example.com", "admin123");
            
            // Si les identifiants sont corrects, l'employé ne devrait pas être null
            assertNotNull(employe, "L'employé devrait être trouvé avec des identifiants valides");
            
            // Test avec des identifiants invalides
            Employe invalidEmploye = EmployeRepository.login("invalid@example.com", "wrongpassword");
            assertNull(invalidEmploye, "L'employé ne devrait pas être trouvé avec des identifiants invalides");
        } catch (Exception e) {
            fail("La méthode login() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test pour la méthode getEmploye() - vérifie qu'elle peut être appelée sans exception.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testGetEmploye() {
        try {
            // Note: Cet ID de ligue doit exister dans la base de données pour que le test réussisse
            // On suppose que la ligue avec ID=1 existe
            int ligueId = 1;
            
            // Récupérer les employés de cette ligue
            assertNotNull(EmployeRepository.getEmploye(ligueId), "La méthode getEmploye() ne devrait pas retourner null");
        } catch (Exception e) {
            fail("La méthode getEmploye() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test pour la méthode addEmploye() - vérifie qu'elle fonctionne correctement.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testAddEmploye() {
        try {
            // Créer une ligue pour l'employé
            // Note: Cette ligue doit exister dans la base de données pour que le test réussisse
            // On suppose que la ligue avec ID=1 existe
            Ligue ligue = new Ligue(1, "Ligue Test");
            
            // Créer un employé avec un email unique
            String uniqueEmail = "test" + System.currentTimeMillis() + "@example.com";
            Employe employe = new Employe(
                0, 
                "Test", 
                "Utilisateur", 
                "password123", 
                LocalDate.now(), 
                LocalDate.now().plusYears(5), 
                0, 
                uniqueEmail
            );
            
            // Essayer d'ajouter l'employé et vérifier le résultat
            boolean result = EmployeRepository.addEmploye(employe, ligue);
            assertTrue(result, "L'ajout d'un nouvel employé devrait réussir");
            
            // Vérifier que l'ajout d'un employé avec le même email échoue
            boolean resultDuplicate = EmployeRepository.addEmploye(employe, ligue);
            assertFalse(resultDuplicate, "L'ajout d'un employé avec un email existant devrait échouer");
        } catch (Exception e) {
            fail("La méthode addEmploye() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test simple de la classe EmployeRepository sans accès à la base de données.
     */
    @Test
    public void testEmployeRepositoryClassExists() {
        // Vérifier simplement que la classe existe
        assertNotNull(EmployeRepository.class, "La classe EmployeRepository devrait exister");
    }
    
    /**
     * Test pour vérifier que la variable rootUser peut être définie et récupérée.
     */
    @Test
    public void testRootUserVariable() {
        // Créer un employé
        Employe employe = new Employe(
            1, 
            "Root", 
            "User", 
            "rootpass", 
            LocalDate.now(), 
            LocalDate.now().plusYears(10), 
            2, 
            "root@example.com"
        );
        
        // Définir l'utilisateur root
        EmployeRepository.rootUser = employe;
        
        // Vérifier que l'utilisateur est correctement défini
        assertNotNull(EmployeRepository.rootUser, "L'utilisateur root ne devrait pas être null après affectation");
        assertEquals("Root", EmployeRepository.rootUser.getNom(), "Le nom de l'utilisateur root devrait correspondre");
        assertEquals("User", EmployeRepository.rootUser.getPrenom(), "Le prénom de l'utilisateur root devrait correspondre");
        assertEquals(2, EmployeRepository.rootUser.getAdmin(), "Le niveau admin de l'utilisateur root devrait être 2");
    }
} 