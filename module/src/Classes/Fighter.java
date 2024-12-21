package Classes;

import Enemies.Enemy;
import Weapons.*;

public class Fighter extends Character {

    public int[] proficiency = {1, 5, 13}, specialization = {7, 15};


    public Fighter(Weapon weapon, String mainStat, int level, int[] statArray) {
        super(weapon, mainStat, level, statArray);
        super.setProficiency(proficiency);
        super.setSpecialization(specialization);
    }

    public void setLevel(int level) {
        super.setLevel(level, proficiency, specialization);
    }

    public double doubleSlice(Enemy enemy) {
        if(getWeapons().size() == 1) {
            System.out.println("You cannot use Double Slice with one weapon.");
            return 0;
        }

        Weapon firstWeapon = getWeapons().get(0);
        Weapon secondWeapon = getWeapons().get(1);
        int firstToHit = getToHit(firstWeapon);
        int secondToHit = getToHit(secondWeapon);
        double first = attackRoll(firstWeapon, firstToHit - firstWeapon.getMAP(), enemy.getAC());
        double second;

        if(secondWeapon.isAgile()) {
            second = attackRoll(secondWeapon, secondToHit - secondWeapon.getMAP(), enemy.getAC());
        } else {
            second = attackRoll(secondWeapon, secondToHit - (secondWeapon.getMAP() + 2), enemy.getAC());
        }

        applyMAP();
        applyMAP();
        return first + second;
    }

    public double twinTakedown(Enemy enemy) {
        if(this.getWeapons().size() == 1 ) {
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
        applyMAP();
        result[0] += attackRoll(secondWeapon, secondToHit - secondWeapon.getMAP(), enemy.getAC());
        applyMAP();
        return result[0];
    }

    public double doubleShot(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double first = attackRoll(weapon, toHit - weapon.getMAP() - 2, enemy.getAC());
        double second = attackRoll(weapon, toHit - weapon.getMAP() - 2, enemy.getAC());
        this.applyMAP();
        this.applyMAP();
        return first + second;
    }

    public double tripleShot(Enemy enemy) {
        //MAP does not matter, all three actions are used.
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double first = attackRoll(weapon, toHit - 2, enemy.getAC());
        double second = attackRoll(weapon, toHit - 2, enemy.getAC());
        double third = attackRoll(weapon, toHit - 4, enemy.getAC());
        return first + second + third;
    }

    public double exactingStrike(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double first = attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC());

        if(first != 0) {
            applyMAP();
        }

        return first;
    }

    public double slamDown(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double first = attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC());

        if(first != 0) {
            //make enemy prone
        }

        applyMAP();
        return first;
    }

    public double brutalFinish(Enemy enemy) {
        return super.strike(enemy) + getWeapons().get(0).getDamage();
    }

    public double overwhelmingBlow(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        int roll = attackRoll(toHit - weapon.getMAP());
        double first = 0, additionalDice = 0;

        if(roll >= enemy.getAC() + 10 || roll - weapon.getMAP() == 20) {
            additionalDice = 6.5;
        } else if(roll >= enemy.getAC() && roll <= enemy.getAC() + 9) {
            roll += 10;
        }

        first = getDamage(weapon, roll, enemy.getAC());

        return first + additionalDice;
    }

    public double viciousSwing(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double first = attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC()), additionalDice = 6.5;

        if(getLevel() >= 18) {
            additionalDice *= 3;
        } else if(getLevel() >= 10 && getLevel() <= 17) {
            additionalDice *= 2;
        }

        applyMAP();
        return first + additionalDice;
    }
}
