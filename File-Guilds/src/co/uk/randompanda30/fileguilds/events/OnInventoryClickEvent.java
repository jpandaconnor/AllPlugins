package co.uk.randompanda30.fileguilds.events;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.TEMP;
import co.uk.randompanda30.fileguilds.commands.ranks.CommandRankPerms;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Rank;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnInventoryClickEvent implements Listener {

    public OnInventoryClickEvent() {
        Guilds.getPlugin().getServer().getPluginManager().registerEvents(this, Guilds.getPlugin());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        Inventory inventory = event.getInventory();
        ItemStack is = event.getCurrentItem();

        if (is == null) {
            return;
        }

        if (is.getType().equals(Material.AIR)) {
            return;
        }

        if (inventory.getName().equals(CommandRankPerms.INVENTORY_TITLE)) {
            event.setCancelled(true);

            final GuildOB guild = new GuildOB(PlayerData.getGuild(player));

            Rank rank2 = guild.getRank(TEMP.editingPermissions.get(player.getUniqueId()));

            // Checking if they clicked a rank block here
            if (is.getType().equals(Material.WOOL)) {
                String name = (is.hasItemMeta() ? is.getItemMeta().getDisplayName() : "");

                switch (name) {
                    case "Kick":
                        if (rank2.canKick()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Kick;true", "Kick;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Kick;false", "Kick;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Invite":
                        if (rank2.canInvite()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Invite;true", "Invite;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Invite;false", "Invite;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "GChat":
                        if (rank2.canGChat()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("GChat;true", "GChat;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("GChat;false", "GChat;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "AChat":
                        if (rank2.canAChat()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AChat;true", "AChat;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AChat;false", "AChat;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Ally":
                        if (rank2.canAlly()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Ally;true", "Ally;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Ally;false", "Ally;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Unally":
                        if (rank2.canUnally()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Unally;true", "Unally;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("Unally;false", "Unally;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Ally Accept":
                        if (rank2.canAcceptAlly()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AllyAccept;true", "AllyAccept;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AllyAccept;false", "AllyAccept;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Ally Deny":
                        if (rank2.canDenyAlly()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AllyDeny;true", "AllyDeny;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("AllyDeny;false", "AllyDeny;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Rank Set":
                        if (rank2.canRankSet()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankSet;true", "RankSet;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankSet;false", "RankSet;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Rank Title":
                        if (rank2.canRankTitle()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankTitle;true", "RankTitle;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankTitle;false", "RankTitle;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Rank Create":
                        if (rank2.canRankCreate()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankCreate;true", "RankCreate;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankCreate;false", "RankCreate;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Rank Delete":
                        if (rank2.canRankDelete()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankDelete;true", "RankDelete;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankDelete;false", "RankDelete;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Rank Perms":
                        if (rank2.canRankPerms()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankPerms;true", "RankPerms;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("RankPerms;false", "RankPerms;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot Create":
                        if (rank2.canPlotCreate()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotCreate;true", "PlotCreate;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotCreate;false", "PlotCreate;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot Delete":
                        if (rank2.canPlotDelete()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotDelete;true", "PlotDelete;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotDelete;false", "PlotDelete;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot Home":
                        if (rank2.canPlotHome()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotHome;true", "PlotHome;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotHome;false", "PlotHome;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot Build":
                        if (rank2.canPlotBuild()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotBuild;true", "PlotBuild;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotBuild;false", "PlotBuild;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot TP":
                        if (rank2.canPlotTP()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotTP;true", "PlotTP;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotTP;false", "PlotTP;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Plot Reset":
                        if (rank2.canPlotReset()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotReset;true", "PlotReset;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PlotReset;false", "PlotReset;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "War Declare":
                        if (rank2.canWarDeclare()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("WarDeclare;true", "WarDeclare;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("WarDeclare;false", "WarDeclare;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "War Surrender":
                        if (rank2.canWarSurrender()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("WarSurrender;true", "WarSurrender;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("WarSurrender;false", "WarSurrender;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "PVP":
                        if (rank2.canPVP()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PVP;true", "PVP;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("PVP;false", "PVP;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "MOTD":
                        if (rank2.canMOTD()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("MOTD;true", "MOTD;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("MOTD;false", "MOTD;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                    case "Transfer Ownership":
                        if (rank2.canTransferOwnership()) {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("TransferOwnership;true", "TransferOwnership;false");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        } else {
                            String rankString = guild.getRankString();

                            String org = rank2.getOrg();
                            String newOrg = org.replace("TransferOwnership;false", "TransferOwnership;true");

                            String newRankString = rankString.replace(org, newOrg);
                            TEMP.guildsc.set(guild.getName() + ".ranks", newRankString);
                            Guild.save();
                        }
                        break;
                }
                player.closeInventory();
                CommandRankPerms.openInventory(player, guild.getName());
            }
        }
    }
}