package co.uk.RandomPanda30.CasinoM.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Items.BaseItems;
import co.uk.RandomPanda30.CasinoM.Methods.LocationMethods;
import co.uk.RandomPanda30.CasinoM.Methods.StringMethods;
import co.uk.RandomPanda30.CasinoM.Methods.VectorMethods;
import co.uk.RandomPanda30.CasinoM.Methods.Machines.SlotMachineMethods;

public class OnPlayerInteractEvent implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getPlayer().getItemInHand();
		Location location = event.getClickedBlock().getLocation();
		Action action = event.getAction();

		if (item == null) {
			return;
		}

		if (location == null) {
			return;
		}

		if (action.equals(Action.LEFT_CLICK_BLOCK)) {
			if (item.equals(BaseItems.obtainWand())) {
				event.setCancelled(true);
				if (CasinoData.getPosition(1) != null
						&& CasinoData.getPosition(1).equals(location))
					return;
				CasinoData.setPosition(1, location);
				StringMethods
						.sendMessage(
								(String) CasinoData.messagesC
										.get("CREATION.SELECTION")
										.toString()
										.replaceAll("%selection",
												Integer.toString(1))
										.replaceAll(
												"%xLoc",
												Double.toString(location.getX()))
										.replaceAll(
												"%yLoc",
												Double.toString(location.getY())
														.replaceAll(
																"%zLoc",
																Double.toString(location
																		.getZ()))),
								player);
			}
		}

		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (item.equals(BaseItems.obtainWand())) {
				if (CasinoData.getPosition(2) != null
						&& CasinoData.getPosition(2).equals(location))
					return;
				CasinoData.setPosition(2, location);
				StringMethods
						.sendMessage(
								(String) CasinoData.messagesC
										.get("CREATION.SELECTION")
										.toString()
										.replaceAll("%selection",
												Integer.toString(2))
										.replaceAll(
												"%xLoc",
												Double.toString(location.getX()))
										.replaceAll(
												"%yLoc",
												Double.toString(location.getY())
														.replaceAll(
																"%zLoc",
																Double.toString(location
																		.getZ()))),
								player);
				event.setCancelled(true);
			}

			if (event.getClickedBlock().getType().equals(Material.LEVER)) {
				for (String s : CasinoData.slotmachinesC
						.getConfigurationSection("").getKeys(false)) {
					if (LocationMethods.decompileLocation(
							(String) CasinoData.slotmachinesC.get(s
									+ ".leverpos")).equals(location)) {
						Bukkit.broadcastMessage("Test");
						SlotMachineMethods.drawSlotMachine(s, player, location);
						VectorMethods vector = new VectorMethods(
								LocationMethods.decompileLocation(
										(String) CasinoData.slotmachinesC.get(s
												+ ".pos1")).toVector(),
								LocationMethods.decompileLocation(
										(String) CasinoData.slotmachinesC.get(s
												+ ".pos2")).toVector());
						if (vector.contains(player.getLocation())) {

						}
					}
				}
			}
		}
	}
}