package co.uk.RandomPanda30.Murge.Collection.Player;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Kits.Kit;
import co.uk.RandomPanda30.Murge.Kits.KitTemp;
import co.uk.RandomPanda30.Murge.Methods.ItemMethods;

@SuppressWarnings("unchecked")
public class KitsCollection {

	private static KitsCollection instance = new KitsCollection();

	public static KitsCollection getCollection() {
		return instance;
	}

	public void load() {
		Kit kit = null;
		for (String s : getKits()) {
			kit = new Kit(s, getItems(s), getArmour(s), getPotions(s),
					getSuffix(s), getCooldown(s), getKey(s), getStarter(s));
			KitTemp.addKit(kit);
		}
	}

	public void save() {
		for (Kit kit : KitTemp.getKits()) {
			kit.save();
		}
	}

	public String getSuffix(String kit) {
		return (String) Murge.cso.getKitsC().get(kit + ".suffix");
	}

	public String getCooldown(String kit) {
		return (String) Murge.cso.getKitsC().get(kit + ".cooldown");
	}

	public String getKey(String kit) {
		return (String) Murge.cso.getKitsC().get(kit + ".uk");
	}

	public ArrayList<String> getKits() {
		ArrayList<String> kits = new ArrayList<String>();
		for (String s : Murge.cso.getKitsC().getConfigurationSection("")
				.getKeys(false)) {
			kits.add(s);
		}
		return kits;
	}

	public boolean getStarter(String kit) {
		return (Boolean) Murge.cso.getKitsC().get(kit + ".starter");
	}

	public ItemStack[] getItems(String kit) {
		ArrayList<ItemStack> items = (ArrayList<ItemStack>) Murge.cso
				.getKitsC().get(kit + ".items");
		if (items != null) {
			ItemStack[] i = new ItemStack[items.size()];
			return items.toArray(i);
		}
		return null;
	}

	public ItemStack[] getArmour(String kit) {
		ArrayList<ItemStack> items = (ArrayList<ItemStack>) Murge.cso
				.getKitsC().get(kit + ".armour");
		if (items != null) {
			ItemStack[] i = new ItemStack[items.size()];
			return items.toArray(i);
		}
		return null;
	}

	public ArrayList<PotionEffect> getPotions(String kit) {
		ArrayList<PotionEffect> potions = (ArrayList<PotionEffect>) Murge.cso
				.getKitsC().get(kit + ".potions");
		if (potions != null) {
			return potions;
		}
		return null;
	}

	public boolean canUse(String kit, Player player) {
		if (!player.hasPermission("murge." + kit)) {
			return false;
		}
		return true;
	}

	public ArrayList<ItemStack> getKitAsObjects() {
		ArrayList<ItemStack> papers = new ArrayList<ItemStack>();
		String name = "";
		for (Kit kit : KitTemp.getKits()) {
			name = "%NName: %A" + kit.getName();
			ItemStack is = ItemMethods.createItem(name, Material.PAPER, 1, 0,
					new ArrayList<String>());
			papers.add(is);
		}
		return papers;
	}
}