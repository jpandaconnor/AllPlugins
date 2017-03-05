package co.uk.RandomPanda30.SAdminO.Commands.List.NoArg;

import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;
import co.uk.RandomPanda30.SAdminO.SAdminO;
import co.uk.RandomPanda30.SAdminO.Util.Sender;
import co.uk.RandomPanda30.SAdminO.Util.System.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBan implements CommandExecutor {

    public SAdminO plugin;

    public CommandBan(SAdminO plugin) {
        this.plugin = plugin;
        plugin.getCommand("ban").setExecutor(this);
    }

	/*
     *
	 * TO DO -
	 * 
	 * Check if they're online, ban if so - if they're not online check they're
	 * in the system, ban - else mistake
	 * 
	 * Permissions
	 * 
	 * .ban <Player name> <time> <reason>
	 */

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Sender s = new Sender(sender);

        if (args.length != 2) {
            // Message here
        }

        String name = args[0];
        String time = args[1];
        String reason = "";

        for (int i = 2; i < args.length; i++) {
            String arg = args[i] + " ";
            reason = reason + arg;
        }

		/* Do all the null checks and stuff here */

        if (Bukkit.getPlayer(name) != null) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(name);
            BanUtil bu;

            String theyBanned = (String) MessagesValues.BAN_THEYBANNED.value.toString();
            theyBanned = theyBanned.replace("%player", name);
            theyBanned = theyBanned.replace("%time", time);
            theyBanned = theyBanned.replace("%reason", reason);

            if (op.isOnline()) {
                Player player = op.getPlayer();
                bu = new BanUtil(player, time, reason);
                if (!bu.isBanned()) {
                    bu.ban();
                } else {

                }
            } else {
                bu = new BanUtil(op.getUniqueId(), time, reason);
                if (!bu.isBanned()) {
                    bu.ban();
                } else {

                }
            }
        } else {
            // Null player here
        }

        return true;
    }
}