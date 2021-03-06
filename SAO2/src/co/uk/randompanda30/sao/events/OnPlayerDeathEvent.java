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
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeathEvent implements Listener {

    public OnPlayerDeathEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        int xp = event.getDroppedExp();

        if (BoosterHandler.isBoosterRunning()) {
            int perc = BoosterHandler.getRunningBooster().getPercentage();
            if (!(perc < 0)) {
                event.setDroppedExp((((perc / 100) * xp) + xp) * 2);
            } else {
                int div = perc * -1;
                double newDiv = (div / 100.0);
                event.setDroppedExp((int) (xp - (newDiv * xp)));
            }
        }
    }
}