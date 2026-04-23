package jeu;

import java.util.*;
import cartes.*;

public class ZoneDeJeu {
    private List<Limite> pileLimites = new ArrayList<>();
    private List<Bataille> pileBataille = new ArrayList<>();
    private List<Borne> collectionBornes = new ArrayList<>();
    private Set<Botte> collectionBottes = new HashSet<>();

    public int donnerLimitationVitesse() {
        if (pileLimites.isEmpty()
                || pileLimites.getLast() instanceof FinLimite
                || estPrioritaire()) {
            return 200;
        }
        return 50;
    }

    public int donnerKmParcourus() {
        int totalKm = 0;
        for (Iterator<Borne> ite = collectionBornes.iterator(); ite.hasNext(); ) {
            Borne borne = ite.next();
            totalKm += borne.getKm();
        }
        return totalKm;
    }

    public void deposer(Carte carte) {
        if (carte instanceof Limite)   pileLimites.add((Limite) carte);
        if (carte instanceof Bataille) pileBataille.add((Bataille) carte);
        if (carte instanceof Borne)    collectionBornes.add((Borne) carte);
        if (carte instanceof Botte)    collectionBottes.add((Botte) carte);
    }

    private boolean possedeBotteProtection(Bataille sommet) {
        if (collectionBottes.isEmpty()) return false;
        for (Botte b : collectionBottes) {
            if (b.getType().equals(sommet.getType())) return true;
        }
        return false;
    }

    public boolean peutAvancer() {
        if (pileBataille.isEmpty()) return estPrioritaire();
        Bataille sommet = pileBataille.getLast();
        if (sommet.equals(Cartes.FEU_VERT)) return true;
        if (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT) && estPrioritaire()) return true;
        if (sommet.equals(Cartes.FEU_ROUGE) && estPrioritaire()) return true;
        return sommet instanceof Attaque && !sommet.equals(Cartes.FEU_ROUGE)
            && possedeBotteProtection(sommet) && estPrioritaire();
    }

    private boolean estDepotFeuVertAutorise() {
        if (estPrioritaire()) return false;
        if (pileBataille.isEmpty()) return true;
        Bataille sommet = pileBataille.getLast();
        return sommet.equals(Cartes.FEU_ROUGE)
            || ((sommet instanceof Parade) && !sommet.equals(Cartes.FEU_VERT))
            || (sommet instanceof Attaque && possedeBotteProtection(sommet));
    }

    private boolean estDepotBorneAutorise(Borne borne) {
        return (donnerKmParcourus() + borne.getKm()) <= 1000
            && donnerLimitationVitesse() >= borne.getKm()
            && peutAvancer();
    }

    private boolean estDepotLimiteAutorise(Limite limite) {
        if (estPrioritaire()) return false;
        if (limite instanceof DebutLimite
                && (pileLimites.isEmpty() || pileLimites.getLast() instanceof FinLimite)) return true;
        return limite instanceof FinLimite
            && (!pileLimites.isEmpty() && pileLimites.getLast() instanceof DebutLimite);
    }

    private boolean estDepotBatailleAutorise(Bataille bataille) {
        if (possedeBotteProtection(bataille)) return false;
        if (bataille instanceof Attaque && peutAvancer()) return true;
        if (bataille instanceof Parade) {
            return (bataille.equals(Cartes.FEU_VERT) && estDepotFeuVertAutorise())
                || (!pileBataille.isEmpty()
                    && pileBataille.getLast().getType().equals(bataille.getType())
                    && pileBataille.getLast() instanceof Attaque);
        }
        return false;
    }

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne)    return estDepotBorneAutorise((Borne) carte);
        if (carte instanceof Bataille) return estDepotBatailleAutorise((Bataille) carte);
        if (carte instanceof Limite)   return estDepotLimiteAutorise((Limite) carte);
        return carte instanceof Botte; // Botte toujours autorisé
    }

    public boolean estPrioritaire() {
        return collectionBottes.contains(Cartes.PRIORITAIRE);
    }

    public Bataille donnerSommetBataille() {
        if (pileBataille.isEmpty()) return null;
        return pileBataille.getLast();
    }

    public Set<Botte> getBottes() {
        return collectionBottes;
    }
}
