package co.uk.RandomPanda30.DailyRewardsPlus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.DailyRewardsPlus.B;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.O;
import co.uk.RandomPanda30.DailyRewardsPlus.Methods.P;

public class XA implements XX {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		if (!player.hasPermission("dr.admin")) {
			O.openMenu(player);
		} else {
			O.openAdminMenu(player);
		}
		
		P.sendMessage((String) B.messagesC.get("DR.OPENEDMENU"), player);
		return true;
	}
}