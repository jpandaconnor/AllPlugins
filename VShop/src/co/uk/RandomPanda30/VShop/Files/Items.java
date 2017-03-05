package co.uk.RandomPanda30.VShop.Files;

import java.util.ArrayList;

public enum Items {

	ITEM_WAND_NAME ("%AVShop wand"),
	ITEM_WAND_AMOUNT (1),
	@SuppressWarnings("serial")
	ITEM_WAND_LORES (new ArrayList<String>() {
		{
			add("%ALeft %Nclick to select the first position");
			add("%ARight %Nclick to select the second position");
		}
	}),

	ITEM_EXITBLOCK_NAME ("%EExit"),
	@SuppressWarnings("serial")
	ITEM_EXITBLOCK_LORES (new ArrayList<String>() {
		{
			add("%NClick this item to exit");
		}
	}),

	ITEM_PLOTDETAILS_NAME ("%ADetails"),
	@SuppressWarnings("serial")
	ITEM_PLOTDETAILS_LORES (new ArrayList<String>() {
		{
			add("%NName: %A'plotname'");
			add("%NPrice: %A'price'");
		}
	}),

	ITEM_BUYPLOT_NAME ("%ABuy!"),

	ITEM_SELLPLOT_NAME ("%ESell"),
	@SuppressWarnings("serial")
	ITEM_SELLPLOT_LORES (new ArrayList<String>() {
		{
			add("%EWarning - This action cannot be undone");
			add("%EClick to sell your current shop space");
		}
	}),

	ITEM_UPDATETIME_NAME ("%AUpdate plot time"),
	@SuppressWarnings("serial")
	ITEM_UPDATETIME_LORES (new ArrayList<String>() {
		{
			add("%NYour plot has a 24 hour time limit.");
			add("%NYou must pay to keep you shop every 24 hours");
			add("%ATime%N: %A'tijd'");
			add("%AClick to update plot time!");
		}
	});

	public Object value;

	Items (Object value) {
		this.value = value;
	}

}
