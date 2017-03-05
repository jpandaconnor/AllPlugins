package co.uk.randompanda30.fileguilds.commands;

import co.uk.randompanda30.fileguilds.commands.handle.Command;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandList extends Command {

    public CommandList() {
        super("guild list", "", "Lists all the members in your guild", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.GUILDS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTINGUILD.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        int i = 0;
        for(String s : new ArrayList<>(guild.getMembers())) {
            i++;
            if(Bukkit.getServer().getPlayer(UUID.fromString(s)) == null) {
                continue;
            }
            Dispatch.sendMessage("&f" + Integer.toString(i) + ": %A" + (Bukkit.getServer().getPlayer(UUID.fromString(s)) != null ?
                    Bukkit.getServer().getPlayer(UUID.fromString(s)).getName() : "Null! Something went wrong"), player);
        }
        return true;
    }
}