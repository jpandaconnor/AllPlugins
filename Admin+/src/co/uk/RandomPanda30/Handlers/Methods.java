package co.uk.RandomPanda30.Handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.AdminPlus.AdminPlus;

public class Methods {

	public static void sendCMDmessage(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

	public static void startupMessages(String[] messages) {
		for (String message : messages) {
			Methods.sendCMDmessage(message);
		}
	}

	public static void shutdownMessages(String[] messages) {
		for (String message : messages) {
			Methods.sendCMDmessage(message);
		}
	}

	public static String getAuthor(int authorInList) {
		String author = AdminPlus.pdfFile.getAuthors().get(authorInList);
		return author;
	}

	public static List<String> getAllAuthors() {
		List<String> authors = AdminPlus.pdfFile.getAuthors();
		return authors;
	}

	public static Inventory createInventory(String inventoryName, int rows) {
		int rowNumber = rows * 9;
		final Inventory inv = Bukkit.createInventory(null, rowNumber,
				inventoryName);

		return inv;
	}

	public static ItemStack createInvItem(String itemName, Material material, int itemAmount, String[] testLores) {
		ItemStack is = new ItemStack(material, itemAmount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(itemName);
		
		List<String> newLores = new ArrayList<String>();
		for(String lore : testLores) {
			newLores.add(lore);
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}

}
