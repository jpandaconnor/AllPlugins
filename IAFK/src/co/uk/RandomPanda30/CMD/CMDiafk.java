package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.IAFK.Main;
import co.uk.RandomPanda30.Text.TextH;

public class CMDiafk implements CommandExecutor {

	public CMDiafk(Main plugin) {
		Main.plugin = plugin;
	}

	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("iafk")) {
			if (s.hasPermission("iafk.afkcmd")) {
				if (args.length >= 0) {
					Player player = (Player) s;

					if (Main.player.contains(player)) {
						player.sendMessage(TextH.alreadyInv);
					}

					World world = player.getWorld();
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();

					Location loc = new Location(world, x, y, z);

					player.sendMessage(TextH.nowInv);

					for (Player otherPlayers : Bukkit.getOnlinePlayers()) {
						otherPlayers.hidePlayer(player);
						player.hidePlayer(otherPlayers);
					}
				} else {
					s.sendMessage(TextH.iafkSyntax);
				}
			} else {
				s.sendMessage(TextH.noPerm);
			}
		}
		return true;
	}

}
