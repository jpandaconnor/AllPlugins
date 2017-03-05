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
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.sound.Yell;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoShutdownTask extends BukkitRunnable {

    // Every 10 seconds

    @Override
    public void run() {
        long t = TEMP.restartcounter;

        if (t == 144000) { // 1 hour left
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_1HOURLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 198000) { // 15 mins left
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_15MINSLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 204000) { // 10 mins left
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_10MINSLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 210000) { // 5 mins left
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_5MINSLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 214800) { // 1 min left
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_1MINLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);
        } else if (t == 215400) { // 30 seconds
            Dispatch.broadcastMessage((String) Messages.MessagesValues.MODULES_AUTOSHUTDOWN_30SECONDSLEFT.value, true);
            Yell.broadcastSound(Sound.NOTE_BASS, 1, 1);

            // Cancel timer0
            // Set to shutdown task
            TEMP.restartcounter = TEMP.restartcounter + 100;

            new ShutdownTask().runTaskTimer(SAO.getPlugin(), 0L, 20L);

            this.cancel();
            return;
        }

        TEMP.restartcounter = TEMP.restartcounter + 100;
    }
}