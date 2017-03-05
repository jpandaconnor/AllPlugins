/*ban
 * 
 */

package co.uk.RandomPanda30.Main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import co.uk.RandomPanda30.CMD.CMDban;
import co.uk.RandomPanda30.CMD.CMDdeop;
import co.uk.RandomPanda30.CMD.CMDdistress;
import co.uk.RandomPanda30.CMD.CMDinfo;
import co.uk.RandomPanda30.CMD.CMDkick;
import co.uk.RandomPanda30.CMD.CMDkickall;
import co.uk.RandomPanda30.CMD.CMDkill;
import co.uk.RandomPanda30.CMD.CMDmod;
import co.uk.RandomPanda30.CMD.CMDmute;
import co.uk.RandomPanda30.CMD.CMDnkick;
import co.uk.RandomPanda30.CMD.CMDop;
import co.uk.RandomPanda30.CMD.CMDslap;
import co.uk.RandomPanda30.CMD.CMDt;
import co.uk.RandomPanda30.CMD.CMDth;
import co.uk.RandomPanda30.CMD.CMDunmute;
import co.uk.RandomPanda30.CMD.CMDvoteban;
import co.uk.RandomPanda30.CMD.CMDvotekick;
import co.uk.RandomPanda30.CMD.CMDykick;
import co.uk.RandomPanda30.Events.OnFoodLevelChangeEvent;
import co.uk.RandomPanda30.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.Events.OnPlayerChatEvent;
import co.uk.RandomPanda30.Events.OnPlayerDropItemEvent;
import co.uk.RandomPanda30.Events.OnPlayerInteractEvent;
import co.uk.RandomPanda30.Events.OnPlayerJoinEvent;
import co.uk.RandomPanda30.Events.OnPlayerLoginEvent;
import co.uk.RandomPanda30.Events.OnPlayerQuitEvent;
import co.uk.RandomPanda30.Handlers.Text_Handlers;

public class Main extends JavaPlugin {
	
	public static HashMap<String, Player> alreadyRequested = new HashMap<String, Player>();
	public static ArrayList<Integer> voteKicks = new ArrayList<Integer>();
	public static ArrayList<Integer> noKick = new ArrayList<Integer>();
	public static HashMap<String, Player> distressSound = new HashMap<String, Player>();
	public static HashMap<String, Player> muted = new HashMap<String, Player>();
	public static HashMap<String, Player> toKick = new HashMap<String, Player>();
	public static HashMap<String, Player> toBan = new HashMap<String, Player>();
	public static HashMap<String, Player> alreadyVoted = new HashMap<String, Player>();
	public static ArrayList<Integer> playersOnline = new ArrayList<Integer>();
	public static HashMap<String, Player> cmdMuted = new HashMap<String, Player>();
	
	public static HashMap<String, Player> devs = new HashMap<String, Player>();
	public static HashMap<String, Player> admins = new HashMap<String, Player>();
	public static HashMap<String, Player> documentors = new HashMap<String, Player>();
	public static HashMap<String, Player> members = new HashMap<String, Player>();

	// Classes here

	public static Main plugin;

	public final OnPlayerJoinEvent pj = new OnPlayerJoinEvent(this);
	public final OnFoodLevelChangeEvent flc = new OnFoodLevelChangeEvent(this);
	public final OnInventoryClickEvent ic = new OnInventoryClickEvent(this);
	public final OnPlayerInteractEvent pic = new OnPlayerInteractEvent(this);
	public final OnPlayerLoginEvent pl = new OnPlayerLoginEvent(this);
	public final OnPlayerQuitEvent pq = new OnPlayerQuitEvent(this);
	public final OnPlayerDropItemEvent pdi = new OnPlayerDropItemEvent(this);
	public final OnPlayerChatEvent ph = new OnPlayerChatEvent(this);

	public void onEnable() {

		Bukkit.getConsoleSender().sendMessage(Text_Handlers.startM.toString());
		Bukkit.getConsoleSender().sendMessage(
				Text_Handlers.startAPIV.toString());
		Bukkit.getConsoleSender().sendMessage(Text_Handlers.startBV.toString());

		getServer().getPluginManager().registerEvents(this.pj, this);
		getServer().getPluginManager().registerEvents(this.flc, this);
		getServer().getPluginManager().registerEvents(this.ic, this);
		getServer().getPluginManager().registerEvents(this.pic, this);
		getServer().getPluginManager().registerEvents(this.pl, this);
		getServer().getPluginManager().registerEvents(this.pq, this);
		getServer().getPluginManager().registerEvents(this.pdi, this);
		getServer().getPluginManager().registerEvents(this.ph, this);

		getCommand("kickall").setExecutor(new CMDkickall(this));
		getCommand("kick").setExecutor(new CMDkick(this));
		getCommand("ban").setExecutor(new CMDban(this));
		getCommand("kill").setExecutor(new CMDkill(this));
		getCommand("t").setExecutor(new CMDt(this));
		getCommand("th").setExecutor(new CMDth(this));
		getCommand("mute").setExecutor(new CMDmute(this));
		getCommand("unmute").setExecutor(new CMDunmute(this));
		getCommand("slap").setExecutor(new CMDslap(this));
		getCommand("votekick").setExecutor(new CMDvotekick(this));
		getCommand("voteban").setExecutor(new CMDvoteban(this));
		getCommand("ykick").setExecutor(new CMDykick(this));
		getCommand("nkick").setExecutor(new CMDnkick(this));
		getCommand("op").setExecutor(new CMDop(this));
		getCommand("deop").setExecutor(new CMDdeop(this));
		getCommand("mod").setExecutor(new CMDmod(this));
		getCommand("distress").setExecutor(new CMDdistress(this));
		getCommand("info").setExecutor(new CMDinfo(this));

	}

	public void onDisable() {

		Bukkit.getConsoleSender().sendMessage(Text_Handlers.closeS.toString());
	}

}
