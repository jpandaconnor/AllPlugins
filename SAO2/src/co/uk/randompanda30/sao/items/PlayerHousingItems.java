package co.uk.randompanda30.sao.items;

/* 
   Created by panda on 20/08/16.
   
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

import co.uk.randompanda30.sao.TEMP;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Calendar;

public class PlayerHousingItems {

    private static PlayerHousingItems instance = new PlayerHousingItems();

    public static PlayerHousingItems getItems() {
        return instance;
    }

    public ItemStack getEditorLeave() {
        return ItemBuilder.buildItem("&c&lLeave edit mode", Material.WOOL, 1, 14, new ArrayList<>());
    }

    public ItemStack getEditorBlock() {
        return ItemBuilder.buildItem("&3&lPlace to select region!", Material.WOOL, 1, 9, new ArrayList<>());
    }

    public ItemStack getEditorSpawn() {
        return ItemBuilder.buildItem("&7&lPlace to select the outside spawn!", Material.WOOL, 1, 7, new ArrayList<>());
    }

    public ItemStack getEditorReset() {
        return ItemBuilder.buildItem("&e&lReset selection", Material.WOOL, 1, 4, new ArrayList<>());
    }

    public ItemStack getEditorDone() {
        return ItemBuilder.buildItem("&a&lFinished", Material.WOOL, 1, 5, new ArrayList<>());
    }

    public ItemStack getMinus1() {
        return ItemBuilder.buildItem("&c-1", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getMinus10() {
        return ItemBuilder.buildItem("&c-10", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getMinus100() {
        return ItemBuilder.buildItem("&c-100", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getMinus1000() {
        return ItemBuilder.buildItem("&c-1000", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getAdd1() {
        return ItemBuilder.buildItem("&a+1", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getAdd10() {
        return ItemBuilder.buildItem("&a+10", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getAdd100() {
        return ItemBuilder.buildItem("&a+100", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getAdd1000() {
        return ItemBuilder.buildItem("&a+1000", Material.GOLD_NUGGET, 1, 0, new ArrayList<>());
    }

    public ItemStack getPaperDetails(String houseName) {
        //    return ItemBuilder.buildItem("%NPrice: %A" + Integer.toString(HouseQuery.getHousePrice(houseName)),
        //        Material.PAPER, 1, 0, new ArrayList<>());
        return null;
    }

    public ItemStack getHouseDetails(String houseName) {
        long time = TEMP.playerhousingdatac.getLong(houseName + ".rent");

        long days = 0;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long finalTime = time - currentTime;

        days = finalTime / 1000 / 60 / 60 / 24;

        ArrayList<String> lores = new ArrayList<>();
        lores.add((days != 0 ? "%A" + Long.toString(days) + " %Ndays left" : "%E> 1 day left"));
        lores.add("%AIncrease your rent time by 1 day");

        return ItemBuilder.buildItem("%NPay rent!", Material.LEVER, 1, 0, lores);
    }
}