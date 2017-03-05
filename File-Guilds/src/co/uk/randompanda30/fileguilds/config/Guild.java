package co.uk.randompanda30.fileguilds.config;

import co.uk.randompanda30.fileguilds.Guilds;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.randompanda30.fileguilds.TEMP.*;

public class Guild {

    public Guild(Guilds plugin) {
        try {
            guilds = new File("plugins/" + plugin.getName() + "/guilds.yml");
            if (!guilds.exists()) {
                guilds.getParentFile().mkdirs();
                guilds.createNewFile();
            }

            guildsc = new YamlConfiguration();
            guildscs = guildsc.getConfigurationSection("");
            guildsc.load(guilds);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            guildsc.save(guilds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}