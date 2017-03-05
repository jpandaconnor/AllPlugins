package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDkill implements CommandExecutor {

	public CMDkill (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("kill"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player != null) {
						player.setHealth(0);
						Bukkit.getConsoleSender()
								.sendMessage(
										Text_Handlers.tag.toString()
												+ "§B"
												+ player.getName().toString()
												+ " §Chas been killed using the /kill command by §B"
												+ s.getName().toString());
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have been killed");
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§AYou have killed §B"
								+ player.getName().toString());
					} else {
						s.sendMessage(Text_Handlers.nullPlayer.toString());
					}
				} else {
					s.sendMessage(Text_Handlers.Btag.toString()
							+ Text_Handlers.killSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}
}
