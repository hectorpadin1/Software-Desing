package e2;

public class Code {

    /**
     * Determines  whether a keypad  is  valid. To do this , it must be a rectangle  and
     * the  numbers  must  follow  the  alphanumeric  sequence  indicated. If the  array
     * (or any of its  subarrays) is null it will be  returned  false.
     *
     * @param keypad The  keypad  to be  analyzed.
     * @return true if it is valid , false  otherwise.
     */
    public static boolean isKeypadValid(char[][] keypad) {
        if (keypad==null) return false;
        boolean moves01 = false; // determines keypad direction
        if (keypad[0][0] == '1') {
            if (keypad[0].length == 1 && keypad.length == 1) return true;
            if (keypad[0].length > 1 && keypad[0][1] == '2') {
                moves01 = true;
            }
        } else return false;
        int last = 0;
        boolean stepped = false;
        for (int i = 0; i < (moves01 ? keypad.length : keypad[0].length); i++) {
            for (int j = 0; j < (moves01 ? keypad[0].length : keypad.length); j++) {
                char c = keypad[moves01 ? i : j][moves01 ? j : i];
                int charValue = Character.getNumericValue(c);
                if (Character.isLetter(c) && Character.isLowerCase(c)) return false;
                if (last == 9 && !stepped) {
                    last = -1;
                    stepped = true;
                }
                if (charValue != ++last) return false;
                if (last == 0 && stepped) last = 9;
            }
        }
        return last < 35;
    }

    /**
     * Checks  if an  array  of  movements  is valid  when  obtaining a key on a keypad.
     * An  array  of  movements  is  valid  if it is  formed  by  Strings  that  only  have  the
     * four  capital  letters U, D, L and R. If the  array  of  Strings  or any of the
     * Strings  is null it will be  returned  false.
     *
     * @param movements Array  of  Strings  representing  movements.
     * @return true if it is valid , false  otherwise.
     */
    public static boolean areMovementsValid(String[] movements) {
        //the array can not be null
        if (movements==null)
            return false;
        boolean b = true;
        //for-each loop to go through the array
        for (String s: movements) {
            //take the string and check if it's null or empty
            if (s==null || s.isEmpty()) {
                b = false;
                break;
            }
            //take the string and convert it into a char array
            char[] ch = s.toCharArray();
            for (char c : ch) {
                //get the numeric value of the char and compare it with the allowed letters
                int x = Character.getNumericValue(c);
                if (x != 30 && x != 13 && x != 21 && x != 27)
                    b = false;
                //the letter must be in capital letters
                else if (Character.isLowerCase(c))
                    b = false;
            }
        }
        return b;
    }

    /**
     * Given a keypad  and an  array  of movements, it  returns a String  with  the  code
     * resulting  from  applying  the  movements  on the  keypad.
     *
     * @param keypad    alphanumeric  keypad.
     * @param movements Array  with  the  different  movements  to be made  for  eachnumber  of the  key.
     * @return Resulting  code.
     */
    public static String obtainCode(char[][] keypad, String[] movements) {
        StringBuilder code = new StringBuilder();
        char[] ch;
        int i = 0;  //positions to move into keypad
        int j = 0;
        //loop to go through the movements
        for (String s : movements) {
            ch = s.toCharArray();
            //loop to go through each movement
            for (char c : ch) {
                try {
                    switch (c) {
                        case 'U' :  //go up
                            if (keypad[i-1][j]!='\u0000') //checks if the char is printable
                                i--;    //going up means decrease variable i in the matrix
                            break;
                        case 'D' :  //go down
                            if (keypad[i+1][j]!='\u0000')
                                i++;    //going down means increase variable i in the matrix
                            break;
                        case 'R' :  //right
                            if (keypad[i][j+1]!='\u0000')
                                j++;    //going right means increase variable j in the matrix
                            break;
                        case 'L' :  //left
                            if (keypad[i][j-1]!='\u0000')
                                j--;    //going left means decrease variable j in the matrix
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    //if the position at the keypad is not valid, will throw an exception, so we
                    //catch it and keep reading next instructions
                }
            }
            //add the final number for each group of movements to the string
            code.append(keypad[i][j]);
        }
        //returns the final code
        return code.toString();
    }
}