package Sword;

public class Rapier extends Sword {

	public Rapier() {
		super("Melee", "Martial", 3.5);
		traits.put("Deadly", 4.5);
		setPropulsive(true);
		setFinesse(true);
	}

}