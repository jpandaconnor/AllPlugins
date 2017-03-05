package co.uk.randompanda30.houseshop.events.handle;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.events.*;

/**
 * Created by panda on 02/05/16.
 */
public class EventHandler {

    HouseShop plugin;

    public EventHandler(HouseShop plugin) {
        this.plugin = plugin;

        register();
    }

    public void register() {
        new OnPlayerInteractEvent(plugin);
        new OnBlockPlaceEvent(plugin);
        new OnAsyncPlayerChatEvent(plugin);
        new OnInventoryClickEvent(plugin);
        new OnInventoryCloseEvent(plugin);
        // new OnPlayerMoveEvent(plugin);
        new OnBlockBreakEvent(plugin);
        new OnPlayerJoinEvent(plugin);
    }
}