package co.uk.randompanda30.playerrestrictions.events;

import co.uk.randompanda30.playerrestrictions.PlayerRestrictions;
import co.uk.randompanda30.playerrestrictions.TEMP;
import co.uk.randompanda30.playerrestrictions.item.StackComparison;
import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.MetaItemStack;
import net.ess3.api.IEssentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Created by panda on 29/05/16.
 */
public class OnPlayerPickupItemEvent implements Listener {

    public OnPlayerPickupItemEvent() {
        PlayerRestrictions.getPlugin().getServer().getPluginManager().registerEvents(this, PlayerRestrictions.getPlugin());
    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        List<String> xkits = PlayerRestrictions.getPlugin().getConfig().getStringList("bypass");

        for(String kit : PlayerRestrictions.getEssentials().getSettings().getKits().getKeys(false)) {
            if(xkits.contains(kit)) {
                continue;
            }
            // tools
            // notch etc...
            HashMap<String, Object> ko = (HashMap<String, Object>) PlayerRestrictions.getEssentials().getSettings().getKit(kit);

            Iterator i = ko.entrySet().iterator();

            while(i.hasNext()) {
                Map.Entry pair = (Map.Entry) i.next();

                String s = (String) pair.getKey();

                if(s.equalsIgnoreCase("items")) {
                    for(String item : (List<String>) pair.getValue()) {
                        String[] parts = item.split(" +");
                        try {
                            ItemStack parseStack = PlayerRestrictions.getEssentials().getItemDb().get(parts[0],
                                    parts.length > 1 ? Integer.parseInt(parts[1]):1);

                            MetaItemStack mis = new MetaItemStack(parseStack);

                            if(parts.length > 2) {
                                mis.parseStringMeta((CommandSource) null, true, parts, 2, (IEssentials)
                                        PlayerRestrictions.getEssentials());
                            }

                            if(StackComparison.compare(event.getItem().getItemStack(), mis.getItemStack())) {
                                if(!player.hasPermission("essentials.kits." + kit)) {
                                    if(!TEMP.msent.contains(uuid)) {
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                "use this item until " + kit + " has been bought"));
                                        TEMP.msent.add(uuid);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                if(TEMP.msent.contains(uuid)) {
                                                    TEMP.msent.remove(uuid);
                                                }
                                            }
                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                    }
                                    event.setCancelled(true);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}