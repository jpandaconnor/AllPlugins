package com.ediTv2.Basics.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.ediTv2.Basics.Basics;
import com.ediTv2.Basics.Text.TextH;

public class CMDunmutechat implements CommandExecutor {
	
	public CMDunmutechat(Basics plugin) {
		Basics.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender player, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("unmute")) {
			if (player.hasPermission("basics.unmute")) {
				if (args.length >= 0) {
					
					if(Basics.chatDisabled == false) {
						player.sendMessage(TextH.alreadyUnmute);
					}
					
					Bukkit.broadcastMessage(TextH.chatUnmute);
					Basics.chatDisabled = false;
				} else {
					player.sendMessage(TextH.unmuteSyntax);
				}
			} else {
				player.sendMessage(TextH.noPerm);
			}
		}
		return true;
	}

}
