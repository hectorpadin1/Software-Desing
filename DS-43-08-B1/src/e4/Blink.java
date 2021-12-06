package e4;

public enum Blink {
    ON(true) {
        @Override
        Blink change() {
            return OFF;
        }
    }, OFF(false) {
        @Override
        Blink change() {
            return ON;
        }
    };

    private final boolean blinking;

    Blink(boolean blinking) {
        this.blinking = blinking;
    }

    public boolean isActive() {
        return blinking;
    }

    abstract Blink change();
}
