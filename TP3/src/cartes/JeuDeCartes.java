package cartes;

import java.awt.List;
import java.util.Arrays;
import java.util.Collections;

import cartes.Carte.Type;

public class JeuDeCartes{
	private Configuration[] typesDeCartes = new Configuration[19];
	
	public JeuDeCartes() {
	    // Bornes 
	    typesDeCartes[0] = new Configuration(new Borne(25), 10);
	    typesDeCartes[1] = new Configuration(new Borne(50), 10);
	    typesDeCartes[2] = new Configuration(new Borne(75), 10);
	    typesDeCartes[3] = new Configuration(new Borne(100), 12);
	    typesDeCartes[4] = new Configuration(new Borne(200), 4);

	    // Parades
	    typesDeCartes[5] = new Configuration(new Parade(Type.FEU), 14); // Feu Vert
	    typesDeCartes[6] = new Configuration(new FinLimite(), 6);
	    typesDeCartes[7] = new Configuration(new Parade(Type.ESSENCE), 6); // Bidon d'essence
	    typesDeCartes[8] = new Configuration(new Parade(Type.CREVAISON), 6); // Roue de secours
	    typesDeCartes[9] = new Configuration(new Parade(Type.ACCIDENT), 6); // Réparation

	    // Attaques
	    typesDeCartes[10] = new Configuration(new Attaque(Type.FEU), 5); // Feu Rouge
	    typesDeCartes[11] = new Configuration(new DebutLimite(), 4);
	    typesDeCartes[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
	    typesDeCartes[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
	    typesDeCartes[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);

	    // Bottes
	    typesDeCartes[15] = new Configuration(new Botte(Type.FEU), 1); // Prioritaire
	    typesDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1); // Citerne
	    typesDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1); // Increvable
	    typesDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1); // As du volant
	}
	
	public String affichageJeuDeCartes() {
	    StringBuilder sb = new StringBuilder("JEU:\n");
	    for (Configuration conf : typesDeCartes) {
	        if (conf != null) {
	        	sb.append(conf.getNbExemplaires())
	        	  .append(" ")
	        	  .append(conf.getCarte().toString())
	        	  .append("\n");
	        }
	    }
	    return sb.toString();
	}
	
	public Carte[] donnerCartes() {
        int total = 0;
        for (Configuration conf : typesDeCartes) {
            if (conf != null) total += conf.getNbExemplaires();
        }
        
        Carte[] toutesLesCartes = new Carte[total];
        int index = 0;
        for (Configuration conf : typesDeCartes) {
            if (conf != null) {
                for (int i = 0; i < conf.getNbExemplaires(); i++) {
                    toutesLesCartes[index++] = conf.getCarte();
                }
            }
        }
        return toutesLesCartes;
    }
	
	public boolean checkCount() {
	    Carte[] cartesGenerees = donnerCartes();
	    
	    java.util.List<Carte> listeCartes = Arrays.asList(cartesGenerees);

	    for (Configuration conf : typesDeCartes) {
	        Carte carteAttendue = conf.getCarte();
	        int nbAttendu = conf.getNbExemplaires();

	        int nbReel = Collections.frequency(listeCartes, carteAttendue);
	        
	        if (nbReel != nbAttendu) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	private static class Configuration {
		private Carte carte;
		private Integer nbExemplaires;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		private Carte getCarte() {
			return carte;
		}
		
		private Integer getNbExemplaires() {
			return nbExemplaires;
		}
	}
}