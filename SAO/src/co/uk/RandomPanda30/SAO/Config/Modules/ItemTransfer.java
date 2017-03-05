package co.uk.RandomPanda30.SAO.Config.Modules;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ItemTransfer {

    public static File itemtransfer;
    public static FileConfiguration itemtransferf;
    public static ConfigurationSection itemtransfers;

    public ItemTransfer() {
        init();
    }

    public static void save() {
        try {
            itemtransferf.save(itemtransfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            itemtransfer = new File("plugins/" + SAO.defaults.getName() + "/modules/itemtransfer.yml");
            if (!itemtransfer.exists()) {
                itemtransfer.getParentFile().mkdirs();
                itemtransfer.createNewFile();
            }

            itemtransferf = new YamlConfiguration();
            itemtransfers = itemtransferf.getConfigurationSection("");

            itemtransferf.load(itemtransfer);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}