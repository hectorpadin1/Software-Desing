package e1.characters.beasts;

import e1.Dice;
import e1.characters.Character;

public abstract class Beast extends Character {
    private static int BEAST_MAX = 90;

    public Beast(String name, int life, int armour) {
        super(name, life, armour, new Dice().setMax(BEAST_MAX));
    }

    public Beast(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice.setMax(BEAST_MAX));
    }

    public static void changeMax(int max) {
        BEAST_MAX = max;
    }

    @Override
    public int getAttackLevel(Character rival) {
        return dice.getValue();
    }
}
