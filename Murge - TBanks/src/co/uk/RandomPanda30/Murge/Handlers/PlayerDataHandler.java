package co.uk.RandomPanda30.Murge.Handlers;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerDataHandler {
	public GameMode pGameMode;
	public ItemStack[] pInventory;
	public ItemStack[] pArmor;
	public Float pEXP;
	public Integer pEXPL;
	public Double pHealth;
	public Integer pFood;
	public Collection<PotionEffect> pPotionEffects;

	public PlayerDataHandler (GameMode pGameMode,
			ItemStack[] pInventory, ItemStack[] pArmor, Float pEXP,
			Integer pEXPL, Double pHealth, Integer pFood,
			Collection<PotionEffect> pPotionEffects) {
		this.pGameMode = pGameMode;
		this.pInventory = pInventory;
		this.pArmor = pArmor;
		this.pEXP = pEXP;
		this.pEXPL = pEXPL;
		this.pHealth = pHealth;
		this.pFood = pFood;
		this.pPotionEffects = pPotionEffects;
	}
}