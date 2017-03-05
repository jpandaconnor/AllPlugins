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
import co.uk.randompanda30.sao.view.submenus.V_MENU_PH_UNOWNED;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class V_MENU_PA_CREATE implements IView {

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.PARTY_CREATE.value);

        inventory.setItem(0, V_MENU_PA_CREATE_ITEMS.CREATEPARTY.item);

        inventory.setItem(25, GenericItems.getItems().getBack());
        inventory.setItem(26, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }

    @Override
    public IView getView() {
        return this;
    }

    public enum V_MENU_PA_CREATE_ITEMS {
        CREATEPARTY(ItemBuilder.buildItem("%GCreate party", Material.EMERALD_BLOCK, 1, 1, new ArrayList<String>() {
            {
                add("%TYou are not in a party at the moment");
                add(" ");
                add("%TClick here to create a party");
            }
        }));

        public ItemStack item;

        V_MENU_PA_CREATE_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}