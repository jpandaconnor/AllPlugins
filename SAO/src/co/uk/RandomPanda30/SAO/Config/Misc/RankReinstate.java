package co.uk.RandomPanda30.SAO.Config.Misc;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RankReinstate {

    public static File beater;
    public static FileConfiguration beaterf;
    public static ConfigurationSection beaters;

    public static File beaterplus;
    public static FileConfiguration beaterplusf;
    public static ConfigurationSection beaterpluss;

    public RankReinstate() {
        init();
    }

    public void init() {
        try {
            beater = new File("plugins/" + SAO.defaults.getName() + "/modules/rankreinstate/beater.yml");
            if (!beater.exists()) {
                beater.getParentFile().mkdirs();
                beater.createNewFile();
            }

            beaterf = new YamlConfiguration();
            beaters = beaterf.getConfigurationSection("");

            beaterf.load(beater);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        try {
            beaterplus = new File("plugins/" + SAO.defaults.getName() + "/modules/rankreinstate/beaterplus.yml");
            if (!beaterplus.exists()) {
                beaterplus.getParentFile().mkdirs();
                beaterplus.createNewFile();
            }

            beaterplusf = new YamlConfiguration();
            beaterpluss = beaterplusf.getConfigurationSection("");

            beaterplusf.load(beaterplus);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            beaterf.save(beater);
            beaterplusf.save(beaterplus);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}