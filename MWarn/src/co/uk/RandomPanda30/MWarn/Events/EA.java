package co.uk.RandomPanda30.MWarn.Events;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.uk.RandomPanda30.MWarn.B;
import co.uk.RandomPanda30.MWarn.S;
import co.uk.RandomPanda30.MWarn.Display.V;
import co.uk.RandomPanda30.MWarn.Display.VA;
import co.uk.RandomPanda30.MWarn.Display.VB;
import co.uk.RandomPanda30.MWarn.Display.VC;
import co.uk.RandomPanda30.MWarn.Display.VD;
import co.uk.RandomPanda30.MWarn.Display.VE;
import co.uk.RandomPanda30.MWarn.Display.VEA;
import co.uk.RandomPanda30.MWarn.Display.VF;
import co.uk.RandomPanda30.MWarn.Items.I;
import co.uk.RandomPanda30.MWarn.Items.IA;
import co.uk.RandomPanda30.MWarn.Items.IB;
import co.uk.RandomPanda30.MWarn.Items.IC;
import co.uk.RandomPanda30.MWarn.Items.ID;
import co.uk.RandomPanda30.MWarn.Items.IE;
import co.uk.RandomPanda30.MWarn.Methods.L;
import co.uk.RandomPanda30.MWarn.Methods.N;
import co.uk.RandomPanda30.MWarn.Methods.O;
import co.uk.RandomPanda30.MWarn.Methods.P;
import co.uk.RandomPanda30.MWarn.Methods.T;
import co.uk.RandomPanda30.MWarn.Methods.W;
import co.uk.RandomPanda30.MWarn.Methods.WB;
import co.uk.RandomPanda30.MWarn.Methods.Y;
import co.uk.RandomPanda30.MWarn.Methods.Z;

public class EA implements Listener {

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		Inventory inventory = event.getInventory();

		if (item == null) {
			return;
		}

		if (item.getType().equals(Material.AIR)) {
			return;
		}

		if (event.getInventory().getName().startsWith("\u00A7r")) {
			event.setCancelled(true);
		}

		if (item.getType().equals(Material.SKULL_ITEM)) {
			if (inventory.getName().contains("\u00A7r " + "Search by letter (")
					|| inventory.getName().contains(
							"\u00A7r " + "Search for player (")) {
				// REMOVE PLAYER OUT OF SAM
				if (B.sam.contains(player.getUniqueId())) {
					String name = item.getItemMeta().getDisplayName();
					Player target = Bukkit.getPlayer(name);
					if (target != null) {
						B.samUUID.put(player.getUniqueId(),
								target.getUniqueId());
						B.sam.remove(player.getUniqueId());
						VD.openReasonMenu(player);
					}
				} else if (B.srm.contains(player.getUniqueId())) {
					String name = item.getItemMeta().getDisplayName();
					Player target = Bukkit.getPlayer(name);
					if (target != null) {
						B.srmUUID.put(player.getUniqueId(),
								target.getUniqueId());
						B.srm.remove(player.getUniqueId());
						VF.openActivePlayersWarnings(player, target);
					}
				} else if (B.scm.contains(player.getUniqueId())) {
					String name = item.getItemMeta().getDisplayName();
					Player target = Bukkit.getPlayer(name);
					if (target != null) {
						B.scmUUID.put(player.getUniqueId(),
								target.getUniqueId());
						B.scm.remove(player.getUniqueId());
						W.removeAllWarnings(target);
						Y.sendMessage(
								(String) B.messagesC
										.get("MWARN.PLAYERCLEARED")
										.toString()
										.replaceAll("'playername'",
												target.getName()), player);
						Y.sendMessage(
								(String) B.messagesC
										.get("MWARN.TARGETCLEARED")
										.toString()
										.replaceAll("'playername'",
												player.getName()), target);
						if ((Boolean) B.configC.get("BROADCAST.CLEARWARNINGS") == true) {
							String message = (String) B.messagesC.get(
									"MWARN.BROADCASTCLEARED").toString();
							message = message.replaceAll("'playername'",
									target.getName());
							message = message.replaceAll("'playername2'",
									player.getName());
							Y.broadcastMessage(message);
						}
						player.closeInventory();
					}
				} else if (B.sbm.contains(player.getUniqueId())) {
					String name = item.getItemMeta().getDisplayName();
					Player target = Bukkit.getPlayer(name);
					if (target != null) {
						B.sbmUUID.put(player.getUniqueId(),
								target.getUniqueId());
						B.sbm.remove(player.getUniqueId());
						VD.openReasonMenu(player);
					}
				}
			}
		}

		if (item.getType().equals(Material.SKULL_ITEM)) {
			if (inventory.getName().contains("\u00A7r " + "Reasons (")) {
				String reason = item.getItemMeta().getDisplayName();
				Player target = null;
				if (B.samUUID.containsKey(player.getUniqueId())) {
					target = Bukkit.getPlayer(B.samUUID.get(player
							.getUniqueId()));
					W.addWarning(target, player, reason);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.PLAYERWARNED")
									.toString()
									.replaceAll("'playername'",
											target.getName())
									.replaceAll("'reason'", reason), player);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.TARGETWARNED")
									.toString()
									.replaceAll("'playername'",
											player.getName())
									.replaceAll("'reason'", reason), target);
					if ((Boolean) B.configC.get("BROADCAST.WARNING") == true) {
						String message = (String) B.messagesC.get(
								"MWARN.BROADCASTWARNED").toString();
						message = message.replaceAll("'playername'",
								target.getName());
						message = message.replaceAll("'playername2'",
								player.getName());
						message = message.replaceAll("'reason'", reason);
						Y.broadcastMessage(message);
					}
					B.samUUID.remove(player.getUniqueId());
					player.closeInventory();
				} else if (B.sbmUUID.containsKey(player.getUniqueId())) {
					target = Bukkit.getPlayer(B.sbmUUID.get(player
							.getUniqueId()));
					W.banPlayer(player, target, reason);
					Y.sendMessage(
							(String) B.messagesC
									.get("MWARN.PLAYERBANNED")
									.toString()
									.replaceAll("'playername'",
											target.getName()), player);
					if ((Boolean) B.configC.get("BROADCAST.BAN") == true) {
						String message = (String) B.messagesC.get(
								"MWARN.BROADCASTBANNED").toString();
						message = message.replaceAll("'playername'",
								target.getName());
						message = message.replaceAll("'playername2'",
								player.getName());
						Y.broadcastMessage(message);
					}
				}
			} else if (inventory.getName().contains(
					"\u00A7r " + "Choose a letter")) {
				String letter = item.getItemMeta().getDisplayName();
				VE.openLetterSearch(player, letter);
			} else if (inventory.getName().contains(
					"\u00A7r " + "Click a warning (")) {
				Player target = Bukkit.getPlayer(B.srmUUID.get(player
						.getPlayer().getUniqueId()));
				String key = ChatColor.stripColor(item.getItemMeta().getLore()
						.get(2).replaceAll("Key: ", ""));
				B.getKeys();
				int warningNo = 0;

				Set<String> numeralKeys = null;
				ArrayList<Integer> no = new ArrayList<Integer>();
				if (B.keys.contains(target.getUniqueId().toString())) {
					if (B.dataC.get(target.getUniqueId().toString()
							+ ".warnings") != null) {
						numeralKeys = B.dataC.getConfigurationSection(
								target.getUniqueId().toString() + ".warnings")
								.getKeys(false);
						for (String s : numeralKeys) {
							no.add(Integer.parseInt(s));
						}
					}
				}

				for (int i : no) {
					if (WB.getState(
							target.getUniqueId().toString() + ".warnings." + i
									+ ".state").equals(S.ACTIVE)) {
						if (B.dataC.getString(
								target.getUniqueId().toString() + ".warnings."
										+ i + ".key").equals(key)) {
							warningNo = i;
						}
					}
				}
				W.removeWarning(target, warningNo);
				Y.sendMessage(
						(String) B.messagesC
								.get("MWARN.WARNINGREMOVED")
								.toString()
								.replaceAll("'warning'",
										Integer.toString(warningNo)), player);

				if ((Boolean) B.configC.get("BROADCAST.REMOVEWARNING") == true) {
					String message = (String) B.messagesC.get(
							"MWARN.BROADCASTREMOVEWARNING").toString();
					message = message.replaceAll("'warning'",
							Integer.toString(warningNo));
					message = message.replaceAll("'playername'",
							target.getName());
					message = message.replaceAll("'playername2'",
							player.getName());
					Y.broadcastMessage(message);
				}
				player.closeInventory();
			} else if (inventory.getName().contains(
					"\u00A7r " + "Banned players (")) {
				String tuuid = ChatColor.stripColor(item.getItemMeta()
						.getDisplayName().toString());
				W.unbanPlayer(player, tuuid);
				Y.sendMessage((String) B.messagesC.get("MWARN.PLAYERUNBANNED")
						.toString().replaceAll("'playername'", tuuid), player);
				if ((Boolean) B.configC.get("BROADCAST.UNBAN") == true) {
					String message = (String) B.messagesC.get(
							"MWARN.BROADCASTUNBANNED").toString();
					message = message.replaceAll("'playername'", tuuid);
					message = message.replaceAll("'playername2'",
							player.getName());
					Y.broadcastMessage(message);
				}
				player.closeInventory();
			}
		}

		// Check name of these two here

		if (item.equals(I.obtainPrevious())) {
			String invName = inventory.getName();
			if (invName.contains("\u00A7r " + "Search for player")) {
				VC.previousPage(player);
			} else if (invName.contains("\u00A7r " + "Active warnings (")) {
				VA.previousPage(player);
			} else if (invName.contains("\u00A7r " + "Expired warnings (")) {
				VB.previousPage(player);
			} else if (invName.contains("\u00A7r " + "Turn features on/off")) {
				T.openControlPanel(player);
			} else if (invName.contains("\u00A7r " + "Edit config values")) {
				T.openControlPanel(player);
			}
		}

		if (item.equals(I.obtainNext())) {
			String invName = inventory.getName();
			if (invName.contains("\u00A7r " + "Search for player")) {
				VC.nextPage(player);
			} else if (invName.contains("\u00A7r " + "Active warnings (")) {
				VA.nextPage(player);
			} else if (invName.contains("\u00A7r " + "Expired warnings (")) {
				VB.nextPage(player);
			} else if (invName.contains("\u00A7r " + "MWarn control panel")) {
				// Next page when implemented
			}
		}

		if (item.equals(IC.obtainSearchWithLetter())) {
			VEA.openPreSearch(player);
		}

		if (item.equals(I.obtainExit())) {
			player.closeInventory();
		}

		if (item.equals(IA.obtainActiveWarnings(player))) {
			VA.openActiveWarningsMenu(player);
		}

		if (item.equals(IA.obtainExpiredWarnings(player))) {
			VB.openExpiredWarningsMenu(player);
		}

		if (item.equals(IB.obtainAccessButton())) {
			if (player.hasPermission("mwarn.admin") || player.isOp()) {
				T.openControlPanel(player);
			} else if (player.hasPermission("mwarn.moderator")) {
				T.openMControlPanel(player);
			}
		}

		if (item.equals(IB.obtainAddWarning())) {
			VC.openSearchInventory(player);
			B.sam.add(player.getUniqueId());
		}

		if (item.equals(IB.obtainRemoveWarning())) {
			VC.openSearchInventory(player);
			B.srm.add(player.getUniqueId());
		}

		if (item.equals(IB.obtainClearWarnings())) {
			VC.openSearchInventory(player);
			B.scm.add(player.getUniqueId());
		}

		if (item.equals(IB.obtainBanButton())) {
			VC.openSearchInventory(player);
			B.sbm.add(player.getUniqueId());
		}

		if (item.equals(IB.obtainUnbanButton())) {
			O.openUnbanMenu(player);
			B.subm.add(player.getUniqueId());
		}

		if (item.equals(IB.obtainOffOnButton())) {
			N.openOnOffInventory(player);
		}

		if (item.equals(IB.obtainInitConfigs())) {
			Z.initConfig();
			Z.initData();
			// Z.initItems();
			Z.initMessages();
			Z.initBans();
			Y.sendMessage((String) B.messagesC.get("MWARN.CONFGISINIT"), player);
		}

		if (item.equals(IA.obtainBans(player))) {
			P.openBans(player);
		}

		if (item.equals(IB.obtainConfigValues())) {
			L.openConfigValue(player);
		}

		if (item.equals(IE.obtainBanLength_MBUTTON())) {
			int bl = (Integer) B.configC.get("WARNINGS.BANLENGTH");
			if (!(bl - 1 <= 0)) {
				B.configC.set("WARNINGS.BANLENGTH", bl - 1);
				L.openConfigValue(player);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (item.equals(IE.obtainBanLength_PBUTTON())) {
			int bl = (Integer) B.configC.get("WARNINGS.BANLENGTH");
			B.configC.set("WARNINGS.BANLENGTH", bl + 1);
			L.openConfigValue(player);
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (item.equals(IE.obtainWarningThreshold_MBUTTON())) {
			int bl = (Integer) B.configC.get("WARNINGS.THRESHOLD");
			if (!(bl - 1 <= 0)) {
				B.configC.set("WARNINGS.THRESHOLD", bl - 1);
				L.openConfigValue(player);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (item.equals(IE.obtainWarningThreshold_PBUTTON())) {
			int bl = (Integer) B.configC.get("WARNINGS.THRESHOLD");
			B.configC.set("WARNINGS.THRESHOLD", bl + 1);
			L.openConfigValue(player);
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (item.equals(ID.obtainBroadcastWarning())) {
			boolean b = (Boolean) B.configC.get("BROADCAST.WARNING");
			if (b) {
				B.configC.set("BROADCAST.WARNING", false);
			} else {
				B.configC.set("BROADCAST.WARNING", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainBroadcastBan())) {
			boolean b = (Boolean) B.configC.get("BROADCAST.BAN");
			if (b) {
				B.configC.set("BROADCAST.BAN", false);
			} else {
				B.configC.set("BROADCAST.BAN", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainBroadcastUnban())) {
			boolean b = (Boolean) B.configC.get("BROADCAST.UNBAN");
			if (b) {
				B.configC.set("BROADCAST.UNBAN", false);
			} else {
				B.configC.set("BROADCAST.UNBAN", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainBroadcastClearWarnings())) {
			boolean b = (Boolean) B.configC.get("BROADCAST.CLEARWARNINGS");
			if (b) {
				B.configC.set("BROADCAST.CLEARWARNINGS", false);
			} else {
				B.configC.set("BROADCAST.CLEARWARNINGS", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainBroadcastRemoveWarning())) {
			boolean b = (Boolean) B.configC.get("BROADCAST.REMOVEWARNING");
			if (b) {
				B.configC.set("BROADCAST.REMOVEWARNING", false);
			} else {
				B.configC.set("BROADCAST.REMOVEWARNING", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainCMDCLEAR())) {
			boolean b = (Boolean) B.configC.get("CMD.CLEAR");
			if (b) {
				B.configC.set("CMD.CLEAR", false);
			} else {
				B.configC.set("CMD.CLEAR", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainCMDREMOVE())) {
			boolean b = (Boolean) B.configC.get("CMD.REMOVE");
			if (b) {
				B.configC.set("CMD.REMOVE", false);
			} else {
				B.configC.set("CMD.REMOVE", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainCMDWARN())) {
			boolean b = (Boolean) B.configC.get("CMD.WARN");
			if (b) {
				B.configC.set("CMD.WARN", false);
			} else {
				B.configC.set("CMD.WARN", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(ID.obtainMWarn())) {
			boolean b = (Boolean) B.configC.get("PLUGIN.ENABLE");
			if (b) {
				B.configC.set("PLUGIN.ENABLE", false);
			} else {
				B.configC.set("PLUGIN.ENABLE", true);
			}
			try {
				B.configC.save(B.config);
			} catch (Exception e) {
				e.printStackTrace();
			}
			N.openOnOffInventory(player);
		}

		if (item.equals(IB.obtainMainButton())) {
			if (player.hasPermission("mwarn.admin")) {
				V.openAdminInventory(player);
			} else if (player.hasPermission("mwarn.moderator")) {
				V.openAdminInventory(player);
			} else {
				V.openPlayerInventory(player);
			}
		}
	}
}