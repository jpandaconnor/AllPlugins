package co.uk.randompanda30.party.events;

import co.uk.randompanda30.party.Party;
import co.uk.randompanda30.party.TEMP;
import co.uk.randompanda30.party.object.Timeout;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * Created by panda on 23/04/16.
 */
public class OnPlayerJoinEvent implements Listener {

    Party plugin;

    public OnPlayerJoinEvent() {
        this.plugin = Party.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        for(Timeout t : TEMP.timesouts) {
            if(t.getPlayer().equals(uuid)) {
                t.cancel();
                break;
            }
        }
    }
}