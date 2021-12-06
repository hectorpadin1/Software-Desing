package e1;

import java.util.Random;

public class StringUtilities {
    /**
     * Checks  if a given  string  is a valid c of two  others. That is, it  contains
     * all  characters  of the  other  two  and  order  of all  characters  in the  individual
     * strings  is  preserved.
     *
     * @param a First  String  to be  mixed
     * @param b Second  String  to be  mixed
     * @param c Mix of the  two  other  Strings
     * @return true if the c is valid , false  otherwise
     */
    public static boolean isValidMix(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) return false;
        int ia = 0, ib = 0;
        for (int i = 0; i < c.length(); i++) {
            if (ia < a.length() && a.charAt(ia) == c.charAt(i)) ia++;
            else if (ib < b.length() && b.charAt(ib) == c.charAt(i)) ib++;
            else return false;
        }
        return true;
    }

    /**
     * Generates a random  valid  mix  for  two  Strings
     *
     * @param a First  String  to be  mixed
     * @param b Second  String  to be  mixed
     * @return A String  that is a random  valid  mix of the  other  two.
     */
    public static String generateRandomValidMix(String a, String b) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int alen = a.length(), blen = b.length();
        int ablen = alen + blen;
        int ai = 0, bi = 0;
        for (int i = 0; i < ablen; i++) {
            if (random.nextBoolean()) {
                if (ai < alen) sb.append(a.charAt(ai++));
                else sb.append(b.charAt(bi++));
            } else {
                if (bi < blen) sb.append(b.charAt(bi++));
                else sb.append(a.charAt(ai++));
            }
        }
        return sb.toString();
    }
}