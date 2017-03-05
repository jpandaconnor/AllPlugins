package co.uk.RandomPanda30.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDinfo implements CommandExecutor {

	public CMDinfo(Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("info"))
			if (s.hasPermission("api.admin") || s.hasPermission("api.member")
					|| s.isOp()) {
				if (args.length >= 0) {
					s.sendMessage(Text_Handlers.mEen.toString());
					s.sendMessage(Text_Handlers.mTwee.toString());
				} else {
					s.sendMessage(Text_Handlers.infoSyntax.toString());
				}
			}
		return true;
	}
}
