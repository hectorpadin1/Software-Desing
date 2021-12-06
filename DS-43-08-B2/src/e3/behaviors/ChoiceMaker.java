package e3.behaviors;

import e3.Behavior;
import e3.Gunslinger;
import e3.GunslingerAction;

public class ChoiceMaker implements Behavior {
    @Override
    public GunslingerAction action(Gunslinger g) {
        int me = g.getLoads(), rival = g.getRivalLoads();
        if (me == 0 && rival == 0) return GunslingerAction.RELOAD;
        if (me > 0) return GunslingerAction.SHOOT;
        if (g.getRivalActions().get(g.getRivalActions().size() - 1) == GunslingerAction.SHOOT) return GunslingerAction.RELOAD;
        return GunslingerAction.PROTECT;
    }
}