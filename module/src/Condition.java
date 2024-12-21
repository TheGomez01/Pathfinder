import java.util.HashMap;

import Enemies.Enemy;

public class Condition {

	HashMap<String, Double> cond = new HashMap<>();
	
	public Condition() {
		cond.put("Blinded", (double) 0);
		cond.put("Clumsy", (double) 0);
		cond.put("Concealed", (double) 0);
		cond.put("Confused", (double) 0);
		cond.put("Controlled", (double) 0);
		cond.put("Dazzled", (double) 0);
		cond.put("Deafened", (double) 0);
		cond.put("Doomed", (double) 0);
		cond.put("Drained", (double) 0);
		cond.put("Dying", (double) 0);
		cond.put("Encumbered", (double) 0);
		cond.put("Enfeebled", (double) 0);
		cond.put("Fascinated", (double) 0);
		cond.put("Fatigued", (double) 0);
		cond.put("Fleeing", (double) 0);
		cond.put("Frightened", (double) 0);
		cond.put("Grabbed", (double) 0);
		cond.put("Hidden", (double) 0);
		cond.put("Immobilized", (double) 0);
		cond.put("Invisible", (double) 0);
		cond.put("Off Guard", (double) 0);
		cond.put("Paralyzed", (double) 0);
		cond.put("Persistent Damage", (double) 0);
		cond.put("Petrified", (double) 0);
		cond.put("Prone", (double) 0);
		cond.put("Quickened", (double) 0);
		cond.put("Restrained", (double) 0);
		cond.put("Sickened", (double) 0);
		cond.put("Slowed", (double) 0);
		cond.put("Stunned", (double) 0);
		cond.put("Stupefied", (double) 0);
		cond.put("Unconscious", (double) 0);
		cond.put("Undetected", (double) 0);
		cond.put("Wounded", (double) 0);
		
		
	}
	
	public HashMap<String, Double> getAll() {
		return cond;
	}
	
	public void setCondition(String condition, double status) {
		
		if(!cond.containsKey(condition)) {
			System.out.println("Condition doesn't exist.");
			return;
		}
		
		cond.put(condition, status);
	}
	
	public void applyCondition(Enemy enemy, String condition) {
		
		switch (condition) {
	    case "Blinded":
	        // Do something
	        break;
	    case "Clumsy":
	        // Do something
	        break;
	    case "Concealed":
	        // Do something
	        break;
	    case "Confused":
	        // Do something
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Controlled":
	        // Do something
	        break;
	    case "Dazzled":
	        // Do something
	        break;
	    case "Deafened":
	        // Do something
	        break;
	    case "Doomed":
	        // Do something
	        break;
	    case "Drained":
	        // Do something
	        break;
	    case "Dying":
	        // Do something
	        break;
	    case "Encumbered":
	        // Do something
	        break;
	    case "Enfeebled":
	        // Do something
	        break;
	    case "Fascinated":
	        // Do something
	        break;
	    case "Fatigued":
	        // Do something
	        break;
	    case "Fleeing":
	        // Do something
	        break;
	    case "Frightened":
	        // Do something
	        break;
	    case "Grabbed":
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Hidden":
	        // Do something
	        break;
	    case "Immobilized":
	        // Do something
	        break;
	    case "Invisible":
	        // Do something
	        break;
	    case "Off Guard":
	        enemy.setAC(enemy.getAC() - 2);
	        break;
	    case "Paralyzed":
	        // Do something
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Persistent Damage":
	        // Do something
	        break;
	    case "Petrified":
	        // Do something
	        break;
	    case "Prone":
	        // Do something
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Quickened":
	        // Do something
	        break;
	    case "Restrained":
	        // Do something
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Sickened":
	        // Do something
	        break;
	    case "Slowed":
	        // Do something
	        break;
	    case "Stunned":
	        // Do something
	        break;
	    case "Stupefied":
	        // Do something
	        break;
	    case "Unconscious":
	        // Do something
	        setCondition("Off Guard", 1);
	        applyCondition(enemy, "Off Guard");
	        break;
	    case "Undetected":
	        // Do something
	        break;
	    case "Wounded":
	        // Do something
	        break;
	    default:
	        // Default case
	        break;
	}
	}

	public void reduceConditions(Enemy enemy) {
		
	}
}
