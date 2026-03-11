package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	public String toString() {
		return this.getType().botte();
	}
}
