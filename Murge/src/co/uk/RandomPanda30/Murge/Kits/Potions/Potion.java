package co.uk.RandomPanda30.Murge.Kits.Potions;

import org.bukkit.potion.PotionEffectType;

public class Potion {
	
	private String name;
	private PotionEffectType pe;

	public Potion (String name) {
		this.name = name;
	}

	public Potion getPotion() {
		return this;
	}

}