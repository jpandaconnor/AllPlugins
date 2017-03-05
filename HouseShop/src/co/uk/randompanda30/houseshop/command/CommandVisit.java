package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by panda on 10/05/16.
 */
public class CommandVisit extends Command {

    public CommandVisit() {
        super("houseshop visit <Player name>", "houseshop.visit", "Visit a friends house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = visit
        // args[1] = name

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (Bukkit.getServer().getPlayer(args[1]) == null) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_PLAYERNOTFOUND.value, player);
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (!PlayerQ.hasHouse(target)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.VISIT_NOTHASHOUSE.value, player);
            return true;
        }

        String house = PlayerQ.getHouse(target);

        if (TEMP.datac.contains(house + ".friends")) {
            List<String> friends = (List<String>) TEMP.datac.get(house + ".friends");
            if (friends.contains(player.getUniqueId().toString())) {
                player.teleport((Location) TEMP.datac.get(house + ".spawn"));
                Dispatch.sendMessage(Messages.MessagesValues.VISIT_SENT.value.toString().replace("%name",
                        target.getName()), player);
            } else {
                Dispatch.sendMessage((String) Messages.MessagesValues.VISIT_NOTINFRIENDSLIST.value, player);
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.VISIT_NOTINFRIENDSLIST.value, player);
        }
        return true;
    }
}