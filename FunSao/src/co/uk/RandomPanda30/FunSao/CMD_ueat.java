package co.uk.RandomPanda30.FunSao;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.FunSao.Main.Sender;

public class CMD_ueat implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Sender s = new Sender(sender);
		if (args.length == 0) {
			s.sendMessage(Main.tag
					+ "&CError, you have use the command incorrectly. Usage: /ueat <Player name>");
			return true;
		}

		if (s.player) {
			if (!s.sendp.hasPermission("funsao.ueat")) {
				Main.sendMessage("&CHahahaha no", s.sendp);
				return true;
			}
		}

		if (Bukkit.getPlayer(args[0]) == null) {
			s.sendMessage("&CThis player is not online");
			return true;
		}

		String colour = "&";

		int max = 15;
		int min = 1;

		Random random = new Random();

		int col = random.nextInt((max - min) + 1) + min;

		switch (col) {
		case 1:
			colour += "0";
			break;
		case 2:
			colour += "1";
			break;
		case 3:
			colour += "3";
			break;
		case 4:
			colour += "4";
			break;
		case 5:
			colour += "5";
			break;
		case 6:
			colour += "6";
			break;
		case 7:
			colour += "7";
			break;
		case 8:
			colour += "8";
			break;
		case 9:
			colour += "9";
			break;
		case 10:
			colour += "a";
			break;
		case 11:
			colour += "b";
			break;
		case 12:
			colour += "c";
			break;
		case 13:
			colour += "d";
			break;
		case 14:
			colour += "e";
			break;
		case 15:
			colour += "f";
			break;
		}

		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
				colour + args[0] + colour + " has been eaten by " + (s.player
						? colour + s.sendp.getName() : colour + "console")));

		return true;
	}
}