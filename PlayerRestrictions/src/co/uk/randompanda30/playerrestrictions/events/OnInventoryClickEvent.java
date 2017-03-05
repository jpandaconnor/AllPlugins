package co.uk.randompanda30.playerrestrictions.events;

import co.uk.randompanda30.playerrestrictions.PlayerRestrictions;
import co.uk.randompanda30.playerrestrictions.item.StackComparison;
import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.MetaItemStack;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by panda on 18/06/16.
 */
public class OnInventoryClickEvent implements Listener {


    public OnInventoryClickEvent() {
        PlayerRestrictions.getPlugin().getServer().getPluginManager().registerEvents(this, PlayerRestrictions.getPlugin());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        List<String> xkits = PlayerRestrictions.getPlugin().getConfig().getStringList("bypass");

        if(event.getView().getTopInventory().getType() == InventoryType.ANVIL) {
            if(PlayerRestrictions.getEssentials().getSettings().getKits().getKeys(false) != null) {
                for (String kit : PlayerRestrictions.getEssentials().getSettings().getKits().getKeys(false)) {
                    if (xkits.contains(kit)) {
                        continue;
                    }
                    // tools
                    // notch etc...
                    HashMap<String, Object> ko = (HashMap<String, Object>) PlayerRestrictions.getEssentials().getSettings().getKit(kit);

                    Iterator i = ko.entrySet().iterator();

                    while (i.hasNext()) {
                        Map.Entry pair = (Map.Entry) i.next();

                        String s = (String) pair.getKey();

                        if (s.equalsIgnoreCase("items")) {
                            for (String item : (List<String>) pair.getValue()) {
                                String[] parts = item.split(" +");
                                try {
                                    ItemStack parseStack = PlayerRestrictions.getEssentials().getItemDb().get(parts[0],
                                            parts.length > 1 ? Integer.parseInt(parts[1]) : 1);

                                    MetaItemStack mis = new MetaItemStack(parseStack);

                                    if (parts.length > 2) {
                                        mis.parseStringMeta((CommandSource) null, true, parts, 2, (IEssentials)
                                                PlayerRestrictions.getEssentials());
                                    }

                                        if (event.getCurrentItem() != null) {
                                            if (StackComparison.compare(event.getCurrentItem() , mis.getItemStack())) {
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                                        "&cYou cannot rename this item"));
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

        if (event.getInventory().getType() == InventoryType.ANVIL) {
            if(event.getSlot() == 0 || event.getSlot() == 1) {
                if(event.getCursor().getType().equals(Material.GRASS)) {
                    Bukkit.broadcastMessage("ahhh");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Anvils are currently disabled"));
                    player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    event.setCancelled(true);
                }
            }
        }
    }

}