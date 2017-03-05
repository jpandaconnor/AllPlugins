package co.uk.randompanda30.houseshop.config;

import co.uk.randompanda30.houseshop.HouseShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.houseshop.TEMP.*;

/**
 * Created by panda on 02/05/16.
 */
public class Data {

    public Data(HouseShop plugin) {
        try {
            data = new File("plugins/" + plugin.getName() + "/data.yml");
            if (!data.exists()) {
                data.getParentFile().mkdirs();
                data.createNewFile();
            }

            datac = new YamlConfiguration();
            datacs = datac.getConfigurationSection("");

            datac.load(data);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            datac.save(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}