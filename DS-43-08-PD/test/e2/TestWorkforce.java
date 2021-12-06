package e2;

import e2.workforce.Team;
import e2.workforce.Worker;
import e2.workforce.WorkforceElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWorkforce {
    static Team t1, t2, t3;
    static Worker w1, w2, w3, w4, w5, w6, w7, w8;
    static Project p1, p2;

    @Test
    @BeforeAll
    public static void createSchema() {
        t1 = new Team("Fast");
        t2 = new Team("Slow");
        t3 = new Team("Turtle");
        w1 = new Worker("Jorge", 5f);
        w2 = new Worker("John", 10f);
        w3 = new Worker("Frank", 15f);
        w4 = new Worker("Jonny", 20f);
        w5 = new Worker("Michael", 25f);
        w6 = new Worker("Adele", 30f);
        w7 = new Worker("Ellen", 35f);
        w8 = new Worker("Jack", 40f);
        t1.addChildren(t2);
        t1.addChildren(w1);
        t1.addChildren(w2);
        t1.addChildren(w3);
        t2.addChildren(w4);
        t2.addChildren(w5);
        t2.addChildren(w6);
        t3.addChildren(w7);
        t3.addChildren(w8);
        p1 = new Project("Cool Yeah", t1);
        p2 = new Project("Boring Yet", t2);
        p1.addTeam(t3);
        p2.addTeam(t3);
        w1.addHours(p1, 4f);
        w1.addHours(p1, 3f);
        w2.addHours(p1, 4f);
        w2.addHours(p1, 1f);
        w3.addHours(p1, 3f);
        w3.addHours(p1, 7.5f);
        w4.addHours(p1, 7.5f);
        w4.addHours(p1, 5.5f);
        w5.addHours(p1, 3f);
        w5.addHours(p1, 4f);
        w6.addHours(p1, 17f);
        w4.addHours(p2, 1.5f);
        w4.addHours(p2, 2.5f);
        w5.addHours(p2, 1f);
        w5.addHours(p2, 3f);
        w6.addHours(p2, 6f);
        w7.addHours(p1, 1f);
        w8.addHours(p1, 1f);
        w7.addHours(p2, 2f);
        w8.addHours(p2, 2f);
    }

    @Test
    @AfterAll
    public static void removeWorker() {
        t1.removeChildren(w1);
        w1 = new Worker("Jorge", 0f);
    }

    @Test
    public void belongsTest() {
        assertTrue(p1.hasWorkforceElement(w4));
        assertTrue(p2.hasWorkforceElement(w4));
        assertTrue(p1.hasWorkforceElement(w1));
        assertFalse(p2.hasWorkforceElement(w1));
    }

    @Test
    public void costTest() {
        assertEquals(1187.5f, t1.getCost(p1));
        assertThrows(IllegalArgumentException.class, () -> t1.getCost(p2));
        assertEquals(945f, t2.getCost(p1));
        assertEquals(360f, t2.getCost(p2));
    }

    @Test
    public void iteratorTest() {
        int nodes = 0;
        System.out.println("Tree from " + t1.getName());
        for (WorkforceElement we : t1) {
            nodes++;
            System.out.println(we.getName());
        }
        assertEquals(8, nodes);
    }

    @Test
    public void uniqueNames() {
        assertThrows(IllegalArgumentException.class, () -> new Worker("Jorge", 0f));
        assertThrows(IllegalArgumentException.class, () -> new Team("Fast"));
        assertThrows(IllegalArgumentException.class, () -> new Project("Cool Yeah", null));
    }

    @Test
    public void notRelatedTeams() {
        assertThrows(IllegalArgumentException.class, () -> p2.addTeam(t1));
    }

    @Test
    public void reportsTest() {
        System.out.println("------------------------------------");
        p1.report();
        System.out.println("------------------------------------");
        p2.report();
        System.out.println("------------------------------------");
        t1.reportOn(p1);
    }
}
