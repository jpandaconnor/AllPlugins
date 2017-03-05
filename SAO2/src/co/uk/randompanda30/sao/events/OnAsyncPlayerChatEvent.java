package co.uk.randompanda30.sao.events;

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

import co.uk.randompanda30.sao.SAO;
import co.uk.randompanda30.sao.TEMP;
import co.uk.randompanda30.sao.config.Config;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OnAsyncPlayerChatEvent implements Listener {

    public OnAsyncPlayerChatEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (BoosterHandler.isMessageShowing()) {
            event.setCancelled(true);
        }

        if (TEMP.playerdata_beingreset.contains(uuid)) {
            event.setCancelled(true);
            event.getRecipients().remove(player);
        }

        int caps = 0;
        String message = event.getMessage();

        if (message.length() > 5) {
            for (int i = message.length() - 1; i >= 0; i--) {
                if (Character.isUpperCase(message.charAt(i))) {
                    caps++;
                }
            }

            int perc = 20;
            if (caps * 100 / message.length() >= perc) {
                message = message.toLowerCase();
                event.setMessage(message);
            }
        }

        String[] chat = event.getMessage().split(" ");

        for (String w : chat) {
            String result = w.replaceAll("[-+.^:,!*%$£|/]", "");

            ((List<String>) Config.ConfigValues.FORBIDDEN_WORDS.value).stream().filter(result::equalsIgnoreCase).forEach(ww -> {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_CHATFILTER_DONOTSWEAR.value, player);
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 4, 1);
                event.setCancelled(true);
            });
        }

        Iterator i = TEMP.questions.entrySet().iterator();

        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();

            String[] words = (String[]) pair.getKey();
            String response = (String) pair.getValue();

            int length = words.length;
            int found = 0;

            for (String ss : words) {
                if (message.contains(ss)) {
                    found++;
                }
            }

            if (length == found) {
                Dispatch.sendMessage(" ", player);
                Dispatch.sendMessage(Messages.MessagesValues.MODULES_MICI_ATTENTION.value.toString().
                        replace("%player", player.getName()) + response, player);
                Dispatch.sendMessage(" ", player);

                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 1);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () ->
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 5, 0.2f), 5L);
                break;
            }
        }
    }
}