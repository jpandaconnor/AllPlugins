package co.uk.RandomPanda30.RPG.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class InventoryH {

	private static InventoryH instance = new InventoryH();

	public static InventoryH getInstance() {
		return instance;
	}

	public Inventory deSerializeInventory(String string) {
		String[] blocks = string.split(";");
		Inventory custom = Bukkit.getServer().createInventory(null,
				Integer.valueOf(blocks[0]).intValue(), "Bank:");
		for (int i = 1; i < blocks.length; i++) {
			String[] block = blocks[i].split("#");
			if (Integer.valueOf(block[0]).intValue() < custom.getSize()) {
				ItemStack is = null;
				String[] itmStack = block[1].split(":");
				for (String info : itmStack) {
					String[] itmAttrib = info.split("@");
					if (itmAttrib[0].equals("t")) {
						is = new ItemStack(Material.getMaterial(Integer
								.valueOf(itmAttrib[1]).intValue()));
					} else if ((itmAttrib[0].equals("d")) && (is != null)) {
						is.setDurability(Short.valueOf(itmAttrib[1])
								.shortValue());
					} else if ((itmAttrib[0].equals("a")) && (is != null)) {
						is.setAmount(Integer.valueOf(itmAttrib[1]).intValue());
					} else if ((itmAttrib[0].equals("e")) && (is != null)) {
						is.addUnsafeEnchantment(Enchantment.getById(Integer
								.valueOf(itmAttrib[1]).intValue()), Integer
								.valueOf(itmAttrib[2]).intValue());
					} else if ((itmAttrib[0].equals("n")) && (is != null)) {
						is = setItemName(is, itmAttrib[1], null);
					} else if ((itmAttrib[0].equals("l")) && (is != null)) {
						List<String> tmp = new ArrayList();
						for (int x = 1; x < itmAttrib.length; x++) {
							tmp.add(itmAttrib[x]);
						}
						is = setItemName(is, null, tmp);
					}
				}
				custom.setItem(Integer.valueOf(block[0]).intValue(), is);
			}
		}
		return custom;
	}

	public String serializeInventory(Inventory inventory) {
		String inv = inventory.getSize() + ";";
		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack is = inventory.getItem(i);
			if (is != null) {
				String serialItemStack = new String();
				serialItemStack = serialItemStack + "t@"
						+ String.valueOf(is.getType().getId());
				if (is.getDurability() != 0) {
					serialItemStack = serialItemStack + ":d@"
							+ String.valueOf(is.getDurability());
				}

				if (is.getAmount() != 1) {
					serialItemStack = serialItemStack + ":a@"
							+ String.valueOf(is.getAmount());
				}

				if (is.getEnchantments().size() != 0) {
					for (Map.Entry<Enchantment, Integer> entry : is
							.getEnchantments().entrySet()) {
						serialItemStack = serialItemStack + ":e@"
								+ ((Enchantment) entry.getKey()).getId() + "@"
								+ entry.getValue();
					}
				}

				if (is.getItemMeta().hasDisplayName()) {
					serialItemStack = serialItemStack + ":n@"
							+ is.getItemMeta().getDisplayName();
				}

				if (is.getItemMeta().hasLore()) {
					serialItemStack = serialItemStack + ":l";
					for (String str : is.getItemMeta().getLore()) {
						serialItemStack = serialItemStack + "@" + str;
					}
				}
				inv = inv + i + "#" + serialItemStack + ";";
			}
		}
		return inv;
	}

	private ItemStack setItemName(ItemStack itm, String name, List<String> lore) {
		ItemMeta im = itm.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}

		if (lore != null) {
			im.setLore(lore);
		}

		itm.setItemMeta(im);
		return itm;
	}
}