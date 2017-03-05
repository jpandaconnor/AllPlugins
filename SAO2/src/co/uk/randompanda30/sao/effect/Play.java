package co.uk.randompanda30.sao.effect;

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

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class Play {

    public static void broadcastEffect(Effect effect, int time) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playEffect(player.getLocation(), effect, time);
        }
    }
}