package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un nœud dans un graphe.
 * @param <E> Le type de la valeur associée au nœud.
 */
public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    /**
     * Constructeur de la classe Noeud.
     * @param valeur La valeur associée au nœud.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur associée au nœud.
     * @return La valeur associée au nœud.
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des voisins du nœud.
     * @return La liste des voisins du nœud.
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un voisin au nœud.
     * @param voisin Le nœud voisin à ajouter.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
