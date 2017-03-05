package co.uk.RandomPanda30.SAO.Objects;

import org.bukkit.inventory.ItemStack;

public class DeathPackage {

    public ItemStack[] inventory;
    public int level;
    public float xp;

    public DeathPackage(ItemStack[] inventory, int level, float xp) {
        this.inventory = inventory;
        this.level = level;
        this.xp = xp;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public int getLevel() {
        return level;
    }

    public float getXP() {
        return xp;
    }
}