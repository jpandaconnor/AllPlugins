package co.uk.RandomPanda30.SAO.Modules.Guilds;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static co.uk.RandomPanda30.SAO.Config.Config.ConfigValues.MODULES_GUILDS_MODE;
import static co.uk.RandomPanda30.SAO.Modules.Guilds.GuildData.*;

public class GuildInitialise {

    private SAO plugin;

    private String[] file = {"yaml", "yml", "file"};
    private String[] sql = {"mysql", "database", "db", "sql"};

    public GuildInitialise(SAO plugin) {
        this.plugin = plugin;

        String mode = (String) MODULES_GUILDS_MODE.value.toString().toLowerCase();
        boolean ff = false;
        boolean ss = false;

        for (String s : file) {
            if (mode.equals(s)) {
                ff = true;
                break;
            }
        }

        if (!ff) {
            for (String s : sql) {
                if (mode.equals(s)) {
                    ss = true;
                    break;
                }
            }
        }

        if (!ss && !ff) {

            // ALERT
        }

        if (ff) {
            loadFile();
        }
    }

    private void loadFile() {
        guildConfig = new File("plugins/" + SAO.defaults.getName() + "/modules/guilds/guilds.yml");

        try {
            if (!guildConfig.exists()) {
                guildConfig.mkdirs();
                guildConfig.createNewFile();
            }


            guildConfigC = new YamlConfiguration();
            guildConfigCS = guildConfigC.getConfigurationSection("");
            guildConfigC.load(guildConfig);

            guildConfigC.load(guildConfig);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        
        // Alert here
    }
}