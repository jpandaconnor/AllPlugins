package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDmute implements CommandExecutor {

	public CMDmute(Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("mute"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (Main.muted.containsValue(player)) {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§CPlayer is already muted");
					} else {
						Main.muted.put("mutedPlayer", player);
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have been muted");
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§BPlayer §F" + player.getName().toString()
								+ "§B has been muted");
					}
				} else {
					s.sendMessage(Text_Handlers.muteSyntax.toString());
				}
			}else{
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}
}
