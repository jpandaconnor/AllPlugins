package co.uk.RandomPanda30.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.InventoryHandler;
import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.Text.TextH;

public class CMDac implements CommandExecutor {

	public CMDac (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		Player player = (Player) s;
		if (cmd.getName().equalsIgnoreCase("ac"))
			if (s.hasPermission("ac.ui") || s.isOp()) {
				if (args.length >= 0) {
					InventoryHandler.mainMenu(player);
				}
			} else {
				s.sendMessage(TextH.noPerm.toString());
			}
		return true;
	}

}
