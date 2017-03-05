package co.uk.RandomPanda30.KnightCrates.Util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.KnightCrates.KnightCrates;

public class Sender {

	private CommandSender sender;

	boolean player = false;
	public Player sendp = null;

	public Sender (CommandSender sender) {
		this.sender = sender;
		check();
	}

	public void check() {
		if (sender instanceof Player) {
			player = true;
			sendp = (Player) sender;
		}
	}

	public void sendMessage(String message) {
		if (!player) {
			KnightCrates.sendMessage(message, null);
		} else {
			KnightCrates.sendMessage(message, sendp);
		}
	}

	public CommandSender getSender() {
		return sender;
	}
	
	public boolean isPlayer() {
		return player;
	}
}