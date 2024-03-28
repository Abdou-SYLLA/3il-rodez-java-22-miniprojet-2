package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import java.util.List;

/**
 * Interface définissant un algorithme de recherche de chemin dans un graphe.
 * @param <E> Le type des éléments stockés dans les nœuds du graphe.
 */
public interface AlgorithmeChemin<E> {

    /**
     * Trouve le chemin entre deux nœuds dans un graphe.
     * @param graphe Le graphe dans lequel rechercher le chemin.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @return Une liste de nœuds représentant le chemin trouvé.
     */
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
