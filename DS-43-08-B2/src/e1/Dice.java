package e1;

import java.util.Random;

public class Dice {
    private int max;
    private final Random random;

    public Dice() {
        max = 100;
        random = new Random();
    }

    public Dice(int max) {
        this.max = max;
        random = new Random();
    }

    public Dice(int max, int seed) {
        this.max = max;
        random = new Random(seed);
    }

    public Dice setMax(int max) {
        this.max = max;
        return this;
    }

    public int getValue() {
        return random.nextInt(max);
    }

}
