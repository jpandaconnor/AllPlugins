package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.LocationS;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by panda on 10/05/16.
 */
public class OnBlockBreakEvent implements Listener {

    HouseShop plugin;

    public OnBlockBreakEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

                ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(location.getWorld())
                        .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

                Iterator<ProtectedRegion> regions = rgs.iterator();

                while (regions.hasNext()) {
                    ProtectedRegion region = regions.next();

                    if (region.getId().equals(PlayerQ.getHouse(player))) {
                        ArrayList<String> locations;

                        if(TEMP.blocksPlaced.containsKey(region.getId())) {
                            locations = TEMP.blocksPlaced.get(region.getId());
                        } else {
                            locations = new ArrayList<>();
                        }

                        if(locations.contains(LocationS.decompileLocation(location))) {
                            locations.remove(LocationS.decompileLocation(location));
                            TEMP.blocksPlaced.put(region.getId(), locations);
                            event.setCancelled(false);
                        }
                    }
                }
        }
}