package co.uk.randompanda30.houseshop.events;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.object.HouseEditor;
import co.uk.randompanda30.houseshop.query.PlayerQ;
import co.uk.randompanda30.houseshop.query.SelectionQ;
import co.uk.randompanda30.houseshop.string.Dispatch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by panda on 02/05/16.
 */
public class OnAsyncPlayerChatEvent implements Listener {

    HouseShop plugin;

    public OnAsyncPlayerChatEvent(HouseShop plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (PlayerQ.isEditing(player)) {
            HouseEditor he = PlayerQ.getHouseEditor(player);
            if (he.isTypingName()) {
                if (!event.getMessage().contains(" ")) {
                    if (!SelectionQ.isHouse(event.getMessage())) {
                        he.create(event.getMessage());
                        event.setCancelled(true);
                    } else {
                        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_TYPINGNAME_ALREADYEXISTS.value, player);
                        event.setCancelled(true);
                    }
                } else {
                    String[] ss = event.getMessage().split(" ");
                    he.create(ss[1]);
                    event.setCancelled(true);
                }
            }
        }
    }
}