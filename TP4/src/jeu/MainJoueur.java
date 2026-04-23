package jeu;

import cartes.Carte;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainJoueur implements Iterable<Carte> {

    private List<Carte> main = new ArrayList<>();

    public void prendre(Carte carte) {
        main.add(carte);
    }

    public void jouer(Carte carte) {
        assert (main.contains(carte));
        main.remove(carte);
    }

    public int size() {
        return main.size();
    }

    @Override
    public Iterator<Carte> iterator() {
        return main.iterator();
    }

    @Override
    public String toString() {
        return main.toString();
    }
}
