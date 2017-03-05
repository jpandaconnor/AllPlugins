package co.uk.randompanda30.petstorage.commands;

import co.uk.randompanda30.petstorage.PetStorage;
import co.uk.randompanda30.petstorage.commands.utils.CommandHandler;
import co.uk.randompanda30.petstorage.config.values.MessagesValues;
import co.uk.randompanda30.petstorage.utilities.string.Dispatch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Command_PS implements CommandExecutor {

    public PetStorage plugin;

    public Command_PS(PetStorage plugin) {
        this.plugin = plugin;

        plugin.getCommand("ps").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!CommandHandler.isPlayer(sender)) {
            Dispatch.sendMessage((String) MessagesValues.NOCONSOLE.value, null);
            return true;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();



        return true;
    }

}