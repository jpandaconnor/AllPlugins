package co.uk.RandomPanda30.SAO.Modules.Items;

/*
This module is designed to transfer items over from server to server
 */

import co.uk.RandomPanda30.SAO.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.SAO.Plugin.CommandHandler;
import co.uk.RandomPanda30.SAO.SAO;
import co.uk.RandomPanda30.SAO.TEMP;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import co.uk.RandomPanda30.SAO.Utilities.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static co.uk.RandomPanda30.SAO.Config.Config.ConfigValues;

public class ItemTransfer implements Listener, CommandExecutor {

    public SAO plugin;

    public ItemTransfer(SAO plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        plugin.getCommand("it").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(Boolean) ConfigValues.MODULES_ITEMTRANSFER_ENABLED.value) {
            sender.sendMessage(Format.format(MessagesValues.MODULE_OFF.value.toString().replace("mod", "ItemTransfer")));
            return true;
        }

        if (!CommandHandler.isPlayer(sender)) {
            Dispatch.sendMessage((String) MessagesValues.NOCONSOLE.value, null);
            return true;
        }

        final Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (args.length == 0) {
            new Syntax("/it", new ArrayList<String>() {
                {
                    add("/it create <Transfer name> <Server 1> <Server 2> %N - Creates an item transfer module");
                    add("/it delete <Transfer name> %N - Deletes an item transfer module");
                    add("/it set <Transfer name> <beaterplus/beater/player> <Item limit> - Sets item limit for a certain rank");
                    add("/it transfer <Transfer name> - Opens item transfer menu");
                }
            }, player);
            return true;
        }

        /*
        /it create <Transfer name> <Server now> <Destination server>
        /it delete <Transfer name>
        /it set <beaterplus, beater, player> <Item limit>
        /it transfer <Transfer name>

        Transfer name
          limits:
            BeaterPlus:
              limit: 5
            Beater:
              limit: 3
            Player:
              limit: 1
          Transfered:
            - In Database
         */


        String transferName = "";
        switch (args[0]) {
            case "create":
                if (!player.hasPermission("sao.itemtransfer.create")) {
                    Dispatch.sendMessage((String) MessagesValues.NOPERM.value, player);
                    return true;
                }

                if (args.length != 4) {
                    new Syntax("/it create", "/it create <Transfer name> <Server 1> <Server 2>", player);
                    return true;
                }

                transferName = args[1];
                String currentServer = args[2];
                String desServer = args[3];

                if (co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.contains(transferName)) {
                    Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_TRANSFERNAMEEXISTS.value, player);
                    return true;
                }

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                try {
                    out.writeUTF("GetServers");
                    plugin.getServer().sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                boolean server1 = false;
                boolean server2 = false;

                for (String server : TEMP.servers) {
                    if (currentServer.equals(server)) {
                        server1 = true;
                    }

                    if (desServer.equals(server)) {
                        server2 = true;
                    }
                }

                if (!server1 || !server2) {
                    if (!server1) {
                        Dispatch.sendMessage(MessagesValues.ITEMTRANSFER_SERVERDOESNOTEXIST.value.toString()
                                .replace("%ser", currentServer), player);
                    }

                    if (!server2) {
                        Dispatch.sendMessage(MessagesValues.ITEMTRANSFER_SERVERDOESNOTEXIST.value.toString()
                                .replace("%ser", desServer), player);
                    }
                    return true;
                }

                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.set(transferName + ".limits.BeaterPlus", 7);
                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.set(transferName + ".limits.Beater", 5);
                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.set(transferName + ".limits.Player", 3);
                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.save();

                Dispatch.sendMessage(MessagesValues.ITEMTRANSFER_TRANSFERCREATED.value.toString().replace("%tra", transferName), player);
                break;
            case "delete":
                if (!player.hasPermission("sao.itemtransfer.delete")) {
                    Dispatch.sendMessage((String) MessagesValues.NOPERM.value, player);
                    return true;
                }

                if (args.length != 2) {
                    new Syntax("/it delete", "/it delete <Transfer name>", player);
                    return true;
                }

                transferName = args[1];

                if (!co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.contains(transferName)) {
                    Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_TRANSFERNOTFOUND.value, player);
                    return true;
                }

                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.set(transferName, null);
                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.save();

                Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_TRANSFERDELETED.value, player);
                break;
            case "set":
                if (!player.hasPermission("sao.itemtransfer.set")) {
                    Dispatch.sendMessage((String) MessagesValues.NOPERM.value, player);
                    return true;
                }

                if (args.length != 4) {
                    new Syntax("/it set", "/it set <Transfer name> <beaterplus/beater/player> <Item limit>", player);
                    return true;
                }

                if (!co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.contains(transferName)) {
                    Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_TRANSFERNOTFOUND.value, player);
                    return true;
                }

                transferName = args[1];

                if (!args[2].toLowerCase().equals("beaterplus") || !args[2].toLowerCase().equals("beater") || !args[2].toLowerCase().equals("player")) {
                    new Syntax("/it set", "/it set <Transfer name> <beaterplus/beater/player> <Item limit>", player);
                    return true;
                }

                String rank = args[2];

                int limit;

                try {
                    limit = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_STRINGFORNUMBER.value, player);
                    return true;
                }

                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.set(transferName + ".limits." + rank, limit);
                co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.save();
                break;

            case "transfer":
                if (args.length != 2) {
                    new Syntax("/it transfer", "/it transfer <Transfer name>", player);
                    return true;
                }

                transferName = args[1];

                if (!player.hasPermission("sao.itemtransfers." + transferName) || player.hasPermission("sao.itemtransfer.transferall")) {
                    Dispatch.sendMessage((String) MessagesValues.NOPERM.value, player);
                    return true;
                }

                if (co.uk.RandomPanda30.SAO.Config.Modules.ItemTransfer.itemtransferf.contains(player.getUniqueId().toString())) {
                    Dispatch.sendMessage((String) MessagesValues.ITEMTRANSFER_ALREADYTRANSFERRED.value, player);
                    return true;
                }

                for (String s : (List<String>) MessagesValues.ITEMTRANSFER_GETREADY.value) {
                    Dispatch.sendMessage(s, player);
                }

                if (player.hasPermission("sao.beaterplus")) {
                    TEMP.chatStopped.add(uuid);

                    final Inventory inventory = Bukkit.createInventory(null, 9, "Beater+ Item Transfer");


                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.openInventory(inventory);
                        }
                    }, 100L);
                } else if (player.hasPermission("sao.beater")) {
                    TEMP.chatStopped.add(uuid);

                    final Inventory inventory = Bukkit.createInventory(null, 9, "Beater Item Transfer");


                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.openInventory(inventory);
                        }
                    }, 100L);
                } else {
                    TEMP.chatStopped.add(uuid);

                    final Inventory inventory = Bukkit.createInventory(null, 9, "Player Item Transfer");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.openInventory(inventory);
                        }
                    }, 100L);
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        TEMP.chatStopped.remove(player);
                    }
                }, 500L);
                break;
        }
        return true;
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        switch (inventory.getName()) {
            case "Beater+ Item Transfer":

                // Message here
                break;
            case "Beater Item Transfer":

                // Message here
                break;

            case "Player Item Transfer":

                // Message here
                break;
        }

    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        for (UUID uuid : TEMP.chatStopped) {
            Player player = Bukkit.getPlayer(uuid);
            event.getRecipients().remove(player);
        }
    }
}