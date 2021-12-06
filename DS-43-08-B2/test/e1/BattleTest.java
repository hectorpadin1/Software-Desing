package e1;

import e1.characters.beasts.Beast;
import e1.characters.beasts.Goblin;
import e1.characters.beasts.Orc;
import e1.characters.heroes.Elf;
import e1.characters.heroes.Hero;
import e1.characters.heroes.Hobbit;
import e1.characters.heroes.Human;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BattleTest {

    @Test
    public void heroesWinTest() {
        Dice tricked = new Dice(-1, 0);
        List<Beast> beasts = new ArrayList<>();
        beasts.add(new Orc("orc1", 100, 20, tricked));
        beasts.add(new Goblin("goblin1", 120, 20, tricked));

        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Elf("elf1", 120, 20, tricked));
        heroes.add(new Hobbit("hobbit1", 120, 20, tricked));
        heroes.add(new Human("human1", 110, 20, tricked));

        List<String> gameResult = Game.battle(beasts, heroes);
        assertEquals(gameResult.get(gameResult.size() - 1), "HEROES WIN!!");
    }

    @Test
    public void beastsWinTest() {
        Dice tricked = new Dice(-1, 0);
        List<Beast> beasts = new ArrayList<>();
        beasts.add(new Orc("orc1", 100, 20, tricked));
        beasts.add(new Goblin("goblin1", 120, 20, tricked));
        beasts.add(new Goblin("goblin2", 120, 20, tricked));
        beasts.add(new Goblin("goblin3", 120, 20, tricked));

        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Elf("elf1", 120, 20, tricked));
        heroes.add(new Hobbit("hobbit1", 120, 20, tricked));
        heroes.add(new Human("human1", 110, 20, tricked));

        List<String> gameResult = Game.battle(beasts, heroes);
        assertEquals(gameResult.get(gameResult.size() - 1), "BEASTS WIN!!");
    }
}
