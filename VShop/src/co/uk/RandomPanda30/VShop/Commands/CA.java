package co.uk.RandomPanda30.VShop.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Methods.X;
import co.uk.RandomPanda30.VShop.Methods.Y;

public class CA implements Y {

	@Override
	public boolean onCommand(CommandSender cmdSender, Command cmd,
			String label, String[] args) {
		Player player = (Player) cmdSender;
		X.sendMessage((String) Messages.VSHOPS_ARG.value, player);
		return true;
	}
}