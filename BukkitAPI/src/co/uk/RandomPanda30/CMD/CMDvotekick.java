package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDvotekick implements CommandExecutor {

	public CMDvotekick (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("votekick"))
			if (s.hasPermission("api.admin") || s.hasPermission("api.member")
					|| s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player != null) {
						if (Main.toKick.containsValue(player)) {
							s.sendMessage(Text_Handlers.Btag.toString()
									+ "§CPlayer is already in line to be kicked");
						}
						Main.toKick.put("toKick", player);
						Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
								+ "§BPlayer §F"
								+ player.getName().toString()
								+ " §Bhas been put in line §Bto be kicked, do §F/ykick §Bto kick §F"
								+ player.getName().toString()
								+ "§B or do §F/nkick §Bto stop him/her from being vote kicked");
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have been reported for a vote kick by §F"
								+ s.getName().toString());
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§C10 votes will get you kicked");
					} else {
						s.sendMessage(Text_Handlers.nullPlayer.toString());
					}
				} else {
					s.sendMessage(Text_Handlers.voteKicksyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;

	}
}
