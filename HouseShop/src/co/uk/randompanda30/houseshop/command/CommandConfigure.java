package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by panda on 02/05/16.
 */
public class CommandConfigure extends Command {

    public CommandConfigure() {
        super("houseshop configure", "houseshop.create", "Admin command to configure a houses data", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // args[0] = configure

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!SelectionQ.isInHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_NOTINHOUSE.value, player);
            return true;
        }

        Iterator i = TEMP.editingHouses.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry map = (Map.Entry) i.next();
            HashMap<String, Inventory> mapInv = (HashMap<String, Inventory>) map.getValue();

            if (mapInv.containsKey(SelectionQ.getHouse(player))) {
                Dispatch.sendMessage((String) Messages.MessagesValues.HS_ALREADYEDITINGHOUSE.value, player);
                return true;
            }
        }

        Inventory inventory = Bukkit.createInventory(null, 45, SelectionQ.getHouse(player));

        // 26 = +1000
        // 25 = +100
        // 24 = +10
        // 23 = +1

        // 22 = paper

        // 21 = -1
        // 20 = -10
        // 19 = -100
        // 18 = -1000

        inventory.setItem(26, Items.getItems().plus_1000);
        inventory.setItem(25, Items.getItems().plus_100);
        inventory.setItem(24, Items.getItems().plus_10);
        inventory.setItem(23, Items.getItems().plus_1);

        inventory.setItem(22, Items.getItems().getPaperDetails(SelectionQ.getHouse(player)));

        inventory.setItem(18, Items.getItems().minus_1000);
        inventory.setItem(19, Items.getItems().minus_100);
        inventory.setItem(20, Items.getItems().minus_10);
        inventory.setItem(21, Items.getItems().minus_1);

        HashMap<String, Inventory> toPut = new HashMap<>();
        toPut.put(SelectionQ.getHouse(player), inventory);

        TEMP.editingHouses.put(player.getUniqueId(), toPut);

        player.openInventory(TEMP.editingHouses.get(player.getUniqueId()).get(SelectionQ.getHouse(player)));
        return true;
    }
}