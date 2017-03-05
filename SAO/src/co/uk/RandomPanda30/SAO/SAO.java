package co.uk.RandomPanda30.SAO;

import co.uk.RandomPanda30.SAO.Config.Config;
import co.uk.RandomPanda30.SAO.Config.Config.ConfigValues;
import co.uk.RandomPanda30.SAO.Config.Messages;
import co.uk.RandomPanda30.SAO.Config.Messages.MessagesValues;
import co.uk.RandomPanda30.SAO.Config.Misc.RankReinstate;
import co.uk.RandomPanda30.SAO.Config.Modules.*;
import co.uk.RandomPanda30.SAO.Events.OnPlayerDeathEvent;
import co.uk.RandomPanda30.SAO.Events.OnPlayerRespawnEvent;
import co.uk.RandomPanda30.SAO.MySQL.MySQL;
import co.uk.RandomPanda30.SAO.MySQL.Tables;
import co.uk.RandomPanda30.SAO.Plugin.PluginDefaults;
import co.uk.RandomPanda30.SAO.Plugin.Register;
import co.uk.RandomPanda30.SAO.Setup.DatabaseLoop;
import co.uk.RandomPanda30.SAO.Utilities.Dispatch;
import co.uk.RandomPanda30.SAO.Utilities.Encryption;
import co.uk.RandomPanda30.SAO.Utilities.Exception;
import co.uk.RandomPanda30.SAO.Utilities.Exception.Cause;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SAO extends JavaPlugin implements PluginMessageListener {

    public static PluginDefaults defaults;
    public static MySQL mysql;
    public SAO plugin;

    @SuppressWarnings("unchecked")
    public void onEnable() {
        plugin = this;
        defaults = new PluginDefaults(this);

        new Config();
        new Messages();

        new ItemTransfer();
        new Backup();
        new Whitelist();
        new Reward();
        new FloorRestart();

        new RankReinstate();

        new Register(this);

        new OnPlayerDeathEvent(this);
        new OnPlayerRespawnEvent(this);

        plugin.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("GetServers");
            plugin.getServer().sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : (ArrayList<String>) MessagesValues.START.value) {
            Dispatch.sendMessage(s.replace("%no", defaults.getVersion()),
                    null);
        }

        if (!(Boolean) ConfigValues.MYSQL_SET.value) {
            Dispatch.sendMessage(MessagesValues.NOTIFICATIONS_NOMYSQL.value.toString().replace("%time", Integer.toString((Integer) ConfigValues.MYSQL_SETUPDELAY.value)), null);

            getServer().getScheduler().scheduleSyncDelayedTask(this,
                    new Runnable() {
                        @Override
                        public void run() {
                            new DatabaseLoop(plugin);
                        }
                    }, new Long((Integer) Config.ConfigValues.MYSQL_SETUPDELAY.value));
        } else {
            Encryption eu = new Encryption(
                    (String) Config.ConfigValues.MYSQL_DBPASS.value);
            try {
                mysql = new MySQL(this,
                        (String) ConfigValues.MYSQL_DBIP.value,
                        (String) ConfigValues.MYSQL_DBNAME.value,
                        (String) ConfigValues.MYSQL_DBUSER.value, eu.decrypt(),
                        (Integer) ConfigValues.MYSQL_DBPORT.value);
                Tables.checkAllTables();
            } catch (InvalidKeyException | NoSuchPaddingException
                    | NoSuchAlgorithmException | BadPaddingException
                    | IllegalBlockSizeException e) {
                new Exception(e.getStackTrace(), Cause.ENCRYPTION_ERROR, "Failed to decrypt loading in MySQL variable");
            }
        }
    }

    public void onDisable() {
        for (String s : (ArrayList<String>) MessagesValues.STOP.value) {
            Dispatch.sendMessage(s.replace("%no", defaults.getVersion()),
                    null);
        }

        this.getServer().getMessenger().unregisterIncomingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");

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

        if (subchannel.equals("GetServers")) {
            try {
                String[] servers = in.readUTF().split(", ");
                for (String s : servers) {
                    TEMP.servers.add(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}