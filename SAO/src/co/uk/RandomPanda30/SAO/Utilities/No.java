package co.uk.RandomPanda30.SAO.Utilities;

import java.util.ArrayList;
import java.util.Collections;

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

    public int getMax(ArrayList<String> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (String s : list) {
            newList.add(Integer.parseInt(s));
        }
        return Collections.max(newList);
    }
}