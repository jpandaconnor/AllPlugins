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
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.modules.CBooster;
import co.uk.randompanda30.sao.config.modules.CPlayerDataReset;
import co.uk.randompanda30.sao.items.BoosterItems;
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class OnPlayerJoinEvent implements Listener {

    public OnPlayerJoinEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (BoosterHandler.isBoosterRunning()) {
            Dispatch.sendMessage(Messages.MessagesValues.MODULES_BOOSTER_ONGOING.value.toString().replace("%player",
                    BoosterHandler.getRunningBooster().getName()), player);
        }

        if (TEMP.boosterf.contains("tg." + uuid.toString())) {
            ArrayList<String> dumps = (ArrayList<String>) TEMP.boosterf.get("tg." + uuid.toString());

            int i = 0;
            for (ItemStack item : player.getInventory()) {
                if (item == null) {
                    i++;
                }
            }

            if (i < dumps.size()) {
                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_BOOSTER_FULLINVENTORY.value, player);
            } else {
                // args[0] = time = 2d5h
                // args[1] = percentage;

                for (String s : dumps) {
                    String[] ss = s.split(";");
                    String time = ss[0];
                    int percentage = Integer.parseInt(ss[1]);

                    player.getInventory().addItem(BoosterItems.getItems().getBoosterItem(time, percentage));
                }

                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_BOOSTER_BOOSTERSGIVEN.value, player);

                TEMP.boosterf.set("tg." + uuid.toString(), null);
                CBooster.save();
            }
        }

        if (TEMP.playerdatac.contains("dump." + uuid.toString())) {
            int code = (int) TEMP.playerdatac.get("dump." + uuid.toString());

            switch (code) {
                case 0:
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () ->
                            Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERDATA_RESETDONE.value,
                                    player), 40L);
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    break;
                case 1:
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () ->
                            Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERDATA_RESETFAILED.value,
                                    player), 40L);
                    player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
                    break;
            }

            TEMP.playerdatac.set("dump." + uuid.toString(), null);
            CPlayerDataReset.save();
        }
    }
}