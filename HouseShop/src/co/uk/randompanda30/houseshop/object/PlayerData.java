package co.uk.randompanda30.houseshop.object;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

/**
 * Created by panda on 02/05/16.
 */
public class PlayerData {

    GameMode gamemode;

    ItemStack[] inventory;
    ItemStack[] armour;

    float exp;
    int level;

    double health;
    int food;

    Collection<PotionEffect> effects;

    boolean allowFlight;
    boolean flying;

    public PlayerData(Player player) {
        this.gamemode = player.getGameMode();
        this.inventory = player.getInventory().getContents();
        this.armour = player.getInventory().getArmorContents();
        this.exp = player.getExp();
        this.level = player.getLevel();
        this.health = player.getHealth();
        this.food = player.getFoodLevel();
        this.effects = player.getActivePotionEffects();
        this.allowFlight = player.getAllowFlight();
        this.flying = player.isFlying();
    }
}