package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Carte;
import cartes.Parade;

public interface Cartes {
    Botte PRIORITAIRE = new Botte(Carte.Type.FEU);
    Attaque FEU_ROUGE = new Attaque(Carte.Type.FEU);
    Parade FEU_VERT = new Parade(Carte.Type.FEU);
}
