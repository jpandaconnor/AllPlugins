package co.uk.RandomPanda30.RPG.Files.Config.Enums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum ConfigDefault {

	// All the list of default config objects will go in here

	TEST ("TEST"),
	TP (true),
	Rewards (new ArrayList<String>() {
		{

		}
	}),
	RewardDelay (10),
	GuildPrefixes (true),
	Chat (true),
	NoBuild (true),
	Signs (new ArrayList<String>() {
		{

		}
	}),
	VAULT (true);

	public Object value;

	ConfigDefault (Object value) {
		this.value = value;
	}
}