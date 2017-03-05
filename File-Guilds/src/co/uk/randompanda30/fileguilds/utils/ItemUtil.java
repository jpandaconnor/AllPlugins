package co.uk.randompanda30.fileguilds.utils;

import co.uk.randompanda30.fileguilds.string.Format;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static ItemStack makeItem(String name, Material material, int amount, byte type, List<String> lores) {
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

    public static boolean isSame(ItemStack item1, ItemStack item2) {
        return (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())
                && (item1.getType().equals(item2.getType())));
    }
}