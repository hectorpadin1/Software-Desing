package e1.characters.heroes;

import e1.characters.Character;
import e1.Dice;
import e1.characters.beasts.Goblin;

public class Hobbit extends Hero {
    public Hobbit(String name, int life, int armour) {
        super(name, life, armour);
    }

    public Hobbit(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice);
    }

    @Override
    public int getAttackLevel(Character rival) {
        int attack = super.getAttackLevel(rival);
        if (rival instanceof Goblin) return attack - 5;
        return attack;
    }
}
