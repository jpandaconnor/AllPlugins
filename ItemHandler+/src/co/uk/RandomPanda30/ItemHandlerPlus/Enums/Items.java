package co.uk.RandomPanda30.ItemHandlerPlus.Enums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Items {

	EXITBUTTON_NAME ("%BExit"),
	BACKBUTTON_NAME ("%NGo back"),

	CLOCKNAME_NAME ("%AServer list"),
	CLOCKNAME_LORES (new ArrayList<String>() {
		{

		}
	}),

	PAPERDETAILS_NAME ("%AItemHandler+"),
	PAPERDETAILS_LORES (new ArrayList<String>() {
		{
			add("%NUse this menu to add hotbar");
			add("%Nrestrictions and item restrictions");
			add("%Nto all players");
		}
	}),

	HOTBARRESTRICTION_NAME ("%AHotbar Restrictions"),
	HOTBARRESTRICTION_LORES (new ArrayList<String>() {
		{
			add("%NClick to access the hotbar");
			add("%Nrestriction menu");
		}
	}),

	ITEMDROPRESTRICTION_NAME ("%AItem drop restrictions"),
	ITEMDROPRESTRICTION_LORES (new ArrayList<String>() {
		{
			add("%NClick to access the item drop menu");
			add("%NDrag and drop any items you want");
			add("%Nrestricted in this menu");
		}
	}),

	TEST ("TEST");

	public Object value;

	Items (Object value) {
		this.value = value;
	}

}
