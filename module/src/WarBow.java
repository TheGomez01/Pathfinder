public class WarBow extends Bow {

	public WarBow() {
		super("Ranged", "Martial", 5.5);
		traits.put("Deadly", 6.5);
		setPropulsive(true);
		setFinesse(true);
	}

}
