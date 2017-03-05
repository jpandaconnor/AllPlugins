package co.uk.RandomPanda30.SAO.Events;

import co.uk.RandomPanda30.SAO.Objects.DeathPackage;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.TEMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeathEvent implements Listener {

    public SAO plugin;

    public OnPlayerDeathEvent(SAO plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();

        TEMP.deathpackages.put(player.getUniqueId(), new DeathPackage(player.getInventory().getContents(), player.getLevel(),
                player.getExp()));
    }
}