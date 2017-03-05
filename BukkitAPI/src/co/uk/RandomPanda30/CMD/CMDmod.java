package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.ModRequestHandler;
import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Main.Main;

public class CMDmod implements CommandExecutor {

	public CMDmod (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		Player request = (Player) s;
		if (cmd.getName().equalsIgnoreCase("mod"))
			if (s.hasPermission("api.admin") || s.hasPermission("api.member")
					|| s.isOp()) {
				if (args.length >= 0) {
					if (Main.alreadyRequested.containsValue(request)) {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have already requested for a mod");
						return true;
					}
					request.sendMessage(Text_Handlers.Btag.toString()
							+ "§AA request has been sent for a mod");
					Main.alreadyRequested.put("ModRequest", request);
					ModRequestHandler.ModRequest();
					for (Player admins : Bukkit.getOnlinePlayers()) {
						if (admins != null) {
							if (Main.admins.containsValue(admins)) {
								admins.sendMessage(Text_Handlers.Btag
										.toString()
										+ "§F"
										+ request.getName().toString()
										+ "§B has requested for a mod");
							} else {
								return true;
							}
						} else {
							return true;
						}
					}
				} else {
					s.sendMessage(Text_Handlers.modSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}

}
