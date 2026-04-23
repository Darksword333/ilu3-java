package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;
import java.util.Objects;

public class Coup {
    private Joueur joueurCourant;
    private Joueur joueurCible;
    private Carte carteJouee;

    public Coup(Joueur joueurCourant, Joueur joueurCible, Carte carteJouee) {
        this.joueurCourant = joueurCourant;
        this.joueurCible = joueurCible;
        this.carteJouee = carteJouee;
    }

    public Carte getCarteJouee() { return carteJouee; }
    public Joueur getJoueurCible() { return joueurCible; }
    public Joueur getJoueurCourant() { return joueurCourant; }

    public boolean estValide() {
        if (joueurCible == null) return true;
        if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
            return !joueurCible.equals(joueurCourant);
        }
        return joueurCible.equals(joueurCourant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coup coup = (Coup) obj;
        return Objects.equals(joueurCourant, coup.joueurCourant)
            && Objects.equals(joueurCible, coup.joueurCible)
            && Objects.equals(carteJouee, coup.carteJouee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueurCourant, joueurCible, carteJouee);
    }

    @Override
    public String toString() {
        if (joueurCible == null) {
            return "defausse la carte " + carteJouee;
        }
        return "depose la carte " + carteJouee + " dans la zone de jeu de " + joueurCible;
    }
}
