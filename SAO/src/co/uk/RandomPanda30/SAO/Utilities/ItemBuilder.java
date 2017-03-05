package co.uk.RandomPanda30.SAO.Utilities;

import co.uk.RandomPanda30.SAO.Utilities.String.Format;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder {

    public static ItemStack createItem(String name, Material material, int amount, byte type, ArrayList<String> lores) {
        ItemStack is = new ItemStack(material, amount, type);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Format.format(name));
        ArrayList<String> lores2 = new ArrayList<>();
        for (String s : lores) {
            lores2.add(Format.format(s));
        }
        im.setLore(lores2);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack createItem(String name, Material material, int amount, byte type, String[] lores) {
        return createItem(name, material, amount, type, (ArrayList<String>) Arrays.asList(lores));
    }

    public static Material getType0(int id) {
        Material material = Material.getMaterial(id);
        return material == null ? Material.AIR : material;
    }

    public static Material getType(int id) {
        return getType0(id);
    }

    public static String getMaterialName(int id) {
        return getType(id).name();
    }
}