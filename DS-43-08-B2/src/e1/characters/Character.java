package e1.characters;

import e1.Dice;

public abstract class Character {
    private final String name;
    private int life;
    private final int armour;
    protected final Dice dice;

    public Character(String name, int life, int armour, Dice dice) {
        this.name = name;
        this.life = life;
        this.armour = armour;
        this.dice = dice;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public int getArmour() {
        return armour;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public abstract int getAttackLevel(Character rival);

    public void attack(Character rival) {
        int attLevel = getAttackLevel(rival);
        if (attLevel > rival.getArmour()) {
            rival.setLife(rival.getLife() - (attLevel - rival.getArmour()));
        }
    }
}
