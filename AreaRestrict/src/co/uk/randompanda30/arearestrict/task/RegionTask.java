package co.uk.randompanda30.arearestrict.task;

import co.uk.randompanda30.arearestrict.TEMP;
import co.uk.randompanda30.arearestrict.config.Messages;
import co.uk.randompanda30.arearestrict.string.Dispatch;
import co.uk.randompanda30.arearestrict.util.VectorUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.Arrays;
import java.util.List;

/**
 * Created by panda on 05/07/16.
 *
 *
 *   Copyright 2016 JPanda (Connor Brady)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

public class RegionTask extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.hasPermission("ar.bypass")) {
                String[] s = PermissionsEx.getUser(player).getGroupNames();
                List<String> pranks = Arrays.asList(s);

                if(TEMP.pdatac.getConfigurationSection("").getKeys(false) != null) {
                    for (String regions : TEMP.pdatac.getConfigurationSection("").getKeys(false)) {
                        Location min = (Location) TEMP.pdatac.get(regions + ".min");
                        Location max = (Location) TEMP.pdatac.get(regions + ".max");

                        VectorUtil vec = new VectorUtil(min.toVector(), max.toVector());

                        String regionrank = (String) TEMP.pdatac.get(regions + ".rank");

                        if (vec.contains(player.getLocation())) {
                            if (!pranks.contains(regionrank)) {
                                // Location ploc = TEMP.locations.get(player.getUniqueId());
                                // player.teleport(ploc);

                                // ector v = player.getLocation().toVector().normalize().add(new Vector(0.2D, 0D, 0D).normalize())

                                // Vector knockbackVector = player.getLocation().getDirection().multiply(-3*1.5).setY(3);
                                // player.setVelocity(knockbackVector);

                                player.setVelocity(player.getLocation().getDirection().multiply(-3));

                                Dispatch.sendMessage(Messages.MessagesValues.RESTRICTION_CANNOTGOIN.value.toString()
                                                .replace("%rank", ChatColor.stripColor(regionrank)), false,
                                        player);
                            } else {
                                TEMP.locations.put(player.getUniqueId(), player.getLocation());
                            }
                            break;
                        } else {
                            TEMP.locations.put(player.getUniqueId(), player.getLocation());
                        }
                    }
                }
            }
        }
    }
}