package co.uk.RandomPanda30.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Handlers.VoteKickBroadcastHandler;
import co.uk.RandomPanda30.Handlers.VoteKickHandler;
import co.uk.RandomPanda30.Main.Main;

public class CMDnkick implements CommandExecutor {

	public CMDnkick (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("nkick"))
			if (s.hasPermission("api.admin") || s.hasPermission("api.member")
					|| s.isOp()) {
				Player voter = (Player) s;
				if (args.length >= 0) {
					if (Main.toKick.isEmpty()) {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§CNo player has been voted to be kicked");
					}
					if (Main.alreadyVoted.containsValue(voter)) {
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§CYou have already voted!");
						return true;
					}
					Player player = Main.toKick.get("toKick");
					if (player != null) {
						Main.noKick.add(1);
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have voted to stop §F"
								+ player.getName().toString()
								+ " §Bfrom §Bgetting kicked");
						VoteKickBroadcastHandler.broadcastResult();
						VoteKickHandler.checkVotes();
						Main.alreadyVoted.put("voter", voter);
					}
				} else {
					s.sendMessage(Text_Handlers.nvoteSyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}

}
