package co.uk.randompanda30.fileguilds.commands.ranks;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import co.uk.randompanda30.fileguilds.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;

public class CommandRankPerms extends Command implements Listener {

    public static final String INVENTORY_TITLE = "Edit permissions";
    static final ItemStack BUTTON_DONE = ItemUtil.makeItem("&aDone", Material.EMERALD_BLOCK, 0, (byte) 0, new ArrayList<String>());
    Guilds plugin;

    public CommandRankPerms() {
        super("guild rank perms <Rank name>", "", "Opens permissions GUI", false);
        this.plugin = Guilds.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static void openInventory(Player player, String guild) {
        Inventory inventory = Bukkit.createInventory(null, 27, INVENTORY_TITLE);
        GuildOB guildOB = new GuildOB(guild);

        Rank rank = guildOB.getRank(TEMP.editingPermissions.get(player.getUniqueId()));

        for (Object o : rank.getMap().entrySet()) {
            Map.Entry set = (Map.Entry) o;

            ItemStack is = new ItemStack(Material.WOOL, 1, (short) (set.getValue() == true ? 5 : 14));
            ItemMeta im = is.getItemMeta();
            im.setDisplayName((String) set.getKey());
            is.setItemMeta(im);

            inventory.addItem(is);
        }

        player.openInventory(inventory);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = rank
        // args[1] = perms
        // args[2] = Rank name

        if (TEMP.editingPermissions.containsKey(player.getUniqueId())) {
            TEMP.editingPermissions.remove(player.getUniqueId());
        }

        if (args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canRankPerms()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        if (!guild.isRank(args[2])) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_NOTEXISTS.value, player);
            return true;
        }

        if (args[2].equalsIgnoreCase("Leader") || args[2].equalsIgnoreCase("Rookie")) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILD_RANK_DELETE_CANNOTTARGET.value, player);
            return true;
        }

        Rank rank = guild.getRank(args[2]);

        Bukkit.broadcastMessage(ChatColor.RED + args[2]);


        TEMP.editingPermissions.put(player.getUniqueId(), args[2]);

        Inventory inventory = Bukkit.createInventory(null, 27, INVENTORY_TITLE);

        for (Object o : rank.getMap().entrySet()) {
            Map.Entry set = (Map.Entry) o;

            ItemStack is = new ItemStack(Material.WOOL, 1, (short) (set.getValue() == true ? 5 : 14));
            ItemMeta im = is.getItemMeta();
            im.setDisplayName((String) set.getKey());
            is.setItemMeta(im);

            inventory.addItem(is);
        }

        player.openInventory(inventory);
        return true;
    }
}