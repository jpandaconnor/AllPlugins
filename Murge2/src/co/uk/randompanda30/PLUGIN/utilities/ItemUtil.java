package co.uk.randompanda30.PLUGIN.utilities;

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

import co.uk.randompanda30.PLUGIN.string.Format;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemUtil {

    public static ItemStack makeItem(String name, Material material, int amount, byte type, List<String> lores) {
        ItemStack is = new ItemStack(material, amount, type);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Format.format(name));
        ArrayList<String> lores2 = lores.stream().map(Format::format).collect(Collectors.toCollection(ArrayList::new));
        im.setLore(lores2);
        is.setItemMeta(im);
        return is;
    }

    public static boolean compareItem(ItemStack item1, ItemStack item2) {
        return (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())
                && (item1.getType().equals(item2.getType())));
    }

    public static boolean compareStack(ItemStack stack1, ItemStack stack2) {
        if(stack1 == null || stack2 == null) return false;
        if(stack1.getType() != stack2.getType()) return false;
        if(stack1.hasItemMeta() != stack2.hasItemMeta()) return false;
        // if(!a.getItemMeta().getDisplayName().equals(b.getItemMeta().getDisplayName())) return false;
        if(stack1.hasItemMeta() && !stack1.getItemMeta().equals(stack2.getItemMeta())) return false;
        return true;
    }
}