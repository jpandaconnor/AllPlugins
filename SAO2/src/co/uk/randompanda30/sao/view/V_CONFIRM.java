package co.uk.randompanda30.sao.view;

/* 
   Created by panda on 17/08/16.
   
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
import co.uk.randompanda30.sao.view.base.IView;
import co.uk.randompanda30.sao.view.data.ViewNames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class V_CONFIRM implements IView {

    @Override
    public IView getView() {
        return this;
    }

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ViewNames.CONFIRMATION_MENU.value);

        inventory.setItem(0, GenericItems.getItems().getConfirm());
        inventory.setItem(8, GenericItems.getItems().getExit());

        player.openInventory(inventory);
    }
}