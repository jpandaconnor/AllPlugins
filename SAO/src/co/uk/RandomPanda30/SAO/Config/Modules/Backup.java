package co.uk.RandomPanda30.SAO.Config.Modules;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Backup {

    public static File backup;
    public static FileConfiguration backupf;
    public static ConfigurationSection backups;

    public Backup() {
        init();
    }

    public static void save() {
        try {
            backupf.save(backup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            backup = new File("plugins/" + SAO.defaults.getName() + "/modules/backup.yml");
            if (!backup.exists()) {
                backup.getParentFile().mkdirs();
                backup.createNewFile();
            }

            backupf = new YamlConfiguration();
            backups = backupf.getConfigurationSection("");

            backupf.load(backup);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}