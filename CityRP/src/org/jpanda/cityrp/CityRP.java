package org.jpanda.cityrp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jpanda.cityrp.config.Config;
import org.jpanda.cityrp.config.Messages;
import org.jpanda.cityrp.plugin.I;
import org.jpanda.cityrp.string.Dispatch;

public class CityRP extends JavaPlugin {

	private CityRP plugin;
	static boolean enabled = false;

	@Override
	public void onEnable() {
		plugin = this;
		
		new Config(this);
		new Messages(this);
		
		new Dispatch.Start();

        if(!((Boolean) Config.ConfigValues.MYSQL_SET.value)) {

        }

		enabled = true;
    }

	@Override
	public void onDisable() {
		new Dispatch.Stop();

		plugin = null;
	}

	public static boolean isInit() {
		return enabled;
	}
}