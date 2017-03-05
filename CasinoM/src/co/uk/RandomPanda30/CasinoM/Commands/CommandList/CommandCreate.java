package co.uk.RandomPanda30.CasinoM.Commands.CommandList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Machines;
import co.uk.RandomPanda30.CasinoM.Commands.CommandInterface;
import co.uk.RandomPanda30.CasinoM.Methods.StringMethods;
import co.uk.RandomPanda30.CasinoM.Methods.Machines.SlotMachineMethods;

public class CommandCreate implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = (Player) sender;
		if ((Boolean) CasinoData.configC.get("COMMAND.CREATE")) {
			if (player.hasPermission("casinom.create")) {
				if (args.length > 3) {
					StringMethods.sendMessage((String) CasinoData.messagesC
							.get("CRITICAL.INVALIDCOMMAND"), player);
					return true;
				}
				String machineType = args[1];
				String name = args[2];

				Machines machine = null;

				switch (machineType) {
				case "slotmachine":
					machine = Machines.SLOTMACHINE;
					SlotMachineMethods.createSlotMachine(name,
							CasinoData.getPosition(1),
							CasinoData.getPosition(2), machine, player);
					break;
				default:
					StringMethods.sendMessage((String) CasinoData.messagesC
							.get("CRITICAL.INVALIDMACHINE"), player);
					return true;
				}
			}
		}
		return true;
	}
}