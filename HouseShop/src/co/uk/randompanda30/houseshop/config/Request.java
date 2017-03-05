package co.uk.randompanda30.houseshop.config;

import co.uk.randompanda30.houseshop.HouseShop;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.houseshop.TEMP.*;

/**
 * Created by panda on 25/06/16.
 */
public class Request {

    public Request(HouseShop plugin) {
        try {
            request = new File("plugins/" + plugin.getName() + "/request.yml");
            if (!request.exists()) {
                request.getParentFile().mkdirs();
                request.createNewFile();
            }

            requestc = new YamlConfiguration();
            requestcs = requestc.getConfigurationSection("");

            requestc.load(request);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            requestc.save(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}