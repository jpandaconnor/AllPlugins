package co.uk.RandomPanda30.Phones.Methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Phones.Files.Config;
import co.uk.RandomPanda30.Phones.Files.Messages;

public class PhonesMethods {
	public static void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender()
				.sendMessage(formatMessage(message.toString()));
	}

	public static void broadcastMessage(String message) {
		Bukkit.broadcastMessage(formatMessage(message.toString()));
	}

	public static void sendPlayerMessage(Player player, String message) {
		player.sendMessage(formatMessage(message.toString()));
	}

	public static String formatMessage(String string) {
		return string.replaceAll("%TAG", (String) Messages.TAG.value)
				.replaceAll("%N", (String) Messages.NORMAL.value)
				.replaceAll("%W", (String) Messages.WARNING.value)
				.replaceAll("%E", (String) Messages.ERROR.value)
				.replaceAll("%A", (String) Messages.ARG.value)
				.replaceAll("%H", (String) Messages.HEADER.value)
				.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2")
				.replaceAll("&u", "\n");
	}

	public static Material checkMaterialFromString(String materialString) {
		String newMaterial = materialString.toUpperCase().replaceAll(" ", "_");
		Material material = Material.getMaterial(newMaterial);
		if (material == null) {
			return null;
		}
		return material;
	}

	@SuppressWarnings("unchecked")
	public static void announceStartUpMessages() {
		ArrayList<String> messages = (ArrayList<String>) Messages.STARTUP_MESSAGES.value;
		for (String message : messages) {
			PhonesMethods.sendConsoleMessage(message);
		}
	}

	@SuppressWarnings("unchecked")
	public static void announceShutDownMessages() {
		ArrayList<String> messages = (ArrayList<String>) Messages.SHUTDOWN_MESSAGES.value;
		for (String message : messages) {
			PhonesMethods.sendConsoleMessage(message);
		}
	}

	public static ItemStack createItem(String itemName, int itemAmount,
			int itemType, Material material, ArrayList<String> lores) {
		ItemStack is = new ItemStack(material, itemAmount, (byte) itemType);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(PhonesMethods.formatMessage(itemName));
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(PhonesMethods.formatMessage(lore));
		}

		im.setLore(newLores);
		is.setItemMeta(im);

		return is;
	}

}
