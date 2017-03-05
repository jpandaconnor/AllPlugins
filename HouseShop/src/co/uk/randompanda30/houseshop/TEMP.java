package co.uk.randompanda30.houseshop;

import co.uk.randompanda30.houseshop.object.ForSaleTimeout;
import co.uk.randompanda30.houseshop.object.HouseEditor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by panda on 02/05/16.
 */
public class TEMP {

    public static File config;
    public static FileConfiguration configc;
    public static ConfigurationSection configcs;

    public static File messages;
    public static FileConfiguration messagesc;
    public static ConfigurationSection messagescs;

    public static File data;
    public static FileConfiguration datac;
    public static ConfigurationSection datacs;

    public static File request;
    public static FileConfiguration requestc;
    public static ConfigurationSection requestcs;

    public static ArrayList<HouseEditor> editing = new ArrayList<>();

    public static HashMap<UUID, HashMap<String, Inventory>> editingHouses = new HashMap<>();
    public static ArrayList<ForSaleTimeout> forsalesTimeouts = new ArrayList<>();

    public static HashMap<String, ArrayList<String>> blocksPlaced = new HashMap<>();

    public static World world = Bukkit.getWorld("AincradFloor1");
}