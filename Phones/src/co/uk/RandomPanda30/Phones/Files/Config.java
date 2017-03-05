package co.uk.RandomPanda30.Phones.Files;

import java.util.ArrayList;

public enum Config {

	/**
	 * USER MODERATOR ADMIN OWNER
	 */

	PHONES_ENABLED (true),

	COMMAND_PHONE_ENABLED (true),

	@SuppressWarnings("serial")
	MAINMENU_DETAILS_SHOWSERVER_NAME (new ArrayList<String>() {
		{
			add("USER");
			add("MODERATOR");
			add("ADMIN");
			add("OWNER");
		}
	}),

	@SuppressWarnings("serial")
	MAINMENU_DETAILS_SHOWSERVER_IP (new ArrayList<String>() {
		{
			add("USER");
			add("MODERATOR");
			add("ADMIN");
			add("OWNER");
		}
	}),

	@SuppressWarnings("serial")
	MAINMENU_DETAILS_SHOWPLAYER_UUID (new ArrayList<String>() {
		{
			add("USER");
			add("MODERATOR");
			add("ADMIN");
			add("OWNER");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}

}
