package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDslap implements CommandExecutor {

	public CMDslap(Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("slap"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					player.damage(0);
					player.sendMessage(Text_Handlers.Btag.toString()
							+ "§C*SMACK* Your face feels rather sore");
					s.sendMessage(Text_Handlers.Btag.toString()
							+ "§BYou have slapped §F"
							+ player.getName().toString()
							+ "§B right in the chops");
				} else {
					s.sendMessage(Text_Handlers.slapSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}

}
