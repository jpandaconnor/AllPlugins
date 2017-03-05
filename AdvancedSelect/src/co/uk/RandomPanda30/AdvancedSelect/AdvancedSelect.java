package co.uk.RandomPanda30.AdvancedSelect;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedSelect extends JavaPlugin implements Listener {

	public AdvancedSelect plugin;
	public PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		plugin = this;
		pdfFile = this.getDescription();

	}

	@Override
	public void onDisable() {

	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(block.gett)
	}

}
