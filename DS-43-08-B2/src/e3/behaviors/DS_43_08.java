package e3.behaviors;

import e3.Behavior;
import e3.Gunslinger;
import e3.GunslingerAction;

import java.util.Random;

public class DS_43_08 implements Behavior {
    private final Random random;

    public DS_43_08() {
        random = new Random();
    }

    public DS_43_08(int seed) {
        random = new Random(seed);
    }

    @Override
    public GunslingerAction action(Gunslinger g) {
        if (g.getLoads() == 5)
            return GunslingerAction.MACHINE_GUN;
        if (g.getLoads() == 4 && g.getRivalLoads() == 0)
            return GunslingerAction.RELOAD;
        if (g.getLoads() == 0) {
            if (g.getRivalLoads() == 0)
                return GunslingerAction.RELOAD;
            return random.nextBoolean() ? GunslingerAction.RELOAD : GunslingerAction.PROTECT;
        } else {
            if (g.getRivalLoads() == 0)
                return random.nextBoolean() ? GunslingerAction.SHOOT : GunslingerAction.RELOAD;
            else
                return random.nextBoolean() ? GunslingerAction.SHOOT : GunslingerAction.PROTECT;
        }
    }
}
