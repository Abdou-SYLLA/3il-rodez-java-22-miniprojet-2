package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> noeudsAExplorer = new PriorityQueue<>(Comparator.comparingDouble(coutTotalEstime::get));

        // Initialisation des coûts
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.MAX_VALUE);
            coutActuel.put(noeud, Double.MAX_VALUE);
            predecesseurs.put(noeud, null);
        }
        coutActuel.put(depart, 0.0);
        coutTotalEstime.put(depart, estimationHeuristique(depart, arrivee));

        // Boucle principale
        noeudsAExplorer.add(depart);
        while (!noeudsAExplorer.isEmpty()) {
            Noeud<E> noeudCourant = noeudsAExplorer.poll();

            if (noeudCourant.equals(arrivee)) {
                // Reconstruction du chemin
                return reconstructionChemin(predecesseurs, arrivee);
            }

            for (Noeud<E> voisin : graphe.getVoisins(noeudCourant)) {
                double coutNouveau = coutActuel.get(noeudCourant) + graphe.getCoutArete(noeudCourant, voisin);
                if (coutNouveau < coutActuel.get(voisin)) {
                    predecesseurs.put(voisin, noeudCourant);
                    coutActuel.put(voisin, coutNouveau);
                    double coutTotalEstimeVoisin = coutNouveau + estimationHeuristique(voisin, arrivee);
                    coutTotalEstime.put(voisin, coutTotalEstimeVoisin);
                    if (!noeudsAExplorer.contains(voisin)) {
                        noeudsAExplorer.add(voisin);
                    }
                }
            }
        }
        // Si aucun chemin n'a été trouvé
        return null;
    }

    private double estimationHeuristique(Noeud<E> noeud, Noeud<E> arrivee) {
        // Exemple d'estimation heuristique : la distance euclidienne entre les nœuds
        // Ici, vous devriez implémenter votre propre fonction d'estimation heuristique
        // en fonction des caractéristiques de votre problème spécifique.
        // Cette fonction doit renvoyer une estimation du coût restant pour atteindre l'arrivée depuis le nœud actuel.
        // Plus cette estimation est précise, plus l'algorithme A* sera efficace.
        // Pour le moment, cette fonction retourne toujours 0, ce qui équivaut à Dijkstra.
        return 0;
    }

    private List<Noeud<E>> reconstructionChemin(Map<Noeud<E>, Noeud<E>> predecesseurs, Noeud<E> arrivee) {
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;
        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = predecesseurs.get(noeudActuel);
        }
        Collections.reverse(chemin);
        return chemin;
    }
}

