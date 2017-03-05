package co.uk.randompanda30.houseshop.command.list;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by panda on 01/07/16.
 */
public class CommandListO extends Command {

    public CommandListO() {
        super("houseshop list o", "houseshop.list.o", "houseshop list o", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if(TEMP.datac.getConfigurationSection("").getKeys(false) != null) {
            TEMP.datac.getConfigurationSection("").getKeys(false).stream().filter(s -> TEMP.datac.get(s + ".state").
                    equals("OWNED")).forEach(s -> {
                String message = "&f - &c&l" + s +   " (Owned by: &f&l" + (Bukkit.getOfflinePlayer(UUID.fromString((String)
                        TEMP.datac.get(s + ".owner"))).getName()) + "&c&l)";
                Dispatch.sendMessage(message, player);
            });
        }
        return true;
    }
}