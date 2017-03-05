package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.SoundCheck;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDdistress implements CommandExecutor {

	public CMDdistress (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("distress")) {
			if (s.hasPermission("api.owner") || s.hasPermission("api.admin")
					|| s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player != null) {
						if (!Main.distressSound.containsValue(player)) {
							Main.distressSound.put("player", player);
							player.sendMessage(Text_Handlers.Btag.toString()
									+ "§CYou are being spammed with sound");
							s.sendMessage(Text_Handlers.Btag.toString()
									+ "§BYou have started spamming §F"
									+ player.getName().toString()
									+ " §Bwith sound");
							SoundCheck.soundCheck(player);
						} else {
							s.sendMessage(Text_Handlers.Btag.toString()
									+ "§BYou have stopped spamming §F"
									+ player.getName().toString()
									+ " §Bwith sound");
							player.sendMessage(Text_Handlers.Btag.toString()
									+ "§BYou have now stopped being§B spammed with sound");
							Main.distressSound.clear();
						}
					} else {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ Text_Handlers.nullPlayer.toString());
					}
				} else {
					s.sendMessage(Text_Handlers.distressSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		}
		return true;
	}
}
