package co.uk.randompanda30.sao.task;

/* 
   Created by panda on 18/08/16.
   
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
import co.uk.randompanda30.sao.sound.Yell;
import co.uk.randompanda30.sao.string.Dispatch;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ShutdownTask extends BukkitRunnable {

    // Every 1 second

    @Override
    public void run() {
        long t = TEMP.restartcounter;

        if (t == 215800) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(10)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215820) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(9)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215840) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(8)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215860) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(7)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215880) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(6)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215900) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(5)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215920) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(4)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215940) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(3)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215960) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(2)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215980) {
            Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_AUTOSHUTDOWN_SHUTDOWNIN.value.toString()
                    .replace("%time", Integer.toString(1)), true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);

            if (TEMP.isBackingup) {
                Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_STOPPEDFORBACKUP.value,
                        true);
                TEMP.pendingRestart = true;
                this.cancel();
                return;
            }
        } else if (t == 216000) {
            if ((Boolean) Config.ConfigValues.AUTOSHUTDOWN_SENDTOBUNGEE.value) {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();

                out.writeUTF("Connect");
                out.writeUTF("hub");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendPluginMessage(SAO.getPlugin(), "BungeeCord", out.toByteArray());
                }
            } else {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer("Scheduled restart. Join again in 2 minutes");
                }
            }

            SAO.getPlugin().getServer().savePlayers();

            for (World world : SAO.getPlugin().getServer().getWorlds()) {
                world.save();
                SAO.getPlugin().getServer().unloadWorld(world, true);
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () ->
                    SAO.getPlugin().getServer().shutdown(), 100L);
        }

        TEMP.restartcounter = TEMP.restartcounter + 20;
    }
}