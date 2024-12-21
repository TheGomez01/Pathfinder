import java.util.HashMap;

import Effects.Condition;

public class Enemy {

	private int level, AC;
	private Condition c = new Condition();
	private HashMap<String, Double> cond = c.getAll();
	
	public Enemy(int level) {
		this.level = level;
		setAC();
	}
	
	public double getCondition(String condition) {
		return cond.get(condition);
	}
	
	public void setCondition(String condition, double status) {
		
		if(!cond.containsKey(condition)) {
			System.out.println("Condition doesn't exist.");
			return;
		}
		
		cond.put(condition, status);
		c.applyCondition(this, condition);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		setAC();
	}

	public int getAC() {
		return AC;
	}
	
	public void setAC(int AC) {
		this.AC = AC;
	}

	public void setAC() {
		int result = 15;
		for(int x = 2; x <= getLevel(); x++) {
			if(x % 2 == 0) {
				result += 2;
			} else {
				result += 1;
			}
		}
		this.AC = result;
	}
	
	public void endTurn() {
		c.reduceConditions(this);
	}
}
