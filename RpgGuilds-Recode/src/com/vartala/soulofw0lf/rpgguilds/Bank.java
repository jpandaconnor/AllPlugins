package com.vartala.soulofw0lf.rpgguilds;

import org.bukkit.inventory.Inventory;

public class Bank {

	private Inventory inv;

	public Bank (String name) {
		loadBank(name, this.inv);
	}

	public static void loadBank(String name, Inventory inven) {
	}

	public static void saveBank(String name, Inventory inven) {
	}
}