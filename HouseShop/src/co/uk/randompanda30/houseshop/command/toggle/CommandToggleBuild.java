package co.uk.randompanda30.houseshop.command.toggle;

import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 01/07/16.
 */
public class CommandToggleBuild extends Command {

    public CommandToggleBuild() {
        super("houseshop toggle build", "houseshop.toggle.build", "Toggles if friends can build in your house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DONTHAVEAHOUSE.value, player);
            return true;
        }



        return true;
    }
}