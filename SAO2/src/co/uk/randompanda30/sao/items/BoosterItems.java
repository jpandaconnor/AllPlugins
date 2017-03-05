package co.uk.randompanda30.sao.items;

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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BoosterItems {

    private static BoosterItems instance = new BoosterItems();

    public static BoosterItems getItems() {
        return instance;
    }

    public ItemStack getBlankBoosterItem() {
        return ItemBuilder.buildItem("%A&lXP Booster", Material.SLIME_BALL, 1, 0, new ArrayList<>());
    }

    public ItemStack getBoosterItem(String time, int percentage) {
        return ItemBuilder.buildItem("%A&lXP Booster", Material.SLIME_BALL, 1, 0, new ArrayList<String>() {
            {
                // 0 = SPACE
                // 1 = Right click to use this booster!
                // 2 = You can only use this one, so use it wisely!
                // 3 = SPACE
                // 4 = Time: time
                // 5 = Percentage: perc
                add(" ");
                add("%G&lRight click to use this booster!");
                add("%G&lYou can only use this once, so use it wisely");
                add("  ");
                add("%N&lTime: %A&l" + time);
                add("%N&lPercentage: %A&l" + Integer.toString(percentage));
            }
        });
    }
}