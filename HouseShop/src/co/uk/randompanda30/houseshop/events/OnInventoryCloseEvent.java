package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

/**
 * Created by panda on 03/05/16.
 */
public class OnInventoryCloseEvent implements Listener {

    HouseShop plugin;

    public OnInventoryCloseEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (TEMP.editingHouses.containsKey(uuid)) {
            TEMP.editingHouses.remove(uuid);
        }
    }
}