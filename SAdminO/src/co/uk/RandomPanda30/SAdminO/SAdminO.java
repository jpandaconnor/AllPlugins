package co.uk.RandomPanda30.SAdminO;

import co.uk.RandomPanda30.SAdminO.Commands.CommandHandler;
import co.uk.RandomPanda30.SAdminO.Commands.List.CommandSaoa;
import co.uk.RandomPanda30.SAdminO.Files.Config.ConfigValues;
import co.uk.RandomPanda30.SAdminO.Files.Messages.MessagesValues;
import co.uk.RandomPanda30.SAdminO.MySQL.MySQL;
import co.uk.RandomPanda30.SAdminO.MySQL.Tables;
import co.uk.RandomPanda30.SAdminO.Setup.DatabaseSetup;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil;
import co.uk.RandomPanda30.SAdminO.Util.Basic.EUtil.Cause;
import co.uk.RandomPanda30.SAdminO.Util.Basic.MUtil;
import co.uk.RandomPanda30.SAdminO.Util.EncryptionUtil;
import co.uk.RandomPanda30.SAdminO.Util.RegisterUtil;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SuppressWarnings("unchecked")
public class SAdminO extends JavaPlugin {
    /*
	 * Permissions list:
	 * 
	 * - sadmino.notifications
	 * 
	 * Commands:
	 * 
	 * - saoa: basic and main command
	 * 
	 * NOTE: SAOA IS THE MAIN COMMAND FOR THE GUI, NOTHING ELSE!
	 * 
	 *
	 */

    private SAdminO plugin;
    private PluginDescriptionFile pdfFile;

    @Override
    public void onEnable() {
        plugin = this;
        pdfFile = this.getDescription();

        new RegisterUtil(this);

        if (!(Boolean) ConfigValues.MYSQL_SET.value) {
            MUtil.sendMessage((String) MessagesValues.NOTIFICATIONS_NOMYSQL.value.toString().replace("%time",
                    Integer.toString((Integer) ConfigValues.MYSQL_SETUPDELAY.value)), null);
            getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    new DatabaseSetup(plugin);
                }
            }, new Long((Integer) ConfigValues.MYSQL_SETUPDELAY.value));
        } else {
            EncryptionUtil eu = new EncryptionUtil((String) ConfigValues.MYSQL_DBPASS.value);
            try {
                Data.mysql = new MySQL(this, (String) ConfigValues.MYSQL_DBIP.value,
                        (String) ConfigValues.MYSQL_DBNAME.value, (String) ConfigValues.MYSQL_DBUSER.value,
                        eu.decrypt(), (Integer) ConfigValues.MYSQL_DBPORT.value);
                Data.initialised = true;
                Tables.checkTables();
            } catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException
                    | IllegalBlockSizeException e) {
                new EUtil(e.getStackTrace(), Cause.ENCRYPTIONERROR, "Failed to decrypt loading in MySQL variable");
            }
        }

        if (Data.initialised) {
            for (String s : (List<String>) MessagesValues.NOTIFICATIONS_PLUGINLOADING.value) {
                MUtil.sendMessage(s, null);
            }
        }
    }

    @Override
    public void onDisable() {
        for (String s : (List<String>) MessagesValues.NOTIFICATIONS_PLUGINDISABLING.value) {
            MUtil.sendMessage(s, null);
        }

        plugin = null;
    }

    public SAdminO getPlugin() {
        return plugin;
    }

    public PluginDescriptionFile getPdfFile() {
        return pdfFile;
    }

    public void registerCommands() {
        CommandHandler handle = new CommandHandler();

        handle.register("saoa", new CommandSaoa(this));
        getCommand("saoa").setExecutor(new CommandHandler());
    }

}
