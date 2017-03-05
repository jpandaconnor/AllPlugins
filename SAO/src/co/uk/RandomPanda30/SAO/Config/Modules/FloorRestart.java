package co.uk.RandomPanda30.SAO.Config.Modules;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FloorRestart {

    public static File floorrestart;
    public static FileConfiguration floorrestartf;
    public static ConfigurationSection floorrestarts;

    public FloorRestart() {
        init();
    }

    public static void save() {
        try {
            floorrestartf.save(floorrestart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            floorrestart = new File("plugins/" + SAO.defaults.getName() + "/modules/floorrestart.yml");
            if (!floorrestart.exists()) {
                floorrestart.getParentFile().mkdirs();
                floorrestart.createNewFile();
            }

            floorrestartf = new YamlConfiguration();
            floorrestarts = floorrestartf.getConfigurationSection("");

            floorrestartf.load(floorrestart);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}