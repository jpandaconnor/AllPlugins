package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandLookup extends Command {

    public CommandLookup() {
        super("guild lookup <Guild name>", "", "Looks up player information", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = lookup
        // args[1] = name

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        String target = args[1];
        if (Bukkit.getServer().getPlayer(target) != null) {
            Player ptk = Bukkit.getPlayer(target);
            UUID uuid = ptk.getUniqueId();

            if (PlayerData.isInGuild(uuid)) {
                Dispatch.sendMessage("%TAG %NPlayer information - ", player);
                Dispatch.sendMessage("%TAG %NName: %A" + ptk.getName(), player);
                Dispatch.sendMessage("%TAG %NGuild: %A" + PlayerData.getGuild(ptk), player);
                Dispatch.sendMessage("%TAG %NRank: %A" + ChatColor.stripColor(PlayerData.getRank(ptk)), player);
            } else {
                Dispatch.sendMessage("%TAG %NPlayer information - ", player);
                Dispatch.sendMessage("%TAG %EPlayer is not in a guild!", player);
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_TARGETNOTFOUND.value, player);
            return true;
        }
        return true;
    }
}