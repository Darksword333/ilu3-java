package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
    private final Sabot sabot;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tabCartes = jeuDeCartes.donnerCartes();

        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, tabCartes);

        List<Carte> listeMelangee = GestionCartes.melanger(listeCartes);

        this.sabot = new Sabot(listeMelangee.toArray(new Carte[0]));
    }

    public Sabot getSabot() {
        return sabot;
    }
}