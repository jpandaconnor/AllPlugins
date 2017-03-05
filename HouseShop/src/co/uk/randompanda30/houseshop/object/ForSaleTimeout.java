package co.uk.randompanda30.houseshop.object;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by panda on 04/05/16.
 */
public class ForSaleTimeout {

    public String house;
    public UUID uuid;

    public int task;

    public ForSaleTimeout instance;

    public ForSaleTimeout(String house, UUID uuid) {
        this.house = house;
        this.uuid = uuid;
        instance = this;

        TEMP.forsalesTimeouts.add(this);

        run();
    }

    private void run() {
        task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(HouseShop.getPlugin(), () -> {
            Bukkit.getServer().getScheduler().cancelTask(task);
            if (TEMP.forsalesTimeouts.contains(instance)) {
                TEMP.forsalesTimeouts.remove(instance);
            }
        }, 20 * 60 * 2);
    }

    public String getHouse() {
        return house;
    }

    public UUID getUuid() {
        return uuid;
    }
}