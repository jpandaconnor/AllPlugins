package co.uk.RandomPanda30.Beta.Config;

import co.uk.RandomPanda30.Beta.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class Defaults {

    public Main plugin;

    public Defaults(Main plugin) {
        this.plugin = plugin;
        initDefault();
    }

    public void initDefault() {
        if (!getConfig().contains("allowjoin.beater")) {
            getConfig().set("allowjoin.beater", true);
        }

        if (!getConfig().contains("allowjoin.beaterplus")) {
            getConfig().set("allowjoin.beaterplus", true);
        }

        saveConfig();
    }

    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    public void saveConfig() {
        plugin.saveConfig();
    }

}