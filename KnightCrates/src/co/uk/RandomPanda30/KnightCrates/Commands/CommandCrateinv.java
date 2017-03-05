package co.uk.RandomPanda30.KnightCrates.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;
import co.uk.RandomPanda30.KnightCrates.Util.Sender;

public class CommandCrateinv implements CommandExecutor {

	public KnightCrates plugin;

	public CommandCrateinv (KnightCrates plugin) {
		this.plugin = plugin;
		plugin.getCommand("crateinv").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Sender s = new Sender(sender);
		if (args.length != 1) {
			s.sendMessage(KnightCrates.tag
					+ "&CError performing this command. Correct syntax: &A/crateinv iron/gold/diamond");
			return true;
		}

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

		Inventory inv = Bukkit.createInventory(null, 54,
				"Crate editor for: " + args[0]);

		switch (args[0]) {
		case "iron":
			if (plugin.getConfig().contains("Iron")) {
				@SuppressWarnings("unchecked")
				List<ItemStack> l = (List<ItemStack>) plugin.getConfig()
						.getList("Iron");
				for (ItemStack ls : l) {
					inv.addItem(ls);
				}
			}
			s.sendp.openInventory(inv);
			break;
		case "gold":
			if (plugin.getConfig().contains("Gold")) {
				@SuppressWarnings("unchecked")
				List<ItemStack> l = (List<ItemStack>) plugin.getConfig()
						.getList("Gold");
				for (ItemStack ls : l) {
					inv.addItem(ls);
				}
			}
			s.sendp.openInventory(inv);
			break;
		case "diamond":
			if (plugin.getConfig().contains("Diamond")) {
				@SuppressWarnings("unchecked")
				List<ItemStack> l = (List<ItemStack>) plugin.getConfig()
						.getList("Diamond");
				for (ItemStack ls : l) {
					inv.addItem(ls);
				}
			}
			s.sendp.openInventory(inv);
			break;
		}
		return true;
	}
}