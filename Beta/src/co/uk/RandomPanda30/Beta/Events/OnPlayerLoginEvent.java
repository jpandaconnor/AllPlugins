package co.uk.RandomPanda30.Beta.Events;

import co.uk.RandomPanda30.Beta.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerLoginEvent implements Listener {

    public Main plugin;

    public OnPlayerLoginEvent(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("beta.beater")) {
            return;
        } else if (!player.hasPermission("beta.beaterplus")) {
            return;
        } else {
            player.kickPlayer("Still in beta. Buy Beater to bypass this!");
        }
    }
}