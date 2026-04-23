package cartes;

public abstract class Probleme extends Carte {
	private Type type;
	
	public Probleme(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
		
	public abstract String toString();
	
	@Override
	public boolean equals(Object obj) {
	    if (!super.equals(obj)) return false;
	    Probleme probleme = (Probleme) obj;
	    return type.equals(probleme.type);
	}
}
