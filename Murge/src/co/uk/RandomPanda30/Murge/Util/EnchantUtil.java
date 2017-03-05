package co.uk.RandomPanda30.Murge.Util;

import java.util.ArrayList;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import org.bukkit.inventory.ItemStack;

public class EnchantUtil {

	private static ArrayList<ItemStack> items = new ArrayList<>();

	public static ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack
				.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}

		if (tag == null) {
			tag = nmsStack.getTag();
		}

		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		items.add(item);
		return CraftItemStack.asCraftMirror(nmsStack);
	}

	public static ItemStack removeGlow(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack
				.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}

		if (tag == null) {
			tag = nmsStack.getTag();
		}

		tag.remove("ench");
		nmsStack.setTag(tag);
		if (items.contains(item)) {
			items.remove(item);
		}

		return CraftItemStack.asCraftMirror(nmsStack);
	}
}
