package co.uk.randompanda30.houseshop.query;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.object.HouseEditor;
import org.bukkit.entity.Player;

/**
 * Created by panda on 02/05/16.
 */
public class PlayerQ {

    public static boolean hasHouse(Player player) {
        for (String s : TEMP.datac.getConfigurationSection("").getKeys(false)) {
            if (TEMP.datac.get(s + ".owner") != null) {
                if (TEMP.datac.get(s + ".owner").equals(player.getUniqueId().toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEditing(Player player) {
        for (HouseEditor e : TEMP.editing) {
            if (e.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static HouseEditor getHouseEditor(Player player) {
        for (HouseEditor e : TEMP.editing) {
            if (e.getPlayer().equals(player)) {
                return e;
            }
        }
        return null;
    }

    public static String getHouse(Player player) {
        if (!hasHouse(player)) {
            return null;
        }

        for (String s : TEMP.datac.getConfigurationSection("").getKeys(false)) {
            if (TEMP.datac.get(s + ".owner") != null) {
                if (TEMP.datac.get(s + ".owner").equals(player.getUniqueId().toString())) {
                    return (String) TEMP.datac.get(s + ".region");
                }
            }
        }
        return null;
    }
}