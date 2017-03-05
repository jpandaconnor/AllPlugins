package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 02/07/16.
 */
public class CommandDelete extends Command {

    public CommandDelete() {
        super("houseshop delete <House name>", "houseshop.delete", "Deletes a house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 2) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        String house = args[1];
        if(!SelectionQ.isHouse(house)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DOESNTEXIST.value, player);
            return true;
        }

        World world = Bukkit.getWorld("AincradFloor1");

        RegionManager manager = HouseShop.getWorldGuard().getRegionManager(world);
        ProtectedRegion region = manager.getRegion(house);

        if(region != null) {
            try {
                manager.removeRegion(house);
                manager.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }

        TEMP.datac.set(house, null);
        Data.save();

        Dispatch.sendMessage((String) Messages.MessagesValues.DELETE_DELETED.value, player);
        return true;
    }
}