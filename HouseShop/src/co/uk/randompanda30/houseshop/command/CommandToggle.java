package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 04/06/16.
 */
public class CommandToggle extends Command {

    public CommandToggle() {
        super("command toggle", "houseshop.toggle", "Toggles random player access to your house", false);
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

        boolean currentStatus = TEMP.datac.getBoolean(PlayerQ.getHouse(player) + ".anyonein");
        if(!currentStatus) {
            Dispatch.sendMessage(Messages.MessagesValues.TOGGLE_DONE.value.toString().replace("%status",
                    "&a&lOn"), player);
            TEMP.datac.set(PlayerQ.getHouse(player) + ".anyonein", true);
            Data.save();
        } else {
            Dispatch.sendMessage(Messages.MessagesValues.TOGGLE_DONE.value.toString().replace("%status",
                    "&c&lOff"), player);
            TEMP.datac.set(PlayerQ.getHouse(player) + ".anyonein", false);
            Data.save();
        }
        return true;
    }
}