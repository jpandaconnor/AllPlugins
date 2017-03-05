package co.uk.RandomPanda30.CityRP.Commands.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import co.uk.RandomPanda30.CityRP.CityRPData;
import co.uk.RandomPanda30.CityRP.Commands.CommandInterface;
import co.uk.RandomPanda30.CityRP.Objects.Player;
import co.uk.RandomPanda30.CityRP.Setup.LocationSetup;

public class CMD_CityRP implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = CityRPData
				.getPlayerObject(Bukkit.getPlayer(sender.getName()));
		LocationSetup.addToEditing(player);
		// TODO Auto-generated method stub
		return false;
	}

}