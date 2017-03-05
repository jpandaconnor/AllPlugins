package co.uk.randompanda30.houseshop.command;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
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
 * Created by panda on 05/05/16.
 */
public class CommandSell extends Command {

    public CommandSell() {
        super("houseshop sell", "houseshop.sell", "Sells your house", false);
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();

        if (args.length != 1) {
            Dispatch.sendMessage(Messages.MessagesValues.HS_SYNTAX.value.toString().
                    replaceAll("%syntax", getUsage()), player);
            return true;
        }

        if (!PlayerQ.hasHouse(player)) {
            Dispatch.sendMessage((String) Messages.MessagesValues.HS_DONTHAVEAHOUSE.value, player);
            return true;
        }

        int price = HouseQ.getHousePrice(PlayerQ.getHouse(player));
        double quater = (price / 2) / 2;
        double toGive = price - quater;

        new HouseHandle().sellHouse(player.getUniqueId(), PlayerQ.getHouse(player));
        HouseShop.getEconomy().depositPlayer(player, toGive);

        World world = Bukkit.getWorld("AincradFloor1");
        ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(world)
                .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

        rgs.getRegions().stream().filter(rp -> rp.getId().contains("hs_")).forEach(rp -> {
            RegionManager manager = HouseShop.getWorldGuard().getRegionManager(world);
            ProtectedRegion newrp = rp;

            DefaultDomain domain = new DefaultDomain();
            domain.removePlayer(player.getUniqueId());

            newrp.setOwners(domain);

            manager.removeRegion(rp.getId());
            manager.addRegion(newrp);
            try {
                manager.save();
            } catch (StorageException e) {
                e.printStackTrace();
            }
        });

        Dispatch.sendMessage((String) Messages.MessagesValues.SELL_SOLD.value, player);

        // Kickout shit here

        if (SelectionQ.isInHouse(player) && SelectionQ.getHouse(player).equals(PlayerQ.getHouse(player))) {
            if (TEMP.configc.contains("kickout")) {
                Location slocation = (Location) TEMP.configc.get("kickout");
                player.teleport(slocation);
                Dispatch.sendMessage((String) Messages.MessagesValues.SELL_MOVEDTOKICKOUT.value, player);
            } else {
                player.teleport(player.getLocation().getWorld().getSpawnLocation());
                Dispatch.sendMessage((String) Messages.MessagesValues.SELL_MOVEDTOKICKOUT.value, player);
            }
        }
        return true;
    }
}