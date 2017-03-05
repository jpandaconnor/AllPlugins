package co.uk.RandomPanda30.GXP;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Main plugin;
    public PluginDescriptionFile pdfFile;

    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();


    }

    public void onDisable() {
        plugin = null;
    }

}
