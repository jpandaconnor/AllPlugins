package co.uk.randompanda30.houseshop.object;

import co.uk.randompanda30.houseshop.HouseShop;
import co.uk.randompanda30.houseshop.HouseState;
import co.uk.randompanda30.houseshop.TEMP;
import co.uk.randompanda30.houseshop.config.Config;
import co.uk.randompanda30.houseshop.config.Data;
import co.uk.randompanda30.houseshop.config.Messages;
import co.uk.randompanda30.houseshop.item.Items;
import co.uk.randompanda30.houseshop.string.Dispatch;
import co.uk.randompanda30.houseshop.string.LocationS;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 02/05/16.
 */
public class HouseEditor {

    static final ArrayList<BlockFace> faces = new ArrayList<BlockFace>() {
        {
            add(BlockFace.NORTH);
            add(BlockFace.EAST);
            add(BlockFace.SOUTH);
            add(BlockFace.WEST);

            add(BlockFace.DOWN);
            add(BlockFace.UP);

            // add(BlockFace.NORTH_EAST);
            // add(BlockFace.NORTH_WEST);
            // add(BlockFace.SOUTH_EAST);
            // add(BlockFace.SOUTH_WEST);
        }
    };

    public Player player;
    public PlayerData data;
    // public ArrayList<String> selection = new ArrayList<>();
    public List<BlockVector2D> points = Lists.newArrayList();
    public ArrayList<String> selection = new ArrayList<>();
    public Location spawn = null;
    int minY = 300;
    int maxY = 0;
    boolean e = true;
    boolean s = true;
    boolean f = false;
    boolean typingName = false;
    int i = 0;
    int ii = 1000;
    World world = null;

    Thread thread;

    public HouseEditor(Player player) {
        this.player = player;

        data = new PlayerData(player);

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

        player.getInventory().setItem(0, Items.getItems().editor_block);
        player.getInventory().setItem(1, Items.getItems().editor_spawn);
        player.getInventory().setItem(4, Items.getItems().editor_reset);
        player.getInventory().setItem(6, Items.getItems().editor_done);
        player.getInventory().setItem(8, Items.getItems().editor_leave);

        TEMP.editing.add(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void startSelection(Block block) {
        block.getChunk().load();
        Bukkit.getServer().getScheduler().runTaskAsynchronously(HouseShop.getPlugin(),
                () -> {
                    thread = new Thread() {
                        public void run() {
                            processLocation(block);
                        }

                        public void processLocation(Block block) {
                            Location location = block.getLocation();

                            if(world == null) {
                                world = location.getWorld();
                            }

                            int currenyT = (int) location.getY();

                            if(currenyT > maxY) {
                                maxY = currenyT;
                            }

                            // If number is bigger than stored, add

                            // CurrentT is never less than 0 you fucking idiot

                            if(currenyT < minY) {
                                minY = currenyT;
                            }

                            // If number is less than stored, add

                            ApplicableRegionSet rgs = HouseShop.getWorldGuard().getRegionManager(world)
                                    .getApplicableRegions(new Vector(location.getX(), location.getY(), location.getZ()));

                            if(!rgs.getRegions().isEmpty()) {
                                for(ProtectedRegion rr : rgs.getRegions()) {
                                    if(rr.getId().contains("hs_")) {
                                        if (s) {
                                            Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_SELECTION_OVERLAP.value, player);
                                            if (!f) {
                                                Bukkit.getScheduler().runTask(HouseShop.getPlugin(), ()
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
                                Bukkit.getServer().getScheduler().runTask(HouseShop.getPlugin(), () ->
                                        block.setType(Material.AIR));
                                player.sendBlockChange(location, Material.STATIONARY_WATER, (byte) 0);
                                // selection.add(LocationS.decompileLocation(location));
                                points.add(new BlockVector2D(location.getX(), location.getZ()));
                                selection.add(LocationS.decompileLocation(location));
                                f = true;
                            }

                            if (e) {
                                if (block.getType().equals(Material.AIR)) {
                                    player.sendBlockChange(location, Material.STATIONARY_WATER, (byte) 0);
                                }

                                for (BlockFace bf : faces) {
                                    Block nb = block.getRelative(bf);
                                    if (nb.getType().equals(Material.AIR)) {
                                        // if (!selection.contains(LocationS.decompileLocation(nb.getLocation()))) {
                                        if (!selection.contains(LocationS.decompileLocation(nb.getLocation()))) {
                                            if (i >= (Integer) Config.ConfigValues.SELECTLIMIT.value) {
                                                if (s) {
                                                    Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_SELECTION_OVERLIMIT.value, player);
                                                    s = false;
                                                }
                                                e = false;
                                                return;
                                            }

                                            points.add(new BlockVector2D(nb.getLocation().getX(), nb.getLocation().getZ()));
                                            selection.add(LocationS.decompileLocation(nb.getLocation()));
                                            // selection.add(LocationS.decompileLocation(nb.getLocation()));
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
                        Dispatch.sendMessage((String) Messages.MessagesValues.SELECTION_FINISHED.value, player);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
    }

    private void revert() {
        Bukkit.getServer().getScheduler().runTask(HouseShop.getPlugin(), () -> {
            player.getInventory().clear();

            player.setGameMode(data.gamemode);

            player.getInventory().setContents(data.inventory);
            player.getInventory().setArmorContents(data.armour);

            player.setExp(data.exp);
            player.setLevel(data.level);

            player.setHealth(data.health);
            player.setFoodLevel(data.food);

            player.addPotionEffects(data.effects);

            player.setAllowFlight(data.allowFlight);
            player.setFlying(data.flying);
        });
    }

    public void end() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(HouseShop.getPlugin(), () -> {
            player.getInventory().clear();

            revert();
        }, 100L);


        for (String l : selection) {
            player.sendBlockChange(LocationS.compileLocation(l), Material.AIR, (byte) 0);
        }

        if (TEMP.editing.contains(this)) {
            TEMP.editing.remove(this);
        }

        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_LEAVE_LEFT.value, player);
    }

    public void reset() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(HouseShop.getPlugin(), () -> {
            player.getInventory().clear();

            player.getInventory().setItem(0, Items.getItems().editor_block);
            player.getInventory().setItem(1, Items.getItems().editor_spawn);
            player.getInventory().setItem(4, Items.getItems().editor_reset);
            player.getInventory().setItem(6, Items.getItems().editor_done);
            player.getInventory().setItem(8, Items.getItems().editor_leave);
        }, 100L);

        spawn = null;

        thread.stop();

        for (String l : selection) {
            player.sendBlockChange(LocationS.compileLocation(l), Material.AIR, (byte) 0);
        }

        i = 0;

        selection.clear();
        points.clear();

        e = true;
        s = true;
        f = false;

        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_RESET_RESET.value, player);
    }

    public void create(String name) {
        name = "hs_" + name;
        // TEMP.datac.set(name + ".locations", selection);
        TEMP.datac.set(name + ".region", name);
        TEMP.datac.set(name + ".price", 500);
        TEMP.datac.set(name + ".enabled", true);
        TEMP.datac.set(name + ".state", HouseState.FORSALE.toString());
        TEMP.datac.set(name + ".spawn", LocationS.decompileLocation(spawn));
        TEMP.datac.set(name + ".anyonein", false);
        TEMP.datac.set(name + ".build", false);

        RegionManager manager = HouseShop.getWorldGuard().getRegionManager(world);

        try {
            ProtectedRegion region = new ProtectedPolygonalRegion(name, points, minY, maxY);

            region.setPriority(0);

            manager.addRegion(region);
            manager.save();
        } catch (StorageException e1) {
            e1.printStackTrace();
        }

        Data.save();

        finish();

        Dispatch.sendMessage(Messages.MessagesValues.EDITOR_DONE_FINISHED.value.toString().replace("%name", name), player);
    }

    private void finish() {
        revert();

        for (String l : selection) {
            player.sendBlockChange(LocationS.compileLocation(l), Material.AIR, (byte) 0);
        }

        if (TEMP.editing.contains(this)) {
            TEMP.editing.remove(this);
        }
    }

    public void initTypingName() {
        if (selection.isEmpty()) {
            Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_SELECTION_NOTSELECTED.value, player);
            return;
        }
        typingName = true;
        Dispatch.sendMessage((String) Messages.MessagesValues.EDITOR_TYPINGNAME_PLEASETYPE.value, player);
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public boolean isSpawnPlaced() {
        return spawn != null;
    }

    public boolean isTypingName() {
        return typingName;
    }
}