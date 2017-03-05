package co.uk.RandomPanda30.Phones.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import co.uk.RandomPanda30.Phones.Files.Messages;
import co.uk.RandomPanda30.Phones.Methods.PhonesMethods;

public class PhoneI {

	@SuppressWarnings("unchecked")
	public static ItemStack phoneI() {
		ItemStack is = new ItemStack(
				PhonesMethods
						.checkMaterialFromString((String) Messages.ITEM_PHONE_MATERIAL.value),
				(Integer) Messages.ITEM_PHONE_AMOUNT.value);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(PhonesMethods
				.formatMessage((String) Messages.ITEM_PHONE_NAME.value));
		List<String> lores = (List<String>) Messages.ITEM_PHONE_LORES.value;
		ArrayList<String> newLores = new ArrayList<String>();
		for (String lore : lores) {
			newLores.add(PhonesMethods.formatMessage(lore));
		}
		im.setLore(newLores);
		is.setItemMeta(im);
		return is;
	}
}
