package co.uk.randompanda30.sao.view.submenus;

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

public class V_MENU_PH_UNOWNED implements IView {

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.HOUSE_UNOWNED_MENU.value);

        inventory.setItem(0, V_MENU_PH_UNOWNED_ITEMS.HOUSING_BUYHOUSE.item);

        if (player.hasPermission("sao.playerhousing.admin")) {
            inventory.setItem(23, GenericItems.getItems().getAdminMenu());
        }

        inventory.setItem(25, GenericItems.getItems().getBack());
        inventory.setItem(26, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }

    @Override
    public IView getView() {
        return this;
    }

    /*
    Buy house - alert if no houses

    Show admin menu if has perm
     */

    public enum V_MENU_PH_UNOWNED_ITEMS {
        HOUSING_BUYHOUSE(ItemBuilder.buildItem("%TBuy house", Material.BED, 1, 0, new ArrayList<String>() {
            {
                add("%TClick here to search/buy a new house");
            }
        }));

        public ItemStack item;

        V_MENU_PH_UNOWNED_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}