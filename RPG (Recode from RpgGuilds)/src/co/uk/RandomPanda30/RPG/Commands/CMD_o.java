package co.uk.RandomPanda30.RPG.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;
import co.uk.RandomPanda30.RPG.Handlers.StringH;

public class CMD_o implements CommandExecutor {

	public RPG plugin;

	public CMD_o (RPG plugin) {
		this.plugin = plugin;
	}

	public GuildH gh = new GuildH(plugin);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.ONLYCONSOLE), null);
			return true;
		}

		Player player = (Player) sender;
		UUID uuid = player.getUniqueId();

		String guildName = "";
		String guildRank = "";

		if (!gh.inGuild(uuid)) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOTINAGUILD), player);
			return true;
		}

		guildName = gh.getGuild(uuid);
		guildRank = gh.getRank(uuid);

		if (!gh.hasPermission(guildName, guildRank, "Ochat")) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOPERM), player);
			return true;
		}

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			buffer.append(' ').append(args[i]);
		}
		String s = buffer.toString();

		for (String key : plugin.getRPGValues().getConfigC()
				.getConfigurationSection("Guilds." + guildName + ".Players")
				.getKeys(false)) {
			String forRank = (String) plugin.getRPGValues().getConfigC()
					.get("Guilds." + guildName + ".Players." + key + ".Rank");
			if (Bukkit.getPlayer(key) != null) {
				Player nPlayer = Bukkit.getPlayer(key);
				if (gh.hasPermission(guildName, forRank, "Ochat")) {
					nPlayer.sendMessage(StringH.getInstance().formatMessage(
							"&F[" + gh.getTitle(guildName, guildRank) + "&F]"
									+ player.getName() + ": &a" + s));
				}
			}
		}
		return true;
	}
}