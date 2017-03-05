package co.uk.randompanda30.playerrestrictions.item;

import org.bukkit.inventory.ItemStack;

/**
 * Created by panda on 01/06/16.
 */
public class StackComparison {

    public static boolean compare(ItemStack a, ItemStack b) {
        if(a == null || b == null) return false;
        if(a.getType() != b.getType()) return false;
        if(a.hasItemMeta() != b.hasItemMeta()) return false;
        // if(!a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName())) return false;
        if(a.hasItemMeta() && !a.getItemMeta().equals(b.getItemMeta())) return false;
        return true;
    }
}