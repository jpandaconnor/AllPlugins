package co.uk.randompanda30.playerrestrictions;

import co.uk.randompanda30.playerrestrictions.events.OnInventoryClickEvent;
import co.uk.randompanda30.playerrestrictions.events.OnPlayerPickupItemEvent;
import co.uk.randompanda30.playerrestrictions.ticker.InventoryChecker;
import net.ess3.api.IEssentials;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PlayerRestrictions extends JavaPlugin {

    static PlayerRestrictions plugin;
    static IEssentials essentials;

    @Override
    public void onEnable() {
        plugin = this;

        essentials = (IEssentials) this.getServer().getPluginManager().getPlugin("Essentials");

        new OnPlayerPickupItemEvent();
        new OnInventoryClickEvent();

        new InventoryChecker().runTaskTimerAsynchronously(this, 0L, 100L);

        if(!getConfig().contains("bypass")) {
            getConfig().set("bypass", new ArrayList<String>() {
                {
                    add("starter");
                }
            });
            saveConfig();
        }
    }

    @Override
    public void onDisable() {

        plugin = null;
    }

    public static PlayerRestrictions getPlugin() {
        return plugin;
    }

    public static com.earth2me.essentials.IEssentials getEssentials() {
        return essentials;
    }
}
