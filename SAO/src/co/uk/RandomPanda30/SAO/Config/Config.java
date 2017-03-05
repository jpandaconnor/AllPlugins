package co.uk.RandomPanda30.SAO.Config;

import co.uk.RandomPanda30.SAO.SAO;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File config;
    public static FileConfiguration configf;
    public static ConfigurationSection configs;

    public Config() {
        init();
    }

    public static void save() {
        try {
            configf.save(config);
        } catch (IOException e) {

        }
    }

    private void init() {
        try {
            config = new File("plugins/" + SAO.defaults.getName() + "/config.yml");
            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }

            configf = new YamlConfiguration();
            configs = configf.getConfigurationSection("");
            configf.load(config);

            for (ConfigValues value : ConfigValues.values()) {
                if (configs.get(value.name().replaceAll("_", ".")) == null) {
                    configs.set(value.name().replaceAll("_", "."), value.value);
                    save();
                }

                value.value = configs
                        .get(value.name().replaceAll("_", "."));
            }

            configf.load(config);
        } catch (IOException | InvalidConfigurationException e) {

        }
    }

    public enum ConfigValues {
        FLOOR(1),

        MYSQL_SET(false),
        MYSQL_SETUPDELAY(5),

        MYSQL_DBIP("null"),
        MYSQL_DBNAME("null"),
        MYSQL_DBUSER("null"),
        MYSQL_DBPASS("null"),
        MYSQL_DBPORT(0),

        MODULES_BACKUP_ENABLED(true),
        MODULES_BACKUP_AUTOMATICBACKUP(true),
        MODULES_BACKUP_BACKUPDELAY("72h"),

        MODULES_GUILDS_MODE("yml"),

        MODULES_BACKUP_PATH_FOLDER("changeme"),
        MODULES_BACKUP_PATH_BACKUP("changeme"),
        MODULES_BACKUP_PATH_LOGS("changeme"),

        MODULES_ITEMTRANSFER_ENABLED(true),

        MODULES_ENTRY_ENABLED(true),
        MODULES_ENTRY_CUSTOMWHITELIST(false),
        MODULES_ENTRY_XPRESTRICTION(true),
        MODULES_ENTRY_CHECKDATABASELEVEL(true),
        MODULES_ENTRY_LEVEL(150),

        MODULES_LOGIN_ENABLED(true),
        MODULES_LOGIN_JOINMESSAGE_PLAYER(false),
        MODULES_LOGIN_JOINMESSAGE_BEATER(true),
        MODULES_LOGIN_JOINMESSAGE_BEATERPLUS(true),
        MODULES_LOGIN_GLOBALXP(true),

        MODULES_ADMIN_ENABLED(true),

        MODULES_REWARD_ENABLED(true),

        MODULES_FLOORRESTART_ENABLED(true),
        MODULES_FLOORRESTART_LEVELTOITEMS(true), // Enables the /fr items edit <Level name>
        MODULES_FLOORRESTART_PLAYERRESET_LEVEL_ENABLED(true),
        MODULES_FLOORRESTART_PLAYERRESET_LEVEL_AMOUNT(0),
        MODULES_FLOORRESTART_PLAYERREST_COINS(true),

        MODULES_MISC_RANKREINSTATE_ENABLED(false),
        MODULES_MISC_RANKREINSTATE_BEATERCOMMAND("pp user %player addgroup beater %server"),
        MODULES_MISC_RANKREINSTATE_BEATERPLUSCOMMAND("pp user %player addgroup beaterplus");

        public Object value;

        ConfigValues(Object value) {
            this.value = value;
        }
    }
}