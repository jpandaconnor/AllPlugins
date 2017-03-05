package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.config.Request;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * Created by panda on 01/07/16.
 */
public class OnPlayerJoinEvent implements Listener {

    public OnPlayerJoinEvent(HouseShop plugin) {
        HouseShop.getPlugin().getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if(TEMP.requestc.contains(uuid.toString())) {
            if(TEMP.requestc.contains(uuid.toString() + ".fufilled")) {
                Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_REQUESTDONE.value, player);
                TEMP.requestc.set(uuid.toString(), null);
                Request.save();
            }
        }
    }
}