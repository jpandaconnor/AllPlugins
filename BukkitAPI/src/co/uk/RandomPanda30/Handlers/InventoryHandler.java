package co.uk.RandomPanda30.Handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHandler {

	public static void openTeleportMenu(Player player) {
		Inventory teleportMenu = Bukkit.createInventory(null, 9,
				Text_Handlers.teleportMenu.toString());

		// To hub
		ItemStack toHub = new ItemStack(Material.PORTAL);
		ItemMeta toHub_Im = toHub.getItemMeta();
		toHub_Im.setDisplayName(Text_Handlers.toHub.toString());
		toHub.setItemMeta(toHub_Im);

		teleportMenu.setItem(0, toHub);
		// To hub
		
		//To SG
		ItemStack toSG = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta toSG_Im = toSG.getItemMeta();
		toSG_Im.setDisplayName(Text_Handlers.toSG.toString());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Text_Handlers.sgLoreSpace.toString());
		lore.add(Text_Handlers.sgLore1.toString());
		lore.add(Text_Handlers.sgLore2.toString());
		lore.add(Text_Handlers.sgLore2C.toString());
		toSG_Im.setLore(lore);
		toSG.setItemMeta(toSG_Im);
		
		teleportMenu.setItem(2, toSG);
		
		player.openInventory(teleportMenu);
	}
}
