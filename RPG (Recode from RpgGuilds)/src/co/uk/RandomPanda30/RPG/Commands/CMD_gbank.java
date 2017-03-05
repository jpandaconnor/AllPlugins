package co.uk.RandomPanda30.RPG.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.BankH;

public class CMD_gbank implements CommandExecutor {

	public RPG plugin;

	public CMD_gbank (RPG plugin) {
		this.plugin = plugin;
	}

	BankH bank = new BankH(plugin);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("gbank")) {
			if (!(sender instanceof Player)) {
				plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
						.getStat(MessagesValues.ONLYCONSOLE), null);
				return true;
			}

			Player player = (Player) sender;
			RPG.gbanks.add(player.getUniqueId());
			return bank.bankCommands(sender, args);
		}
		return true;
	}
}