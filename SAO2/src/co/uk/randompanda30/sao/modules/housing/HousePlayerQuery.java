package co.uk.randompanda30.sao.modules.housing;

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

import co.uk.randompanda30.sao.TEMP;
import org.bukkit.entity.Player;

public class HousePlayerQuery {

    public static boolean hasHouse(Player player) {
        for (String s : TEMP.playerhousingdatac.getConfigurationSection("").getKeys(false)) {
            if (HouseHomeQuery.getHouseState(s) == HouseState.SOLD) {
                if (HouseHomeQuery.getHomeOwner(s).equals(player.getUniqueId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getHouse(Player player) {
        for (String s : TEMP.playerhousingdatac.getConfigurationSection("").getKeys(false)) {
            if (HouseHomeQuery.getHouseState(s) == HouseState.SOLD) {
                if (HouseHomeQuery.getHomeOwner(s).equals(player.getUniqueId())) {
                    return s;
                }
            }
        }
        return null;
    }

    public static boolean isPlayerEditing(Player player) {
        for(HouseEditor hs : TEMP.editingHouses) {
            if(hs.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    public static HouseEditor getHouseEditorObject(Player player) {
        for(HouseEditor hs : TEMP.editingHouses) {
            if(hs.getPlayer().equals(player)) {
                return hs;
            }
        }
        return null;
    }
}