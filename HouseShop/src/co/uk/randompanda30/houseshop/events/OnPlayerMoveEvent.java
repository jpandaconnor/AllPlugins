package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.object.ForSaleTimeout;
import co.uk.randompanda30.houseshop.query.HouseQ;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by panda on 03/05/16.
 */
public class OnPlayerMoveEvent implements Listener {

    HouseShop plugin;

    public OnPlayerMoveEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();


        Bukkit.getServer().getScheduler().runTaskAsynchronously(HouseShop.getPlugin(),
                () -> {
                    if (SelectionQ.isInHouse(player)) {
                        if (SelectionQ.getHouseState(SelectionQ.getHouse(player)) == HouseState.FORSALE) {
                            if (!HouseQ.hasBeenShowHouse(SelectionQ.getHouse(player), player.getUniqueId())) {
                                Dispatch.sendMessage(Messages.MessagesValues.ENTERED_HOUSEFORSALE.value.toString().replace("%price",
                                        Integer.toString(HouseQ.getHousePrice(SelectionQ.getHouse(player)))), player);
                                TEMP.forsalesTimeouts.add(new ForSaleTimeout(SelectionQ.getHouse(player), uuid));
                            }
                            // Open GUI here
                        } else if (SelectionQ.getHouseState(SelectionQ.getHouse(player)) == HouseState.OWNED) {
                            if (TEMP.datac.get(SelectionQ.getHouse(player) + ".owner").equals(uuid.toString())) {
                                return;
                            }
                            if (TEMP.datac.contains(SelectionQ.getHouse(player) + ".friends")) {
                                List<String> friends = (List<String>) TEMP.datac.get(SelectionQ.getHouse(player) + ".friends");
                                if (!friends.contains(uuid.toString())) {
                                    player.teleport(LocationS.compileLocation((String) TEMP.datac.get(SelectionQ.getHouse(player) + ".spawn")));
                                    Dispatch.sendMessage((String) Messages.MessagesValues.MOVEMENT_NOTHOUSE.value, player);
                                }
                            } else {
                                player.teleport(LocationS.compileLocation((String) TEMP.datac.get(SelectionQ.getHouse(player) + ".spawn")));
                                Dispatch.sendMessage((String) Messages.MessagesValues.MOVEMENT_NOTHOUSE.value, player);
                            }
                        }
                    }
                });
    }
}