package e3;

import e3.behaviors.ChoiceMaker;

import java.util.ArrayList;
import java.util.List;

public class Gunslinger {
    private final List<GunslingerAction> rivalActions;
    private int loads;
    private int rivalLoads;
    private Behavior behavior;

    public Gunslinger() {
        loads = 0;
        rivalActions = new ArrayList<>();
        behavior = new ChoiceMaker();
    }

    public GunslingerAction action() {
        GunslingerAction nextAction = behavior.action(this);
        if (nextAction == GunslingerAction.SHOOT) loads--;
        if (nextAction == GunslingerAction.RELOAD) loads++;
        return nextAction;
    }

    public int getLoads() {
        return loads;
    }

    public void rivalAction(GunslingerAction action) {
        if (action == GunslingerAction.RELOAD) rivalLoads++;
        if (action == GunslingerAction.SHOOT) rivalLoads--;
        rivalActions.add(action);
    }

    public List<GunslingerAction> getRivalActions() {
        return rivalActions;
    }

    public int getRivalLoads() {
        return rivalLoads;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}
