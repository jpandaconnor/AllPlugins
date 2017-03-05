package co.uk.RandomPanda30.ERRanks.Enums;

import java.util.ArrayList;

@SuppressWarnings("serial")
public enum Config {

	RANKSLIST (new ArrayList<String>() {
		{
			add("builder");
			add("player");
			add("staff");
		}
	}),

	RANKS_DEFAULT_TAG ("&2%name: &f"),

	RANKS_BUILDER_TAG ("&r[&5Builder&r] &e%name: &f"),
	RANKS_BUILDER_PERMISSION ("tag.builder"),

	RANKS_PLAYER_TAG ("&r[&5Player+&r] &e%name: &f"),
	RANKS_PLAYER_PERMISSION ("tag.player"),

	RANKS_STAFF_TAG ("&r[&eStaff&r] &9%name: &f"),
	RANKS_STAFF_PERMISSION ("tag.staff");

	public Object value;

	Config (Object value) {
		this.value = value;
	}
}