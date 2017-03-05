package org.jpanda.PE;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jpanda.PE.config.Config;
import org.jpanda.PE.config.values.ConfigValues;
import org.jpanda.PE.config.values.MessagesValues;
import org.jpanda.PE.mysql.MySQL;
import org.jpanda.PE.mysql.Tables;
import org.jpanda.PE.utilities.plugin.Exception;
import org.jpanda.PE.utilities.plugin.Register;
import org.jpanda.PE.utilities.string.Dispatch;
import org.jpanda.PE.utilities.system.Encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class PE extends JavaPlugin implements PluginMessageListener {

    public static MySQL mysql;
    private static PE plugin;
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

        plugin.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        if (!(Boolean) ConfigValues.MYSQL_SET.value) {
            Dispatch.sendMessage(MessagesValues.NOTIFICATIONS_NOMYSQL.value.toString().replace("%time",
                    Integer.toString((Integer) ConfigValues.MYSQL_SETUPDELAY.value)), null);

            getServer().getScheduler().scheduleSyncDelayedTask(this,
                    new Runnable() {
                        @Override
                        public void run() {
                            // new DatabaseLoop(plugin);
                        }
                    }, new Long((Integer) ConfigValues.MYSQL_SETUPDELAY.value));
        } else {
            Encryption eu = new Encryption(
                    (String) ConfigValues.MYSQL_DBPASS.value);
            try {
                mysql = new MySQL(this,
                        (String) ConfigValues.MYSQL_DBIP.value,
                        (String) ConfigValues.MYSQL_DBNAME.value,
                        (String) ConfigValues.MYSQL_DBUSER.value, eu.decrypt(),
                        (Integer) ConfigValues.MYSQL_DBPORT.value);
                Tables.checkTables();
            } catch (InvalidKeyException | NoSuchPaddingException
                    | NoSuchAlgorithmException | BadPaddingException
                    | IllegalBlockSizeException e) {
                new Exception(e.getStackTrace(),
                        Exception.Cause.ENCRYPTION_ERROR, "Failed to decrypt loading in MySQL variable");
            }
        }


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

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(
                message));
        String subchannel = "";
        try {
            subchannel = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}