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

import co.uk.randompanda30.sao.string.Format;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ItemBuilder {

    public static ItemStack buildItem(String name, Material material, int amount, int type, ArrayList<String> lores) {
        ItemStack is = new ItemStack(material, amount, (short) type);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Format.format(name));
        ArrayList<String> lores2 = lores.stream().map(Format::format).collect(Collectors.toCollection(ArrayList::new));
        im.setLore(lores2);
        is.setItemMeta(im);
        return is;
    }

    public static boolean compareItems(ItemStack i1, ItemStack i2) {
        if (i1.getItemMeta().hasDisplayName() && i2.getItemMeta().hasDisplayName()) {
            if (i1.getItemMeta().getDisplayName().equals(i2.getItemMeta().getDisplayName())
                    && i1.getType().equals(i2.getType())) {
                return true;
            }
        }
        return false;
    }
}