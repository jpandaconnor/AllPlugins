package co.uk.RandomPanda30.SAO.Events;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitEvent implements Listener {

    public OnPlayerQuitEvent(SAO plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        SAO.mysql.updateTable("playerdata", "sao_level", "uuid", player.getUniqueId().toString(), player.getLevel());
        SAO.mysql.updateTable("playerdata", "sao_exp", "uuid", player.getUniqueId().toString(), (int) player.getExp());
    }
}