package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDban implements CommandExecutor {

	public CMDban (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("ban"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = (Bukkit.getPlayer(args[0]));
					if (player.isOnline()) {
						player.kickPlayer(Text_Handlers.banReason.toString());
						Bukkit.getConsoleSender().sendMessage(
								Text_Handlers.tag.toString() + "§F"
										+ player.getName().toString()
										+ "§C has been banned");
						player.setBanned(true);
					} else {
						s.sendMessage(Text_Handlers.Btag
								+ "Player is not online");
					}
				} else {
					s.sendMessage(Text_Handlers.banSyntax.toString());
				}
			} else {
				Text_Handlers.noPerm.toString();
			}
		return true;
	}
}