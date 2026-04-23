package jeu;

import cartes.Carte;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Joueur {
    private String nom;
    private ZoneDeJeu zdj;
    private MainJoueur main;

    public Joueur(String nom) {
        this.nom = nom;
        this.zdj = new ZoneDeJeu();
        this.main = new MainJoueur();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Joueur joueur = (Joueur) obj;
        return Objects.equals(nom, joueur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    @Override
    public String toString() {
        return nom;
    }

    public MainJoueur getMain() { return main; }

    public void donner(Carte carte) { main.prendre(carte); }

    public Carte prendreCarte(Sabot sabot) {
        Carte carte = sabot.piocher();
        if (carte != null) donner(carte);
        return carte;
    }

    public int donnerKmParcourus() { return zdj.donnerKmParcourus(); }

    public void deposer(Carte carte) { zdj.deposer(carte); }

    public boolean estDepotAutorise(Carte carte) { return zdj.estDepotAutorise(carte); }

    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> coups = new HashSet<>();
        for (Carte carte : main) {
            for (Joueur participant : participants) {
                Coup coup = new Coup(this, participant, carte);
                if (coup.estValide() && participant.estDepotAutorise(carte)) {
                    coups.add(coup);
                }
            }
        }
        return coups;
    }

    public Set<Coup> coupsDefausse() {
        Set<Coup> coups = new HashSet<>();
        for (Carte carte : main) {
            coups.add(new Coup(this, null, carte));
        }
        return coups;
    }

    public void retirerDeLaMain(Carte carte) {
        main.jouer(carte);
    }

    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> coups = coupsPossibles(participants);
        if (coups.isEmpty()) coups = coupsDefausse();
        return choisirAleatoire(coups);
    }

    private Coup choisirAleatoire(Set<Coup> coups) {
        int index = (int) (Math.random() * coups.size());
        Iterator<Coup> it = coups.iterator();
        for (int i = 0; i < index; i++) it.next();
        return it.next();
    }

    public String afficherEtatJoueur() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bottes            : ").append(zdj.getBottes()).append("\n");
        sb.append("Limitation vitesse: ").append(zdj.donnerLimitationVitesse() == 50).append("\n");
        sb.append("Sommet bataille   : ").append(zdj.donnerSommetBataille()).append("\n");
        sb.append("Main              : ").append(main);
        return sb.toString();
    }
}
