package co.uk.RandomPanda30.SAdminO;

import co.uk.RandomPanda30.SAdminO.MySQL.MySQL;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Data {

    public static File configF;
    public static FileConfiguration config;
    public static ConfigurationSection configC;

    public static File messagesF;
    public static FileConfiguration messages;
    public static ConfigurationSection messagesC;

    public static MySQL mysql;

    public static boolean initialised = false;

    private static Collection collection = new Collection();

    public static Collection getCollection() {
        return collection;
    }
}