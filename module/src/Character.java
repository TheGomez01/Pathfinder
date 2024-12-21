import Weapons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Enemies.Enemy;

public class Character {

    private String mainStat;
    private List<Weapon> weapons = new ArrayList<>();
    private int level, potency, numDice, proficiency = 2, spec;
    private int[] potScale = {2, 10, 16},
            diceScale = {4, 12, 19},
            statArray = new int[6];

    public Character(Weapon weapon, String mainStat, int level, int[] statArray) {
        weapons.add(weapon);
        this.mainStat = mainStat;
        this.level = level;
        this.statArray = statArray;
        setPotency(potScale);
        setNumDice(diceScale);
    }

    public double strike(Enemy enemy) {
        Weapon weapon = getWeapons().get(0);
        int toHit = getToHit(weapon);
        double strike = attackRoll(weapon, toHit - weapon.getMAP(), enemy.getAC());
        applyMAP();
        return strike;
    }

    public int getToHit(Weapon weapon) {
        if(weapon.isFinesse()) {
            return getLevel() + getProficiency() + getPotency() + getDex();
        } else {
            return getLevel() + getProficiency() + getPotency() + getStr();
        }
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int[] scale) {
        int prof = 2;
        for(int x = 0; x < scale.length; x++) {
            if(getLevel() >= scale[x]) {
                prof += 2;
            }
        }
        proficiency = prof;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int[] scale) {
        int result = 0;
        for(int x = 0; x < potScale.length; x++) {
            if(getLevel() >= potScale[x]) {
                result += 1;
            }
        }
        potency = result;
    }

    public int getSpecialization() {
        return spec;
    }

    public void setSpecialization(int[] scale) {
        boolean greater = getLevel() >= scale[1];
        boolean lesser = getLevel() >= scale[0] && getLevel() < scale[1];

        switch(getProficiency()) {
            case 4:
                //Expert
                if(greater) {
                    spec = 4;
                } else if(lesser){
                    spec = 2;
                }
                break;
            case 6:
                //Master
                if(greater) {
                    spec = 6;
                } else if(lesser) {
                    spec = 3;
                }
                break;
            case 8:
                //Legendary
                if(greater) {
                    spec = 8;
                } else if(lesser) {
                    spec = 4;
                }
                break;
            default:
                //Barbarian shit
        }

    }

    public int getNumDice() {
        return numDice;
    }

    public void setNumDice(int[] scale) {
        int result = 1;
        for(int x = 0; x < diceScale.length; x++) {
            if(getLevel() >= diceScale[x]) {
                result++;
            }
        }
        numDice = result;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public String getMainStat() {
        return this.mainStat;
    }

    public void setMainStat(String mainStat) {
        this.mainStat = mainStat;
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel(int level, int[] profScale, int[] specScale) {
        this.level = level;

        if(level == 10) {
            setMainStat();
        }

        setSpecialization(specScale);
        setProficiency(profScale);
        setPotency(potScale);
        setNumDice(diceScale);
    }

    public void applyMAP() {
        for(int x = 0; x < weapons.size(); x++) {
            weapons.get(x).increaseMAP();
        }
    }

    public void endTurn() {
        for(int x = 0; x < weapons.size(); x++) {
            weapons.get(x).setMAP(0);
        }
    }

    public void setMainStat() {

        switch(getMainStat()) {
            case "Str":
                setStr(getStr() + 1);
                break;
            case "Dex":
                setDex(getDex() + 1);
                break;
            case "Con":
                setCon(getCon() + 1);
                break;
            case "Wis":
                setWis(getWis() + 1);
                break;
            case "Int":
                setIntel(getIntel() + 1);
                break;
            case "Cha":
                setCha(getCha() + 1);
                break;
        }
    }

    public double attackRoll(Weapon weapon, int toHit, int AC) {
        Random r = new Random();
        int roll = r.nextInt(20) + 1;
        boolean crit = roll + toHit >= AC + 10 || roll == 20;
        boolean hit = roll + toHit >= AC && roll + toHit <= AC + 9;

        if(crit) {
            return weapon.findDamage("crit", getNumDice(), getSpecialization(), getStr());
        } else if(hit) {
            return weapon.findDamage("hit", getNumDice(), getSpecialization(), getStr());
        } else {
            return 0;
        }
    }

    public int attackRoll(int toHit) {
        Random r = new Random();
        int roll = r.nextInt(20) + 1;
        return roll + toHit;
    }

    public double getDamage(Weapon weapon, int roll, int AC) {
        boolean crit = roll >= AC + 10 || roll == 20;
        boolean hit = roll >= AC && roll <= AC + 9;

        if(crit) {
            return weapon.findDamage("crit", getNumDice(), getSpecialization(), getStr());
        } else if(hit) {
            return weapon.findDamage("hit", getNumDice(), getSpecialization(), getStr());
        } else {
            return 0;
        }
    }

    public int getStr() {
        return statArray[0];
    }

    public void setStr(int str) {
        statArray[0] = str;
    }

    public int getDex() {
        return statArray[1];
    }

    public void setDex(int dex) {
        statArray[1] = dex;
    }

    public int getCon() {
        return statArray[2];
    }

    public void setCon(int con) {
        statArray[2] = con;
    }

    public int getWis() {
        return statArray[3];
    }

    public void setWis(int wis) {
        statArray[3] = wis;
    }

    public int getIntel() {
        return statArray[4];
    }

    public void setIntel(int intel) {
        statArray[4] = intel;
    }

    public int getCha() {
        return statArray[5];
    }

    public void setCha(int cha) {
        statArray[5] = cha;
    }
}