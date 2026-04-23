package jeu;

public class TestJeu {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();

        Joueur jack  = new Joueur("Jack");
        Joueur bill  = new Joueur("Bill");
        Joueur luffy = new Joueur("Luffy");

        jeu.inscrire(jack, bill, luffy);
        jeu.distribuerCartes();

        System.out.println("=== État initial ===");
        System.out.println("Jack :\n"  + jack.afficherEtatJoueur());
        System.out.println("Bill :\n"  + bill.afficherEtatJoueur());
        System.out.println("Luffy :\n" + luffy.afficherEtatJoueur());

        System.out.println("\n=== Premier tour ===");
        System.out.print(jeu.jouerTour(jack));
        System.out.print(jeu.jouerTour(bill));
        System.out.print(jeu.jouerTour(luffy));

        System.out.println("\n=== Partie complète ===");
        System.out.print(jeu.lancer());
    }
}
