/**
 * Package fr.ecole3il.rodez2023.carte.chemin.elements contient les classes relatives aux éléments du graphe.
 */
package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.List;

/**
 * Représente un nœud dans un graphe.
 *
 * @param <E> Type de la valeur contenue dans le nœud.
 */
public class Noeud<E> {

    /**
     * La valeur stockée dans le nœud.
     */
    private E valeur;

    /**
     * La liste des nœuds voisins.
     */
    private List<Noeud<E>> voisins;

    /**
     * Construit une nouvelle instance de Noeud avec une valeur donnée.
     *
     * @param valeur La valeur à stocker dans le nœud.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
    }

    /**
     * Construit une nouvelle instance de Noeud avec une liste de nœuds voisins.
     *
     * @param voisins La liste des nœuds voisins.
     */
    public Noeud(List<Noeud<E>> voisins) {
        this.voisins = voisins;
    }

    /**
     * Récupère la valeur stockée dans le nœud.
     *
     * @return La valeur du nœud.
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des nœuds voisins.
     *
     * @return La liste des nœuds voisins.
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un nœud voisin à la liste des voisins.
     *
     * @param voisin Le nœud voisin à ajouter.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        voisins.add(voisin);
    }
}
