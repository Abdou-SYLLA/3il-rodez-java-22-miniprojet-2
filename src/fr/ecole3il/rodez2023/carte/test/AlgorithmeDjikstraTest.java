package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDjikstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmeDjikstraTest {

    @Test
    void trouverChemin() {
        // Création d'un graphe de test
        Graphe<Integer> graphe = new Graphe<>();
        Noeud<Integer> noeud1 = new Noeud<>(1);
        Noeud<Integer> noeud2 = new Noeud<>(2);
        Noeud<Integer> noeud3 = new Noeud<>(3);
        Noeud<Integer> noeud4 = new Noeud<>(4);
        Noeud<Integer> noeud5 = new Noeud<>(5);

        graphe.ajouterNoeud(noeud1);
        graphe.ajouterNoeud(noeud2);
        graphe.ajouterNoeud(noeud3);
        graphe.ajouterNoeud(noeud4);
        graphe.ajouterNoeud(noeud5);

        graphe.ajouterArete(noeud1, noeud2, 1);
        graphe.ajouterArete(noeud1, noeud3, 4);
        graphe.ajouterArete(noeud2, noeud3, 2);
        graphe.ajouterArete(noeud2, noeud4, 5);
        graphe.ajouterArete(noeud3, noeud5, 3);
        graphe.ajouterArete(noeud4, noeud5, 1);

        AlgorithmeChemin<Integer> algorithme = new AlgorithmeDjikstra<>();
        List<Noeud<Integer>> chemin = algorithme.trouverChemin(graphe, noeud1, noeud5);

        assertNotNull(chemin);
        assertEquals(4, chemin.size()); // Le chemin le plus court est 1 -> 2 -> 3 -> 5
        assertEquals(noeud1, chemin.get(0));
        assertEquals(noeud2, chemin.get(1));
        assertEquals(noeud3, chemin.get(2));
        assertEquals(noeud5, chemin.get(3));
    }
}
