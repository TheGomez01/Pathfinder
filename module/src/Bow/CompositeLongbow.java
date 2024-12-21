package Bow;

public class CompositeLongbow extends Bow {

	public CompositeLongbow() {
		super("Ranged", "Martial", 4.5);
		traits.put("Deadly", 5.5);
		setPropulsive(true);
		setFinesse(true);
	}

}
