package co.uk.RandomPanda30.KnightCrates.Objects;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class Key {

	public ArrayList<ItemStack> items;

	public Key (ArrayList<ItemStack> items) {
		this.items = items;
	}
	
	public ArrayList<ItemStack> getItems() {
		return items;
	}
	
	public void setItems(ArrayList<ItemStack> items) {
		this.items = items;
	}
}