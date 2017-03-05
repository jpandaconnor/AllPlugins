package co.uk.RandomPanda30.GBlock.Events;

import java.lang.reflect.Field;

import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import co.uk.RandomPanda30.GBlock.GBlock;
import co.uk.RandomPanda30.GBlock.Plot.PlotsGenerator;

public class OnWorldInitListener implements Listener {

	public GBlock plugin;

	public OnWorldInitListener (GBlock plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onWorldInit(WorldInitEvent event) {
		World world = event.getWorld();

		if ((world.getGenerator() instanceof PlotsGenerator)) {
			// WorldServer worldServer = ((CraftWorld) world).getHandle();
			try {
				net.minecraft.server.v1_7_R4.WorldData data = ((CraftWorld) world)
						.getHandle().worldData;
				Field f = data.getClass().getDeclaredField("type");
				f.setAccessible(true);
				f.set(data, WorldType.FLAT);
				f.setAccessible(false);
				world.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}