package co.uk.randompanda30.houseshop.timer;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;
import java.util.List;

/**
 * Created by panda on 11/05/16.
 */
public class Checker extends BukkitRunnable {

    HouseShop plugin;

    public Checker(HouseShop plugin) {
        this.plugin = plugin;
    }

    public void run() {
        for (String s : TEMP.datac.getConfigurationSection("").getKeys(false)) {
            if(!TEMP.datac.contains(s + ".rent")) {
                continue;
            }

            long lt = TEMP.datac.getLong(s + ".rent");

            List<String> locations = (List<String>) TEMP.datac.get(s + ".locations");

            if (Calendar.getInstance().getTimeInMillis() > lt) {
                TEMP.datac.set(s + ".owner", null);
                TEMP.datac.set(s + ".state", HouseState.FORSALE.toString());
                TEMP.datac.set(s + ".rent", null);

                Bukkit.getScheduler().runTask(HouseShop.getPlugin(), () -> {
                    for(String loc : locations) {
                        Location l = LocationS.compileLocation(loc);
                        l.getWorld().getBlockAt(l).setType(Material.AIR);
                    }
                });
                Data.save();
            }
        }
    }
}