package co.uk.RandomPanda30.SAO.Config.Modules;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Whitelist {

    public static File whitelist;
    public static FileConfiguration whitelistf;
    public static ConfigurationSection whitelists;

    public Whitelist() {
        init();
    }

    public static void save() {
        try {
            whitelistf.save(whitelist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            whitelist = new File("plugins/" + SAO.defaults.getName() + "/modules/whitelist.yml");
            if (!whitelist.exists()) {
                whitelist.getParentFile().mkdirs();
                whitelist.createNewFile();
            }

            whitelistf = new YamlConfiguration();
            whitelists = whitelistf.getConfigurationSection("");

            whitelistf.load(whitelist);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}