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
	
	@Override
	public boolean equals(Object obj) {
	    if (!super.equals(obj)) return false;
	    Borne borne = (Borne) obj;
	    return km == borne.km;
	}
}
