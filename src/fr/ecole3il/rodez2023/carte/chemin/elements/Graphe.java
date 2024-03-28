package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

/**
 * Représente un graphe utilisé pour la recherche de chemin.
 * @param <E> Le type des éléments associés aux nœuds du graphe.
 */
public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> adjacence;

    /**
     * Récupère la pénalité associée à un nœud.
     * @param noeud Le nœud pour lequel récupérer la pénalité.
     * @return La pénalité associée au nœud.
     */
    public int getPenalite(Noeud<E> noeud) {
        if (noeud.getValeur() instanceof Case) {
            Case caseValue = (Case) noeud.getValeur();
            return caseValue.getTuile().getPenalite();
        }
        return 0;
    }

    /**
     * Constructeur de la classe Graphe.
     */
    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new HashMap<>();
    }

    /**
     * Ajoute un nœud au graphe, s'il n'existe pas déjà.
     * @param noeud Le nœud à ajouter.
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            adjacence.put(noeud, new HashMap<>());
        }
    }

    /**
     * Ajoute une arête entre deux nœuds avec un coût spécifié.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @param cout Le coût de l'arête.
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).put(arrivee, cout);
    }

    /**
     * Récupère le coût de l'arête entre deux nœuds.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @return Le coût de l'arête entre les deux nœuds.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return adjacence.get(depart).get(arrivee);
    }

    /**
     * Récupère la liste des nœuds du graphe.
     * @return La liste des nœuds du graphe.
     */
    public List<Noeud<E>> getNoeuds() {
        return noeuds;
    }

    /**
     * Récupère la liste des voisins d'un nœud.
     * @param noeud Le nœud pour lequel récupérer les voisins.
     * @return La liste des voisins du nœud.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adjacence.get(noeud).keySet());
    }

    /**
     * Récupère le nœud correspondant à des coordonnées spécifiées.
     * @param nx La coordonnée X du nœud.
     * @param ny La coordonnée Y du nœud.
     * @return Le nœud correspondant aux coordonnées spécifiées, ou null s'il n'existe pas.
     */
    public Noeud<E> getNoeud(int nx, int ny) {
        for (Noeud<E> noeud : noeuds) {
            if (noeud.getValeur() instanceof Case) {
                Case caseValue = (Case) noeud.getValeur();
                if (caseValue.getX() == nx && caseValue.getY() == ny) {
                    return noeud;
                }
            }
        }
        return null;
    }

    /**
     * Récupère le coût entre deux nœuds.
     * @param noeudCourant Le nœud de départ.
     * @param voisin Le nœud voisin.
     * @return Le coût entre les deux nœuds.
     * @throws IllegalArgumentException si aucune arête n'existe entre les nœuds fournis.
     */
    public Double getCout(Noeud<E> noeudCourant, Noeud<E> voisin) {
        if (adjacence.containsKey(noeudCourant) && adjacence.get(noeudCourant).containsKey(voisin)) {
            return adjacence.get(noeudCourant).get(voisin);
        } else {
            throw new IllegalArgumentException("Aucune arête n'existe entre les nœuds fournis.");
        }
    }
}
