package co.uk.randompanda30.petstorage.utilities.math;

public class No {

    public static boolean isNumber(String number) {
        try {
            Integer.valueOf(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int getNumber(String number) {
        try {
            return Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}