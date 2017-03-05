package co.uk.RandomPanda30.KnightCrates.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Items.Crates;
import co.uk.RandomPanda30.KnightCrates.Util.Sender;

public class CommandCrate implements CommandExecutor {

	public KnightCrates plugin;

	public CommandCrate (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getCommand("crate").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Sender s = new Sender(sender);

		// if(args.length != 0) {
		// s.sendMessage("&C");
		// return true;
		// }

		if (!s.isPlayer()) {
			s.sendMessage(KnightCrates.tag
					+ "&CYou cannot do this command as console");
			return true;
		}

		if (!s.sendp.hasPermission("kc.crates")) {
			s.sendMessage(KnightCrates.tag
					+ "&CYou do not have permission to do this");
			return true;
		}

		s.sendp.getInventory().addItem(Crates.getCrate());
		s.sendMessage(KnightCrates.tag + "&AYou have been given a crate");
		return true;
	}
}