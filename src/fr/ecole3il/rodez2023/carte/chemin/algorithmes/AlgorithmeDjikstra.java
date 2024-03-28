package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;
import java.util.Map;

import java.util.*;

public class AlgorithmeDjikstra<E> implements AlgorithmeChemin<E> {
    private Map<Noeud<E>, Double> couts;
    private Map<Noeud<E>, Noeud<E>> predecesseurs;
    private PriorityQueue<Noeud<E>> filePriorite;

    private final Double INFINI = Double.MAX_VALUE;



    public void initialisationCout(Graphe<E> graphe, Noeud<E> depart) {
        couts = new HashMap<>();
        predecesseurs = new HashMap<>();
        filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            if (noeud.equals(depart)) {
                couts.put(noeud, 0.0);
            } else {
                couts.put(noeud, INFINI);
            }
            predecesseurs.put(noeud, null);
            filePriorite.add(noeud);
        }
    }

    public void explorationNoeuds(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break; // Sortie de la boucle si le nœud d'arrivée est atteint
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.remove(voisin); // Mise à jour de la priorité dans la file
                    filePriorite.add(voisin);
                }
            }
        }
    }

    public List<Noeud<E>> reconstructionChemin(Noeud<E> arrivee) {
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;
        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = predecesseurs.get(noeudActuel);
        }
        Collections.reverse(chemin); // Inversion de l'ordre pour obtenir le chemin du départ à l'arrivée
        return chemin;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        initialisationCout(graphe, depart);
        explorationNoeuds(graphe, depart, arrivee);
        return reconstructionChemin(arrivee);
    }
}

