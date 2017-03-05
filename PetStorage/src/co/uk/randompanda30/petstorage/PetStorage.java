package co.uk.randompanda30.petstorage;

import co.uk.randompanda30.petstorage.config.values.MessagesValues;
import co.uk.randompanda30.petstorage.objects.CustomCow;
import co.uk.randompanda30.petstorage.utilities.plugin.Register;
import co.uk.randompanda30.petstorage.utilities.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class PetStorage extends JavaPlugin {

    private static PetStorage plugin;
    private static PluginDescriptionFile des;

    @Override
    public void onEnable() {
        plugin = this;
        des = this.getDescription();

        // new Config("config", "", ".", TEMP.config);
        // new Config("messages", "", ".", TEMP.messages);

        new Register(this);


        for (String s : (ArrayList<String>) MessagesValues.START.value) {
            Dispatch.sendMessage(s.replace("%no", (String) getProperty("version")),
                    null);
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().equals("RandomPanda30")) {
                CustomCow cow = new CustomCow(this, player);
                // PathfinderGoalWalkToLoc path = new PathfinderGoalWalkToLoc(cow, 1.0D, player);
                // path.update();

                // EntityTypes.spawnEntity(new CustomCow(player.getLocation().getWorld()), player.getLocation());
            }
        }
    }

    @Override
    public void onDisable() {
        for (String s : (ArrayList<String>) MessagesValues.STOP.value) {
            Dispatch.sendMessage(s.replace("%no", (String) getProperty("version")),
                    null);
        }
        plugin = null;
    }

    public static Object getProperty(String property) {
        Object object = null;
        switch(property.toLowerCase()) {
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
}