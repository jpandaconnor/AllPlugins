package co.uk.randompanda30.sao.modules.player;

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
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.modules.CPlayerDataReset;
import co.uk.randompanda30.sao.string.Dispatch;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class PurgePlayerData {

    Player player;
    UUID uuid;

    String folderLoc = CPlayerDataReset.PlayerDataValues.RESET_PATH.value +
            (CPlayerDataReset.PlayerDataValues.RESET_PATH.value.toString().endsWith("/") ? "" : "/");

    public PurgePlayerData(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public void start() {
        if (!new File(folderLoc).exists()) {
            // Dump
            // uuid:
            // 1 = error
            // 0 success

            TEMP.playerdatac.set("dump." + uuid.toString(), 1);
            CPlayerDataReset.save();

            return;
        }

        announce();

        TEMP.playerdata_beingreset.add(uuid);
        // Delete files here

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () ->
                player.kickPlayer("Player data reset starting..."), 120L);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () -> {

            for (String s : (List<String>) CPlayerDataReset.PlayerDataValues.WORLDS_PATH.value) {
                s = s.endsWith("/") ? "" : "/";

                // NPC file
                if (new File(folderLoc + s + "customnpcs/playerdata/" + uuid.toString() + ".json").exists()) {
                    new File(folderLoc + s + "customnpcs/playerdata/" + uuid.toString() + ".json").delete();
                }

                // Player's world .dat file
                if (new File(folderLoc + s + "playerdata/" + uuid.toString() + ".dat").exists()) {
                    new File(folderLoc + s + "playerdata/" + uuid.toString() + ".dat").delete();
                }

                // Backpack x2
                if (new File(folderLoc + s + "playerdata/" + player.getName() + ".baub").exists()) {
                    new File(folderLoc + s + "playerdata/" + player.getName() + ".baub").delete();
                }

                if (new File(folderLoc + s + "playerdata/" + player.getName() + ".baubback").exists()) {
                    new File(folderLoc + s + "playerdata/" + player.getName() + ".baubback").delete();
                }

                // Stats
                if (new File(folderLoc + s + "stats/" + uuid.toString() + ".json").exists()) {
                    new File(folderLoc + s + "stats/" + uuid.toString() + ".json").delete();
                }

                // Essentials data - uuid.yml and name.yml
                if (new File(folderLoc + "plugins/Essentials/userdata/" + player.getName().toLowerCase() + ".yml").exists()) {
                    new File(folderLoc + "plugins/Essentials/userdata/" + player.getName().toLowerCase() + ".yml").delete();
                }

                if (new File(folderLoc + "plugins/Essentials/userdata/" + uuid.toString() + ".yml").exists()) {
                    new File(folderLoc + "plugins/Essentials/userdata/" + uuid.toString() + ".yml").delete();
                }
            }

            TEMP.playerdatac.set("dump." + uuid.toString(), 0);
            CPlayerDataReset.save();

            TEMP.playerdata_beingreset.remove(uuid);
        }, 80L);

        // inform on join that reset has been done
    }

    public void announce() {
        List<String> messages = (List<String>) Messages.MessagesValues.MODULES_PLAYERDATA_RESET.value;
        Bukkit.getScheduler().runTaskAsynchronously(SAO.getPlugin(), () -> {
            for (String s : messages) {
                Dispatch.sendMessage(StringUtils.center(s, ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH), player);
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 5, 2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}