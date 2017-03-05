package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 08/06/16.
 */
public class CommandHome extends Command {

    public CommandHome() {
        super("houseshop home", "houseshop.home", "Sends you to your house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DONTHAVEAHOUSE.value, player);
            return true;
        }

        String housename = PlayerQ.getHouse(player);

        Location l = LocationS.compileLocation((String) TEMP.datac.get(housename + ".spawn"));
        player.teleport(l);
        Dispatch.sendMessage((String) Messages.MessagesValues.TP_TELEPORTED.value, player);

        return true;
    }
}