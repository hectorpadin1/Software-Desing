package e4;

public class TrafficLight {
    private final static int MAX_GREEN = 15;
    private final static int MAX_AMBER = 5;
    private final CardinalDirection name;
    private Color color;
    private Blink blink;
    private int counter;

    public TrafficLight(CardinalDirection name, boolean active, boolean green) {
        this.color = active ? (green ? Color.GREEN : Color.RED) : Color.AMBER;
        this.name = name;
        this.blink = active ? Blink.OFF : Blink.ON;
        this.counter = -1;
        timeGoesBy();
    }

    /**
     * Increments the counter and changes state if necessary.
     *
     * @return true if switches to red
     */
    public boolean timeGoesBy() {
        counter++;
        switch (color) {
            case GREEN:
                if (counter > MAX_GREEN) {
                    color = color.next();
                    counter = 0;
                }
                break;
            case AMBER:
                if (!blink.isActive() && counter > MAX_AMBER) {
                    color = color.next();
                    return true;
                }
        }
        return false;
    }

    /**
     * Turns the light GREEN and the counter to 0.
     * USE CAREFULLY - Does not check anything.
     */
    public void goGreen() {
        color = color.next();
        counter = -1;
    }

    /**
     * If active, turns to blinking amber mode.
     * If inactive, turns the light RED.
     */
    public void switchActive() {
        if (blink.isActive()) {
            blink = blink.change();
            color = Color.RED;
        } else {
            color = Color.AMBER;
            blink = blink.change();
        }
    }

    /**
     * Checks if the traffic light is active.
     *
     * @return the blinking status of the light
     */
    public boolean isActive() {
        return blink.isActive();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[" + name + ": ");
        sb.append(color);
        switch (color) {
            case RED:
                return sb.append("]").toString();
            case GREEN:
                return sb.append(" ")
                        .append(counter)
                        .append("]")
                        .toString();
            default:
                if (blink.isActive())
                    return sb.append(" ")
                            .append(blink)
                            .append("]")
                            .toString();
                else return sb.append(" ")
                        .append(blink)
                        .append(" ")
                        .append(counter)
                        .append("]")
                        .toString();
        }
    }

}
