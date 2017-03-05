package co.uk.RandomPanda30.SAO.Events;

import co.uk.RandomPanda30.SAO.Objects.DeathPackage;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.TEMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnEvent implements Listener {

    public SAO plugin;

    public OnPlayerRespawnEvent(SAO plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (TEMP.deathpackages.containsKey(player.getUniqueId())) {
            DeathPackage deathPackage = TEMP.deathpackages.get(player.getUniqueId());
            player.getInventory().setContents(deathPackage.getInventory());
            player.setLevel(deathPackage.getLevel());
            player.setExp(deathPackage.getXP());
        }
    }
}