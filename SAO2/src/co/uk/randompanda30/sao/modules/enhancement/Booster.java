package co.uk.randompanda30.sao.modules.enhancement;

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
import org.bukkit.Bukkit;

import java.util.Calendar;
import java.util.UUID;

public class Booster {

    String name;
    UUID uuid;

    long startTime;
    long endtime;
    long duration;

    int percentage;

    int id;

    public Booster(String name, UUID uuid, long startTime, long endtime, long duration, int percentage) {
        this.name = name;
        this.uuid = uuid;
        this.startTime = startTime;
        this.endtime = endtime;
        this.duration = duration;
        this.percentage = percentage;
        start();
    }

    public void start() {
        BoosterHandler.startBooster(this);

        setID(Bukkit.getScheduler().scheduleSyncRepeatingTask(SAO.getPlugin(), () -> {
            if (Calendar.getInstance().getTimeInMillis() > endtime) {
                end();
            }
        }, 0L, 100L));
    }

    private void end() {
        BoosterHandler.stopBooster(this);
        Bukkit.getScheduler().cancelTask(id);
    }

    private void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getPercentage() {
        return percentage;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndtime() {
        return endtime;
    }

    public long getDuration() {
        return duration;
    }
}