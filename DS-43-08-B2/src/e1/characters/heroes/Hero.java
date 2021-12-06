package e1.characters.heroes;

import e1.Dice;
import e1.characters.Character;

public abstract class Hero extends Character {
    private static int HERO_MAX = 100;

    public Hero(String name, int life, int armour) {
        super(name, life, armour, new Dice().setMax(HERO_MAX));
    }

    public Hero(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice.setMax(HERO_MAX));
    }

    public static void changeMax(int max) {
        HERO_MAX = max;
    }

    @Override
    public int getAttackLevel(Character rival) {
        return Math.max(dice.getValue(), dice.getValue());
    }
}
