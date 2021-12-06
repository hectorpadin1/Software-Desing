package e3;

import e1.Game;
import e3.behaviors.ChoiceMaker;
import e3.behaviors.DS_43_08;
import e3.behaviors.StupidJerk;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GunfightTest {
    @Test
    public void gunslingerTest() {
        List<GunslingerAction> gas = new ArrayList<>();
        gas.add(GunslingerAction.RELOAD);
        gas.add(GunslingerAction.RELOAD);
        gas.add(GunslingerAction.PROTECT);
        gas.add(GunslingerAction.SHOOT);
        Gunslinger g = new Gunslinger();
        g.setBehavior(new StupidJerk(0));
        assertEquals(GunslingerAction.PROTECT, g.action());
        assertEquals(GunslingerAction.PROTECT, g.action());
        assertEquals(GunslingerAction.RELOAD, g.action());
        assertEquals(1, g.getLoads());
        assertEquals(GunslingerAction.PROTECT, g.action());
        assertEquals(GunslingerAction.PROTECT, g.action());
        assertEquals(GunslingerAction.RELOAD, g.action());
        assertEquals(2, g.getLoads());
        assertEquals(GunslingerAction.SHOOT, g.action());
        assertEquals(1, g.getLoads());
        assertEquals(0, g.getRivalLoads());
        g.rivalAction(gas.get(0));
        assertEquals(1, g.getRivalLoads());
        g.rivalAction(gas.get(1));
        assertEquals(2, g.getRivalLoads());
        g.rivalAction(gas.get(2));
        g.rivalAction(gas.get(3));
        assertEquals(1, g.getRivalLoads());
        for (int i = 0; i < gas.size(); i++) {
            assertEquals(g.getRivalActions().get(i), gas.get(i));
        }
    }

    @Test
    public void gunfightTest() {
        // WINS G2
        Gunslinger g1 = new Gunslinger();
        g1.setBehavior(new StupidJerk(0));
        Gunslinger g2 = new Gunslinger();
        g2.setBehavior(new StupidJerk(1));
        Gunfight.duel(g1, g2);

        // WINS G1
        g1 = new Gunslinger();
        g1.setBehavior(new StupidJerk(1));
        g2 = new Gunslinger();
        g2.setBehavior(new StupidJerk(0));
        Gunfight.duel(g1, g2);

        // TIE
        g1 = new Gunslinger();
        g1.setBehavior(new StupidJerk(0));
        g2 = new Gunslinger();
        g2.setBehavior(new StupidJerk(0));
        Gunfight.duel(g1, g2);
    }

    @Test
    public void behaviorsTest() {
        Gunslinger g = new Gunslinger();
        Behavior b = new StupidJerk();
        assertNotEquals(GunslingerAction.SHOOT, b.action(g));
        b = new StupidJerk(0);
        assertNotEquals(GunslingerAction.SHOOT, b.action(g));
        b = new ChoiceMaker();
        assertNotEquals(GunslingerAction.SHOOT, b.action(g));
        g.rivalAction(GunslingerAction.SHOOT);
        b.action(g);
        g.rivalAction(GunslingerAction.PROTECT);
        b.action(g);
        b = new DS_43_08();
        b = new DS_43_08(0);
        assertNotEquals(GunslingerAction.SHOOT, b.action(g));
    }

    @Test
    public void championTest() {
        int wins = 0, ties = 0, losses = 0, total = 10000;
        for (int i = 0; i < total; i++) {
            Gunslinger g1 = new Gunslinger();
            g1.setBehavior(new DS_43_08());
            Gunslinger g2 = new Gunslinger();
            g2.setBehavior(i < total/2 ? new ChoiceMaker() : new StupidJerk());
            switch (Gunfight.duel(g1, g2)) {
                case Gunfight.TIE -> ties++;
                case Gunfight.G1WIN -> wins++;
                case Gunfight.G2WIN -> losses++;
            }
        }
        System.out.printf("WINS %d    TIES %d    LOSSES %d", wins, ties, losses);
    }
}
