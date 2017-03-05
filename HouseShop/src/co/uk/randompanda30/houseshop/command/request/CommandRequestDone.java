package co.uk.randompanda30.houseshop.command.request;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.config.Request;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 29/06/16.
 */
public class CommandRequestDone extends Command {

    public CommandRequestDone() {
        super("houseshop request done <Player Name>", "houseshop.request.done", "Marks a request as done", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length != 3) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        String housen = args[2].toLowerCase();
        boolean found = false;

        if(TEMP.requestc.getConfigurationSection("").getKeys(false) != null) {
            for(String s : TEMP.requestc.getConfigurationSection("").getKeys(false)) {
                String p = (String) TEMP.requestc.get(s + ".name");
                if(p.equalsIgnoreCase(housen)) {
                    found = true;

                    if(Bukkit.getPlayer(UUID.fromString(s)) != null && Bukkit.getPlayer(UUID.fromString(s)).isOnline()) {
                        TEMP.requestc.set(s, null);
                        Request.save();

                        Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_PLAYERINFORED.value, player);
                        Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_REQUESTDONE.value, Bukkit.getPlayer(
                                UUID.fromString(s)
                        ));
                        // send Message
                    } else {
                        TEMP.requestc.set(s + ".fufilled.uuid", player.getUniqueId().toString());
                        Request.save();

                        Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_REQUESTWILLSEND.value, player);
                    }
                    return true;
                }
            }

            if(!found) {
                Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_NOTFOUND.value, player);
                return true;
            }
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.REQUEST_NOREQUEST.value, player);
        }

        return true;
    }

}