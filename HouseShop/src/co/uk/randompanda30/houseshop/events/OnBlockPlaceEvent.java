package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.object.HouseEditor;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by panda on 02/05/16.
 */
public class OnBlockPlaceEvent implements Listener {

    HouseShop plugin;

    public OnBlockPlaceEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack is = event.getItemInHand();
        Location location = event.getBlock().getLocation();

        if (player.getItemInHand() != null) {
            ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(location.getWorld())
                    .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

            Iterator<ProtectedRegion> regions = rgs.iterator();

            while(regions.hasNext()) {
                ProtectedRegion region = regions.next();

                if(region.getId().equals(PlayerQ.getHouse(player))) {

                    ArrayList<String> locations;

                    if(TEMP.blocksPlaced.containsKey(region.getId())) {
                       locations = TEMP.blocksPlaced.get(region.getId());
                    } else {
                        locations = new ArrayList<>();
                    }

                    if(!locations.contains(LocationS.decompileLocation(location))) {
                        locations.add(LocationS.decompileLocation(location));
                        TEMP.blocksPlaced.put(region.getId(), locations);
                    }

                    event.setCancelled(false);
                }
            }
        }

        if (player.getItemInHand() != null) {
            // If player is in house
            // If player has a house
            // If current house equals player house

            if (player.getItemInHand().equals(Items.getItems().editor_block)) {
                if (!PlayerQ.isEditing(player)) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_WHYHAVETHISITEM.value, player);
                    player.getInventory().remove(Items.getItems().editor_block);
                    return;
                }

                HouseEditor he = PlayerQ.getHouseEditor(player);
                he.startSelection(event.getBlock());

                Dispatch.sendMessage((String) Messages.MessagesValues.SELECTION_STARTED.value, player);

                player.getInventory().remove(Items.getItems().editor_block);

            }

            if (player.getItemInHand().equals(Items.getItems().editor_spawn)) {
                if (!PlayerQ.isEditing(player)) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_WHYHAVETHISITEM.value, player);
                    player.getInventory().remove(Items.getItems().editor_spawn);
                    return;
                }

                HouseEditor he = PlayerQ.getHouseEditor(player);
                he.setSpawn(event.getBlock().getLocation());
                event.getBlock().setType(Material.AIR);
                player.getInventory().remove(Items.getItems().editor_spawn);
            }
        }
    }
}