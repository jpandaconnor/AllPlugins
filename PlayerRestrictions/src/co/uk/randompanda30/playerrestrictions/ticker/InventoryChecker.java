package co.uk.randompanda30.playerrestrictions.ticker;

import co.uk.randompanda30.playerrestrictions.PlayerRestrictions;
import co.uk.randompanda30.playerrestrictions.TEMP;
import co.uk.randompanda30.playerrestrictions.item.StackComparison;
import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.MetaItemStack;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Created by panda on 31/05/16.
 */
public class InventoryChecker extends BukkitRunnable {

    @Override
    public void run() {
        List<String> xkits = PlayerRestrictions.getPlugin().getConfig().getStringList("bypass");
        for(Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
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

                                    for (ItemStack is : player.getInventory().getContents()) {
                                        if (is != null) {
                                            if (StackComparison.compare(is, mis.getItemStack())) {
                                                if (!player.hasPermission("essentials.kits." + kit)) {
                                                    if (!TEMP.msent.contains(uuid)) {
                                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                                "use this item until " + kit + " has been bought"));
                                                        TEMP.msent.add(uuid);
                                                        new BukkitRunnable() {
                                                            @Override
                                                            public void run() {
                                                                if (TEMP.msent.contains(uuid)) {
                                                                    TEMP.msent.remove(uuid);
                                                                }
                                                            }
                                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                                    }

                                                    Bukkit.getScheduler().runTask(PlayerRestrictions.getPlugin(), () -> {
                                                        player.getWorld().dropItem(player.getLocation(), is);
                                                        player.getInventory().remove(is);
                                                    });
                                                }
                                            }
                                        }
                                    }

                                    if (player.getInventory().getHelmet() != null) {
                                        if (player.getInventory().getHelmet().getType() != Material.AIR) {
                                            if (StackComparison.compare(player.getInventory().getHelmet(), mis.getItemStack())) {
                                                if (!player.hasPermission("essentials.kits." + kit)) {
                                                    if (!TEMP.msent.contains(uuid)) {
                                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                                "use this item until " + kit + " has been bought"));
                                                        TEMP.msent.add(uuid);
                                                        new BukkitRunnable() {
                                                            @Override
                                                            public void run() {
                                                                if (TEMP.msent.contains(uuid)) {
                                                                    TEMP.msent.remove(uuid);
                                                                }
                                                            }
                                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                                    }

                                                    Bukkit.getScheduler().runTask(PlayerRestrictions.getPlugin(), () -> {
                                                        player.getWorld().dropItem(player.getLocation(), player.getInventory()
                                                                .getHelmet());
                                                        player.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
                                                    });
                                                }
                                            }
                                        }
                                    }

                                    if (player.getInventory().getChestplate() != null) {
                                        if (player.getInventory().getChestplate().getType() != Material.AIR) {
                                            if (StackComparison.compare(player.getInventory().getChestplate(), mis.getItemStack())) {
                                                if (!player.hasPermission("essentials.kits." + kit)) {
                                                    if (!TEMP.msent.contains(uuid)) {
                                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                                "use this item until " + kit + " has been bought"));
                                                        TEMP.msent.add(uuid);
                                                        new BukkitRunnable() {
                                                            @Override
                                                            public void run() {
                                                                if (TEMP.msent.contains(uuid)) {
                                                                    TEMP.msent.remove(uuid);
                                                                }
                                                            }
                                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                                    }

                                                    Bukkit.getScheduler().runTask(PlayerRestrictions.getPlugin(), () -> {
                                                        player.getWorld().dropItem(player.getLocation(), player.getInventory()
                                                                .getChestplate());
                                                        player.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
                                                    });
                                                }
                                            }
                                        }
                                    }

                                    if (player.getInventory().getLeggings() != null) {
                                        if (player.getInventory().getLeggings().getType() != Material.AIR) {
                                            if (StackComparison.compare(player.getInventory().getLeggings(), mis.getItemStack())) {
                                                if (!player.hasPermission("essentials.kits." + kit)) {
                                                    if (!TEMP.msent.contains(uuid)) {
                                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                                "use this item until " + kit + " has been bought"));
                                                        TEMP.msent.add(uuid);
                                                        new BukkitRunnable() {
                                                            @Override
                                                            public void run() {
                                                                if (TEMP.msent.contains(uuid)) {
                                                                    TEMP.msent.remove(uuid);
                                                                }
                                                            }
                                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                                    }

                                                    Bukkit.getScheduler().runTask(PlayerRestrictions.getPlugin(), () -> {
                                                        player.getWorld().dropItem(player.getLocation(), player.getInventory()
                                                                .getLeggings());
                                                        player.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
                                                    });
                                                }
                                            }
                                        }
                                    }

                                    if (player.getInventory().getBoots() != null) {
                                        if (player.getInventory().getBoots().getType() != Material.AIR) {
                                            if (StackComparison.compare(player.getInventory().getBoots(), mis.getItemStack())) {
                                                if (!player.hasPermission("essentials.kits." + kit)) {
                                                    if (!TEMP.msent.contains(uuid)) {
                                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You cannot " +
                                                                "use this item until " + kit + " has been bought"));
                                                        TEMP.msent.add(uuid);
                                                        new BukkitRunnable() {
                                                            @Override
                                                            public void run() {
                                                                if (TEMP.msent.contains(uuid)) {
                                                                    TEMP.msent.remove(uuid);
                                                                }
                                                            }
                                                        }.runTaskLater(PlayerRestrictions.getPlugin(), 100L);
                                                    }

                                                    Bukkit.getScheduler().runTask(PlayerRestrictions.getPlugin(), () -> {
                                                        player.getWorld().dropItem(player.getLocation(), player.getInventory()
                                                                .getBoots());
                                                        player.getInventory().setBoots(new ItemStack(Material.AIR, 1));
                                                    });
                                                }
                                            }
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
    }
}