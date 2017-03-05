package co.uk.RandomPanda30.Murge.Kits;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Items.DefaultItems;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

public class KitEditor implements Listener {

	private Kit kit;
	private Player player;

	@SuppressWarnings("unused")
	private Murge plugin;

	public KitEditor (Murge plugin) {
		this.plugin = plugin;
	}

	public KitEditor (Kit kit, Player player) {
		this.kit = kit;
		this.player = player;
	}

	public void open() {
		Inventory menu = Bukkit.createInventory(null, 9, "Edit kit");
		mainMenuItems(menu);
		this.player.openInventory(menu);
	}

	public void items() {
		Inventory menu = Bukkit
				.createInventory(null, 54, "Drag and drop items");
		reset(menu);
		defaultEditItems(menu);
		menu.addItem(kit.getItems());
		this.player.openInventory(menu);
	}

	public void armour() {
		Inventory menu = Bukkit.createInventory(null, 54,
				"Drag and drop armour");
		reset(menu);
		defaultEditItems(menu);
		menu.addItem(kit.getArmour());
		this.player.openInventory(menu);
	}

	public Kit getKit() {
		return kit;
	}

	public Player getPlayer() {
		return player;
	}

	public void setItems(ItemStack[] items) {
		this.kit.setItems(items);
	}

	public void setArmour(ItemStack[] armour) {
		this.kit.setArmour(armour);
	}

	public void add() {
		KitTemp.addEditingKits(this);
	}

	public void remove() {
		KitTemp.removeEditingKits(this);
	}

	private void mainMenuItems(Inventory inventory) {
		inventory.setItem(0, obtainEditItems());
		inventory.setItem(1, obtainEditArmour());
		inventory.setItem(2, obtainEditPotionEffects());
		inventory.setItem(7, DefaultItems.obtainBack());
		inventory.setItem(8, DefaultItems.obtainExit());
	}

	private void defaultEditItems(Inventory inventory) {
		inventory.setItem(52, DefaultItems.obtainBack());
		inventory.setItem(53, DefaultItems.obtainExit());
	}

	private void reset(Inventory inventory) {
		inventory.clear();
	}

	public ItemStack obtainEditItems() {
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TClick here to edit the current items for");
		lores.add("%Tthis kit");
		return ItemMethods.createItem("%NEdit: %AItems", Material.STONE_BUTTON,
				1, 0, lores);
	}

	public ItemStack obtainEditArmour() {
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TClick here to edit the current armour for");
		lores.add("%Tthis kit");
		return ItemMethods.createItem("%NEdit: %AArmour",
				Material.STONE_BUTTON, 1, 0, lores);
	}

	public ItemStack obtainEditPotionEffects() {
		ArrayList<String> lores = new ArrayList<String>();
		lores.add("%TClick here to edit the current potion effects");
		lores.add("%Tfor this kit");
		return ItemMethods.createItem("%NEdit: %APotion effects",
				Material.STONE_BUTTON, 1, 0, lores);
	}
}