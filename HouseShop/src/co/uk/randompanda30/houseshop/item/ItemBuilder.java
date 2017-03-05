package co.uk.randompanda30.houseshop.item;

import co.uk.randompanda30.houseshop.string.Format;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by panda on 02/05/16.
 */
public class ItemBuilder {

    public static ItemStack buildItem(String name, Material material, int amount, int type, ArrayList<String> lores) {
        ItemStack is = new ItemStack(material, amount, (short) type);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Format.format(name));
        ArrayList<String> lores2 = lores.stream().map(Format::format).collect(Collectors.toCollection(ArrayList::new));
        im.setLore(lores2);
        is.setItemMeta(im);
        return is;
    }
}