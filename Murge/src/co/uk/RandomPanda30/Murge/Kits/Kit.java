package co.uk.RandomPanda30.Murge.Kits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class Kit {

	private String name;
	private ItemStack[] items;
	private ItemStack[] armour;
	private ArrayList<PotionEffect> effects;
	private String permSuffix;
	private String cooldown;
	private boolean starter;
	private String uk;

	public Kit (String name, ItemStack[] items, ItemStack[] armour,
			ArrayList<PotionEffect> effects, String suffix, String cooldown,
			String key, boolean starter) {
		this.setName(ChatColor.stripColor(name));
		this.setItems(items);
		this.setArmour(armour);
		this.setEffects(effects);
		this.setPermSuffix(suffix);
		this.setCooldown(cooldown);
		this.setUk(ChatColor.stripColor(key));
		this.setStarter(starter);
	}

	public void give(Player player) {
		if (player.hasPermission("murge." + permSuffix)) {

			int space = 0;
			for (ItemStack content : player.getInventory().getContents()) {
				if (content == null) {
					space++;
				}
			}

			if ((items.length + armour.length) < space) {
				player.getInventory().addItem(items);
				player.getInventory().addItem(armour);

				if (!effects.isEmpty()) {
					for (PotionEffect effect : effects) {
						player.addPotionEffect(effect);
					}
				}
				reset(player);
				// Message here
			}
		} else {
			StringMethods.sendMessage(
					(String) Murge.mMap.getStat(MessagesValues.NOPERMISSION),
					player);
			return;
		}
	}

	public void reset(Player player) {
		String[] splitter = { cooldown };
		long days = 0;
		long hours = 0;
		long minutes = 0;
		long seconds = 0;

		long length = 0;

		if (cooldown.contains("d")) {
			splitter = splitter[0].split("d");
			days = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (cooldown.contains("h")) {
			splitter = splitter[0].split("h");
			hours = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (cooldown.contains("m")) {
			splitter = splitter[0].split("m");
			minutes = new Long(splitter[0]);
			if (splitter.length == 2) {
				splitter[0] = splitter[1];
			}
		}

		if (cooldown.contains("s")) {
			splitter = splitter[0].split("s");
			seconds = new Long(splitter[0]);
		}

		length = Calendar.getInstance().getTimeInMillis()
				+ (days * 1000 * 24 * 60 * 60) + (hours * 1000 * 60 * 60)
				+ (minutes * 1000 * 60) + (seconds * 1000);
		Murge.cso.getDataC().set(
				player.getUniqueId().toString() + ".kit." + name, length);
		try {
			Murge.cso.getDataC().save(Murge.cso.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void remove() {
		Murge.cso.getKitsC().set(getName(), null);
		try {
			Murge.cso.getKitsC().save(Murge.cso.getKits());
		} catch (IOException e) {
			e.printStackTrace();
		}
		KitTemp.removeKit(this);
	}

	public void save() {
		if (items != null) {
			Murge.cso.getKitsC().set(name + ".items", items);
		} else {
			Murge.cso.getKitsC().set(name + ".items",
					new ArrayList<ItemStack>());
		}

		if (armour != null) {
			Murge.cso.getKitsC().set(name + ".armour", armour);
		} else {
			Murge.cso.getKitsC().set(name + ".armour",
					new ArrayList<ItemStack>());
		}

		if (effects != null) {
			Murge.cso.getKitsC().set(name + ".effects", effects);
		} else {
			Murge.cso.getKitsC().set(name + ".effects",
					new ArrayList<ItemStack>());
		}

		Murge.cso.getKitsC().set(name + ".suffix", name);
		Murge.cso.getKitsC().set(name + ".cooldown", cooldown);
		Murge.cso.getKitsC().set(name + ".uk", uk);
		try {
			Murge.cso.getKitsC().save(Murge.cso.getKits());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ItemStack[] getItems() {
		return items;
	}

	public void setItems(ItemStack[] items) {
		this.items = items;
	}

	public ItemStack[] getArmour() {
		return armour;
	}

	public void setArmour(ItemStack[] armour) {
		this.armour = armour;
	}

	public ArrayList<PotionEffect> getEffects() {
		return effects;
	}

	public void setEffects(ArrayList<PotionEffect> effects) {
		this.effects = effects;
	}

	public String getPermSuffix() {
		return permSuffix;
	}

	public void setPermSuffix(String permSuffix) {
		this.permSuffix = permSuffix;
	}

	public String getCooldown() {
		return cooldown;
	}

	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
	}

	public String getUk() {
		return uk;
	}

	public void setUk(String uk) {
		this.uk = uk;
	}

	public boolean isStarter() {
		return starter;
	}

	public void setStarter(boolean starter) {
		this.starter = starter;
	}
}