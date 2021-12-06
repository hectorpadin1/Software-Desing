package e1.characters.beasts;

import e1.characters.Character;
import e1.Dice;

public class Orc extends Beast {
    public Orc(String name, int life, int armour) {
        super(name, life, armour);
    }

    public Orc(String name, int life, int armour, Dice dice) {
        super(name, life, armour, dice);
    }

    @Override
    public void attack(Character rival) {
        int attLevel = getAttackLevel(rival);
        if (attLevel > rival.getArmour() * 0.9f) {
            rival.setLife(rival.getLife() - (attLevel - (int) (rival.getArmour() * 0.9f)));
        }
    }
}
