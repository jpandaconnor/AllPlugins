package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDunmute implements CommandExecutor {

	public CMDunmute(Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("unmute"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (Main.muted.containsValue(player)) {
						Main.muted.remove("mutedPlayer");
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have been unmuted");
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§BPlayer §F" + player.getName().toString()
								+ "§B has been unmuted");
					} else {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§CPlayer was never muted");
					}
				} else {
					s.sendMessage(Text_Handlers.unmuteSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}
}
