package co.uk.RandomPanda30.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Handlers.MessageH;
import co.uk.RandomPanda30.Markets.Main;

public class Command_Wand implements CommandExecutor {

	public Command_Wand (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String args[]) {
		if (cmd.getName().equalsIgnoreCase("mk")) {
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("wand")) {
					Player player = (Player) sender;
					if (player != null) {
						ItemStack wand = new ItemStack(Material.STICK);
						ItemMeta wand_im = wand.getItemMeta();
						wand_im.setDisplayName(MessageH.wandName);
						String[] lores = MessageH.lores;
						List<String> lores2 = new ArrayList<String>();
						for(String lore : lores) {
							lores2.add(lore);
						}
						wand_im.setLore(lores2);
						wand.setItemMeta(wand_im);

						player.getInventory().addItem(wand);

					}
				}
			}
		}
		return true;
	}

}
