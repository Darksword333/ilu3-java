package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.Carte;
import cartes.JeuDeCartes;

class TestJeuDeCartes {
	private JeuDeCartes jeu;

	@BeforeEach
	void setUp() throws Exception {
		jeu = new JeuDeCartes();
	}

	@Test
    void testTailleDuJeu() {
        Carte[] toutesLesCartes = jeu.donnerCartes();
        assertNotNull(toutesLesCartes, "Le tableau de cartes ne doit pas être nul.");
        assertEquals(106, toutesLesCartes.length, "Le jeu contient 106 cartes.");
    }

	@Test
    void testAffichage() {
        String affichage = jeu.affichageJeuDeCartes();
        assertTrue(affichage.contains("JEU:"), "L'affichage doit commencer par 'JEU:'.");
        assertTrue(affichage.contains("10 25KM"), "L'affichage doit mentionner les 10 bornes de 25KM.");
    }
}
