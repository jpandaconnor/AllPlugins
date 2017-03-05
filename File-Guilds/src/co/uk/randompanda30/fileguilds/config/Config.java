package co.uk.randompanda30.fileguilds.config;

import co.uk.randompanda30.fileguilds.Guilds;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.fileguilds.TEMP.*;

public class Config {

    public Config(Guilds plugin) {
        try {
            config = new File("plugins/" + plugin.getName() + "/config.yml");
            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }

            configc = new YamlConfiguration();
            configcs = configc.getConfigurationSection("");
            configc.load(config);

            for (ConfigValues value : ConfigValues.values()) {
                if (configc.get(value.name().replaceAll("_", ".")) == null) {
                    configc.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = configc.get(value.name().replaceAll("_", "."));
            }

            configc.load(config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            configc.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum ConfigValues {
        MODE("file"),

        PATCH(false),

        PLOTWORLD("plots"),
        FALLBACKWORLD("AincradFloor1");

        public Object value;

        ConfigValues(Object value) {
            this.value = value;
        }
    }
}