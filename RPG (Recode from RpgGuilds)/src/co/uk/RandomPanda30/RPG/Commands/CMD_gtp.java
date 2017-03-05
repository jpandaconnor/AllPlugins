package co.uk.RandomPanda30.RPG.Commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.RPG.RPG;
import co.uk.RandomPanda30.RPG.Files.Config.Enums.ConfigValues;
import co.uk.RandomPanda30.RPG.Files.Messages.Enums.MessagesValues;
import co.uk.RandomPanda30.RPG.Handlers.GuildH;
import co.uk.RandomPanda30.RPG.Handlers.TeleportH;

public class CMD_gtp implements CommandExecutor {

	public RPG plugin;

	public CMD_gtp (RPG plugin) {
		this.plugin = plugin;
	}

	public TeleportH teleport = new TeleportH(plugin);
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

		if (!player.hasPermission("guild.teleport")) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOPERM), player);
			return true;
		}

		if (args.length == 0) {
			return true;
		}

		if (!(Boolean) plugin.getRPGConfig().getConfigMap()
				.getStat(ConfigValues.TP)) {
			// Message here
			return true;
		}

		if (Bukkit.getPlayer(args[0]) == null) {
			// Message here
			return true;
		}

		Player target = Bukkit.getPlayer(args[0]);
		UUID nUUID = target.getUniqueId();

		String guildName = gh.getGuild(uuid);
		String targetGuildName = gh.getGuild(nUUID);

		String guildRank = gh.getRank(uuid);

		if (!gh.hasPermission(guildName, guildRank, "TP")) {
			plugin.sendMessage((String) plugin.getRPGConfig().getMessagesMap()
					.getStat(MessagesValues.NOPERM), player);
			return true;
		}

		if (!guildName.equalsIgnoreCase(targetGuildName)) {
			// Message - cannot teleport, not in same guild
			return true;
		}

		teleport.teleport(player, target);
		return true;
	}

}
