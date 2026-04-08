package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> collectionBornes = new ArrayList<>();

	public int donnerLimitationVitesse(){
		if (pileLimites.isEmpty()
				|| pileLimites.getLast() instanceof FinLimite){
			return 200;
		}
		return 50;
	}

	public int donnerKmParcourus(){
		int totalKm = 0;
		for (Iterator<Borne> ite = collectionBornes.iterator(); ite.hasNext(); ){
			Borne borne = ite.next();
			totalKm += borne.getKm();
		}
		return totalKm;
	}

	public void deposer(Carte carte){
		if (carte instanceof Limite)
			pileLimites.add((Limite) carte);
		if (carte instanceof Bataille)
			pileBataille.add((Bataille) carte);
		if (carte instanceof Borne)
			collectionBornes.add((Borne) carte);
	}
	public boolean peutAvancer() {
		if (pileBataille.isEmpty()) return false;
		Bataille sommet = pileBataille.getLast();
		return sommet.equals(Cartes.FEU_VERT);
	}

	private boolean estDepotFeuVertAutorise(){
		if (pileBataille.isEmpty()) return true;
		Bataille sommet = pileBataille.getLast();
		return sommet.equals(Cartes.FEU_ROUGE) || ((sommet instanceof Parade) && !sommet.equals(Cartes.FEU_VERT));
	}

	private boolean estDepotBorneAutorise(Borne borne){
		return (donnerKmParcourus() + borne.getKm()) < 1000 && donnerLimitationVitesse() >= borne.getKm() && peutAvancer();
	}

	private boolean estDepotLimiteAutorise(Limite limite){
		// Cas 4)a et 4)b codé sur 2 lignes pour la clarté
		if (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.getLast() instanceof FinLimite)) return true;
        return limite instanceof FinLimite && (!pileLimites.isEmpty() && pileLimites.getLast() instanceof DebutLimite);
    }

	private boolean estDepotBatailleAutorise(Bataille bataille){
		// Cas Attaque
		if (bataille instanceof Attaque && (!pileBataille.isEmpty() && !(pileBataille.getLast() instanceof Attaque))) return true;
		// Cas Parade
		if (bataille instanceof Parade){
            return (bataille.equals(Cartes.FEU_VERT) && estDepotFeuVertAutorise())
					|| (!pileBataille.isEmpty() && pileBataille.getLast().getType().equals(bataille.getType()) && pileBataille.getLast() instanceof Attaque);
		}
		return false;
	}

	public boolean estDepotAutorise(Carte carte){
		if (carte instanceof Borne)
			return estDepotBorneAutorise((Borne) carte);
		if (carte instanceof Bataille)
			return estDepotBatailleAutorise((Bataille) carte);
		if (carte instanceof Limite)
			return estDepotLimiteAutorise((Limite) carte);
		if (carte instanceof  Botte)
			return true; //Toujours autorisé
		return false;
	}
}
