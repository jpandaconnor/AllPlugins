package co.uk.randompanda30.dailyrewardsplus;

import co.uk.randompanda30.dailyrewardsplus.config.Config;
import co.uk.randompanda30.dailyrewardsplus.config.values.MessagesValues;
import co.uk.randompanda30.dailyrewardsplus.utilities.plugin.Register;
import co.uk.randompanda30.dailyrewardsplus.utilities.string.Dispatch;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static PluginDescriptionFile des;

    public static Object getProperty(String property) {
        Object object = null;
        switch (property.toLowerCase()) {
            case "name":
                object = des.getName();
                break;
            case "version":
                object = des.getVersion();
                break;
            case "authors":
                object = des.getAuthors();
                break;
        }
        return object;
    }

    @Override
    public void onEnable() {
        plugin = this;
        des = this.getDescription();

        new Config("config", "", ".", TEMP.config);
        new Config("messages", "", ".", TEMP.messages);

        new Register(this);

        for (String s : (ArrayList<String>) MessagesValues.START.value) {
            Dispatch.sendMessage(s.replace("%no", (String) getProperty("version")),
                    null);
        }
    }

    /*
    Name
    Version
     */

    @Override
    public void onDisable() {
        for (String s : (ArrayList<String>) MessagesValues.STOP.value) {
            Dispatch.sendMessage(s.replace("%no", (String) getProperty("version")),
                    null);
        }

        plugin = null;
    }
}