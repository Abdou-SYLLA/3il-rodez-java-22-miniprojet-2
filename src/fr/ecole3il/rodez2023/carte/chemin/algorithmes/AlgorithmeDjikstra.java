package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * Implémente l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux nœuds dans un graphe.
 * @param <E> Le type des éléments stockés dans les nœuds du graphe.
 */
public class AlgorithmeDjikstra<E> implements AlgorithmeChemin<E> {
    private Map<Noeud<E>, Double> couts;
    private Map<Noeud<E>, Noeud<E>> predecesseurs;
    private PriorityQueue<Noeud<E>> filePriorite;

    private final Double INFINI = Double.MAX_VALUE;

    /**
     * Initialise les coûts et les prédecesseurs pour l'algorithme.
     * @param graphe Le graphe dans lequel effectuer la recherche de chemin.
     * @param depart Le nœud de départ.
     */
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

    /**
     * Explore les nœuds du graphe pour trouver le chemin le plus court.
     * @param graphe Le graphe dans lequel effectuer la recherche de chemin.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     */
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

    /**
     * Reconstruit le chemin à partir des prédecesseurs.
     * @param arrivee Le nœud d'arrivée.
     * @return Une liste de nœuds représentant le chemin reconstruit.
     */
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

    /**
     * Trouve le chemin le plus court entre deux nœuds dans un graphe en utilisant l'algorithme de Dijkstra.
     * @param graphe Le graphe dans lequel effectuer la recherche de chemin.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @return Une liste de nœuds représentant le chemin le plus court trouvé.
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        initialisationCout(graphe, depart);
        explorationNoeuds(graphe, depart, arrivee);
        return reconstructionChemin(arrivee);
    }

}
