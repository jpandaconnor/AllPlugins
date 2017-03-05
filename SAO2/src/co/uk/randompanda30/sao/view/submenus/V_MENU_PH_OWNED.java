package co.uk.randompanda30.sao.view.submenus;

/* 
   Created by panda on 31/08/16.
   
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

import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.modules.housing.HouseHomeQuery;
import co.uk.randompanda30.sao.modules.housing.HousePlayerQuery;
import co.uk.randompanda30.sao.view.base.IView;
import co.uk.randompanda30.sao.view.data.ViewNames;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.ChatPaginator;

import java.util.ArrayList;

public class V_MENU_PH_OWNED implements IView {

    @Override
    public IView getView() {
        return this;
    }

    @Override
    public void openView(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ViewNames.HOUSE_MENU.value);

        inventory.setItem(0, getHousing_IcrmRentItem(player));
        inventory.setItem(1, V_MENU_PH_ITEMS.HOUSING_ADDFRIENDS.item);
        inventory.setItem(2, V_MENU_PH_ITEMS.HOUSING_REMOVEFRIENDS.item);

        player.openInventory(inventory);
    }

    // Items that are static here and really really cannot be made any other way
    public ItemStack getHousing_IcrmRentItem(Player player) {
        String house = HousePlayerQuery.getHouse(player);
        long days = HouseHomeQuery.getHomeTimeInDays(house);

        return ItemBuilder.buildItem("Increase rent time!", Material.GOLD_INGOT, 1, 1, new ArrayList<String>() {
            {
                add("%TClick to add another day's rent to your time");
                add(" ");
                add(StringUtils.center("&b&lTime remaning: &a&l" + (
                        (days != 0 ? Long.toString(days) + " days" : "&c&l> 1 day left")
                ), ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH));
            }
        });
    }

    public enum V_MENU_PH_ITEMS {
        HOUSING_ADDFRIENDS(ItemBuilder.buildItem("&aAdd friends", Material.SKULL_ITEM, 1, 1, new ArrayList<String>() {
            {
                add("%TClick to add a friend to your friends list on your house");
            }
        })),

        HOUSING_REMOVEFRIENDS(ItemBuilder.buildItem("&cRemove friends", Material.SKULL_ITEM, 1, 1, new ArrayList<String>() {
            {
                add("%TClick to remove a friend from your friends list on your house");
            }
        }));

        public ItemStack item;

        V_MENU_PH_ITEMS(ItemStack item) {
            this.item = item;
        }
    }
}