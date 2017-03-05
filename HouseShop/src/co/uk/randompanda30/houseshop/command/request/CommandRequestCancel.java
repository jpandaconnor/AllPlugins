package co.uk.randompanda30.houseshop.command.request;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.config.Request;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 29/06/16.
 */
public class CommandRequestCancel extends Command {

    public CommandRequestCancel() {
        super("houseshop request cancel", "houseshop.request.cancel", "Cancels a pending house request", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        // 0 = request
        // 1 = cancel

        if(args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(!TEMP.requestc.contains(uuid.toString())) {
            Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_NOTREQUESTED.value, player);
            return true;
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_CANCELLED.value, player);

        TEMP.requestc.set(uuid.toString(), null);
        Request.save();

        return true;
    }
}