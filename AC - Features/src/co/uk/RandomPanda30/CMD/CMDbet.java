package co.uk.RandomPanda30.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import co.uk.RandomPanda30.Main.Main;
import co.uk.RandomPanda30.Text.TextH;

public class CMDbet implements CommandExecutor {

	public CMDbet (Main plugin) {
		Main.plugin = plugin;
	}

	public static int jackpotSize;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("bet")) {
			if (s.hasPermission("ac.bet")) {
				if (args.length > 0) {
					String rawAmount = args[0];
					int amount = Integer.parseInt(rawAmount);
					if (amount > jackpotSize) {
						try {
							Economy.subtract(s.getName(), amount);
							Bukkit.broadcastMessage(TextH.ltag + "§9"
									+ s.getName().toString()
									+ "§6 Raised the jackpot to §9 " + amount);
							s.sendMessage(TextH.ltag
									+ "§6You raised the jackpot to §9" + amount);
							jackpotSize = amount;
							Bukkit.broadcastMessage(Integer
									.toString(jackpotSize));
						} catch (NoLoanPermittedException e) {
							s.sendMessage(TextH.ltag.toString() + " "
									+ TextH.noLoans.toString());
						} catch (UserDoesNotExistException e) {
							s.sendMessage(TextH.ltag + " " + TextH.nullPlayer);
						}
					} else {
						s.sendMessage(TextH.ltag.toString()
								+ "§CYour bet needs to be higher than§9 "
								+ jackpotSize);
					}
				} else {
					s.sendMessage(TextH.betSyntax.toString());
				}
			} else {
				s.sendMessage(TextH.noPerm.toString());
			}
		}
		return true;
	}
}