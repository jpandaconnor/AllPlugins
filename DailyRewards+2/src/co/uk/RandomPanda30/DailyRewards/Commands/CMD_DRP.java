package co.uk.RandomPanda30.DailyRewards.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DailyRewards.DR;
import co.uk.RandomPanda30.DailyRewards.Menus.MainMenu;
import co.uk.RandomPanda30.DailyRewards.Utils.SenderI;

public class CMD_DRP implements CommandExecutor {

	public DR plugin;

	public CMD_DRP (DR plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		SenderI s = new SenderI(sender);

		if (s.isPlayer()) {
			Player player = s.getPlayer();

			if (!player.hasPermission("dr.admin")) {
				MainMenu.openPlayerMenu(player);
			} else {
				MainMenu.openAdminMenu(player);
			}
		} else {
			s.sendMessage("&CYou cannot do this command ");
		}
		return true;
	}

}