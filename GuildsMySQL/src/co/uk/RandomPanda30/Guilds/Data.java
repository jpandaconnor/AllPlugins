package co.uk.RandomPanda30.Guilds;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Guilds.MySQL.MySQL;
import co.uk.RandomPanda30.Guilds.Objects.GInvite;
import co.uk.RandomPanda30.Guilds.Objects.Invite;
import co.uk.RandomPanda30.Guilds.Objects.Rank;
import co.uk.RandomPanda30.Guilds.Plots.Plot;

public class Data {

	public static File config;
	public static FileConfiguration configf;
	public static ConfigurationSection configs;

	public static File messages;
	public static FileConfiguration messagesf;
	public static ConfigurationSection messagess;

	public static MySQL mysql;

	public static HashMap<Player, Invite> invites = new HashMap<>();
	public static HashMap<String, GInvite> ginvites = new HashMap<>();
	
	public static ArrayList<Plot> plots = new ArrayList<>();
	
	public static HashMap<Player, Rank> editing = new HashMap<>();
}
