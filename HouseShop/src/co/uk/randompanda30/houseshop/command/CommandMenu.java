package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by panda on 10/05/16.
 */
public class CommandMenu extends Command {

    public CommandMenu() {
        super("houseshop menu", "houseshop.menu", "Opens the player menu for home owners", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = menu

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DONTHAVEAHOUSE.value, player);
            return true;
        }

        String house = PlayerQ.getHouse(player);
        Inventory menu = Bukkit.createInventory(null, 9, "House options");

        menu.setItem(4, Items.getItems().getHouseDetails(house));

        player.openInventory(menu);
        return true;
    }
}