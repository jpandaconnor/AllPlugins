package co.uk.RandomPanda30.SpigotOverride;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public void onEnable() {
        plugin = this;

        new PlayerConnection();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {

    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        
    }
}
