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
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.config.PATHS;
import co.uk.randompanda30.sao.config.modules.CBooster;
import co.uk.randompanda30.sao.effect.Play;
import co.uk.randompanda30.sao.sound.Yell;
import co.uk.randompanda30.sao.string.Dispatch;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.ChatPaginator;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static co.uk.randompanda30.sao.TEMP.*;

public class BoosterHandler {

    /*
    Handles everything that has to do with the Booster.class class
     */

    public static boolean isBoosterRunning() {
        return (currentBooster != null);
    }

    public static boolean isMessageShowing() {
        return showingBoosterMessage;
    }

    public static Booster getRunningBooster() {
        return currentBooster;
    }

    public static boolean isDumped() {
        return boosterf.contains((String) PATHS.BOOSTER_DUMP.value);
    }

    public static void startDumpedBooster() {
        String name = boosterf.getString((String) PATHS.BOOSTER_NAME.value);
        UUID uuid = UUID.fromString(boosterf.getString((String) PATHS.BOOSTER_UUID.value));

        long start = boosterf.getLong((String) PATHS.BOOSTER_ST.value);
        long end = boosterf.getLong((String) PATHS.BOOSTER_ET.value);
        long dump = boosterf.getLong((String) PATHS.BOOSTER_DT.value);
        long duration = boosterf.getLong((String) PATHS.BOOSTER_D.value);

        int percentage = boosterf.getInt((String) PATHS.BOOSTER_P.value);

        long a = dump - start;
        long b = duration - a;
        long c = end + b;

        new Booster(name, uuid, start, c, duration, percentage);

        boosterf.set((String) PATHS.BOOSTER_DUMP.value, null);
        CBooster.save();
    }

    public static void startBooster(Booster booster) {
        setBooster(booster);

        showingBoosterMessage = true;

        List<String> messages = (List<String>) Messages.MessagesValues.MODULES_BOOSTER_BOOSTERSTARTED.value;

        new BukkitRunnable() {
            @Override
            public void run() {
                for (String s : messages) {
                    SAO.getPlugin().getServer().getScheduler().runTask(SAO.getPlugin(), () -> {
                        if (Bukkit.getServer().getPlayer(booster.uuid) != null && Bukkit.getServer().getPlayer
                                (booster.uuid).isOnline()) {
                            Firework fw = (Firework) Bukkit.getServer().getPlayer(booster.uuid).getWorld()
                                    .spawnEntity(Bukkit.getServer().getPlayer(booster.uuid).getLocation(), EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            FireworkEffect effect = FireworkEffect.builder().flicker(false)
                                    .withColor(Color.FUCHSIA).withFade(Color.FUCHSIA)
                                    .with(FireworkEffect.Type.BALL_LARGE).trail(true).build();

                            fwm.addEffect(effect);
                            fwm.setPower(0);
                            fw.setFireworkMeta(fwm);
                        }
                    });

                    Dispatch.broadcastMessage(" ", true);

                    String newS = s;
                    newS = newS.replaceAll("%player", booster.name);
                    newS = newS.replaceAll("%perc", Integer.toString(booster.percentage));

                    Dispatch.broadcastMessage(StringUtils.center(newS, ChatPaginator.AVERAGE_CHAT_PAGE_WIDTH), true);
                    Yell.broadcastSound(Sound.NOTE_BASS, 5, 2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Yell.broadcastSound(Sound.LEVEL_UP, 5, 1);
                Play.broadcastEffect(Effect.ENDER_SIGNAL, 5);
                showingBoosterMessage = false;
            }
        }.runTaskAsynchronously(SAO.getPlugin());
    }

    public static void stopBooster(Booster booster) {
        removeBooster(booster);

        Yell.broadcastSound(Sound.ANVIL_BREAK, 5, 2);

        Dispatch.broadcastMessage(Messages.MessagesValues.MODULES_BOOSTER_EXPIRED.value.toString().replace("%player",
                booster.getName()), true);
    }

    public static void dumpBooster(Booster booster) {
        boosterf.set((String) PATHS.BOOSTER_NAME.value, booster.name);
        boosterf.set((String) PATHS.BOOSTER_UUID.value, booster.uuid.toString());

        boosterf.set((String) PATHS.BOOSTER_ST.value, booster.startTime);
        boosterf.set((String) PATHS.BOOSTER_DT.value, Calendar.getInstance().getTimeInMillis());
        boosterf.set((String) PATHS.BOOSTER_ET.value, booster.endtime);
        boosterf.set((String) PATHS.BOOSTER_D.value, booster.duration);
        boosterf.set((String) PATHS.BOOSTER_P.value, booster.percentage);

        CBooster.save();
    }

    private static void setBooster(Booster booster) {
        currentBooster = booster;
    }

    private static void removeBooster(Booster booster) {
        currentBooster = null;
    }
}