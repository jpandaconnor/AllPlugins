package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDdeop implements CommandExecutor {

	public CMDdeop (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("deop"))
			if (s.hasPermission("api.owner") || s.isOp()) {
				if (args.length > 0) {
					Player toDeop = Bukkit.getPlayer(args[0]);
					if (toDeop != null) {
						toDeop.setOp(false);
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§AYou have deopped§F "
								+ toDeop.getName().toString());
						toDeop.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have been deopped");
					} else {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ Text_Handlers.nullPlayer.toString());
					}
				} else {
					s.sendMessage(Text_Handlers.DeopSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.Btag.toString()
						+ "§COnly §FRandomPanda30 §Ccan use this command!");
			}
		return true;
	}

}
