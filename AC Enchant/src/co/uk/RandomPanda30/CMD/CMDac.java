package co.uk.RandomPanda30.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.ItemInventory.MainInventory;
import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.MiscText.NoPermText;

public class CMDac implements CommandExecutor {

	public CMDac(Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		Player player = (Player) s;
		if (cmd.getName().equalsIgnoreCase("ac"))
			if (s.hasPermission("ac.menu") || s.isOp()) {
				if (args.length >= 0) {
					MainInventory.mainMenu(player);
				}
			} else {
				player.sendMessage(NoPermText.noPerm);
			}
		return true;
	}
}