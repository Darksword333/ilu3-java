package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
    private static final int NBCARTES = 6;
    private final Sabot sabot;
    private Set<Joueur> joueurs = new LinkedHashSet<>();
    private Iterator<Joueur> iterJoueurs;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tabCartes = jeuDeCartes.donnerCartes();
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, tabCartes);
        List<Carte> listeMelangee = GestionCartes.melanger(listeCartes);
        this.sabot = new Sabot(listeMelangee.toArray(new Carte[0]));
    }

    public Sabot getSabot() { return sabot; }

    public void inscrire(Joueur... nouveauxJoueurs) {
        for (Joueur j : nouveauxJoueurs) {
            joueurs.add(j);
        }
    }

    public void distribuerCartes() {
        boolean carteDistribuee;
        do {
            carteDistribuee = false;
            for (Joueur j : joueurs) {
                if (j.getMain().size() < NBCARTES && !sabot.estVide()) {
                    j.prendreCarte(sabot);
                    carteDistribuee = true;
                }
            }
        } while (carteDistribuee);
    }

    public Joueur donnerJoueurSuivant() {
        if (iterJoueurs == null || !iterJoueurs.hasNext()) {
            iterJoueurs = joueurs.iterator();
        }
        return iterJoueurs.next();
    }

    public String jouerTour(Joueur joueur) {
        StringBuilder sb = new StringBuilder();

        Carte cartePiochee = joueur.prendreCarte(sabot);
        sb.append("Le joueur ").append(joueur).append(" a pioche ").append(cartePiochee).append("\n");
        sb.append("Il a dans sa main : ").append(joueur.getMain()).append("\n");

        Coup coup = joueur.choisirCoup(joueurs);
        joueur.retirerDeLaMain(coup.getCarteJouee());

        if (coup.getJoueurCible() == null) {
            sabot.ajouterCarte(coup.getCarteJouee());
        } else {
            coup.getJoueurCible().deposer(coup.getCarteJouee());
        }

        sb.append(joueur).append(" ").append(coup).append("\n");
        return sb.toString();
    }

    public String lancer() {
        StringBuilder sb = new StringBuilder();
        while (!sabot.estVide()) {
            Joueur joueur = donnerJoueurSuivant();
            sb.append(jouerTour(joueur));
            if (joueur.donnerKmParcourus() >= 1000) break;
        }
        return sb.toString();
    }
}
