import java.util.Random;

import Enemies.Enemy;
import Weapons.Weapon;

public class Swash extends Character {

    private String subclass;
    private boolean panache;
    private double preciseStrike = 7;
    private int[] proficiency = {5, 13},
            specialization = {7, 15},
            preciseStrikeProgression = {5, 9, 13, 17};

    public Swash(String subclass, Weapon weapon, String mainStat, int level, int[] statArray) {
        super(weapon, mainStat, level, statArray);
        this.subclass = subclass;
        super.setProficiency(proficiency);
        super.setSpecialization(specialization);
        setPreciseStrike();
    }

    public void setLevel(int level) {
        super.setLevel(level, proficiency, specialization);
        setPreciseStrike();
    }

    public double getPreciseStrike() {
        return preciseStrike;
    }

    public void setPreciseStrike() {
        preciseStrike = 7;

        for(int x = 0; x < preciseStrikeProgression.length; x++) {
            if(getLevel() >= preciseStrikeProgression[x]) {
                preciseStrike += 3.5;
            }
        }
    }

    @Override
    public double strike(Enemy enemy) {
        double strike = super.strike(enemy);
        return panache? strike + getPreciseStrike() / 3.5: strike;
    }

    public double confidentFinisher(Enemy enemy) {
        if(!hasPanache()) {
            System.out.println("You cannot use a Finisher without Panache.");
            return 0;
        }
        double strike = super.strike(enemy);
        setPanache(false);
        double crit = getWeapons().get(0).findDamage("crit", getNumDice(), getSpecialization(), getStr());

        if(strike == crit) {
            return strike + getPreciseStrike() * 2;
        } else if(strike != 0) {
            return strike + getPreciseStrike();
        } else {
            return getPreciseStrike() / 2;
        }
    }

    public double bleedingFinisher(Enemy enemy) {
        if(!hasPanache()) {
            System.out.println("You cannot use a Finisher without Panache.");
            return 0;
        }
        double strike = super.strike(enemy);
        setPanache(false);
        double crit = getWeapons().get(0).findDamage("crit", getNumDice(), getSpecialization(), getStr());

        if(strike == crit) {
            enemy.setCondition("Persistent Damage", getPreciseStrike() * 2);
            return strike + getPreciseStrike() * 2;
        } else if(strike != 0) {
            enemy.setCondition("Persistent Damage", getPreciseStrike());
            return strike + getPreciseStrike();
        } else {
            return 0;
        }
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public boolean hasPanache() {
        return panache;
    }

    public void setPanache(boolean panache) {
        this.panache = panache;
    }

    public boolean getPanache() {
        Random rand = new Random();
        int panacheCheck = rand.nextInt(20) + 1 + 22;
        if(panacheCheck >= 27) {
            setPanache(true);
            return true;
        }
        return false;
    }
}
