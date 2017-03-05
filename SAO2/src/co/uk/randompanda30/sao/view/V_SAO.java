package co.uk.randompanda30.sao.view;

/* 
   Created by panda on 16/08/16.
   
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

public class V_SAO implements IView {

    @Override
    public IView getView() {
        return this;
    }

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.SAO_MENU.value);

        /*
        PH  GUILD  PARTY  DUNGEINR
         */

        inventory.setItem(0, V_SAO_ITEMS.HOUSING_ENTER.item);
        inventory.setItem(1, V_SAO_ITEMS.PARTY_ENTER.item);
        inventory.setItem(3, V_SAO_ITEMS.DUNGEINFINDING_ENTER.item);
        inventory.setItem(8, V_SAO_ITEMS.PLAYERDATA_RESET.item);

        inventory.setItem(26, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }

    public enum V_SAO_ITEMS {
        HOUSING_ENTER(ItemBuilder.buildItem("Player housing", Material.BRICK, 1, 1, new ArrayList<String>() {
            {
                add("%TClick here to access the player housing menu");
            }
        })),

        PLAYERDATA_RESET(ItemBuilder.buildItem("Reset Player Data", Material.SKULL_ITEM, 1, 1, new ArrayList<String>() {
            {
                add("%TClick here to reset all your player data");
                add(" ");
                add("&c&lWARNING: &cAll playerdata including");
                add("&cbackpack and inventory data will be");
                add("&cremoved! This process &c&lCANNOT &cbe reversed");
            }
        })),

        PARTY_ENTER(ItemBuilder.buildItem("Party", Material.FIREWORK, 1, 1, new ArrayList<String>() {
            {
                add("%TClick here to enter the party manager");
            }
        })),

        DUNGEINFINDING_ENTER(ItemBuilder.buildItem("Dungeon finder", Material.FIREBALL, 1, 1, new ArrayList<String>() {
            {
                add("%TClick here to enter the dungeon finder");
                add(" ");
            }
        }));

        public ItemStack item;

        V_SAO_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}