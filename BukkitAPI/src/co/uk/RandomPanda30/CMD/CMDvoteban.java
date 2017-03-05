package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDvoteban implements CommandExecutor {

	public CMDvoteban (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("voteban"))
			if (s.hasPermission("api.admin") || s.hasPermission("api.member")
					|| s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player != null) {
						if (Main.toBan.containsValue(player)) {
							s.sendMessage(Text_Handlers.Btag.toString()
									+ "§CPlayer is already in line to be kicked");
						}
						Main.toBan.put("toBan", player);
						Bukkit.broadcastMessage(Text_Handlers.Btag.toString()
								+ "§BPlayer §F"
								+ player.getName().toString()
								+ " §Bhas been put in line §Bto be banned, do §F/yban §Bto ban §F"
								+ player.getName().toString()
								+ "§B or do §F/nban §Bto stop him/her from being vote baned");
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have been reported for a vote ban by §F"
								+ s.getName().toString());
						int halfPlayers = Main.playersOnline.size();
						player.sendMessage(Text_Handlers.Btag.toString()
								+ "§C " + halfPlayers
								+ " §Cvotes will get you banned");

					}
				}
			}
		return true;
	}
}
