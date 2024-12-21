package Bow;

public class Longbow extends Bow {
	
	public Longbow() {
		super("Ranged", "Martial", 4.5);
		traits.put("Deadly", 5.5);
		setFinesse(true);
	}

}
