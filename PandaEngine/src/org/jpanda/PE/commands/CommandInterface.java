package org.jpanda.PE.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandInterface {

    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args);
}
