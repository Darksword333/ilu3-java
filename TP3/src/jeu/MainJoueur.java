package jeu;

import cartes.Carte;

import java.util.Iterator;
import java.util.List;

public class MainJoueur {
    private List<Carte> main;

    public void prendre(Carte carte){
        main.add(carte);
    }
    public void jouer(Carte carte){
        assert (main.contains(carte));
        main.remove(carte);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Carte> ite = main.iterator(); ite.hasNext(); ){
            Carte carte = ite.next();
            sb.append(carte.toString());
        }
        return sb.toString();
    }

}
