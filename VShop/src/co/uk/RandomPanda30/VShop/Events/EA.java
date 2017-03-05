package co.uk.RandomPanda30.VShop.Events;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.VShop.A;
import co.uk.RandomPanda30.VShop.S;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Items.I;
import co.uk.RandomPanda30.VShop.Methods.EBC;
import co.uk.RandomPanda30.VShop.Methods.ECA;
import co.uk.RandomPanda30.VShop.Methods.R;
import co.uk.RandomPanda30.VShop.Methods.U;
import co.uk.RandomPanda30.VShop.Methods.X;

public class EA implements Listener {

	private static Location pos1;
	private static Location pos2;

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Block block = event.getClickedBlock();
		ItemStack item = player.getItemInHand();

		if (item == null) {
			return;
		}

		if (action == Action.LEFT_CLICK_BLOCK) {
			if (item.equals(I.obtainWand())) {
				event.setCancelled(true);
				if (pos1 == block.getLocation()) {
					return;
				}
				pos1 = block.getLocation();
				String msg = (String) Messages.POSITIONS_POSITION1.value;
				msg = msg.replaceAll("'x'", Double.toString(pos1.getX()));
				msg = msg.replaceAll("'y'", Double.toString(pos1.getY()));
				msg = msg.replaceAll("'z'", Double.toString(pos1.getZ()));
				X.sendMessage(msg, player);
			}
		}

		if (action == Action.RIGHT_CLICK_BLOCK) {
			if (ECA.isSign(block)) {
				Sign sign = (Sign) block.getState();
				String[] lines = sign.getLines();
				Location location = sign.getLocation();

				String plot = "";
				S state = null;

				if (ChatColor.stripColor(lines[0]).equalsIgnoreCase(
						"[" + A.pdfFile.getName() + "]")
						|| lines[0].equalsIgnoreCase("[vs]")) {
					if (EBC.signLocations.containsValue(location)) {
						for (Map.Entry<String, Location> e : EBC.signLocations
								.entrySet()) {
							if (e.getValue().equals(location)) {
								plot = e.getKey();
							}
						}
						state = U.getPlotState(plot);

						if (state.equals(S.FORSALE)) {
							R.openForSaleMenu(player, plot);
						} else if (state.equals(S.OWNED)) {
							if (player.getUniqueId()
									.equals(U.getLastUUID(plot))) {
								R.openOwnerMenu(player, plot);
							} else {
								X.sendMessage(
										(String) Messages.CRITICAL_CANNOTEDIT.value,
										player);
							}
						}
					}
				}
			}

			if (item.equals(I.obtainWand())) {
				event.setCancelled(true);
				if (pos2 == block.getLocation()) {
					return;
				}
				pos2 = block.getLocation();
				String msg = (String) Messages.POSITIONS_POSITION2.value;
				msg = msg.replaceAll("'x'", Double.toString(pos2.getX()));
				msg = msg.replaceAll("'y'", Double.toString(pos2.getY()));
				msg = msg.replaceAll("'z'", Double.toString(pos2.getZ()));
				X.sendMessage(msg, player);
			}
		}
	}

	public static Location getPos1() {
		return pos1;
	}

	public static void setPos1(Location pos1) {
		EA.pos1 = pos1;
	}

	public static Location getPos2() {
		return pos2;
	}

	public static void setPos2(Location pos2) {
		EA.pos2 = pos2;
	}

}
