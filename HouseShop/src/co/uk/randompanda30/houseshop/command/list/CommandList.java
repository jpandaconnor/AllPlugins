package co.uk.randompanda30.houseshop.command.list;

import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 04/06/16.
 */
public class CommandList extends Command {

    public CommandList() {
        super("houseshop list", "houseshop.list", "Lists the houses for sale/owned", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length < 0) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        for(String s : TEMP.datac.getConfigurationSection("").getKeys(false)) {
            HouseState owned = SelectionQ.getHouseState(s);

            String message = "&f - " + (owned == HouseState.FORSALE ? "&a&l" + s  + " (For sale)" : "&c&l" + s +
                    " (Owned by: &f&l" + (Bukkit.getOfflinePlayer(UUID.fromString((String) TEMP.datac.get(s + ".owner"))).
                    getName()) + "&c&l)");
            Dispatch.sendMessage(message, player);
        }
        return true;
    }
}