package co.uk.randompanda30.sao.view.submenus.party;

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

import co.uk.randompanda30.sao.items.GenericItems;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.view.base.IView;
import co.uk.randompanda30.sao.view.data.ViewNames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class V_MENU_PA_LEADER implements IView {

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.PARTY_LEADER.value);

        inventory.setItem(0, V_MENU_PA_LEADER_ITEMS.ADD.item);
        inventory.setItem(1, V_MENU_PA_LEADER_ITEMS.KICK.item);
        inventory.setItem(3, V_MENU_PA_LEADER_ITEMS.LIST.item);

        inventory.setItem(25, GenericItems.getItems().getBack());
        inventory.setItem(26, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }

    @Override
    public IView getView() {
        return this;
    }

    public enum V_MENU_PA_LEADER_ITEMS {
        ADD(ItemBuilder.buildItem("&a&lInvite to your party", Material.EMERALD_BLOCK, 1, 1, new ArrayList<String>() {
            {
                add("%TInvite a player to your party");
                add(" ");
                add("&lNote: Maximum party size is: 6");
            }
        })),

        KICK(ItemBuilder.buildItem("&c&lKick from your party", Material.REDSTONE_BLOCK, 1, 1, new ArrayList<String>() {
            {
                add("%TKick a player from your party");
            }
        })),

        LIST(ItemBuilder.buildItem("&7&lList players", Material.BOOK, 1, 1, new ArrayList<String>() {
            {
                add("%TList all the players in the party");
            }
        }));

        public ItemStack item;

        V_MENU_PA_LEADER_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}