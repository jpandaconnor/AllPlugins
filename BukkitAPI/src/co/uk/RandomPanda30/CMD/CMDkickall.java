package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDkickall implements CommandExecutor {

	public CMDkickall (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (s.hasPermission("api.admin") || s.isOp()) {
			if (cmd.getName().equalsIgnoreCase("kickall")) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.kickPlayer(Text_Handlers.Btag
							+ "§CEveryone has been kicked, try rejoining soon");
					Bukkit.getConsoleSender().sendMessage(
							Text_Handlers.tag.toString()
									+ "§CThe command /kickall has been used");
				}
			}
		} else {
			s.sendMessage(Text_Handlers.noPerm.toString());
		}
		return true;
	}
}
