package co.uk.randompanda30.sao.events;

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

import co.uk.randompanda30.sao.SAO;
import co.uk.randompanda30.sao.TEMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class OnPlayerLoginEvent implements Listener {

    public OnPlayerLoginEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (TEMP.playerdata_beingreset.contains(uuid)) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Player data reset still occurring. Please try later");
        }
    }
}