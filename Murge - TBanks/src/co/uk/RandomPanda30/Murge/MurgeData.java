package co.uk.RandomPanda30.Murge;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import co.uk.RandomPanda30.Murge.Handlers.SetupHandler;

public class MurgeData {

	public static Murge plugin;
	public static PluginDescriptionFile pdfFile;

	private static boolean purge = false;
	private static World world;

	private static SetupHandler setup;

	private static Player editingLocation;

	public static Murge getPlugin() {
		return plugin;
	}

	public static void initDataFile() {
		pdfFile = plugin.getDescription();
	}

	public static PluginDescriptionFile getDataFile() {
		return pdfFile;
	}

	public static String getAuthor(int no) {
		return getDataFile().getAuthors().get(no);
	}

	public static World getWorld() {
		return world;
	}

	public static void setWorld(World world) {
		MurgeData.world = world;
	}

	public static boolean isPurge() {
		return purge;
	}

	public static void setPurge(boolean purge) {
		MurgeData.purge = purge;
	}

	public static ArrayList<String> getInventoryNames() {
		ArrayList<String> invs = new ArrayList<String>();
		invs.add("Murge");
		invs.add("Murge control panel");
		invs.add("World border settings");
		invs.add("Adjust flight speed");
		invs.add("Purge value settings");
		invs.add("Purge options settings");
		invs.add("Broadcast options settings");
		return invs;
	}

	public static ArrayList<Material> getInteractionMaterials() {
		ArrayList<Material> mats = new ArrayList<Material>();
		mats.add(Material.BED_BLOCK);
		mats.add(Material.BED);
		mats.add(Material.CHEST);
		mats.add(Material.WORKBENCH);
		mats.add(Material.FURNACE);
		mats.add(Material.IRON_DOOR);
		mats.add(Material.WOODEN_DOOR);
		mats.add(Material.SPRUCE_DOOR);
		mats.add(Material.BIRCH_DOOR);
		mats.add(Material.JUNGLE_DOOR);
		mats.add(Material.ACACIA_DOOR);
		mats.add(Material.DARK_OAK_DOOR);
		mats.add(Material.LEVER);
		mats.add(Material.STONE_BUTTON);
		mats.add(Material.WOOD_BUTTON);
		mats.add(Material.REDSTONE_ORE);
		mats.add(Material.JUKEBOX);
		mats.add(Material.REDSTONE_COMPARATOR);
		mats.add(Material.TRAPPED_CHEST);
		mats.add(Material.TRAP_DOOR);
		mats.add(Material.IRON_TRAPDOOR);
		mats.add(Material.FENCE_GATE);
		mats.add(Material.JUNGLE_FENCE_GATE);
		mats.add(Material.DARK_OAK_FENCE_GATE);
		mats.add(Material.BIRCH_FENCE_GATE);
		mats.add(Material.ACACIA_FENCE_GATE);
		mats.add(Material.ENCHANTMENT_TABLE);
		mats.add(Material.ENDER_CHEST);
		mats.add(Material.COMMAND);
		mats.add(Material.COMMAND_MINECART);
		mats.add(Material.BEACON);
		mats.add(Material.FLOWER_POT);
		mats.add(Material.ANVIL);
		mats.add(Material.HOPPER);
		mats.add(Material.HOPPER_MINECART);
		mats.add(Material.DROPPER);
		mats.add(Material.ENDER_PEARL);
		mats.add(Material.BREWING_STAND);
		mats.add(Material.ARMOR_STAND);
		mats.add(Material.DISPENSER);
		return mats;
	}

	public static Player getEditingLocation() {
		return editingLocation;
	}

	public static void setEditingLocation(Player editingLocation) {
		MurgeData.editingLocation = editingLocation;
	}

	public static SetupHandler getSetup() {
		return setup;
	}

	public static void setSetup(SetupHandler setup) {
		MurgeData.setup = setup;
	}
}