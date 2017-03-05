package co.uk.randompanda30.mici.event;

/* 
   Created by panda on 17/07/16.
   
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

import co.uk.randompanda30.mici.Mici;
import co.uk.randompanda30.mici.TEMP;
import co.uk.randompanda30.mici.config.Messages;
import co.uk.randompanda30.mici.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Iterator;
import java.util.Map;

public class OnPlayerAsyncChatEvent implements Listener {

    public OnPlayerAsyncChatEvent() {
        Mici.getPlugin().getServer().getPluginManager().registerEvents(this, Mici.getPlugin());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChatEvent(PlayerChatEvent event) {
        Player player = event.getPlayer();

        String message = event.getMessage();

        Iterator i = TEMP.questions.entrySet().iterator();

        while(i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();

            String[] words = (String[]) pair.getKey();
            String response = (String) pair.getValue();

            int length = words.length;
            int found = 0;

            for(String ss : words) {
                if(message.contains(ss)) {
                    found++;
                }
            }

            if(length == found) {
                Dispatch.sendMessage(" ", false, player);
                Dispatch.sendMessage(Messages.MessagesValues.ATTENTION.value.toString().
                        replace("%player", player.getName()) + response, false, player);
                Dispatch.sendMessage(" ", false, player);

                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(Mici.getPlugin(), () ->
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 0.2f), 5L);
                break;
            }
        }

        // ArrayList of String[]

            // Check through every message
            // Get the length of the array
            // loop through array
            // check if message contains


    }
}