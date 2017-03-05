package co.uk.randompanda30.houseshop.task;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.object.ForSaleTimeout;
import co.uk.randompanda30.houseshop.query.HouseQ;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * Created by panda on 18/05/16.
 */
public class LocationTask extends BukkitRunnable {

    HouseShop plugin;

    public LocationTask(HouseShop plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            Location location = player.getLocation();
            if (SelectionQ.isInHouse(player)) {
                String house = SelectionQ.getHouse(player);
                if (SelectionQ.getHouseState(SelectionQ.getHouse(player)) == HouseState.FORSALE) {
                    if (!HouseQ.hasBeenShowHouse(SelectionQ.getHouse(player), player.getUniqueId())) {
                        Dispatch.sendMessage(Messages.MessagesValues.ENTERED_HOUSEFORSALE.value.toString().replace("%price",
                                Integer.toString(HouseQ.getHousePrice(SelectionQ.getHouse(player)))), player);
                        TEMP.forsalesTimeouts.add(new ForSaleTimeout(SelectionQ.getHouse(player), uuid));
                    }
                    // Open GUI here
                } else if (SelectionQ.getHouseState(SelectionQ.getHouse(player)) == HouseState.OWNED) {
                    if(!TEMP.datac.getBoolean(SelectionQ.getHouse(player) + ".anyonein")) {
                        if (!player.isOp()) {
                            if (!TEMP.datac.get(SelectionQ.getHouse(player) + ".owner").equals(uuid.toString())) {

                                ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(TEMP.world)
                                        .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

                                if(!rgs.getRegions().isEmpty()) {
                                    rgs.getRegions().stream().filter(rr -> rr.getId().contains("hs_")).forEach(rr -> {
                                        ProtectedRegion newrp = rr;
                                        if (newrp.getMembers() != null) {
                                            if (!newrp.getMembers().contains(uuid)) {
                                                player.teleport(LocationS.compileLocation((String) TEMP.datac.get
                                                        (SelectionQ.getHouse(player) + ".spawn")));
                                                Dispatch.sendMessage((String) Messages.MessagesValues.
                                                        MOVEMENT_NOTHOUSE.value, player);
                                            }
                                        } else {
                                            player.teleport(LocationS.compileLocation((String) TEMP.datac.get
                                                    (SelectionQ.getHouse(player) + ".spawn")));
                                            Dispatch.sendMessage((String) Messages.MessagesValues.
                                                    MOVEMENT_NOTHOUSE.value, player);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}