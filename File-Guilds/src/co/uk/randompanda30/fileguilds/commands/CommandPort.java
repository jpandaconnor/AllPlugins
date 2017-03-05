package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Data;
import co.uk.randompanda30.fileguilds.config.Guild;
import co.uk.randompanda30.fileguilds.data.GuildData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static co.uk.randompanda30.fileguilds.TEMP.guildsc;
import static co.uk.randompanda30.fileguilds.TEMP.pdatac;

public class CommandPort extends Command {

    public CommandPort() {
        super("guild port", "", "Admin command only", true);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {

        sender.sendMessage("DOING DOING DOING DOING DOING DOING DOING DOING DOING DOING DOING ");

        try {

            File file = new File("plugins/" + Guilds.getPlugin().getName() + "/old.yml");
            FileConfiguration config = new YamlConfiguration();

            config.load(file);

            // Remember Guilds.

            ArrayList<String> test = new ArrayList<>();

            for (String s : config.getConfigurationSection("Guilds").getKeys(false)) {
                test.add(s);
            }

            for (String s : test) {
                Bukkit.broadcastMessage(ChatColor.AQUA + s);
                Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + s);
                // Name of guild

                if (!((String) config.get("Guilds." + s + ".Leader")).
                        matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
                    continue;
                }

                Bukkit.broadcastMessage(ChatColor.RED + (String) config.get("Guilds." + s + ".Leader"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + (String) config.get("Guilds." + s + ".Leader"));

                guildsc.set(ChatColor.stripColor(s) + ".tag", config.get("Guilds." + s + ".Tag"));
                guildsc.set(ChatColor.stripColor(s) + ".leader.uuid", config.get("Guilds." + s + ".Leader"));

                ArrayList<String> memebers = new ArrayList<>();

                for (String ss : config.getConfigurationSection("Guilds." + s + ".Players").getKeys(false)) {
                    if (config.get("Guilds." + s + ".Players." + ss + ".Rank").equals("Leader")) {
                        guildsc.set(ChatColor.stripColor(s) + ".leader.uuid", config.get("Guilds." + s + ".Leader"));
                        guildsc.set(ChatColor.stripColor(s) + ".leader.name", config.get("Guilds." + s + ".Players.Name"));
                        pdatac.set(ss + ".rank", "Leader");
                        pdatac.set(ss + ".guild", ChatColor.stripColor(s));
                        continue;
                    }

                    memebers.add(ss);
                }

                for (String sss : memebers) {
                    pdatac.set(sss + ".rank", "Rookie");
                    pdatac.set(sss + ".guild", ChatColor.stripColor(s));
                }

                guildsc.set(ChatColor.stripColor(s) + ".members", memebers);

                guildsc.set(ChatColor.stripColor(s) + ".motd", config.get("Guilds." + s + ".Gmotd"));

                guildsc.set(ChatColor.stripColor(s) + ".plot.1.X", config.get("Guilds." + s + ".Plot.1.X"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.1.Y", config.get("Guilds." + s + ".Plot.1.Y"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.1.Z", config.get("Guilds." + s + ".Plot.1.Z"));

                guildsc.set(ChatColor.stripColor(s) + ".plot.2.X", config.get("Guilds." + s + ".Plot.2.X"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.2.Y", config.get("Guilds." + s + ".Plot.2.Y"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.2.Z", config.get("Guilds." + s + ".Plot.2.Z"));

                guildsc.set(ChatColor.stripColor(s) + ".plot.3.X", config.get("Guilds." + s + ".Plot.3.X"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.3.Y", config.get("Guilds." + s + ".Plot.3.Y"));
                guildsc.set(ChatColor.stripColor(s) + ".plot.3.Z", config.get("Guilds." + s + ".Plot.3.Z"));

                guildsc.set(ChatColor.stripColor(s) + ".pvp", false);

                String newRanks = "";

                for (String r : GuildData.getLeaderRanks()) {
                    newRanks += r;
                }

                newRanks += "$";

                for (String r : GuildData.getRookieRanks()) {
                    newRanks += r;
                }

                guildsc.set(ChatColor.stripColor(s) + ".ranks", newRanks);

                Guild.save();
                Data.save();
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return true;
    }
}