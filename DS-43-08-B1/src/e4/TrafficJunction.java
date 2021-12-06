package e4;

public class TrafficJunction {

    private final TrafficLight[] lights;

    /**
     * Creates a traffic  junction  with  four  traffic  lights  named  north , south ,
     * east  and  west. The  north  traffic  light  has  just  started  its  green  cycle.
     */
    public TrafficJunction() {
        lights = new TrafficLight[CardinalDirection.values().length];
        int i = 0;
        for (CardinalDirection cd : CardinalDirection.values()) {
            lights[i++] = new TrafficLight(cd, true, cd == CardinalDirection.NORTH);
        }
    }

    /**
     * Indicates  that a second  of time  has  passed, so the  traffic  light  with
     * the  green  or  amber  light  should  add 1 to its  counter. If the  counter
     * passes  its  maximum  value  the  color  of the  traffic  light  must  change.
     * If it  changes  to red  then  the  following  traffic  light  changes  to  green.
     * The  order  is: north, south, east, west  and  then  again  north.
     */
    public void timesGoesBy() {
        TrafficLight tf;
        boolean lastRed;
        for (int i = 0; i < lights.length; i++) {
            tf = lights[i];
            lastRed = tf.timeGoesBy();
            if (lastRed) lights[(i + 1) % lights.length].goGreen();
        }
    }

    /**
     * If  active  is true  all the  traffic  lights  of the  junction  must  change  to
     * blinking  amber (meaning a non -controlled  junction ).
     * If  active  is  false  it  resets  the  traffic  lights  cycle  and  started  again
     * with  north at  green  and  the  rest at red.
     *
     * @param active true or  false
     */
    public void amberJunction(boolean active) {
        for (TrafficLight light : lights) {
            if (light.isActive() != active) light.switchActive();
        }
        if (!active) lights[0].goGreen();
        timesGoesBy();
    }

    /**
     * Returns a String  with  the  state  of the  traffic  lights.
     * The  format  for  each  traffic  light  is the  following:
     * - For  red  colors: "[name: RED]"
     * - For  green  colors: "[name: GREEN  counter ]"
     * - For  yellow  colors  with  blink  at OFF: "[name: YELLOW  OFF  counter]"
     * - For  yellow  colors  with  blink  at ON: "[name: YELLOW  ON]"
     * Examples:
     * [NORTH: GREEN  2][ SOUTH: RED][EAST: RED][WEST: RED]
     * [NORTH: AMBER  OFF  5][ SOUTH: RED][EAST: RED][WEST: RED]
     * [NORTH: AMBER  ON][ SOUTH: AMBER  ON][EAST: AMBER  ON][WEST: AMBER  ON]
     *
     * @return String  that  represents  the  state  of the  traffic  lights
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TrafficLight light : lights) {
            sb.append(light);
        }
        return sb.toString();
    }

}
