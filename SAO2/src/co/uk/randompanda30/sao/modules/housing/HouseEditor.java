package co.uk.randompanda30.sao.modules.housing;

/* 
   Created by panda on 27/08/16.
   
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
import co.uk.randompanda30.sao.items.PlayerHousingItems;
import co.uk.randompanda30.sao.object.PlayerData;
import co.uk.randompanda30.sao.string.*;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HouseEditor {

    /*
    Theory -

    Player is given blocks which they will have to place all of them down
    Then they have to select a district - don't allow to create if no districts
    Random number given

    World guard region created
    done
     */

    static final ArrayList<BlockFace> faces = new ArrayList<BlockFace>() {
        {
            add(BlockFace.NORTH);
            add(BlockFace.EAST);
            add(BlockFace.SOUTH);
            add(BlockFace.WEST);

            add(BlockFace.DOWN);
            add(BlockFace.UP);
        }
    };

    public Player player;
    public PlayerData playerData;

    public ArrayList<String> selection = new ArrayList<>();
    public List<BlockVector2D> points = Lists.newArrayList();

    public Location spawn = null;
    public World world;

    public Thread thread;

    public String districtChosen;

    boolean selectedDistrict = false;
    boolean e = true;
    boolean s = true;
    boolean f = false;

    int minY = 300;
    int maxY = 0;
    int i = 0;
    int ii = 1000;

    public HouseEditor(Player player, String district) {
        this.player = player;
        this.districtChosen = district;

        playerData = new PlayerData(player);

        player.setGameMode(GameMode.CREATIVE);
        player.getInventory().clear();

        player.getInventory().setHelmet(
                new ItemStack(Material.AIR));
        player.getInventory().setChestplate(
                new ItemStack(Material.AIR));
        player.getInventory().setLeggings(
                new ItemStack(Material.AIR));
        player.getInventory().setBoots(
                new ItemStack(Material.AIR));

        player.setAllowFlight(true);
        player.setFlying(true);

        player.getInventory().setItem(0, PlayerHousingItems.getItems().getEditorBlock());
        player.getInventory().setItem(1, PlayerHousingItems.getItems().getEditorSpawn());
        player.getInventory().setItem(4, PlayerHousingItems.getItems().getEditorReset());
        // 6 can be the district choose
        player.getInventory().setItem(7, PlayerHousingItems.getItems().getEditorDone());
        player.getInventory().setItem(8, PlayerHousingItems.getItems().getEditorLeave());

        TEMP.editingHouses.add(this);
    }

    public void startSelection(Block block) {
        block.getChunk().load();
        Bukkit.getServer().getScheduler().runTaskAsynchronously(SAO.getPlugin(),
                () -> {
                    thread = new Thread() {
                        public void run() {
                            processLocation(block);
                        }

                        public void processLocation(Block block) {
                            Location location = block.getLocation();

                            if (world == null) {
                                world = location.getWorld();
                            }

                            int currenyT = (int) location.getY();

                            if (currenyT > maxY) {
                                maxY = currenyT;
                            }

                            if (currenyT < minY) {
                                minY = currenyT;
                            }

                            ApplicableRegionSet rgs = SAO.getWorldGuard().getRegionManager(world)
                                    .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

                            if (!rgs.getRegions().isEmpty()) {
                                for (ProtectedRegion rr : rgs.getRegions()) {
                                    if (rr.getId().contains("hs_")) {
                                        if (s) {
                                            Dispatch.sendMessage((String) Messages.MessagesValues.
                                                    MODULES_PLAYERHOUSING_EDITOR_SELECTION_OVERLAP.value, player);
                                            if (!f) {
                                                Bukkit.getScheduler().runTask(SAO.getPlugin(), ()
                                                        -> block.setType(Material.AIR));
                                                f = true;
                                            }
                                            s = false;
                                        }
                                        e = false;
                                        return;
                                    }
                                }
                            }

                            if (!f) {
                                Bukkit.getServer().getScheduler().runTask(SAO.getPlugin(), () ->
                                        block.setType(Material.AIR));
                                player.sendBlockChange(location, Material.STATIONARY_WATER, (byte) 0);
                                points.add(new BlockVector2D(location.getX(), location.getZ()));
                                selection.add(co.uk.randompanda30.sao.string.Location.decompileLocation(location));
                                f = true;
                            }

                            if (e) {
                                if (block.getType().equals(Material.AIR)) {
                                    player.sendBlockChange(location, Material.STATIONARY_WATER, (byte) 0);
                                }

                                for (BlockFace bf : faces) {
                                    Block nb = block.getRelative(bf);
                                    if (nb.getType().equals(Material.AIR)) {
                                        if (!selection.contains(co.uk.randompanda30.sao.string.Location.decompileLocation(
                                                nb.getLocation()
                                        ))) {
                                            if (i >= (Integer) Config.ConfigValues.SELECTLIMIT.value) {
                                                if (s) {
                                                    Dispatch.sendMessage((String) Messages.MessagesValues.
                                                            MODULES_PLAYERHOUSING_EDITOR_SELECTION_OVERLIMIT.value, player);
                                                    s = false;
                                                }
                                                e = false;
                                                return;
                                            }

                                            points.add(new BlockVector2D(nb.getLocation().getX(), nb.getLocation().getZ()));
                                            selection.add(co.uk.randompanda30.sao.string.Location.decompileLocation
                                                    (nb.getLocation()));
                                            i++;
                                            processLocation(nb);
                                        }
                                    }
                                }
                            }

                            if (i > ii) {
                                ii += 1000;
                                Runtime.getRuntime().freeMemory();
                            }
                        }
                    };

                    thread.setPriority(Thread.MAX_PRIORITY);
                    thread.start();

                    try {
                        thread.join();
                        Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_SELECTION_FINISHED.value,
                                player);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
    }

    public void revert() {
        Bukkit.getServer().getScheduler().runTask(SAO.getPlugin(), () -> {
            player.getInventory().clear();

            player.setGameMode(playerData.gamemode);

            player.getInventory().setContents(playerData.inventory);
            player.getInventory().setArmorContents(playerData.armour);

            player.setExp(playerData.exp);
            player.setLevel(playerData.level);

            player.setHealth(playerData.health);
            player.setFoodLevel(playerData.food);

            player.addPotionEffects(playerData.effects);

            player.setAllowFlight(playerData.allowFlight);
            player.setFlying(playerData.flying);
        });
    }

    public void end() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () -> {
            player.getInventory().clear();

            revert();
        }, 100L);

        for (String l : selection) {
            player.sendBlockChange(co.uk.randompanda30.sao.string.Location.compileLocation(l), Material.AIR, (byte) 0);
        }

        if (TEMP.editingHouses.contains(this)) {
            TEMP.editingHouses.remove(this);
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_EDITOR_LEAVE_LEFT.value, player);
    }

    public void stop() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SAO.getPlugin(), () -> {
            player.getInventory().clear();

            player.getInventory().setItem(0, PlayerHousingItems.getItems().getEditorBlock());
            player.getInventory().setItem(1, PlayerHousingItems.getItems().getEditorSpawn());
            player.getInventory().setItem(4, PlayerHousingItems.getItems().getEditorReset());
            // 6 can be the district choose
            player.getInventory().setItem(7, PlayerHousingItems.getItems().getEditorDone());
            player.getInventory().setItem(8, PlayerHousingItems.getItems().getEditorLeave());
        }, 100L);

        spawn = null;

        thread.stop();

        for (String l : selection) {
            player.sendBlockChange(co.uk.randompanda30.sao.string.Location.compileLocation(l), Material.AIR, (byte) 0);
        }

        i = 0;

        selection.clear();
        points.clear();

        e = true;
        s = true;
        f = false;

        Dispatch.sendMessage((String) Messages.MessagesValues.MODULES_PLAYERHOUSING_EDITOR_RESET_RESET.value, player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public Location getSpawn() {
        return spawn;
    }
}