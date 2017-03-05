package co.uk.randompanda30.fileguilds.commands.nohandle;

import co.uk.randompanda30.fileguilds.Guilds;
import co.uk.randompanda30.fileguilds.config.Messages;
import co.uk.randompanda30.fileguilds.data.PlayerData;
import co.uk.randompanda30.fileguilds.object.GuildOB;
import co.uk.randompanda30.fileguilds.object.Sender;
import co.uk.randompanda30.fileguilds.string.Dispatch;
import co.uk.randompanda30.fileguilds.string.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandAlChat implements CommandExecutor {

    Guilds plugin;

    public CommandAlChat(Guilds plugin) {
        this.plugin = plugin;
        plugin.getCommand("al").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Sender s = new Sender(sender);

        if (!s.isPlayer) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NOTFORCONSOLE.value, null);
            return true;
        }

        Player player = s.getPlayer();
        if (!PlayerData.isInGuild(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        GuildOB guild = new GuildOB(PlayerData.getGuild(player));

        if (!guild.getRank(PlayerData.getRank(player)).canAChat()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.GUILDS_NORANKPERM.value, player);
            return true;
        }

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            buffer.append(' ').append(args[i]);
        }

        String message = buffer.toString();
        if(guild.getAllies() != null) {
            for (String ally : guild.getAllies()) {
                GuildOB target = new GuildOB(ally);
                for(String uuid : target.getMembers()) {
                    if(Bukkit.getServer().getPlayer(UUID.fromString(uuid)) != null &&
                            Bukkit.getServer().getPlayer(UUID.fromString(uuid)).isOnline()) {
                        Dispatch.sendMessage(Format.format("&6&lAlly chat> &c&l" + guild.getName() +
                                "&f, &a&l" + player.getName() + "&f:" + message), Bukkit.getServer().getPlayer
                                (UUID.fromString(uuid)));
                    }
                }
            }
        }
        return true;
    }

}