package co.uk.randompanda30.infarmour;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class InfArmour extends JavaPlugin implements Listener {

    static InfArmour plugin;

    @Override
    public void onEnable() {
        plugin = this;

        plugin.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

        plugin = null;
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            for(ItemStack i : player.getInventory().getArmorContents()) {
                if (i != null) {
                    i.setDurability((short) 0);
                }
            }
        }
    }
}