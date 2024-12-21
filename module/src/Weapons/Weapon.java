package Weapons;

import java.util.HashMap;

public class Weapon {

    private String type, category;
    private boolean finesse, agile, propulsive;
    private int MAP;

    public HashMap<String, Double> traits = new HashMap<>();
    //Deadly, Fatal

    private double damage;

    public Weapon(String type, String category, double damage) {
        this.type = type;
        this.category = category;
        this.damage = damage;
    }

    public double findDamage(String result, int numDice, int spec, int str) {
        if(!isPropulsive()) {
            str = 0;
        }

        switch(result) {
            case "crit":
                if(traits.get("Fatal") != null) {
                    return 2 * (numDice * traits.get("Fatal") + spec + str) + traits.get("Fatal");
                }

                if(traits.get("Deadly") != null) {
                    return 2 * (numDice * getDamage() + spec + str) + traits.get("Deadly");
                }

                return 2 * (numDice * getDamage() + spec + str);
            case "hit":
                return numDice * getDamage() + spec + str;
            default:
                return 0;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMAP() {
        return MAP;
    }

    public void setMAP(int MAP) {
        this.MAP = MAP;
    }

    public void increaseMAP() {
        if(getMAP() >= 6) {
            return;
        }

        if(isAgile()) {
            setMAP(getMAP() + 4);
        } else {
            setMAP(getMAP() + 5);
        }
    }

    public boolean isFinesse() {
        return finesse;
    }

    public void setFinesse(boolean finesse) {
        this.finesse = finesse;
    }

    public boolean isAgile() {
        return agile;
    }

    public void setAgile(boolean agile) {
        this.agile = agile;
    }

    public boolean isPropulsive() {
        return propulsive;
    }

    public void setPropulsive(boolean propulsive) {
        this.propulsive = propulsive;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }



}