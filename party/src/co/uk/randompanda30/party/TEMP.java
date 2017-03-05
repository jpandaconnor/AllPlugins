package co.uk.randompanda30.party;

import co.uk.randompanda30.party.object.Invite;
import co.uk.randompanda30.party.object.PartyOB;
import co.uk.randompanda30.party.object.RegroupTimeout;
import co.uk.randompanda30.party.object.Timeout;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by panda on 15/04/16.
 */
public class TEMP {

    public static File config;
    public static FileConfiguration configc;
    public static ConfigurationSection configcs;

    public static File messages;
    public static FileConfiguration messagesc;
    public static ConfigurationSection messagescs;

    public static ArrayList<PartyOB> parties = new ArrayList<>();
    public static ArrayList<Timeout> timesouts = new ArrayList<>();

    public static HashMap<Entity, ArrayList<UUID>> normiesHit = new HashMap<>();
    public static HashMap<UUID, RegroupTimeout> regroups = new HashMap<>();
    public static HashMap<UUID, Invite> invites = new HashMap<>();
}