package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Config;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 09/05/16.
 */
public class CommandKickout extends Command {

    public CommandKickout() {
        super("houseshop kickout", "houseshop.kickout", "Sets the spawn location for when players sell houses/friends kicked out", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = kickout

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.KICKOUTSET.value, player);

        TEMP.configc.set("kickout", player.getLocation());
        Config.save();
        return true;
    }
}