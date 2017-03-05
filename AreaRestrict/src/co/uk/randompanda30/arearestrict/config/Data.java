package co.uk.randompanda30.arearestrict.config;

import co.uk.randompanda30.arearestrict.AreaRestrict;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.arearestrict.TEMP.*;

public class Data {

    public Data(AreaRestrict plugin) {
        try {
            pdata = new File("plugins/" + plugin.getName() + "/data.yml");
            if (!pdata.exists()) {
                pdata.getParentFile().mkdirs();
                pdata.createNewFile();
            }

            pdatac = new YamlConfiguration();
            pdatacs = configc.getConfigurationSection("");

            pdatac.load(pdata);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            pdatac.save(pdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}