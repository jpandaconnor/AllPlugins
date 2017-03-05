package co.uk.randompanda30.sao.events.modules;

/*
   Created by panda on 16/07/16.

   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import co.uk.randompanda30.sao.SAO;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.items.PlayerHousingItems;
import co.uk.randompanda30.sao.modules.housing.HouseEditor;
import co.uk.randompanda30.sao.modules.housing.HousePlayerQuery;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerHousingEvents implements Listener {

    public PlayerHousingEvents() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if(event.getCurrentItem() == null) {
            return;
        }

        if(event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        ItemStack is = event.getItemInHand();

        if(ItemBuilder.compareItems(is, PlayerHousingItems.getItems().getEditorBlock())) {
            if(!HousePlayerQuery.isPlayerEditing(player)) {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_EDITOR_LEAVE_WHYHAVETHISITEM.value,
                        player);
                player.getInventory().remove(PlayerHousingItems.getItems().getEditorBlock());
                return;
            }
        }

        if(ItemBuilder.compareItems(is, PlayerHousingItems.getItems().getEditorSpawn())) {
            if(!HousePlayerQuery.isPlayerEditing(player)) {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_EDITOR_LEAVE_WHYHAVETHISITEM.value,
                        player);
                player.getInventory().remove(PlayerHousingItems.getItems().getEditorSpawn());
                return;
            }

            HouseEditor he = HousePlayerQuery.getHouseEditorObject(player);
            he.setSpawn(event.getBlock().getLocation());
            event.getBlock().setType(Material.AIR);
            player.getInventory().remove(PlayerHousingItems.getItems().getEditorSpawn());
        }
    }
}