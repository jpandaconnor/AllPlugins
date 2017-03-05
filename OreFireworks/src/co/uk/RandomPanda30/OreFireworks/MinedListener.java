package co.uk.RandomPanda30.OreFireworks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class MinedListener implements CommandExecutor, Listener {

	public MinedListener (Main plugin) {
		Main.plugin = plugin;
	}

	public static Inventory inv = Bukkit.createInventory(null, 9,
			"Test Inventory");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("test")) {
			if (sender.isOp()) {
				Player player = (Player) sender;
				LivingEntity entity = (LivingEntity) player.getWorld()
						.spawnEntity(player.getLocation(), EntityType.VILLAGER);
				entity.setCustomName("§dTest Dummy");
				entity.setCustomNameVisible(true);

				player.openInventory(inv);
			}
		}
		return true;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		Entity ent = e.getRightClicked();
		if (ent instanceof Villager) {
			Villager living = (Villager) ent;
			if (living.getCustomName().equals("§dTest Dummy")) {
				Bukkit.broadcastMessage("as");
				Player player = e.getPlayer();
				if (ent.getType() == EntityType.VILLAGER) {
					e.setCancelled(true);
					player.closeInventory();
				}
				player.openInventory(inv);
			}
		}
	}
}