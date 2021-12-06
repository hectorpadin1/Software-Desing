package e1.characters.heroes;

import e1.Dice;

public class Human extends Hero {
    public Human(String name, int life, int armour) {
        super(name, life, armour);
    }

    public Human(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice);
    }
}
