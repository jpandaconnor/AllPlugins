package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.object.HouseEditor;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by panda on 02/05/16.
 */
public class CommandBlock extends Command {

    public CommandBlock() {
        super("houseshop block", "houseshop.create", "Gives you the block to create a new house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = block

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (PlayerQ.isEditing(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_ALREADYEDITING.value, player);
            return true;
        }

        Dispatch.sendMessageList((ArrayList<String>) Messages.MessagesValues.EDITOR_JOIN_DETAILS.value, player);
        new HouseEditor(player);

        return true;
    }
}