package co.uk.RandomPanda30.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Handlers.Text_Handlers;
import co.uk.RandomPanda30.Handlers.VoteKickBroadcastHandler;
import co.uk.RandomPanda30.Handlers.VoteKickHandler;
import co.uk.RandomPanda30.Main.Main;

public class CMDykick implements CommandExecutor {

	public CMDykick (Main plugin) {
		Main.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("ykick"))
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
						Main.voteKicks.add(1);
						s.sendMessage(Text_Handlers.Btag.toString()
								+ "§BYou have voted to kick §F"
								+ player.getName().toString());
						Main.alreadyVoted.put("voted", voter);
						VoteKickBroadcastHandler.broadcastResult();
						VoteKickHandler.checkVotes();
					}
				} else {
					s.sendMessage(Text_Handlers.voteKicksyntax.toString());
				}
			} else {
				s.sendMessage(Text_Handlers.noPerm.toString());
			}
		return true;
	}
}
