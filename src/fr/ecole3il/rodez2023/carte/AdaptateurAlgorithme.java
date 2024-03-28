package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilisée pour adapter un algorithme de recherche de chemin à partir de la carte et des coordonnées de départ et d'arrivée.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points sur une carte en utilisant un algorithme de recherche de chemin donné.
     * @param algorithme L'algorithme de recherche de chemin à utiliser.
     * @param carte La carte sur laquelle rechercher le chemin.
     * @param xDepart La coordonnée X du point de départ.
     * @param yDepart La coordonnée Y du point de départ.
     * @param xArrivee La coordonnée X du point d'arrivée.
     * @param yArrivee La coordonnée Y du point d'arrivée.
     * @return Le chemin trouvé entre les deux points.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> arrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);
        if (noeudsChemin == null || noeudsChemin.isEmpty()) {
            return new Chemin(new ArrayList<>());
        }
        return new Chemin(afficherChemin(noeudsChemin));
    }

    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(currentNoeud);
            }
        }
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = graphe.getNoeud(x, y);
                try {
                    ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return graphe;
    }

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentNoeud, int x, int y, int largeur, int hauteur) throws Exception {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < largeur && ny >= 0 && ny < hauteur) {
                Noeud<Case> voisinNoeud = graphe.getNoeud(nx, ny);
                if (voisinNoeud != null) {
                    double cout = calculerCout(currentNoeud.getValeur(), voisinNoeud.getValeur());
                    graphe.ajouterArete(currentNoeud, voisinNoeud, cout);
                    currentNoeud.ajouterVoisin(voisinNoeud);
                }
            }
        }
    }

    private static double calculerCout(Case from, Case to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les cases 'de' et 'a' doivent être non nulles");
        }
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    private static List<Case> afficherChemin(List<Noeud<Case>> noeudsChemin) {
        List<Case> chemin = new ArrayList<>();
        for (Noeud<Case> noeud : noeudsChemin) {
            chemin.add(noeud.getValeur());
        }
        return chemin;
    }
}
