package co.uk.randompanda30.houseshop.handle;

import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Data;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by panda on 05/05/16.
 */
public class HouseHandle {

    public void buyHouse(Player player, String name) {
        this.buyHouse(player.getUniqueId(), name);
    }

    public void buyHouse(UUID uuid, String name) {
        TEMP.datac.set(name + ".owner", uuid.toString());
        TEMP.datac.set(name + ".state", HouseState.OWNED.toString());
        TEMP.datac.set(name + ".rent", Calendar.getInstance().getTimeInMillis() + (1000 * 60 * 60 * 24));
        Data.save();
    }

    public void sellHouse(UUID uuid, String name) {
        TEMP.datac.set(name + ".owner", null);
        TEMP.datac.set(name + ".state", HouseState.FORSALE.toString());
        Data.save();
    }
}