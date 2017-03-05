package com.ediTv2.Basics;

import java.sql.Connection;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.ediTv2.Basics.Commands.CMDmutechat;
import com.ediTv2.Basics.Commands.CMDunmutechat;
import com.ediTv2.Basics.Handlers.ConsoleH;
import com.ediTv2.Basics.Handlers.ListenerH;
import com.ediTv2.Basics.Listeners.OnAsyncPlayerChatEvent;
import com.ediTv2.Basics.Listeners.OnPlayerDropEvent;
import com.ediTv2.Basics.Listeners.OnPlayerJoinEvent;
import com.ediTv2.Basics.Listeners.OnPlayerLoginEvent;
import com.ediTv2.Basics.Listeners.OnPlayerMoveEvent;
import com.ediTv2.Basics.Listeners.OnPlayerQuitEvent;
import com.ediTv2.Basics.MySQL.MySQL;
import com.ediTv2.Basics.Text.TextH;

public class Basics extends JavaPlugin {

	public static ArrayList<Listener> listeners = new ArrayList<Listener>();

	/*
	 * Put the listener class in a variableJust makes it a lot easier andalso
	 * you can count how many youhave compared to the classes youhave made to
	 * check that you have done them all! ;3
	 */

	/*
	 * I always put the listeners in seperate classes just because it looks a
	 * lot neater and I can debug easier ;3
	 */

	// READ ONPLAYERDROPEVENT FOR FULL TUTORIAL ;3
	
    public final String user;
    public final String database;
    private final String password;
    private final String port;
    private final String hostname;
	
	Connection c = null;

	public final OnPlayerDropEvent pd = new OnPlayerDropEvent(this);
	public final OnPlayerMoveEvent pm = new OnPlayerMoveEvent(this);
	public final OnPlayerLoginEvent pl = new OnPlayerLoginEvent(this);
	public final OnPlayerJoinEvent pj = new OnPlayerJoinEvent(this);
	public final OnPlayerQuitEvent pq = new OnPlayerQuitEvent(this);
	public final OnAsyncPlayerChatEvent apc = new OnAsyncPlayerChatEvent(this);

	// Always create a reference to your main class...ALWAYS OR ELSE NO CHICKEN
	public static Basics plugin;

	public static boolean chatDisabled = false;

	@Override
	public void onEnable() {
		
		c = MySQL.openConnection();

		ConsoleH.sendConsoleM(TextH.enabled);

		ListenerH.addListener(apc);
		ListenerH.addListener(pd);
		ListenerH.addListener(pj);
		ListenerH.addListener(pl);
		ListenerH.addListener(pq);
		ListenerH.addListener(pm);

		for (Listener listener : listeners) {
			getServer().getPluginManager().registerEvents(listener, this);
		}

		Basics.chatDisabled = false;

		getCommand("mutechat").setExecutor(new CMDmutechat(this));
		getCommand("unmutechat").setExecutor(new CMDunmutechat(this));
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(TextH.disabled);
	}
}
