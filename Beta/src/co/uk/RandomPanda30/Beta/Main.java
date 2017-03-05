package co.uk.RandomPanda30.Beta;

import co.uk.RandomPanda30.Beta.Events.OnPlayerLoginEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Main plugin;
    public PluginDescriptionFile pdfFile;

    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        new OnPlayerLoginEvent(this);
    }

    public void onDisable() {
        plugin = null;
    }
}