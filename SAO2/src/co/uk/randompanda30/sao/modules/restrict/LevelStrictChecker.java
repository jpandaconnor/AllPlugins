package co.uk.randompanda30.sao.modules.restrict;

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
import co.uk.randompanda30.sao.config.modules.CLevelBound;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LevelStrictChecker extends BukkitRunnable {

    SAO plugin;

    public LevelStrictChecker() {
        this.plugin = SAO.getPlugin();
    }

    @Override
    public void run() {
        ConcurrentHashMap<Integer, ArrayList<ItemStack>> items = new ConcurrentHashMap<>();

        // levels.5....items
        // levels.6....items
        // Number
        // List of items

        if (TEMP.levelboundf.contains("levels")) {
            for (String s : TEMP.levelboundf.getConfigurationSection("levels").getKeys(false)) {
                ArrayList<ItemStack> is = (ArrayList<ItemStack>) TEMP.levelboundf.get("levels." +
                        Integer.parseInt(s) + ".items");

                items.put(Integer.parseInt(s), is);
            }

            Bukkit.getOnlinePlayers().stream().filter(player ->
                    !player.hasPermission("sao.levelbound.bypass")).forEach(player -> {
                int playerLevel = player.getLevel();

                int itemsTakenOut = 0;

                Iterator i = items.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry pair = (Map.Entry) i.next();

                    int l = (int) pair.getKey();
                    ArrayList<ItemStack> ilist = (ArrayList<ItemStack>) pair.getValue();

                    if (playerLevel < l) {
                        for (ItemStack content : player.getInventory().getContents()) {
                            if (ilist.contains(content)) {
                                itemsTakenOut++;
                                if (TEMP.levelboundf.contains("dump." + player.getUniqueId().toString() + "." +
                                        Integer.toString(l))) {
                                    ArrayList<ItemStack> iss = (ArrayList<ItemStack>) TEMP.boosterf.get
                                            ("dump." + player.getUniqueId().toString() + "." + Integer.toString(l));
                                    iss.add(content);
                                    TEMP.levelboundf.set("dump." + player.getUniqueId().toString() + "." +
                                            Integer.toString(l), iss);
                                    CLevelBound.save();
                                } else {
                                    TEMP.levelboundf.set("dump." + player.getUniqueId().toString() + "." +
                                            Integer.toString(l), new ArrayList<ItemStack>() {
                                        {
                                            add(content);
                                        }
                                    });
                                    CLevelBound.save();
                                }
                                player.getInventory().remove(content);
                            }
                        }
                    }
                }

                if (itemsTakenOut != 0) {
                    Dispatch.sendMessage(Messages.MessagesValues.MODULES_ITEMRESTRICT_ITEMS.value.toString()
                            .replace("%amount", Integer.toString(itemsTakenOut)), player);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}