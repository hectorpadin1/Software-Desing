package e1;

import e1.characters.beasts.Beast;
import e1.characters.Character;
import e1.characters.heroes.Hero;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static List<String> battle(List<Beast> beasts, List<Hero> heroes) {
        List<String> result = new ArrayList<>();
        Character hero, beast;
        int turn = 1;
        do {
            result.add("Turn " + turn++ + ":");
            for (int i = 0; i < Math.min(beasts.size(), heroes.size()); i++) {
                beast = beasts.get(i);
                hero = heroes.get(i);
                result.add(writeFight(beast, hero));
                beast.attack(hero);
                if (hero.getLife() <= 0) {
                    result.add(writeDies(hero));
                    heroes.remove(hero);
                }
                hero.attack(beast);
                if (beast.getLife() <= 0) {
                    result.add(writeDies(beast));
                    beasts.remove(beast);
                }
            }
        } while (!beasts.isEmpty() && !heroes.isEmpty());
        if (beasts.isEmpty())
            result.add("HEROES WIN!!");
        else
            result.add("BEASTS WIN!!");
        return result;
    }

    private static String writeDies(Character c) {
        return '\t' + c.getClass().getSimpleName() + " " + c.getName() + " dies!";
    }

    private static String writeFight(Character c1, Character c2) {
        return "\tFight between " + c1.getName() +
                " (Energy=" + c1.getLife() + ") and " +
                c2.getName() + " (Energy=" + c2.getLife() + ")";
    }
}
