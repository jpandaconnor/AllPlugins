package co.uk.RandomPanda30.CityRP.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Configs.Enums.Messages;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Resources.MessageUtil;

public class CommandHandler implements CommandExecutor {

	private static HashMap<String, CommandInterface> commands = new HashMap<>();

	public void register(String name, CommandInterface cmd) {
		commands.put(name, cmd);
	}

	public boolean exists(String name) {
		return commands.containsKey(name);
	}

	public CommandInterface getExecutor(String name) {
		return commands.get(name);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		org.bukkit.entity.Player player = null;
		if (sender instanceof org.bukkit.entity.Player) {
			player = (org.bukkit.entity.Player) sender;
			if (args.length == 0) {
				getExecutor("cityrp").onCommand(sender, cmd, label, args);
				return true;
			}

			if (args.length > 0) {
				if (exists(args[0])) {
					getExecutor(args[0]).onCommand(sender, cmd, label, args);
					return true;
				} else {
					Player no = CityRPData.getPlayerObject(player);
					MessageUtil.sendMessage(
							(String) Messages.COMMAND_NULL.value, no);
					return true;
				}
			}
		} else {
			MessageUtil.sendMessage((String) Messages.COMMAND_PLAYERONLY.value,
					null);
		}
		return false;
	}
}