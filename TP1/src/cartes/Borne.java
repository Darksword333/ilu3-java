package cartes;

public class Borne extends Carte{
	private Integer km;
	
	public Borne(Integer km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
	    return km + "KM";
	}
}
