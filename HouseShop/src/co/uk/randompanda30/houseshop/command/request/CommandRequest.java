package co.uk.randompanda30.houseshop.command.request;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.config.Request;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 25/06/16.
 */
public class CommandRequest extends Command {

    public CommandRequest() {
        super("houseshop request", "houseshop.request", "Requests an admin to setup a specific house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        Location location = player.getLocation();

        // args[0] = request;

        if(args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(SelectionQ.isInHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_ALREADYAHOUSE.value, player);
            return true;
        }

        if(TEMP.requestc.contains(uuid.toString())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_OLDNOTDONE.value, player);
            return true;
        }

        TEMP.requestc.set(player.getUniqueId().toString() + ".location", LocationS.decompileLocation(location));
        TEMP.requestc.set(player.getUniqueId().toString() + ".name", player.getName());
        Request.save();

        Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_DONE.value, player);
        return true;
    }
}
