package co.uk.RandomPanda30.WECMD;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.WorldEdit;
import com.wasteofplastic.askyblock.ASkyBlockAPI;

import net.md_5.bungee.api.ChatColor;

public class WECMD extends JavaPlugin implements Listener {

	public static WECMD plugin;

	public static ASkyBlockAPI sb;
	public static WorldEdit we;

	@Override
	public void onEnable() {
		plugin = this;
		sb = ASkyBlockAPI.getInstance();
		we = WorldEdit.getInstance();

		Bukkit.getConsoleSender().sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&BLoading WeCMD"));

		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor
				.translateAlternateColorCodes('&', "&4Disabling WeCMD"));
		plugin = null;
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(
			PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();

		ArrayList<String> commands = new ArrayList<String>();
		commands.add("wand");
		commands.add("pos1");
		commands.add("pos2");
		commands.add("hpos1");
		commands.add("hpos2");
		commands.add("chunk");
		commands.add("expand");
		commands.add("contract");
		commands.add("inset");
		commands.add("shift");
		commands.add("size");
		commands.add("count");
		commands.add("distr");
		commands.add("sel");
		commands.add("set");
		commands.add("replace");
		commands.add("walls");
		commands.add("overlay");
		commands.add("stack");
		commands.add("move");
		commands.add("smoothr");
		commands.add("regen");
		commands.add("naturalize");
		commands.add("flora");
		commands.add("deform");
		commands.add("copy");
		commands.add("paste");
		commands.add("cut");
		commands.add("rotate");
		commands.add("flip");
		commands.add("schem");
		commands.add("schematic");
		commands.add("cyl");
		commands.add("hcyl");
		commands.add("sphere");
		commands.add("hsphere");
		commands.add("fill");
		commands.add("fillr");
		commands.add("drain");
		commands.add("fixwater");
		commands.add("fixlava");
		commands.add("removeabove");
		commands.add("removebelow");
		commands.add("removenear");
		commands.add("replacenear");
		commands.add("snow");
		commands.add("green");
		commands.add("thaw");
		commands.add("butcher");
		commands.add("ex");
		commands.add("chunkinfo");
		commands.add("listchunks");
		commands.add("delchunks");
		commands.add("single");
		commands.add("recur");
		commands.add("area");
		commands.add("/sp");
		commands.add("tree");
		commands.add("deltree");
		commands.add("repl");
		commands.add("none");
		commands.add("lrbuild");
		commands.add("farwand");
		commands.add("cycler");
		commands.add("info");
		commands.add("floodfill");
		commands.add("flood");
		commands.add("brush");
		commands.add("br");
		commands.add("mask");
		commands.add("size");

		for (String s : commands) {
			if (event.getMessage().contains(s)) {
				if (sb.hasIsland(player.getUniqueId())) {
					if (!sb.playerIsOnIsland(player)) {
						player.sendMessage(
								ChatColor.translateAlternateColorCodes('&',
										"&4You cannot do this command outside your island"));
						event.setCancelled(true);
						break;
					}
				}
			}
		}
	}
}
