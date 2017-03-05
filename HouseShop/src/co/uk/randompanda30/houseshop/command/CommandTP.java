package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 04/06/16.
 */
public class CommandTP extends Command {

    public CommandTP() {
        super("houseshop tp <House name>", "houseshop.teleport", "Teleports you to a house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // check args
        // check if exists
        // teleport

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        String housename = args[1];

        if(!TEMP.datac.contains(housename)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.TP_NOTAHOUSE.value, player);
            return true;
        }

        Location l = LocationS.compileLocation((String) TEMP.datac.get(housename + ".spawn"));
        player.teleport(l);
        Dispatch.sendMessage((String) Messages.MessagesValues.TP_TELEPORTED.value, player);
        return true;
    }
}