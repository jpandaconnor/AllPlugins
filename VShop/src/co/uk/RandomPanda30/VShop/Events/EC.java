package co.uk.RandomPanda30.VShop.Events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.S;
import co.uk.RandomPanda30.VShop.Methods.ECA;
import co.uk.RandomPanda30.VShop.Methods.T;
import co.uk.RandomPanda30.VShop.Methods.U;

public class EC implements Listener {

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Location location = player.getLocation();
		Location blockLocation = block.getLocation();

		if (ECA.isSign(block)) {
			for (String s : B.keys) {
				T t = new T(new Vector(U.getPosition(s, 1).getX(), U
						.getPosition(s, 1).getY(), U.getPosition(s, 1).getZ()),
						new Vector(U.getPosition(s, 2).getX(), U.getPosition(s,
								2).getY(), U.getPosition(s, 2).getZ()));
				if (t.contains(location)) {
					U.setShopSign(s, blockLocation);
				}
			}
		}

		B.getKeys();
		T t2 = new T(new Vector(U.getPosition("safezone", 1).getX(), U
				.getPosition("safezone", 1).getY(), U
				.getPosition("safezone", 1).getZ()), new Vector(U.getPosition(
				"safezone", 2).getX(), U.getPosition("safezone", 2).getY(), U
				.getPosition("safezone", 2).getZ()));

		if (t2.contains(location)) {
			for (String s : B.keys) {
				T t = new T(new Vector(U.getPosition(s, 1).getX(), U
						.getPosition(s, 1).getY(), U.getPosition(s, 1).getZ()),
						new Vector(U.getPosition(s, 2).getX(), U.getPosition(s,
								2).getY(), U.getPosition(s, 2).getZ()));
				if (!player.hasPermission("vshop.bypass")) {
					if (t.contains(event.getBlock().getLocation())) {
						if (U.getPlotState(s).equals(S.FORSALE)
								|| U.getPlotState(s).equals(S.NENABLED)) {
							event.setCancelled(true);
							return;
						}
						if (U.getLastUUID(s).equals(player.getUniqueId())
								&& U.getPlotState(s).equals(S.OWNED)) {
							event.setCancelled(false);
							return;
						} else {
							event.setCancelled(true);
						}

					} else {
						event.setCancelled(true);
					}
				} else {
					event.setCancelled(false);
					return;
				}
				event.setCancelled(true);
			}
		}
	}
}