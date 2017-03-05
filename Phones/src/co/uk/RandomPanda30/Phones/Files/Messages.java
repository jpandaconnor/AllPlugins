package co.uk.RandomPanda30.Phones.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.Phones.Phones;

public enum Messages {

	ARG ("&6"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&f"),
	TAG ("%A[%N" + Phones.pdfFile.getName() + "%A]%N"),
	WARNING ("&4"),

	@SuppressWarnings("serial")
	STARTUP_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG");
			add("%NNow being enabled...");
			add("%NMade by %A" + Phones.pdfFile.getAuthors().get(0));
		}
	}),

	@SuppressWarnings("serial")
	SHUTDOWN_MESSAGES (new ArrayList<String>() {
		{
			add("%TAG");
			add("%NNow being disabled...");
			add("%NMade by %A" + Phones.pdfFile.getAuthors().get(0));
		}
	}),

	ITEM_PHONE_NAME ("%NPhone"),
	ITEM_PHONE_MATERIAL ("SKULL_ITEM"),
	ITEM_PHONE_AMOUNT (1),
	@SuppressWarnings("serial")
	ITEM_PHONE_LORES (new ArrayList<String>() {
		{
			add("%NRight click to access");
			add("%Nthe phone menu");
		}
	}),

	PHONES_INVENTORY_MAINMENU_NAME ("%NMain menu!"),
	PHONES_INVENTORY_MAINMENU_ITEM_EXIT_NAME ("%EExit"),
	@SuppressWarnings("serial")
	PHONES_INVENTORY_MAINMENU_ITEM_EXIT_LORES (new ArrayList<String>() {
		{
			add("%EClick here to exit");
			add("%Ethe phone menu");
		}
	}),
	
	PHONES_INVENTORY_MAINMENU_ITEM_DETAILS_NAME("%A'playerName'"),

	PHONES_GIVENPHONE ("%TAG %NYou have been given a phone. Enjoy!"),

	CRITICAL_ALREADYCONTAINSPHONE ("%TAG %EError, you already have a phone in your inventory"),
	CRITICAL_PLAYERONLY ("%TAG %EError, this command can only be done by a player in-game"),
	CRITICAL_PLUGINDISABLED ("%TAG %EPhones has been disabled in the config"),
	CRITICAL_COMMANDNULL ("%TAG %EError, this command does not exist"),

	SYNTAX_PHONE ("%E/phones phone syntax: %A/phones phone");

	public Object value;

	Messages (Object value) {
		this.value = value;
	}

}
