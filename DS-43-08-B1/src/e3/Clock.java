package e3;

public class Clock {
    private int seconds;

    public enum Period {PM, AM}

    /**
     * Creates a Clock  instance  parsing a String.
     *
     * @param s The  string  representing  the  hour in 24h format  (17:25:15)  or in
     *          12h format  (05:25:15  PM).
     * @throws IllegalArgumentException if the  string  is not a valid  hour.
     */
    public Clock(String s) throws IllegalArgumentException {
        int h;
        int m;
        int sec;
        try {
            h = Integer.parseInt(s.substring(0, 2));
            m = Integer.parseInt(s.substring(3, 5));
            sec = Integer.parseInt(s.substring(6, 8));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        try {
            switch (s.substring(9, 11)) {
                case "AM" -> this.seconds = convert2Sec(h, m, sec, Period.AM);
                case "PM" -> this.seconds = convert2Sec(h, m, sec, Period.PM);
                default -> throw new IllegalArgumentException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            this.seconds = convert2Sec(h, m, sec, null);
        }
    }

    /**
     * Creates a clock  given  the  values  in 24h format.
     *
     * @param hours   Hours in 24h format.
     * @param minutes Minutes.
     * @param seconds Seconds.
     * @throws IllegalArgumentException if the  values  do not  represent a valid
     *                                  hour.
     */
    public Clock(int hours, int minutes, int seconds) throws IllegalArgumentException {
        this.seconds = convert2Sec(hours,minutes,seconds,null);
    }

    /**
     * Creates a clock  given  the  values  in 12h format. Period  is a enumeration
     * located  inside  the  Clock  class  with  two  values: AM and PM.
     *
     * @param hours   Hours in 12h format.
     * @param minutes Minutes.
     * @param seconds Seconds.
     * @param period  Period  used by the  Clock (represented  by an enum).
     * @throws IllegalArgumentException if the  values  do not  represent a valid hour.
     */
    public Clock(int hours, int minutes, int seconds, Period period) throws IllegalArgumentException {
        try {
            this.seconds = convert2Sec(hours,minutes,seconds,period);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns  the  hours  of the  clock  in 24h format
     *
     * @return the  hours  in 24h format
     */
    public int getHours24() {
        return seconds/(60*60);
    }

    /***
     * Returns  the  hours  of the  clock  in 12h format
     * @return the  hours  in 12h format
     **/
    public int getHours12() {
        int hours = (seconds/(60*60))%12;
        if (hours==0)
            hours=12;
        return hours;
    }

    /**
     * Returns  the  minutes  of the  clock
     *
     * @return the  minutes
     */
    public int getMinutes() {
        int hours = getHours24();
        return seconds/60 - hours*60;
    }

    /**
     * Returns  the  seconds  of the  clock.
     *
     * @return the  seconds.
     */
    public int getSeconds() {
        int hours = getHours24();
        int minutes = getMinutes();
        return seconds - minutes*60 - hours*60*60;
    }

    /**
     * Returns  the  period  of the  day (AM or PM) that  the  clock  is  representing
     *
     * @return An  instance  of the  Clock.Period  enum  depending  if the  time is
     * before  noon (AM) or  after  noon (PM).
     */
    public Period getPeriod() {
        return getHours24()<12 ? Period.AM : Period.PM;
    }

    /**
     * Prints a String  representation  of the  clock  in 24h format.
     *
     * @return An  string  in 24h format
     * @see String.format  function  to  format  integers  with  leading  zeroes
     */
    public String printHour24() {
        String hours = getHours24()==0 ? "00" : String.valueOf(getHours24());
        String minutes = getMinutes()==0 ? "00" : String.valueOf(getMinutes());
        String seconds = getSeconds()==0 ? "00" : String.valueOf(getSeconds());
        return hours + ":" + minutes + ":" + seconds;
    }

    /**
     * Prints a String  representation  of the  clock  in 12h format.
     *
     * @return An  string  in 12h format
     * @see String.format  function  to  format  integers  with  leading  zeroes
     */
    public String printHour12() {
        String period = getHours24()>12 ? "PM" : "AM";
        String hours = getHours12()==0 ? "00" : ((getHours12()>9 ? "" : "0") + getHours12());
        String minutes = getMinutes()==0 ? "00" : ((getMinutes()>9 ? "" : "0") + getMinutes());
        String seconds = getSeconds()==0 ? "00" : ((getSeconds()>9 ? "" : "0") + getSeconds());
        return hours + ":" + minutes + ":" + seconds + " " + period;
    }

    /**
     * Returns the numbers of seconds in the given hour in the format
     * HH:MM:SS AM/PM(period is optional) since 00:00:00
     *
     * @param hours   Hours in 12h format or 24h format.
     * @param minutes Minutes.
     * @param seconds Seconds.
     * @param period  Period  used by the  Clock (represented  by an enum).
     * @throws IllegalArgumentException if the  values  do not  represent a valid hour.
     */
    private int convert2Sec(int hours, int minutes, int seconds, Period period) throws IllegalArgumentException {
        int x;
        if (period==null) {
            if (hours > 24 || hours < 0 || minutes > 60 || minutes < 0 || seconds > 60 || seconds < 0) {
                throw new IllegalArgumentException();
            } else {
                x = hours * 60 * 60 + minutes * 60 + seconds;
            }
        } else if (hours > 12 || hours < 0 || minutes > 60 || minutes < 0 || seconds > 60 || seconds < 0) {
            throw new IllegalArgumentException();
        } else {
            int sum = period!=Period.PM ? 0 : (12*60*60);
            hours = (period==Period.AM && hours==12) ? 0 : hours;
            x = sum + hours * 60 * 60 + minutes * 60 + seconds;
        }
        return x;
    }

    /**
     * Performs  the  equality  tests  of the  current  clock  with  another  clock
     * passed  as a parameter. Two  clock  are  equal  if they  represent  the  same
     * instant  regardless  of  being  in 12h or 24h format.
     *
     * @param o The  clock  to be  compared  with  the  current  clock.
     * @return true if the  clocks  are  equals , false  otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        return this.seconds == ((Clock) o).seconds;
    }

    /**
     * Returns a integer  that is a hash  code  representation  of the  clock  using
     * the "hash  31"  algorithm.
     * Clocks  that  are  equals  must  have  the  same  hash  code.
     *
     * @return the  hash  code
     */
    @Override
    public int hashCode() {
        return this.seconds;
    }
}
