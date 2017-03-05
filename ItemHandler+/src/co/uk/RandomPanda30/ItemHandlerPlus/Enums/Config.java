package co.uk.RandomPanda30.ItemHandlerPlus.Enums;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

@SuppressWarnings("serial")
public enum Config {

	SLOT_1 (true),
	SLOT_2 (true),
	SLOT_3 (true),
	SLOT_4 (true),
	SLOT_5 (true),
	SLOT_6 (true),
	SLOT_7 (true),
	SLOT_8 (true),
	SLOT_9 (true),

	GIVECLOCK_GIVE (true),
	GIVECLOCK_MENU ("example.yml"),

	INVENTORY (new ArrayList<ItemStack>() {
		{

		}
	}),

	TEST ("TEST");

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}