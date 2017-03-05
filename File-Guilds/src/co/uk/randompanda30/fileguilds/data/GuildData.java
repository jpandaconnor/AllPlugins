package co.uk.randompanda30.fileguilds.data;

import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Guild;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;
import static co.uk.randompanda30.fileguilds.TEMP.pdatac;

public class GuildData {

    static ArrayList<String> leaderr = new ArrayList<String>() {
        {

            add("Leader:Leader:");
            // Player ranks
            add("Kick;true!");
            add("Invite;true!");
            add("GChat;true!");
            add("AChat;true!");

            // Guild stuff
            add("Ally;true!");
            add("Unally;true!");
            add("AllyAccept;true!");
            add("AllyDeny;true!");

            // Rank stuff
            add("RankSet;true!");
            add("RankTitle;true!");
            add("RankCreate;true!");
            add("RankDelete;true!");
            add("RankPerms;true!");

            // Plot stuff
            add("PlotCreate;true!");
            add("PlotDelete;true!");
            add("PlotHome;true!");
            add("PlotBuild;true!");
            add("PlotTP;true!"); // Edit TPkit
            add("PlotReset;true!");

            // War stuff
            add("WarDeclare;true!");
            add("WarSurrender;true!");

            // Other guilds things
            add("PVP;true!");    // Toggle PVP between guild members
            add("MOTD;true!");    // Sets message of the day
            add("TransferOwnership;true!");
        }
    };

    static ArrayList<String> rookier = new ArrayList<String>() {
        {

            add("Rookie:Rookie:");
            // Player ranks
            add("Kick;false!");
            add("Invite;false!");
            add("GChat;true!");
            add("AChat;false!");

            // Guild stuff
            add("Ally;false!");
            add("Unally;false!");
            add("AllyAccept;false!");
            add("AllyDeny;false!");

            // Rank stuff
            add("RankSet;false!");
            add("RankTitle;false!");
            add("RankCreate;false!");
            add("RankDelete;false!");
            add("RankPerms;false!");

            // Plot stuff
            add("PlotCreate;false!");
            add("PlotDelete;false!");
            add("PlotHome;true!");
            add("PlotBuild;false!");
            add("PlotTP;false!"); // Edit TP
            add("PlotReset;false!");

            // War stuff
            add("WarDeclare;false!");
            add("WarSurrender;false!");

            // Other guilds things
            add("PVP;false!");    // Toggle PVP between guild members
            add("MOTD;false!");    // Sets message of the day
            add("TransferOwnership;false!");
        }
    };

    public static boolean isGuild(String name) {
        return guildsc.contains(name);
    }

    public static void createGuild(String name, String tag, final Player player) {
        guildsc.set(name + ".tag", tag);
        guildsc.set(name + ".leader.uuid", player.getUniqueId().toString());
        guildsc.set(name + ".leader.name", player.getName());

        guildsc.set(name + ".pvp", false);

        guildsc.set(name + ".members", new ArrayList<String>() {
            {
                add(player.getUniqueId().toString());
            }
        });

        String n = "";

        for (String s : leaderr) {
            n += s;
        }

        n += "$";

        for (String s : rookier) {
            n += s;
        }

        guildsc.set(name + ".ranks", n);

        pdatac.set(player.getUniqueId().toString() + ".guild", name);
        pdatac.set(player.getUniqueId().toString() + ".rank", "Leader");
        Guild.save();
        Data.save();
    }

    public static void deleteGuild(String name) {
        guildsc.set(name, null);
        Guild.save();
    }

    public static ArrayList<String> getLeaderRanks() {
        return leaderr;
    }

    public static ArrayList<String> getRookieRanks() {
        return rookier;
    }
}