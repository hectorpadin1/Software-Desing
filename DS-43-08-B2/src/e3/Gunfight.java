package e3;

import static e3.GunslingerAction.*;

public class Gunfight {
    public final static int TIE = -1, CONTINUE = 0, G1WIN = 1, G2WIN = 2;

    public static int duel(Gunslinger g1, Gunslinger g2) {
        int round = 0;
        do {
            round++;
            System.out.printf("Round %d-------------------------%n", round);
            GunslingerAction a1 = g1.action(), a2 = g2.action();
            g1.rivalAction(a2);
            g2.rivalAction(a1);
            System.out.printf("Gunslinger 1: %s%n", a1);
            System.out.printf("Gunslinger 2: %s%n", a2);
            switch (gameLogic(a1, a2)) {
                case TIE -> {
                    System.out.println("\nThe duel has ended");
                    System.out.println("\nResult ended in TIE");
                    return TIE;
                }
                case CONTINUE -> System.out.println("The duel continues...");
                case G1WIN -> {
                    System.out.println("\nThe duel has ended");
                    System.out.println("\nWinner: GUNSLINGER1");
                    return G1WIN;
                }
                case G2WIN -> {
                    System.out.println("\nThe duel has ended");
                    System.out.println("\nWinner: GUNSLINGER2");
                    return G2WIN;
                }
            }
        } while (true);
    }

    private static int gameLogic(GunslingerAction a1, GunslingerAction a2) {
        if (a1 == SHOOT && a2 == RELOAD) return G1WIN;
        if (a1 == RELOAD && a2 == SHOOT) return G2WIN;
        if (a1 == SHOOT && a2 == SHOOT) return TIE;
        if (a1 == MACHINE_GUN && a2 == MACHINE_GUN) return TIE;
        if (a1 == MACHINE_GUN) return G1WIN;
        if (a2 == MACHINE_GUN) return G2WIN;
        return CONTINUE;
    }
}
