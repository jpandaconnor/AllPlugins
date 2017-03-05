package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDth implements CommandExecutor {

	public CMDth (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("th"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player p = Bukkit.getPlayer(args[0]);
					if (p != null) {
						Player player = (Player) s;
						p.teleport(player.getLocation());
						p.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have been teleported to §F"
								+ player.getName().toString());
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou teleported §F"
								+ p.getName().toString() + "§B to you");
					} else {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§Cplayer is null");
					}
				} else {
					s.sendMessage(Text_Handlers.Btag.toString()
							+ Text_Handlers.teleportHereSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}
}
