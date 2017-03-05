package com.ediTv2.Basics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

public class CMDmutechat implements CommandExecutor {

	public CMDmutechat(Basics plugin) {
		Basics.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender player, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("mute")) {
			if (player.hasPermission("basics.mute")) {
				if (args.length >= 0) {
					
					if(Basics.chatDisabled == true) {
						player.sendMessage(TextH.alreadyMute);
					}
					
					Bukkit.broadcastMessage(TextH.chatMute);
					Basics.chatDisabled = true;
				} else {
					player.sendMessage(TextH.muteSyntax);
				}
			} else {
				player.sendMessage(TextH.noPerm);
			}
		}
		return true;
	}
}
