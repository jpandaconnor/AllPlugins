package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Main.Main;

public class CMDcmdmute implements CommandExecutor {

	public CMDcmdmute (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("cmdmute"))
			if (s.hasPermission("api.admin") || s.isOp()) {
				if (args.length > 0) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player != null) {
						Main.cmdMuted.put("cmdMute", player);
					}

				}

			}
	}

}
