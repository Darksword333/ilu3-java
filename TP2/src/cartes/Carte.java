package cartes;

public abstract class Carte {
	public enum Type {
	    FEU ("Feu Rouge", "Feu Vert", "Prioritaire"),
	    ESSENCE ("Panne d'Essence", "Essence", "Citerne"),
	    CREVAISON ("Crevaison", "Roue de Secours", "Increvable"),
	    ACCIDENT ("Accident", "Réparations", "As du Volant");
	    
	    private final String attaque;
	    private final String parade;
	    private final String botte;
	    Type(String attaque, String parade, String botte) {
	        this.attaque = attaque;
	        this.parade = parade;
	        this.botte = botte;
	    }
	    public String attaque() { return attaque; }
	    public String parade() { return parade; }
	    public String botte() { return botte; }
	  };
	  
	  @Override
	  public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null || getClass() != obj.getClass()) return false;
	      return true;
	  }
}
