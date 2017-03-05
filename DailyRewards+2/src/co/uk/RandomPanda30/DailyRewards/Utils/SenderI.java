package co.uk.RandomPanda30.DailyRewards.Utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SenderI {

	public CommandSender sender;

	Player player;

	boolean checked = false;

	public SenderI (CommandSender sender) {
		this.sender = sender;
		check();
	}

	private void check() {
		if (sender instanceof Player) {
			checked = true;
			player = (Player) sender;
		}
	}

	public void sendMessage(String message) {
		if (checked) {
			MessageUtil.sendMessage(message, player);
		} else {
			MessageUtil.sendMessage(message, null);
		}
	}
	
	public boolean isPlayer() {
		return checked;
	}
	
	public Player getPlayer() {
		return player;
	}
}
