package co.uk.RandomPanda30.SAdminO.Files;

import co.uk.RandomPanda30.SAdminO.Data;
import co.uk.RandomPanda30.SAdminO.SAdminO;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil.Cause;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public SAdminO plugin;

    public Config(SAdminO plugin) {
        this.plugin = plugin;
        init();
    }

    public void init() {
        try {
            Data.configF = new File("plugins/" + plugin.getName() + "/config.yml");
            if (!Data.configF.exists()) {
                Data.configF.getParentFile().mkdirs();
                Data.configF.createNewFile();
            }

            Data.config = new YamlConfiguration();
            Data.configC = Data.config.getConfigurationSection("");
            Data.config.load(Data.configF);

            for (ConfigValues value : ConfigValues.values()) {
                if (Data.config.get(value.name().replaceAll("_", ".")) == null) {
                    Data.config.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = Data.config.get(value.name().replaceAll("_", "."));
            }

            Data.config.load(Data.configF);
        } catch (IOException | InvalidConfigurationException e) {
            new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG, "%EFailed to init the config");
        }
    }

    public void save() {
        try {
            Data.config.save(Data.configF);
        } catch (IOException e) {
            new EUtil(e.getStackTrace(), Cause.FILEWENTWRONG, "%EFailed to save the config");
        }
    }

    public enum ConfigValues {

        MYSQL_SET(false), MYSQL_SETUPDELAY(5),

        MYSQL_DBIP("null"), MYSQL_DBNAME("null"), MYSQL_DBUSER("null"), MYSQL_DBPASS("null"), MYSQL_DBPORT(0);

        public Object value;

        ConfigValues(Object value) {
            this.value = value;
        }

    }
}