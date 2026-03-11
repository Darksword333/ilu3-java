package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Botte;
import cartes.Carte;
import cartes.Carte.Type;
import cartes.JeuDeCartes;
import jeu.Sabot;

class TestSabot {
    private Sabot sabot;
    private JeuDeCartes jeu;

    @BeforeEach
    void setUp() {
        jeu = new JeuDeCartes();
        sabot = new Sabot(jeu.donnerCartes());
    }

    @Test
    void testPiocherToutesLesCartes() {
        int count = 0;
        while (!sabot.estVide()) {
            Carte c = sabot.piocher();
            assertNotNull(c);
            count++;
        }
        assertEquals(106, count, "On doit pouvoir piocher 106 cartes.");
        assertTrue(sabot.estVide());
    }

    @Test
    void testIteratorEtRemove() {
        int count = 0;
        Iterator<Carte> it = sabot.iterator();
        while (it.hasNext()) { // Test de l'itérateur
            it.next();
            it.remove();
            count++;
        }
        assertEquals(106, count);
        assertTrue(sabot.estVide(), "Le sabot doit être vide");
    }

    @Test
    void testConcurrentModificationPiocher() {
        Iterator<Carte> it = sabot.iterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            it.next();
            sabot.piocher();
            it.next();
        }, "Piocher pendant itération leve ConcurrentModificationException.");
    }

    @Test
    void testConcurrentModificationAjouter() {
        sabot.piocher(); 
        
        Iterator<Carte> it = sabot.iterator();
        Carte asDuVolant = new Botte(Type.ACCIDENT);

        assertThrows(ConcurrentModificationException.class, () -> {
            sabot.ajouterCarte(asDuVolant);
            it.next();
        });
    }
    
    @Test
    void testIllegalStateRemove() {
        Iterator<Carte> it = sabot.iterator();
        // On ne peut pas appeler remove avant le premier next
        assertThrows(IllegalStateException.class, () -> {
            it.remove();
        });
    }
}
