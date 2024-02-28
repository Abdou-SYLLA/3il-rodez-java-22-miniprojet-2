package fr.ecole3il.rodez2023.carte.chemin.elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdjacence;

    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.matriceAdjacence = new HashMap<>();
    }
    public Graphe(List<Noeud<E>> noeuds, Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdjacence) {
        this.noeuds = noeuds;
        this.matriceAdjacence = matriceAdjacence;
    }

    public Graphe(List<Noeud<E>> noeuds) {
        this.noeuds = noeuds;
    }

    public void ajouterNoeud(Noeud<E> noeud){
        noeuds.add(noeud);
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) throws Exception {
        if (!noeuds.contains(depart))
            ajouterNoeud(depart);

        if (!noeuds.contains(arrivee))
            ajouterNoeud(arrivee);

        Map<Noeud<E>, Double> mapDepart = matriceAdjacence.get(depart);
        matriceAdjacence.put(depart, mapDepart);
        mapDepart.put(arrivee, cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee){
        Double cout = 0.0;
        Map<Noeud<E>, Double> mapDepart = matriceAdjacence.get(depart);
        cout = mapDepart.get(arrivee);
        return cout;
    }

    public List<Noeud<E>> getNoeuds() {
        return noeuds;
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud){
        return noeud.getVoisins();
    }

}




