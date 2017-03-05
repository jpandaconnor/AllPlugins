package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by panda on 02/05/16.
 */
public class OnPlayerInteractEvent implements Listener {

    HouseShop plugin;

    public OnPlayerInteractEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand() != null) {
                ItemStack is = player.getItemInHand();

                if (is.equals(Items.getItems().editor_leave)) {
                    if (!PlayerQ.isEditing(player)) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_WHYHAVETHISITEM.value, player);
                        player.getInventory().remove(Items.getItems().editor_leave);
                    } else {
                        PlayerQ.getHouseEditor(player).end();
                    }
                    event.setCancelled(true);
                }

                if (is.equals(Items.getItems().editor_reset)) {
                    if (!PlayerQ.isEditing(player)) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_WHYHAVETHISITEM.value, player);
                        player.getInventory().remove(Items.getItems().editor_reset);
                    } else {
                        PlayerQ.getHouseEditor(player).reset();
                        // Bukkit.getServer().getScheduler().cancelTask(OnBlockPlaceEvent.blockTaskID);
                    }
                    event.setCancelled(true);
                }

                if (is.equals(Items.getItems().editor_done)) {
                    if (!PlayerQ.isEditing(player)) {
                        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_WHYHAVETHISITEM.value, player);
                        player.getInventory().remove(Items.getItems().editor_done);
                    } else {
                        if (PlayerQ.getHouseEditor(player).isSpawnPlaced()) {
                            PlayerQ.getHouseEditor(player).initTypingName();
                        } else {
                            Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_NEEDSSPAWN.value, player);
                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}