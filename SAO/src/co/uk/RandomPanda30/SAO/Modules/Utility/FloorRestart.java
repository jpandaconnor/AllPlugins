package co.uk.RandomPanda30.SAO.Modules.Utility;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Manager.DataManager;
import co.uk.RandomPanda30.SAO.Plugin.CommandHandler;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.TEMP;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.ItemBuilder;
import co.uk.RandomPanda30.SAO.Utilities.No;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

/*

Gives ranks
Gives AC

 */

public class FloorRestart implements Listener, CommandExecutor {

    private final String MAIN_MENU = "Floor restart menu";

    public SAO plugin;

    private ItemStack FR_ENABLED = ItemBuilder.createItem((Config.configf.getBoolean("MODULES.FLOORRESTART.ENABLED") ? "%NFloor reset: %GEnabled" : "%NFloor reset: %BDisabled"),
            (Config.configf.getBoolean("MODULES.FLOORRESTART.ENABLED") ? Material.EMERALD : Material.REDSTONE), 1, (byte) 0, new ArrayList<String>());
    private ItemStack FR_EDITITEMS = ItemBuilder.createItem("%NEdit items", Material.BOOK, 1, (byte) 0, new ArrayList<String>());
    private ItemStack FR_RESETLEVEL = ItemBuilder.createItem((Config.configf.getBoolean("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED") ? "%NLevel reset: %GEnabled" : "%NLevel reset: %BDisabled"),
            (Config.configf.getBoolean("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED") ? Material.EMERALD : Material.REDSTONE), 1, (byte) 0, new ArrayList<String>());
    private ItemStack FR_EXIT = ItemBuilder.createItem("%BExit", Material.REDSTONE, 1, (byte) 0, new ArrayList<String>());

    private ArrayList<String> inventoryNames = new ArrayList<String>() {
        {
            add(MAIN_MENU);

        }
    };

    public FloorRestart(SAO plugin) {
        this.plugin = plugin;

        if ((Boolean) Config.ConfigValues.MODULES_FLOORRESTART_ENABLED.value) {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
            plugin.getCommand("fr").setExecutor(this);
        } else {
            // Do shit here
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!CommandHandler.isPlayer(sender)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.NOCONSOLE.value, null);
            return true;
        }

        final Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!player.hasPermission("sao.floorrestart")) {
            return true;
        }

        if (args.length == 0) {
            openMainMenu(player);
            return true;
        }

        return true;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        ItemStack ci = event.getCurrentItem();

        if (ci == null || ci.getType().equals(Material.AIR)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        for (String s : inventoryNames) {
            if (inventory.getName().equals(s)) {
                event.setCancelled(true);
                break;
            }
        }

        switch (inventory.getName()) {
            case MAIN_MENU:
                if (ci.equals(FR_ENABLED)) {
                    if (Config.configf.getBoolean("MODULES.FLOORRESTART.ENABLED")) {
                        Config.configf.set("MODULES.FLOORRESTART.ENABLED", false);
                        Config.save();
                    } else {
                        Config.configf.set("MODULES.FLOORRESTART.ENABLED", true);
                        Config.save();
                    }
                    refresh();
                    openMainMenu(player);
                }

                if (ci.equals(FR_EDITITEMS)) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.FLOORRESTART_TYPEALEVEL.value, player);
                    TEMP.typingLevels.add(uuid);
                    player.closeInventory();
                }

                if (ci.equals(FR_RESETLEVEL)) {
                    if (Config.configf.getBoolean("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED")) {
                        Config.configf.set("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED", false);
                        Config.save();
                    } else {
                        Config.configf.set("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED", true);
                        Config.save();
                    }
                    refresh();
                    openMainMenu(player);
                }

                if (ci.equals(FR_EXIT)) {
                    player.closeInventory();
                }
                break;
        }
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        String message = event.getMessage();

        if (TEMP.typingLevels.contains(uuid)) {
            if (!No.isNumber(message)) {
                Dispatch.sendMessage((String) Messages.MessagesValues.FLOORRESTART_NOTANUMBER.value, player);
            }

            if (!co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.floorrestartf.contains(message)) {
                co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.floorrestartf.set(message + ".items", new ArrayList<String>());
                co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.save();
            }

            Inventory inventory = Bukkit.createInventory(null, 54, "Editing level items");
            ArrayList<ItemStack> is = (ArrayList<ItemStack>) co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.floorrestartf.get(message + ".items");
            ItemStack[] i = new ItemStack[is.size()];
            is.toArray(i);

            inventory.setContents(i);

            player.openInventory(inventory);

            TEMP.editingLevels.put(uuid, Integer.parseInt(message));

            if (TEMP.typingLevels.contains(uuid)) {
                TEMP.typingLevels.remove(uuid);
            }

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        Inventory inventory = event.getInventory();

        if (TEMP.editingLevels.containsKey(uuid)) {
            int level = TEMP.editingLevels.get(uuid);
            ArrayList<ItemStack> items = new ArrayList<>();
            for (ItemStack is : inventory.getContents()) {
                if (is != null) {
                    items.add(is);
                }
            }
            co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.floorrestartf.set(Integer.toString(level) + ".items", items);
            co.uk.RandomPanda30.SAO.Config.Modules.FloorRestart.save();

            if (TEMP.editingLevels.containsKey(uuid)) {
                TEMP.editingLevels.remove(uuid);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if ((Boolean) Config.ConfigValues.MODULES_FLOORRESTART_PLAYERRESET_LEVEL_ENABLED.value) {
            if (DataManager.getInt("playerdata", "reset_feb2march", "uuid", uuid.toString()) == 0) {

                if (player.getLevel() > 20) {
                    player.getInventory().clear();
                    player.setLevel(0);
                }

                DataManager.set("playerdata", "reset_feb2march", 1, "uuid", uuid.toString());
                DataManager.set("playerdata", "sao_level", 0, "uuid", uuid.toString());

                player.setExp(0);
                player.setLevel(DataManager.getInt("playerdata", "sao_level", "uuid", uuid.toString()));

                player.setHealth(1);
                player.setHealth(10);
                player.setFoodLevel(20);

                for (String s : (ArrayList<String>) Messages.MessagesValues.FLOORRESTART_WELCOME.value) {
                    Dispatch.sendMessage(s.replace("%floor", Integer.toString((Integer) Config.ConfigValues.FLOOR.value)),
                            player);
                }
            }
        }
    }

    private void openMainMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, MAIN_MENU);
        inventory.setItem(0, FR_ENABLED);
        inventory.setItem(2, FR_EDITITEMS);
        inventory.setItem(4, FR_RESETLEVEL);
        inventory.setItem(8, FR_EXIT);
        player.openInventory(inventory);
    }

    private void refresh() {
        FR_ENABLED = ItemBuilder.createItem((Config.configf.getBoolean("MODULES.FLOORRESTART.ENABLED") ? "%NFloor reset: %GEnabled" : "%NFloor reset: %BDisabled"),
                (Config.configf.getBoolean("MODULES.FLOORRESTART.ENABLED") ? Material.EMERALD : Material.REDSTONE), 1, (byte) 0, new ArrayList<String>());
        FR_EDITITEMS = ItemBuilder.createItem("%NEdit items", Material.BOOK, 1, (byte) 0, new ArrayList<String>());
        FR_RESETLEVEL = ItemBuilder.createItem((Config.configf.getBoolean("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED") ? "%NLevel reset: %GEnabled" : "%NLevel reset: %BDisabled"),
                (Config.configf.getBoolean("MODULES.FLOORRESTART.PLAYERRESET.LEVEL.ENABLED") ? Material.EMERALD : Material.REDSTONE), 1, (byte) 0, new ArrayList<String>());
        FR_EXIT = ItemBuilder.createItem("%BExit", Material.REDSTONE, 1, (byte) 0, new ArrayList<String>());
    }
}