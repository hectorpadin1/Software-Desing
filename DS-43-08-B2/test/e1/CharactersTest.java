package e1;

import e1.characters.Character;
import e1.characters.beasts.Beast;
import e1.characters.beasts.Goblin;
import e1.characters.beasts.Orc;
import e1.characters.heroes.Elf;
import e1.characters.heroes.Hero;
import e1.characters.heroes.Hobbit;
import e1.characters.heroes.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharactersTest {
    /*
    DICE: MAX = 100 & SEED = 0   ->    FIRST VALUES: 60 48 29 47
    DICE: MAX =  90 & SEED = 0   ->    FIRST VALUES: 60 88 49 47
    DICE: MAX =   5 & SEED = 0   ->    FIRST VALUES:  0  3  4  2
    */

    @Test
    public void characterClassTest() {
        Character c = new Elf("Legolas Greenleaf", 100, 10);
        assertEquals("Legolas Greenleaf", c.getName());
        assertEquals(100, c.getLife());
        assertEquals(10, c.getArmour());
    }

    @Test
    public void elfTest() {
        // ATTACK LEVELS
        Dice tricked = new Dice(-1, 0);
        Elf elf = new Elf("Legolas Greenleaf", 100, 1000, tricked);
        assertEquals(70, elf.getAttackLevel(new Orc("orc", 1, 1))); // MAX 60 o 48 => 60 + 10 -> 70
        assertEquals(47, elf.getAttackLevel(new Goblin("goblin", 1, 1))); // MAX 29 o 47 -> 47

        // RESET VALUES
        tricked = new Dice(-1, 0);
        elf = new Elf("Legolas Greenleaf", 100, 1000, tricked);

        // ATTACKING
        Orc rival1 = new Orc("orc", 100, 50);
        Goblin rival2 = new Goblin("goblin", 100, 30);
        elf.attack(rival1); // damage -> (attack) 70 - (armour) 50 = 20
        assertEquals(80, rival1.getLife());  // LIFE -> (life) 100 - (damage) 20 = 80
        elf.attack(rival2); // damage -> (attack) 47 - (armour) 30 = 17
        assertEquals(83, rival2.getLife());  // LIFE -> (life) 100 - (damage) 17 = 83
    }

    @Test
    public void hobbitTest() {
        // ATTACK LEVELS
        Dice tricked = new Dice(-1, 0);
        Hobbit hobbit = new Hobbit("Bilbo Baggins", 100, 1000, tricked);
        assertEquals(60, hobbit.getAttackLevel(new Orc("orc", 1, 1))); // MAX 60 o 48 -> 60
        assertEquals(42, hobbit.getAttackLevel(new Goblin("goblin", 1, 1))); // MAX 29 o 47 => 47 - 5 -> 42

        // RESET VALUES
        tricked = new Dice(-1, 0);
        hobbit = new Hobbit("Bilbo Baggins", 100, 1000, tricked);

        // ATTACKING
        Orc rival1 = new Orc("orc", 100, 50);
        Goblin rival2 = new Goblin("goblin", 100, 30);
        hobbit.attack(rival1); // damage -> (attack) 60 - (armour) 50 = 10
        assertEquals(90, rival1.getLife());  // LIFE -> (life) 100 - (damage) 10 = 90
        hobbit.attack(rival2); // damage -> (attack) 42 - (armour) 30 = 12
        assertEquals(88, rival2.getLife());  // LIFE -> (life) 100 - (damage) 12 = 88
    }

    @Test
    public void humanTest() {
        // ATTACK LEVELS
        Dice tricked = new Dice(-1, 0);
        Human human = new Human("Aragorn II Elessar", 100, 1000, tricked);
        assertEquals(60, human.getAttackLevel(new Orc("orc", 1, 1))); // MAX 60 o 48 -> 60
        assertEquals(47, human.getAttackLevel(new Goblin("goblin", 1, 1))); // MAX 29 o 47 -> 47

        // RESET VALUES
        tricked = new Dice(-1, 0);
        human = new Human("Aragorn II Elessar", 100, 1000, tricked);

        // ATTACKING
        Orc rival1 = new Orc("orc", 100, 50);
        Goblin rival2 = new Goblin("goblin", 100, 30);
        human.attack(rival1); // damage -> (attack) 60 - (armour) 50 = 10
        assertEquals(90, rival1.getLife());  // LIFE -> (life) 100 - (damage) 10 = 90
        human.attack(rival2); // damage -> (attack) 47 - (armour) 30 = 17
        assertEquals(83, rival2.getLife());  // LIFE -> (life) 100 - (damage) 17 = 83
    }

    @Test
    public void orcTest() {
        // ATTACK LEVELS
        Dice tricked = new Dice(-1, 0);
        Orc orc = new Orc("Azog", 100, 1000, tricked);
        assertEquals(60, orc.getAttackLevel(new Hobbit("hobbit", 1, 1))); // 60
        assertEquals(88, orc.getAttackLevel(new Human("human", 1, 1))); // 88

        // RESET VALUES
        tricked = new Dice(-1, 0);
        orc = new Orc("Azog", 100, 1000, tricked);

        // ATTACKING
        Hobbit rival1 = new Hobbit("hobbit", 100, 50);
        Human rival2 = new Human("human", 100, 30);
        orc.attack(rival1); // damage -> (attack) 60 - (armour 90% of 50) 45 = 15
        assertEquals(85, rival1.getLife());  // LIFE -> (life) 100 - (damage) 15 = 85
        orc.attack(rival2); // damage -> (attack) 88 - (armour 90% of 30) 27 = 61
        assertEquals(39, rival2.getLife());  // LIFE -> (life) 100 - (damage) 61 = 39
    }

    @Test
    public void goblinTest() {
        // ATTACK LEVELS
        Dice tricked = new Dice(-1, 0);
        Goblin goblin = new Goblin("Gollum", 100, 1000, tricked);
        assertEquals(60, goblin.getAttackLevel(new Hobbit("hobbit", 1, 1))); // 60
        assertEquals(88, goblin.getAttackLevel(new Human("human", 1, 1))); // 88

        // RESET VALUES
        tricked = new Dice(-1, 0);
        goblin = new Goblin("Gollum", 100, 1000, tricked);

        // ATTACKING
        Hobbit rival1 = new Hobbit("hobbit", 100, 50);
        Human rival2 = new Human("human", 100, 30);
        goblin.attack(rival1); // damage -> (attack) 60 - (armour) 50 = 10
        assertEquals(90, rival1.getLife());  // LIFE -> (life) 100 - (damage) 10 = 90
        goblin.attack(rival2); // damage -> (attack) 88 - (armour) 30 = 58
        assertEquals(42, rival2.getLife());  // LIFE -> (life) 100 - (damage) 58 = 42
    }

    @Test
    public void changeMaxTest() {
        Dice tricked = new Dice(-1, 0);
        Hero.changeMax(5);
        Elf c = new Elf("Legolas Greenleaf", 100, 10, tricked);
        assertEquals(3, c.getAttackLevel(null)); // MAX 0 o 3 => 3
        assertEquals(4, c.getAttackLevel(null)); // MAX 4 o 2 => 4

        tricked = new Dice(-1, 0);
        Beast.changeMax(5);
        Goblin g = new Goblin("Gollum", 100, 10, tricked);
        assertEquals(0, g.getAttackLevel(null)); // 0
        assertEquals(3, g.getAttackLevel(null)); // 3

        // BACK TO NORMAL
        Hero.changeMax(100);
        Beast.changeMax(90);
    }

}
