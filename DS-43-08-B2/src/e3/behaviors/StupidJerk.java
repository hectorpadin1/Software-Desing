package e3.behaviors;

import e3.Behavior;
import e3.Gunslinger;
import e3.GunslingerAction;

import java.util.Random;

public class StupidJerk implements Behavior {
    private final Random random;

    public StupidJerk() {
        random = new Random();
    }

    public StupidJerk(int seed) {
        random = new Random(seed);
    }

    @Override
    public GunslingerAction action(Gunslinger g) {
        if (g.getLoads() == 0) {
            return random.nextBoolean() ? GunslingerAction.PROTECT : GunslingerAction.RELOAD;
        }
        if (g.getLoads() == 5) return GunslingerAction.MACHINE_GUN;
        return switch (random.nextInt() % 3) {
            case 0 -> GunslingerAction.PROTECT;
            case 1 -> GunslingerAction.RELOAD;
            default -> GunslingerAction.SHOOT;
        };
    }
}
