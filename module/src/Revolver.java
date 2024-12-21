public class Revolver extends Crossbow {

	public Revolver() {
		super("Ranged", "Martial", 3.5);
		traits.put("Fatal", 4.5);
		setFinesse(true);
		setAgile(true);
	}

}
