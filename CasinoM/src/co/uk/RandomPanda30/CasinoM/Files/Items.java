package co.uk.RandomPanda30.CasinoM.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Items {

	SELECTIONWAND_NAME ("%ACasino %Nselection wand"),
	SELECTIONWAND_LORES (new ArrayList<String>() {
		{
			add("%ALeft %Nclick to select position 1");
			add("%ARight %Nclick to selection position 2");
			add("%Nafter making your selection, you can");
			add("%Nthen use commands");
		}
	});

	public Object value;

	Items (Object value) {
		this.value = value;
	}
}