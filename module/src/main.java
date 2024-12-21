import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Classes.Rogue;
import Crossbow.*;
import Enemies.Enemy;

public class main {

    public static void main(String[] args) {

//		String charClass = null, weapon = null;
//		int level = 0;
//		int[] statArray = new int[6];
//		Scanner input = new Scanner(System.in);
//		List<String> classes = getClasses("Classes"), weapons = new ArrayList<>();
//		weapons.add("Bow.Bow");
//		weapons.add("Crossbow.Crossbow");
//		weapons.add("Sword.Sword");
//		System.out.println("Select a class.");
//
//		for(int x = 0; x < classes.size(); x++) {
//			System.out.println(x + 1 + ". " + classes.get(x));
//		}
//
//		while(!classes.contains(charClass)) {
//			charClass = input.nextLine();
//		}
//
//		System.out.println("Input your level.");
//
//		while(level < 1 || level > 20) {
//			level = input.nextInt();
//		}
//
//		System.out.println("Input your Stat Array.\nEx.\n"
//				+ "Str Dex Con Int Wis Cha\n"
//				+ " 0   4   4   0   4   4");
//
//		for(int x = 0; x < statArray.length; x++) {
//			statArray[x] = input.nextInt();
//		}
//
//		System.out.println("Select a weapon group.");
//
//		for(int x = 0; x < weapons.size(); x++) {
//			System.out.println(x + 1 + ". " + weapons.get(x));
//		}
//
//		while(!weapons.contains(weapon)) {
//			weapon = input.nextLine();
//		}
//
//		weapons = getClasses(weapon);
//
//		System.out.println("Select a weapon.");
//
//		for(int x = 0; x < weapons.size(); x++) {
//			System.out.println(x + 1 + ". " + weapons.get(x));
//		}
//
//		weapon = null;
//
//		while(!weapons.contains(weapon)) {
//			weapon = input.nextLine();
//		}
//
//		System.out.println("Class:" + charClass +
//						   "Level:" + level +
//						   "Stats:" + statArray.toString() +
//						   "Weapons.Weapon:" + weapon);
//		input.close();

        int[] bow = new int[] {1, 5, 4, 0, 4, 0};
        int[] xbow = new int[] {0, 5, 4, 4, 0, 4};

        DuelingPistol dp = new DuelingPistol();
        Revolver r = new Revolver();
        Jezail j = new Jezail();

        //public Classes.Rogue(String subclass, Weapons.Weapon weapon, String mainStat, int level, int[] statArray)
        //public Classes.Ranger(String subclass, Weapons.Weapon weapon, String mainStat, int level, int[] statArray) {
        Rogue sorad = new Rogue("Scoundrel", dp, "Dex", 2, xbow);
        Rogue elaric = new Rogue("Scoundrel", r, "Dex", 2, xbow);

        Enemy mook = new Enemy(elaric.getLevel());
        mook.setCondition("Off Guard", 1);

        System.out.println("Level: " + elaric.getLevel() + "\nWeapons: " + elaric.getWeapons().toString() + "\nTo Hit: " + elaric.getToHit(r) +
                "\nNumber of Dice: " + elaric.getNumDice() +
                "\nSpecialization: " + elaric.getSpecialization() + "\n" + elaric.getWeapons().get(0) + " Crit: " +
                elaric.getWeapons().get(0).findDamage("crit", elaric.getNumDice(), elaric.getSpecialization(), elaric.getStr()) +
                "\n" + elaric.getWeapons().get(0) + " Hit: " +
                elaric.getWeapons().get(0).findDamage("hit", elaric.getNumDice(), elaric.getSpecialization(), elaric.getStr())
        );

        double revolver = 0, pistol = 0;
        int shotsFired = 0, reloadsLeft = 3, x = 0;

        for(x = 0; x < 10000000; x++) {
            if(x % 2 == 0) {
                pistol += sorad.strike(mook);

            } else {
                pistol += sorad.strike(mook);
                pistol += sorad.strike(mook);
            }


            switch(shotsFired) {
                case 3:
                    revolver += elaric.strike(mook);
                    revolver += elaric.strike(mook);
                    shotsFired += 2;
                    reloadsLeft -= 1;
                    break;
                case 4:
                    revolver += elaric.strike(mook);
                    shotsFired += 1;
                    reloadsLeft -= 2;
                    break;
                case 5:
                    int leftoverActions = reloadsLeft - 3;
                    reloadsLeft = 3;
                    shotsFired = 0;

                    while(leftoverActions != 0) {
                        elaric.strike(mook);
                        shotsFired++;
                        leftoverActions++;
                    }

                    break;
                default:
                    revolver += elaric.strike(mook);
                    revolver += elaric.strike(mook);
                    shotsFired += 2;
                    break;
            }

            sorad.endTurn();
            elaric.endTurn();
        }

        revolver /= x;
        pistol /= x;

        System.out.println("Crossbow.Revolver: " + revolver + "\nPistol: " + pistol);
    }

    public static List<String> getClasses(String packageName) {
        List<String> classNames = new ArrayList<>();

        // Convert package name to directory path
        String packagePath = packageName.replace('.', '/');

        // Get the directory of the package
        File packageDirectory = new File("./src/" + packagePath); // Modify this path according to your project structure

        // List all files in the package directory
        File[] files = packageDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();

                // Check if the file is a Java class
                if (fileName.endsWith(".java")) {
                    // Extract class name from file name
                    String className = fileName.substring(0, fileName.lastIndexOf('.'));
                    if (isSubclass(file)) {
                        classNames.add(className);
                    }
                }
            }
        }

        return classNames;
    }

    public static boolean isSubclass(File file) {
        boolean isSubclass = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the class definition and doesn't contain "extends"
                if (line.contains("class") && line.contains("extends")) {
                    isSubclass = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSubclass;
    }
}