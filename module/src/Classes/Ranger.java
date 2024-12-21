package Classes;

import Enemies.Enemy;
import Weapons.Weapon;

public class Ranger extends Character {

    private String subclass;
    private boolean hasHit;
    private int[] proficiency = {5, 13}, specialization = {7, 15};

    public Ranger(String subclass, Weapon weapon, String mainStat, int level, int[] statArray) {
        super(weapon, mainStat, level, statArray);
        this.subclass = subclass;
        super.setProficiency(proficiency);
        super.setSpecialization(specialization);
    }

    public void setLevel(int level) {
        super.setLevel(level, proficiency, specialization);
        //dont calculate precision each time
    }

    public String getEdge() {
        return subclass;
    }

    public void setEdge(String rangerEdge) {
        this.subclass = rangerEdge;
    }

    public boolean getHasHit() {
        return hasHit;
    }

    public void setHasHit(boolean hasHit) {
        this.hasHit = hasHit;
    }

    @Override
    public double strike(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double[] first = new double[] {attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC())};
        considerEdge(first);
        return first[0];
    }

    public double steadyStrike(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double[] result = new double[] {attackRoll(weapon, toHit - weapon.getMAP() + 2, enemy.getAC())};
        considerEdge(result);
        return result[0];
    }

    public double twinTakedown(Enemy enemy) {
        if(this.getWeapons().size() == 1) {
            System.out.println("You cannot use Twin Takedown with one weapon.");
            return 0;
        }

        Weapon firstWeapon = getWeapons().get(0);
        Weapon secondWeapon = getWeapons().get(1);

        if(firstWeapon.getType() != "Melee" || secondWeapon.getType() != "Melee") {
            System.out.println("You cannot use Twin Takedown with non-melee weapons.");
            return 0;
        }

        int firstToHit = getToHit(firstWeapon);
        int secondToHit = getToHit(secondWeapon);
        double[] result = new double[] {attackRoll(firstWeapon, firstToHit - firstWeapon.getMAP(), enemy.getAC())};
        considerEdge(new double[1]);
        considerEdge(new double[1]);
        result[0] += attackRoll(secondWeapon, secondToHit - secondWeapon.getMAP(), enemy.getAC());
        considerEdge(result);
        considerEdge(result);
        return result[0];
    }

    public double huntersAim(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double[] result = new double[] {attackRoll(weapon, toHit - weapon.getMAP() + 2, enemy.getAC())};
        considerEdge(result);
        return result[0];
    }

    public double huntedShot(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);

        if(weapon.getType() != "Ranged") {
            System.out.println("You cannot use Hunted Shot without a Ranged weapon.");
            return 0;
        }

        int toHit = getToHit(weapon);
        double[] result = new double[1];
        result[0]  += attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC());
        considerEdge(new double[1]);
        result[0] += attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC());
        considerEdge(result);
        return result[0];
    }

    public double deadlyAim(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double[] result = new double[] {attackRoll(weapon, toHit - weapon.getMAP() - 2, enemy.getAC())};
        considerEdge(result);
        return result[0] != 0? result[0] + 4: result[0];
    }

    public double penetratingShot(Enemy firstEnemy, Enemy secondEnemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        int strike = attackRoll(toHit - weapon.getMAP());
        double first = getDamage(weapon, strike, firstEnemy.getAC());
        double second = getDamage(weapon, strike, secondEnemy.getAC());
        //CONSIDER THE EDGE HERE
        return first + second;
    }

    @Override
    public void endTurn() {
        setHasHit(false);
        super.endTurn();
    }

    private void considerEdge(double[] damage) {
        switch(getEdge()) {
            case "Flurry":
                for(int x = 0; x < getWeapons().size(); x++) {
                    Weapon current = getWeapons().get(x);
                    if(current.isAgile()) {
                        if(current.getMAP() < 4) {
                            current.setMAP(current.getMAP() + 2);
                        }
                    } else {
                        if(current.getMAP() < 6) {
                            current.setMAP(current.getMAP() + 3);
                        }
                    }
                }
                break;
            case "Precision":
                if(damage[0] != 0 && !getHasHit()) {
                    setHasHit(true);
                    damage[0] += 9;
                }
                applyMAP();
                break;
            case "Outwit":
                //lmao
                applyMAP();
                break;
        }
    }
}
