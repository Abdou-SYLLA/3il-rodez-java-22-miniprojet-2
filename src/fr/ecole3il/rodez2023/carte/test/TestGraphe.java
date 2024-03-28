package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import static org.junit.jupiter.api.Assertions.*;

class TestGraphe {
    Graphe<String> graphe = new Graphe<String>();
    Noeud<String> node1 = new Noeud<>("A");
    Noeud<String> node2 = new Noeud<>("B");
    Noeud<String> node3 = new Noeud<>("C");
    Noeud<String> node4 = new Noeud<>("D");
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    //Test ajout arete
    @org.junit.jupiter.api.Test
    void testAjouterNoeud() {
        int valeurAvant = graphe.getNoeuds().size();
        graphe.ajouterNoeud(node1);
        if(graphe.getNoeuds().size() <= valeurAvant )
            System.out.println("Erreur noeud non ajouter");
    }

    @org.junit.jupiter.api.Test
    void ajouterArete() {
    }
}