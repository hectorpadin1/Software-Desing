package e1.characters.heroes;

import e1.characters.Character;
import e1.Dice;
import e1.characters.beasts.Orc;

public class Elf extends Hero {
    public Elf(String name, int life, int armour) {
        super(name, life, armour);
    }

    public Elf(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice);
    }

    @Override
    public int getAttackLevel(Character rival) {
        int attack = super.getAttackLevel(rival);
        if (rival instanceof Orc) return attack + 10;
        return attack;
    }
}
