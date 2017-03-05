package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDop implements CommandExecutor {

	public CMDop (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("op")) {
			if (s.hasPermission("api.owner") || s.isOp()) {
				if (args.length > 0) {
					Player toOp = Bukkit.getPlayer(args[0]);
					if (toOp != null) {
						toOp.setOp(true);
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§AYou have opped§F "
								+ toOp.getName().toString());
						toOp.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have been opped");
					} else {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ Text_Handlers.nullPlayer.toString());
					}
				} else {
					s.sendMessage(Text_Handlers.opSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.Btag.toString()
						+ "§COnly §FRandomPanda30 §Ccan use this command!");
			}
		} else {
			s.sendMessage(Text_Handlers.opSyntax.toString());
		}
		return true;
	}
}
