package co.uk.RandomPanda30.SAO.Config.Modules;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Reward {

    public static File reward;
    public static FileConfiguration rewardf;
    public static ConfigurationSection rewards;

    public Reward() {
        init();
    }

    public static void save() {
        try {
            rewardf.save(reward);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            reward = new File("plugins/" + SAO.defaults.getName() + "/modules/reward.yml");
            if (!reward.exists()) {
                reward.getParentFile().mkdirs();
                reward.createNewFile();
            }

            rewardf = new YamlConfiguration();
            rewards = rewardf.getConfigurationSection("");

            rewardf.load(reward);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}