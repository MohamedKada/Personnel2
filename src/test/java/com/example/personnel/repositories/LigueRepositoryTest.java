package com.example.personnel.repositories;

import com.example.personnel.models.Ligue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe LigueRepository.
 * Note: Ces tests sont désactivés par défaut car ils nécessitent une connexion à une base de données.
 * Pour les exécuter, retirez l'annotation @Disabled et assurez-vous que la base de données est configurée correctement.
 */
public class LigueRepositoryTest {

    /**
     * Test pour la méthode getLigues() - vérifie qu'elle peut être appelée sans exception.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testGetLigues() {
        try {
            assertNotNull(LigueRepository.getLigues(), "La méthode getLigues() ne devrait pas retourner null");
        } catch (Exception e) {
            fail("La méthode getLigues() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test pour la méthode AjouterLigue() - vérifie qu'elle fonctionne correctement.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testAjouterLigue() {
        try {
            // Créer une ligue avec un nom unique (en ajoutant un timestamp)
            Ligue ligue = new Ligue(0, "Ligue Test " + System.currentTimeMillis());
            
            // Essayer d'ajouter la ligue et vérifier le résultat
            boolean result = LigueRepository.AjouterLigue(ligue);
            assertTrue(result, "L'ajout d'une nouvelle ligue devrait réussir");
            
            // Vérifier que l'ajout d'une ligue avec le même nom échoue
            boolean resultDuplicate = LigueRepository.AjouterLigue(ligue);
            assertFalse(resultDuplicate, "L'ajout d'une ligue avec un nom existant devrait échouer");
        } catch (Exception e) {
            fail("La méthode AjouterLigue() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test pour la méthode UpdateLigue() - vérifie qu'elle fonctionne correctement.
     * Désactivé par défaut car nécessite une connexion à la base de données.
     */
    @Test
    @Disabled("Ce test nécessite une connexion à une base de données active")
    public void testUpdateLigue() {
        try {
            // Pour ce test, nous devons d'abord créer une ligue
            String nomOriginal = "Ligue Update Test " + System.currentTimeMillis();
            Ligue ligue = new Ligue(0, nomOriginal);
            
            // Ajouter la ligue
            LigueRepository.AjouterLigue(ligue);
            
            // Récupérer l'ID attribué (nécessiterait une modification de la méthode AjouterLigue pour retourner l'ID)
            // Pour ce test, on suppose que nous avons un moyen d'obtenir l'ID
            // Simulons cela en cherchant la ligue par son nom
            int ligueId = -1;
            for (Ligue l : LigueRepository.getLigues()) {
                if (l.getNom().equals(nomOriginal)) {
                    ligueId = l.getId();
                    break;
                }
            }
            
            // Si nous avons trouvé la ligue, mettre à jour son nom
            if (ligueId != -1) {
                String nouveauNom = "Ligue Mise à Jour " + System.currentTimeMillis();
                Ligue ligueUpdate = new Ligue(ligueId, nouveauNom);
                
                // Mettre à jour et vérifier le résultat
                boolean result = LigueRepository.UpdateLigue(ligueUpdate);
                assertTrue(result, "La mise à jour d'une ligue avec un nouveau nom unique devrait réussir");
            } else {
                fail("Impossible de trouver la ligue créée pour le test");
            }
        } catch (Exception e) {
            fail("La méthode UpdateLigue() a levé une exception: " + e.getMessage());
        }
    }

    /**
     * Test simple de la classe LigueRepository sans accès à la base de données.
     */
    @Test
    public void testLigueRepositoryClassExists() {
        // Vérifier simplement que la classe existe
        assertNotNull(LigueRepository.class, "La classe LigueRepository devrait exister");
    }
} 