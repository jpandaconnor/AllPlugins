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
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.items.BoosterItems;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.modules.enhancement.Booster;
import co.uk.randompanda30.sao.modules.enhancement.BoosterHandler;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Calendar;
import java.util.UUID;

public class OnPlayerInteractEvent implements Listener {

    public OnPlayerInteractEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        ItemStack item = player.getItemInHand();

        if (item == null) {
            return;
        }

        if (item.getType().equals(Material.AIR)) {
            return;
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (ItemBuilder.compareItems(item, BoosterItems.getItems().getBlankBoosterItem())) {
                String time = ChatColor.stripColor(item.getItemMeta().getLore().get(4)).replace("Time: ", "");
                String percentage = ChatColor.stripColor(item.getItemMeta().getLore().get(5)).replace("Percentage: ", "");

                if (!BoosterHandler.isBoosterRunning()) {
                    String ftime = "";
                    String[] t = {time};

                    long ft = 0;
                    long duration = 0;

                    long days = 0;
                    long hours = 0;
                    long minutes = 0;
                    long seconds = 0;

                    if (!time.contains("d") && !time.contains("h")
                            && !time.contains("m") && !time.contains("s")) {
                        ftime = "0";
                    } else {
                        if (time.contains("d")) {
                            t = t[0].split("d");
                            days = Integer.parseInt(t[0]);
                            if (t.length == 2) {
                                t[0] = t[1];
                            }
                        }

                        if (time.contains("h")) {
                            t = t[0].split("h");
                            hours = Integer.parseInt(t[0]);
                            if (t.length == 2) {
                                t[0] = t[1];
                            }
                        }

                        if (time.contains("m")) {
                            t = t[0].split("m");
                            minutes = Integer.parseInt(t[0]);
                            if (t.length == 2) {
                                t[0] = t[1];
                            }
                        }

                        if (time.contains("s")) {
                            t = t[0].split("s");
                            seconds = Integer.parseInt(t[0]);
                        }

                        if (!time.equals("0")) {
                            ft += Calendar.getInstance().getTimeInMillis()
                                    + (days * 1000 * 60 * 60 * 24)
                                    + (hours * 1000 * 60 * 60)
                                    + (minutes * 1000 * 60) + (seconds * 1000);
                            duration += (days * 1000 * 60 * 60 * 24)
                                    + (hours * 1000 * 60 * 60)
                                    + (minutes * 1000 * 60) + (seconds * 1000);
                        }

                        new Booster(player.getName(), uuid, Calendar.getInstance().getTimeInMillis(), ft, duration,
                                Integer.parseInt(percentage));
                    }

                    if (item.getAmount() == 1) {
                        player.getInventory().remove(item);
                    } else {
                        player.getInventory().remove(item);

                        item.setAmount(item.getAmount() - 1);
                        player.getInventory().addItem(item);

                        Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_BOOSTER_ACTIVATED.value, player);
                    }

                } else {
                    Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_BOOSTER_RUNNING.value, player);
                    event.setCancelled(true);
                }
            }
        }
    }
}