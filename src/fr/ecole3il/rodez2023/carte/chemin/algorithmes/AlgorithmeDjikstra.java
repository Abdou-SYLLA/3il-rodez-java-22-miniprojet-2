package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;
import java.util.Map;

public class AlgorithmeDjikstra <E> implements AlgorithmeChemin{
    private Map<Noeud<E>, Map<Noeud<E>, Double>> matriceCouts;
    private final Double INFINI = Double.MAX_VALUE;

    @Override
    public List<Noeud> trouverChemin(Graphe graphe, Noeud depart, Noeud arrivee) {
        return null;
    }

    public void initialisationCout(){

    }

}
