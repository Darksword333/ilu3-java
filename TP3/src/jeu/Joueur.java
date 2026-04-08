package jeu;

import cartes.Carte;

import java.util.Iterator;
import java.util.Objects;

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
	public String toString() {
		return nom;
	}

	public MainJoueur getMain(){
		return main;
	}

	public void donner(Carte carte){
		main.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot){
		Carte carte = sabot.piocher();
		if (carte != null) donner(carte);
		return carte;
	}

	public int donnerKmParcourus() {
		return zdj.donnerKmParcourus();
	}

	public void deposer(Carte carte){
		zdj.deposer(carte);
	}
	public boolean estDepotAutorise(Carte carte){
		return zdj.estDepotAutorise(carte);
	}
}
