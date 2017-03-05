package co.uk.randompanda30.sao.events;

/* 
   Created by panda on 16/08/16.
   
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
import co.uk.randompanda30.sao.items.GenericItems;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.modules.housing.HouseEditor;
import co.uk.randompanda30.sao.modules.housing.HousePlayerQuery;
import co.uk.randompanda30.sao.modules.housing.district.DistrictCreator;
import co.uk.randompanda30.sao.modules.housing.district.DistrictHandler;
import co.uk.randompanda30.sao.modules.player.PurgePlayerData;
import co.uk.randompanda30.sao.string.Dispatch;
import co.uk.randompanda30.sao.view.V_CONFIRM;
import co.uk.randompanda30.sao.view.V_SAO;
import co.uk.randompanda30.sao.view.admin.V_ADMIN_PH;
import co.uk.randompanda30.sao.view.data.ViewNames;
import co.uk.randompanda30.sao.view.data.ViewProcedure;
import co.uk.randompanda30.sao.view.selection.SELECT_DISTRICT;
import co.uk.randompanda30.sao.view.submenus.V_MENU_PH_OWNED;
import co.uk.randompanda30.sao.view.submenus.V_MENU_PH_UNOWNED;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.*;

public class OnInventoryClickEvent implements Listener {

    public OnInventoryClickEvent() {
        SAO.getPlugin().getServer().getPluginManager().registerEvents(this, SAO.getPlugin());
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();

        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }

        /*
        Generic stuff here
         */

        // Exit
        if (ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getExit())) {
            event.setCancelled(true);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);

            HashMap<ViewProcedure, ArrayList<UUID>> map = TEMP.viewonwards;
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                ViewProcedure vp = (ViewProcedure) pair.getKey();
                ArrayList<UUID> in = (ArrayList<UUID>) pair.getValue();

                if (!in.contains(uuid)) {
                    continue;
                }

                switch (vp) {
                    case RESETPDATA:
                        in.remove(uuid);

                        TEMP.viewonwards.put(vp, in);
                        break;
                }

                it.remove();
            }

            player.closeInventory();
        }

        // Confirmation menu - check what state in here
        if (event.getInventory().getName().equals(ViewNames.CONFIRMATION_MENU.value)) {
            event.setCancelled(true);

            HashMap<ViewProcedure, ArrayList<UUID>> map = TEMP.viewonwards;
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                ViewProcedure vp = (ViewProcedure) pair.getKey();
                ArrayList<UUID> in = (ArrayList<UUID>) pair.getValue();

                if (!in.contains(uuid)) {
                    continue;
                }

                switch (vp) {
                    case RESETPDATA:
                        in.remove(uuid);
                        TEMP.viewonwards.put(vp, in);
                        player.closeInventory();
                        new PurgePlayerData(player).start();
                        break;
                }

                it.remove();
            }
        }

        // Main menu
        if (event.getInventory().getName().equals(ViewNames.SAO_MENU.value)) {
            event.setCancelled(true);

            if (ItemBuilder.compareItems(event.getCurrentItem(), V_SAO.V_SAO_ITEMS.PLAYERDATA_RESET.item)) {
                if (!TEMP.viewonwards.containsKey(ViewProcedure.RESETPDATA)) {
                    ArrayList<UUID> in = new ArrayList<>();
                    in.add(uuid);

                    TEMP.viewonwards.put(ViewProcedure.RESETPDATA, in);
                } else {
                    ArrayList<UUID> in = TEMP.viewonwards.get(ViewProcedure.RESETPDATA);
                    in.add(uuid);

                    TEMP.viewonwards.put(ViewProcedure.RESETPDATA, in);
                }
                new V_CONFIRM().openView(player);
            }

            if (ItemBuilder.compareItems(event.getCurrentItem(), V_SAO.V_SAO_ITEMS.HOUSING_ENTER.item)) {
                if (!HousePlayerQuery.hasHouse(player)) {
                    // Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_NOHOUSE.value, player);
                    // player.closeInventory();
                    new V_MENU_PH_UNOWNED().openView(player);
                } else {
                    new V_MENU_PH_OWNED().openView(player);
                }
            }
        }

        /*
            Housing menus
         */

        if (event.getInventory().getName().equals(ViewNames.HOUSE_OWNED_MENU.value)) {
            event.setCancelled(true);


        }

        if (event.getInventory().getName().equals(ViewNames.HOUSE_UNOWNED_MENU.value)) {
            event.setCancelled(true);

            if (ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getBack())) {
                new V_SAO().openView(player);
            }

            if (ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getAdminMenu())) {
                new V_ADMIN_PH().openView(player);
            }
        }

        /*
        Admin menus
         */

        if (event.getInventory().getName().equals(ViewNames.ADMIN_PH.value)) {
            event.setCancelled(true);

            if (ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getBack())) {
                if (!HousePlayerQuery.hasHouse(player)) {
                    new V_MENU_PH_UNOWNED().openView(player);
                } else {
                    new V_MENU_PH_OWNED().openView(player);
                }
            }

            if (ItemBuilder.compareItems(event.getCurrentItem(), V_ADMIN_PH.V_ADMIN_PH_ITEMS.ADMIN_CREATEDISTRICT.item)) {
                new DistrictCreator(player);
            }

            if (ItemBuilder.compareItems(event.getCurrentItem(), V_ADMIN_PH.V_ADMIN_PH_ITEMS.ADMIN_CREATEHOUSE.item)) {
                if (!DistrictHandler.isAnyDistricts()) {
                    Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_NODISTRICTS.value, player);
                    player.closeInventory();
                    return;
                }

                new SELECT_DISTRICT(player).open();



                // Dispatch.sendListMessage((ArrayList<String>) Messages.MessagesValues.
                   //     MODULES_PLAYERHOUSING_EDITOR_JOIN_DETAILS.value, player);
                // new HouseEditor(player);
            }
        }

        /*


        Selection menus


         */

        String[] districtselection = new String[] {
                "select", "a", "district"
        };

        int dsselectionlength = districtselection.length;
        int dsfound = 0;

        for(String s : districtselection) {
            if(event.getInventory().getName().toLowerCase().contains(s.toLowerCase())) {
                Bukkit.broadcastMessage(s);
                dsfound++;
            }
        }

        if(dsfound == dsselectionlength) {
            event.setCancelled(true);

            if(ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getBackExit())) {
                new V_ADMIN_PH().openView(player);
            }

            if(ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getForward())) {
                TEMP.selectionDistricts.get(player.getUniqueId()).nextPage();
            }

            if(ItemBuilder.compareItems(event.getCurrentItem(), GenericItems.getItems().getBack())) {
                TEMP.selectionDistricts.get(player.getUniqueId()).previousPage();
            }

            if(event.getCurrentItem().getType().equals(Material.BOOK)) {
                player.closeInventory();
                TEMP.selectionDistricts.remove(player.getUniqueId());

                Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_DISTRICTCHOSEN.value,
                        player);

                Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () -> {
                    new HouseEditor(player, event.getCurrentItem().getItemMeta().getDisplayName());
                    Dispatch.sendListMessage((ArrayList<String>) Messages.MessagesValues.
                                    MODULES_PLAYERHOUSING_EDITOR_JOIN_DETAILS.value,
                            player);
                }, 40L);
            }
        }
    }
}