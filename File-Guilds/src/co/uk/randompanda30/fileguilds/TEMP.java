package co.uk.randompanda30.fileguilds;

import co.uk.randompanda30.fileguilds.object.invite.GInvite;
import co.uk.randompanda30.fileguilds.object.invite.Invite;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class TEMP {

    public static File config;
    public static FileConfiguration configc;
    public static ConfigurationSection configcs;

    public static File messages;
    public static FileConfiguration messagesc;
    public static ConfigurationSection messagescs;

    public static File guilds;
    public static FileConfiguration guildsc;
    public static ConfigurationSection guildscs;

    public static File pdata;
    public static FileConfiguration pdatac;
    public static ConfigurationSection pdatacs;

    public static HashMap<UUID, Invite> invites = new HashMap<>();
    public static HashMap<String, GInvite> ginvites = new HashMap<>();

    public static HashMap<UUID, String> editingPermissions = new HashMap<>();
}