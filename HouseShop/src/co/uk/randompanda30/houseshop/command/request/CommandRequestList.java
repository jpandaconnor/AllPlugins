package co.uk.randompanda30.houseshop.command.request;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 29/06/16.
 */
public class CommandRequestList extends Command {

    public CommandRequestList() {
        super("houseshop request list", "houseshop.request.list", "Opens the list of house requests", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // 0 = request
        // 1 = list

        if(args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(TEMP.requestc.getConfigurationSection("").getKeys(false) != null) {
            if(TEMP.requestc.getConfigurationSection("").getKeys(false).size() <= 0) {
                Dispatch.sendMessage("%TAG &c&lThere are no requests to take care of", player);
            } else {
                Dispatch.sendMessage("&c&lRequests: ", player);
                for (String s : TEMP.requestc.getConfigurationSection("").getKeys(false)) {
                    Dispatch.sendMessage(Messages.MessagesValues.REQUEST_TEMPLATE.value.toString().replace("%name",
                            (Bukkit.getOfflinePlayer(UUID.fromString(s)) != null ? Bukkit.getOfflinePlayer(UUID.fromString(s)).getName()
                                    : "Could not find offline player")), player);
                }
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_NOREQUEST.value, player);
        }
        return true;
    }
}