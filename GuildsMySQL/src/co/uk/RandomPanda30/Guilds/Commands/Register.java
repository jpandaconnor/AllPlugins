package co.uk.RandomPanda30.Guilds.Commands;

import co.uk.RandomPanda30.Guilds.Guilds;
import co.uk.RandomPanda30.Guilds.Events.OnAsyncPlayerChatEvent;
import co.uk.RandomPanda30.Guilds.Events.OnEntityDamageEvent;
import co.uk.RandomPanda30.Guilds.Events.OnInventoryClickEvent;
import co.uk.RandomPanda30.Guilds.Events.OnPlayerJoinEvent;

public class Register {

	public Guilds plugin;
	
	public Register(Guilds plugin) {
		this.plugin = plugin;
		register();
	}
	
	public void register() {
		new CommandGuild(plugin);
		
		new OnPlayerJoinEvent(plugin);
		new OnAsyncPlayerChatEvent(plugin);
		new OnInventoryClickEvent(plugin);
		new OnEntityDamageEvent(plugin);
	}
}