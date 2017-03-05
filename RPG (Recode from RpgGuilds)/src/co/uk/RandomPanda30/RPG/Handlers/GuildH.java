package co.uk.RandomPanda30.RPG.Handlers;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.RPG.RPG;

public class GuildH {

	public RPG plugin;

	public GuildH (RPG plugin) {
		this.plugin = plugin;
	}

	public static ItemStack[] rewards;

	public boolean inGuild(UUID uuid) {
		if (plugin.getRPGValues().getConfigC().contains(uuid.toString())) {
			return true;
		}
		return false;
	}

	public String getGuild(UUID uuid) {
		String guild = "";
		if (inGuild(uuid)) {
			guild = (String) plugin.getRPGValues().getConfigC()
					.get(uuid.toString() + ".Guild.Name");
		}
		return guild;
	}

	public String getRank(UUID uuid) {
		String rank = "";
		if (inGuild(uuid)) {
			rank = (String) plugin
					.getRPGValues()
					.getConfigC()
					.get("Guilds." + getGuild(uuid) + ".Players."
							+ uuid.toString() + ".Rank");
		}
		return rank;
	}

	public String getTitle(String guildName, String guildRank) {
		return "Guilds." + guildName + ".Ranks." + guildRank + ".Title";
	}

	public boolean hasPermission(String guildName, String rank, String object) {
		if ((Boolean) plugin.getRPGValues().getConfigC()
				.get("Guilds." + guildName + ".Ranks." + rank + "." + object)) {
			return true;
		}
		return false;
	}
}