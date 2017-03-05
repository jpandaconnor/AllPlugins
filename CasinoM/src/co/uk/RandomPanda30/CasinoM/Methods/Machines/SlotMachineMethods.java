package co.uk.RandomPanda30.CasinoM.Methods.Machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import co.uk.RandomPanda30.CasinoM.CasinoData;
import co.uk.RandomPanda30.CasinoM.Machines;
import co.uk.RandomPanda30.CasinoM.Methods.ConfigMethods;
import co.uk.RandomPanda30.CasinoM.Methods.FireworkMethods;
import co.uk.RandomPanda30.CasinoM.Methods.LocationMethods;
import co.uk.RandomPanda30.CasinoM.Methods.RandomMethods;
import co.uk.RandomPanda30.CasinoM.Methods.StringMethods;
import co.uk.RandomPanda30.CasinoM.Methods.VectorMethods;

public class SlotMachineMethods {

	public static void createSlotMachine(String name, Location pos1,
			Location pos2, Machines machineType, Player player) {
		if (pos1.getWorld().getName() != pos2.getWorld().getName()) {
			// Error here
		}

		World world = pos1.getWorld();
		List<Block> blocks = new ArrayList<Block>();
		List<Entity> entities = new ArrayList<Entity>();

		// Slot machine creation here
		if (machineType.equals(Machines.SLOTMACHINE)) {
			if (CasinoData.slotmachinesC.contains(name)) {
				StringMethods.sendMessage((String) CasinoData.messagesC
						.get("CRITICAL.SLOTMACHINE.ALREADYEXISTS"), player);
				return;
			}

			int topBlockX = (pos1.getBlockX() < pos2.getBlockX() ? pos2
					.getBlockX() : pos1.getBlockX());
			int bottomBlockX = (pos1.getBlockX() > pos2.getBlockX() ? pos2
					.getBlockX() : pos1.getBlockX());

			int topBlockY = (pos1.getBlockY() < pos2.getBlockY() ? pos2
					.getBlockY() : pos1.getBlockY());
			int bottomBlockY = (pos1.getBlockY() > pos2.getBlockY() ? pos2
					.getBlockY() : pos1.getBlockY());

			int topBlockZ = (pos1.getBlockZ() < pos2.getBlockZ() ? pos2
					.getBlockZ() : pos1.getBlockZ());
			int bottomBlockZ = (pos1.getBlockZ() > pos2.getBlockZ() ? pos2
					.getBlockZ() : pos1.getBlockZ());

			for (int x = bottomBlockX; x <= topBlockX; x++) {
				for (int z = bottomBlockZ; z <= topBlockZ; z++) {
					for (int y = bottomBlockY; y <= topBlockY; y++) {
						Block block = pos1.getWorld().getBlockAt(x, y, z);
						blocks.add(block);

						Location el = new Location(world, x, y, z);
						for (Entity entity : el.getChunk().getEntities()) {
							if (entity instanceof ItemFrame) {
								entities.add(entity);
							}
						}
					}
				}
			}

			Map<Block, Integer> levers = new HashMap<Block, Integer>();
			Map<Entity, Integer> itemFrame = new HashMap<Entity, Integer>();

			ArrayList<Location> leverblocks = new ArrayList<Location>();

			for (Block block : blocks) {
				if (block.getType().equals(Material.LEVER)) {
					leverblocks.add(block.getLocation());
					Integer count = levers.get(block);
					levers.put(block, (count == null) ? 1 : count + 1);
				}
			}

			for (Entity ents : entities) {
				if (ents.getType().equals(EntityType.ITEM_FRAME)) {
					Integer count = itemFrame.get(ents);
					itemFrame.put(ents, (count == null) ? 1 : count + 1);
				}
			}

			if (levers.size() == 1 && itemFrame.size() == 3) {
				CasinoData.slotmachinesC.set(name + ".pos1",
						LocationMethods.compileLocation(pos1));
				CasinoData.slotmachinesC.set(name + ".pos2",
						LocationMethods.compileLocation(pos2));
				CasinoData.slotmachinesC.set(name + ".leverpos",
						LocationMethods.compileLocation(leverblocks.get(0)));
				StringMethods.sendMessage((String) CasinoData.messagesC
						.get("CREATION.SLOTMACHINE.CREATED"), player);
				ConfigMethods.saveSlotMachines();
			} else {
				StringMethods.sendMessage((String) CasinoData.messagesC
						.get("CRITICAL.SLOTMACHINE.INCORRECTBLOCKS"), player);
				return;
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	public static void drawSlotMachine(String name, Player player,
			Location location) {
		CasinoData.drawing.add(player.getUniqueId());
		VectorMethods vector = new VectorMethods(LocationMethods
				.decompileLocation(
						(String) CasinoData.slotmachinesC.get(name + ".pos1"))
				.toVector(), LocationMethods.decompileLocation(
				(String) CasinoData.slotmachinesC.get(name + ".pos2"))
				.toVector());
		if (!vector.contains(player.getLocation())) {
			StringMethods.sendMessage((String) CasinoData.messagesC
					.get("CRITICAL.SLOTMACHINE.NOTINVECTOR"), player);
			return;
		}
		ArrayList<Entity> itemFrames = vector
				.getItemFrames(location.getWorld());
		ArrayList<ItemFrame> nif = new ArrayList<ItemFrame>();
		for (Entity ents : itemFrames) {
			if (ents.getType().equals(EntityType.ITEM_FRAME)) {
				ItemFrame i = (ItemFrame) ents;
				nif.add(i);
			}
		}
		int emeralds = 0;
		int diamonds = 0;
		int gold = 0;
		int coal = 0;

		ArrayList<Material> is = new ArrayList<Material>();
		is.add(Material.EMERALD);
		is.add(Material.DIAMOND);
		is.add(Material.GOLD_INGOT);
		is.add(Material.COAL);
		is.add(Material.APPLE);

		for (ItemFrame e : nif) {
			loopItems(e, is, player);
			Bukkit.broadcastMessage(Integer.toString(emeralds));
		}

		if (nif.get(0).getItem().getType().equals(Material.EMERALD)) {
			Bukkit.broadcastMessage(ChatColor.RED + Integer.toString(emeralds));
			emeralds = emeralds + 1;
		}

		if (nif.get(1).getItem().getType().equals(Material.EMERALD)) {
			Bukkit.broadcastMessage(ChatColor.RED + Integer.toString(emeralds));
			emeralds = emeralds + 1;
		}

		if (nif.get(2).getItem().getType().equals(Material.EMERALD)) {
			Bukkit.broadcastMessage(ChatColor.RED + Integer.toString(emeralds));
			emeralds = emeralds + 1;
		}

		Bukkit.broadcastMessage(ChatColor.AQUA + Integer.toString(emeralds));
		if (emeralds == 3) {
			FireworkMethods.getManager().launchRandomFirework(
					player.getLocation());
		}
	}

	public static boolean loopItems(final ItemFrame itemFrame,
			final ArrayList<Material> materials, final Player player) {
		boolean c = false;
		if (!c) {
			CasinoData.plugin
					.getServer()
					.getScheduler()
					.scheduleSyncRepeatingTask(CasinoData.plugin,
							new Runnable() {
								int no = 1;

								@Override
								public void run() {
									if (no != 15) {
										itemFrame.setItem(new ItemStack(
												materials.get(RandomMethods
														.randInt(0, materials
																.size() - 2)),
												1));
										// Play effect here
										no = no + 1;
									}
								}
							}, 0, 10);
		} else {
			c = true;
		}
		return c;
	}
}