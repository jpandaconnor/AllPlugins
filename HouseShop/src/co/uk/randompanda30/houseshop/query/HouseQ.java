package co.uk.randompanda30.houseshop.query;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.object.ForSaleTimeout;

import java.util.UUID;

/**
 * Created by panda on 02/05/16.
 */
public class HouseQ {

    public static String getRentTimeRemaining(String house) {
        return (String) TEMP.datac.get(house + ".rent");
    }

    public static int getHousePrice(String name) {
        return (Integer) TEMP.datac.get(name + ".price");
    }

    public static void setHousePrice(String name, int amount) {
        TEMP.datac.set(name + ".price", amount);
        Data.save();
    }

    public static boolean hasBeenShowHouse(String house, UUID uuid) {
        for (ForSaleTimeout ts : TEMP.forsalesTimeouts) {
            if (ts.getUuid().equals(uuid)) {
                if (ts.getHouse().equals(house)) {
                    return true;
                }
            }
        }
        return false;
    }
}