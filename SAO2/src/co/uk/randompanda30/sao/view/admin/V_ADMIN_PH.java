package co.uk.randompanda30.sao.view.admin;

/* 
   Created by panda on 02/09/16.
   
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

// Sort by order menus

// Create a house
// Delete a house - Check for empty

// Lookup house information

// Edit a house - name, price, kick player out of it, wipe, see days rent

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

public class V_ADMIN_PH implements IView {

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.ADMIN_PH.value);

        inventory.setItem(0, V_ADMIN_PH_ITEMS.ADMIN_CREATEHOUSE.item);
        inventory.setItem(1, V_ADMIN_PH_ITEMS.ADMIN_CREATEDISTRICT.item);

        inventory.setItem(25, GenericItems.getItems().getBack());
        inventory.setItem(26, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }

    @Override
    public IView getView() {
        return this;
    }

    public enum V_ADMIN_PH_ITEMS {

        ADMIN_CREATEHOUSE(ItemBuilder.buildItem("&aCreate %Thouse", Material.EMERALD, 1, 0, new ArrayList<String>() {
            {
                add("%TClick to create a new house");
                add(" ");
                add("%TThis will put you in house creation mode which will");
                add("%Tsave + wipe your inventory");
            }
        })),

        ADMIN_CREATEDISTRICT(ItemBuilder.buildItem("&aCreate %Tdistrict", Material.GRASS, 1, 0, new ArrayList<String>() {
            {
                add("%TClick to create a new district");
                add(" ");
                add("%TNote that you must create a district before you can");
                add("%Tcreate any houses");
            }
        }));

        public ItemStack item;

        V_ADMIN_PH_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}