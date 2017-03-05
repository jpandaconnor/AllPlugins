package co.uk.randompanda30.sao.modules.housing.district;

/* 
   Created by panda on 02/09/16.
   
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
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.reflection.AnvilGUI;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DistrictCreator {

    private Player player;

    public DistrictCreator(Player player) {
        this.player = player;
        start();
    }

    private void start() {
        player.closeInventory();

        Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_DISTRICTCREATE_START.value, player);
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);

        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(SAO.getPlugin(), () -> {
            AnvilGUI gui = new AnvilGUI(player, event -> {
                if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                    if (event.getName() != null || !event.getName().equals("w ") || !event.getName().isEmpty()) {
                        event.setWillClose(true);
                        event.setWillDestroy(true);

                        stop(event.getName());
                    } else {
                        Dispatch.sendMessage((String) Messages.MessagesValues.
                                MODULES_PLAYERHOUSING_DISTRICTCREATE_NOTEXT.value, player);

                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);

                        event.setWillClose(true);
                        event.setWillDestroy(true);
                    }
                } else if (event.getSlot() == AnvilGUI.AnvilSlot.INPUT_LEFT) {
                    event.setWillClose(false);
                    event.setWillDestroy(false);
                } else if (event.getSlot() == AnvilGUI.AnvilSlot.INPUT_RIGHT) {
                    event.setWillClose(false);
                    event.setWillDestroy(false);
                }
            });

            gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.buildItem("Information", Material.PAPER,
                    1, 0, new ArrayList<String>() {
                        {
                            add("%TClick on the output name tag to create a");
                            add("%Tdistrict with that name");
                            add(" ");
                            add("%TIf the input is empty, nothing will be created");
                            add("  ");
                            add("%TTo exit, click outside the inventory or exit as normal");
                        }
                    }));

            gui.open();
        }, 100L);
    }

    private void stop(String name) {
        if (!DistrictHandler.isDistrict(name)) {
            DistrictHandler.addDistrct(name);

            Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_DISTRICTCREATE_CREATED.value,
                    player);

            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
        } else {
            Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_DISTRICTCREATE_ALREADYDIS.value,
                    player);
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
        }
    }
}