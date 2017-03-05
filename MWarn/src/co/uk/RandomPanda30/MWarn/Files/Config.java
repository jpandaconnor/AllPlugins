package co.uk.RandomPanda30.MWarn.Files;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	PLUGIN_ENABLE (true),

	CMD_WARN (true),
	CMD_CLEAR (true),
	CMD_REMOVE (true),

	CONSOLE_BANNED ("%EBanned by console!"),

	CLEARWARNINGS_ONUNBAN (true),

	WARN_NOREASON (true),

	BROADCAST_WARNING (true),
	BROADCAST_BAN (true),
	BROADCAST_UNBAN (true),
	BROADCAST_CLEARWARNINGS (true),
	BROADCAST_REMOVEWARNING (true),

	LANGUAGE ("EN"),

	WARNING_REASONS (new ArrayList<String>() {
		{
			add("Greifing");
			add("Being disrespectful");
		}
	}),

	WARNINGS_THRESHOLD (3),
	WARNINGS_BANLENGTH (2);

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}