package e1.characters.beasts;

import e1.Dice;

public class Goblin extends Beast {
    public Goblin(String name, int life, int armour) {
        super(name, life, armour);
    }

    public Goblin(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice);
    }
}
