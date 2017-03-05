package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.Text.TextH;

public class CMDt implements CommandExecutor {

	public CMDt (Main plugin) {
		Main.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("t"))
			if (s.hasPermission("ui.give") || s.isOp()) {
				if (args.length < 3) {
					s.sendMessage(TextH.tSyntax.toString());
				} else {
					Player player = Bukkit.getPlayer(args[1]);
					String amount = args[2];
					double amountValid = Double.parseDouble(amount);
					if (player != null) {
						try {
							Economy.add(player.getName(), amountValid);
						} catch (NoLoanPermittedException e) {
							s.sendMessage(TextH.noLoans.toString());
						} catch (UserDoesNotExistException e) {
							s.sendMessage(TextH.nullPlayer.toString());
						}
						player.sendMessage("§6You have been given "
								+ amountValid + " tokens");
						s.sendMessage("§6You have given " + amountValid
								+ " §6tokens to §9" + player.getName());
					} else {
						s.sendMessage(TextH.nullPlayer.toString());
					}
				}
			}
		return true;
	}
}