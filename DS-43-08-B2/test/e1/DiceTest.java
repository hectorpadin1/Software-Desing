package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest {

    @Test
    public void maxTest() {
        assertTrue(new Dice().getValue() <= 100);
        assertTrue(new Dice(2).getValue() <= 2);
    }

    @Test
    public void seedTest() {
        assertEquals(60,new Dice(100, 0).getValue());
        assertEquals(85,new Dice(100, 1).getValue());
        assertEquals(8,new Dice(100, 2).getValue());
        assertEquals(34,new Dice(100, 3).getValue());
        assertEquals(62,new Dice(100, 4).getValue());
    }
}
