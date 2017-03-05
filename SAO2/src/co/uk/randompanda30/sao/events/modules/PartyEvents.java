package co.uk.randompanda30.sao.events.modules;

/*
   Created by Connor Brady on 13/10/2016.

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
import co.uk.randompanda30.sao.events.handle.ICECheck;
import co.uk.randompanda30.sao.items.GenericItems;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.modules.party.Party;
import co.uk.randompanda30.sao.modules.party.PartyQuery;
import co.uk.randompanda30.sao.string.Dispatch;
import co.uk.randompanda30.sao.view.V_SAO;
import co.uk.randompanda30.sao.view.data.ViewNames;
import co.uk.randompanda30.sao.view.submenus.party.V_MENU_PA_CREATE;
import co.uk.randompanda30.sao.view.submenus.party.V_MENU_PA_LEADER;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.UUID;

public class PartyEvents implements Listener {

    public PartyEvents() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if(!new ICECheck(event).check()) return;

        // Inventories
        if(event.getInventory().getName().equals(ViewNames.PARTY_CREATE.value)) {
            event.setCancelled(true);
        }

        if(event.getInventory().getName().equals(ViewNames.PARTY_LEADER.value)) {
            event.setCancelled(true);
        }

        // Checking if the party item has been clicked in the main inventory
        if(ItemBuilder.compareItems(V_SAO.V_SAO_ITEMS.PARTY_ENTER.item, event.getCurrentItem())) {
            if(!PartyQuery.isPlayerInParty(player)) {
                new V_MENU_PA_CREATE().openView(player);
            } else {
                Party party = PartyQuery.getPlayerParty(player);

                if(party.isLeader(player)) {
                    new V_MENU_PA_LEADER().openView(player);
                } else {
                    // Open normal player menu
                }
            }
        }

        if(ItemBuilder.compareItems(V_MENU_PA_CREATE.V_MENU_PA_CREATE_ITEMS.CREATEPARTY.item, event.getCurrentItem())) {
            new Party(player);
            Dispatch.sendListMessage((ArrayList<String>) Messages.MessagesValues.MODULES_PARTY_PARTYCREATED.value,
                    player);

            player.closeInventory();
        }

        if(ItemBuilder.compareItems(V_MENU_PA_LEADER.V_MENU_PA_LEADER_ITEMS.LIST.item, event.getCurrentItem())) {

        }

        if(ItemBuilder.compareItems(GenericItems.getItems().getBack(), event.getCurrentItem())) {
                if(event.getInventory().getName().equals(ViewNames.PARTY_CREATE.value)) {
                    player.closeInventory();
                    new V_SAO().openView(player);
                }

                if(event.getInventory().getName().equals(ViewNames.PARTY_LEADER.value)) {
                    player.closeInventory();
                    new V_SAO().openView(player);
                }
            }

        // Check if back buttons are in these menus
    }
}