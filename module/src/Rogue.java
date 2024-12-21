import Enemies.Enemy;
import Weapons.Weapon;

public class Rogue extends Character {

    private String subclass;
    private double sneakAttack = 3.5;
    private int[] proficiency = {5, 13}, specialization = {7, 15}, sneakAttackProgression = {1, 5, 11, 17};


    public Rogue(String subclass, Weapon weapon, String mainStat, int level, int[] statArray) {
        super(weapon, mainStat, level, statArray);
        this.subclass = subclass;
        super.setProficiency(proficiency);
        super.setSpecialization(specialization);
        setSneakAttack();
    }

    public void setLevel(int level) {
        super.setLevel(level, proficiency, specialization);
        setSneakAttack();
    }

    public double getSneakAttack() {
        return sneakAttack;
    }

    public void setSneakAttack() {
        sneakAttack = 0;

        for(int x = 0; x < sneakAttackProgression.length; x++) {
            if(getLevel() >= sneakAttackProgression[x]) {
                sneakAttack += 3.5;
            }
        }
    }

    @Override
    public double strike(Enemy enemy) {
        double strike = super.strike(enemy);
        return strike + sneakAttack(enemy, strike);
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    private double sneakAttack(Enemy enemy, double damage) {
        //Be sure to make prone apply off guard
        if(enemy.getCondition("Off Guard") == 1 || damage == 0) {
            return 0;
        }
        //System.out.println("Sneak Attack!");
        return getSneakAttack();
    }
}