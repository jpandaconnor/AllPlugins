package co.uk.RandomPanda30.SAO.Modules.Guilds;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;

public class GuildData {

    public static ArrayList<Guild> guilds = new ArrayList<>();
    public static boolean usingMysql = false;

    public static File guildConfig;
    public static FileConfiguration guildConfigC;
    public static ConfigurationSection guildConfigCS;

}