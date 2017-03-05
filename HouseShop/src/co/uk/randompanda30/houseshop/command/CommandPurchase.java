package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.command.handle.Command;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.handle.HouseHandle;
import co.uk.randompanda30.houseshop.query.HouseQ;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by panda on 04/05/16.
 */
public class CommandPurchase extends Command {

    public CommandPurchase() {
        super("houseshop purchase/buy", "houseshop.purchase", "Lets you purchase a house you're standing in", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();

        // args[0] = purchase

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!SelectionQ.isInHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PURCHASE_NOTINHOUSE.value, player);
            return true;
        }

        if (PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PURCHASE_ALREADYHAD.value, player);
            return true;
        }

        if (SelectionQ.getHouseState(SelectionQ.getHouse(player)) == HouseState.OWNED) {
            Dispatch.sendMessage((String) Messages.MessagesValues.PRUCHASE_ALREADYOWNED.value, player);
            return true;
        }

        String house = SelectionQ.getHouse(player);

        if (!(HouseShop.getEconomy().getBalance(player) >= HouseQ.getHousePrice(house))) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_NOTENOUGH.value, player);
            return true;
        }


        new HouseHandle().buyHouse(player, house);

        World world = Bukkit.getWorld("AincradFloor1");
        ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(world)
                .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

        rgs.getRegions().stream().filter(rp -> rp.getId().contains("hs_")).forEach(rp -> {
            RegionManager manager = HouseShop.getWorldGuard().getRegionManager(world);
            ProtectedRegion newrp = rp;

            DefaultDomain domain = new DefaultDomain();
            domain.addPlayer(player.getUniqueId());

            newrp.setOwners(domain);

            manager.addRegion(newrp);
            try {
                manager.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
        });

        HouseShop.getEconomy().withdrawPlayer(player, HouseQ.getHousePrice(house));
        Dispatch.sendMessage((String) Messages.MessagesValues.PURCAHSE_DONE.value, player);
        return true;
    }
}