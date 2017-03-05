package co.uk.randompanda30.sao.items;

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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GenericItems {

    private static GenericItems instance = new GenericItems();

    public static GenericItems getItems() {
        return instance;
    }

    public ItemStack getConfirm() {
        return ItemBuilder.buildItem("&a&lConfirm", Material.EMERALD_BLOCK, 1, 0, new ArrayList<>());
    }

    public ItemStack getExit() {
        return ItemBuilder.buildItem("&c&lExit", Material.REDSTONE_BLOCK, 1, 0, new ArrayList<>());
    }

    public ItemStack getBackExit() {
        return ItemBuilder.buildItem("&c&lExit to main menu", Material.REDSTONE, 1, 0, new ArrayList<>());
    }

    public ItemStack getAdminMenu() {
        return ItemBuilder.buildItem("&a&lAdmin Access", Material.DIAMOND, 1, 0, new ArrayList<>());
    }

    public ItemStack getBack() {
        return ItemBuilder.buildItem("&7&lBack", Material.ARROW, 1, 0, new ArrayList<>());
    }

    public ItemStack getForward() {
        return ItemBuilder.buildItem("&a&lNext", Material.EMERALD, 1, 0, new ArrayList<>());
    }

    public ItemStack getBorder() {
        return ItemBuilder.buildItem(" ", Material.STAINED_GLASS_PANE, 1, 1, new ArrayList<>());
    }
}