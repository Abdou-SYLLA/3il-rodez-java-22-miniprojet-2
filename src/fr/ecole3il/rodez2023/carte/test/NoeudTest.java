package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoeudTest {
    private Noeud<Integer> noeud;

    @BeforeEach
    void setUp() {
        // Initialisation d'un nœud pour les tests
        noeud = new Noeud<>(5);
    }

    @AfterEach
    void tearDown() {
        // Réinitialisation du nœud après chaque test
        noeud = null;
    }

    @Test
    void getValeur() {
        // Test de la méthode getValeur
        assertEquals(5, noeud.getValeur());
    }

    @Test
    void getVoisins() {
        // Test de la méthode getVoisins pour un nœud sans voisins
        assertTrue(noeud.getVoisins().isEmpty());
    }

    @Test
    void ajouterVoisin() {
        // Test de la méthode ajouterVoisin
        Noeud<Integer> voisin1 = new Noeud<>(10);
        Noeud<Integer> voisin2 = new Noeud<>(15);

        // Vérification initiale que le nœud n'a pas de voisins
        assertTrue(noeud.getVoisins().isEmpty());

        // Ajout de deux voisins au nœud
        noeud.ajouterVoisin(voisin1);
        noeud.ajouterVoisin(voisin2);

        // Vérification que les voisins ont été ajoutés avec succès
        assertEquals(2, noeud.getVoisins().size());
        assertTrue(noeud.getVoisins().contains(voisin1));
        assertTrue(noeud.getVoisins().contains(voisin2));
    }
}
