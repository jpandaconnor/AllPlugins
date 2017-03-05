package co.uk.RandomPanda30.Murge.Kits;

import java.util.ArrayList;
import java.util.UUID;

public class KitTemp {

	private static ArrayList<Kit> kits = new ArrayList<Kit>();
	private static ArrayList<UUID> removing = new ArrayList<UUID>();
	private static ArrayList<UUID> adding = new ArrayList<UUID>();
	private static ArrayList<UUID> editing = new ArrayList<UUID>();

	private static ArrayList<KitSetup> makingKits = new ArrayList<KitSetup>();
	private static ArrayList<KitEditor> editingKits = new ArrayList<KitEditor>();

	public static void addRemoving(UUID uuid) {
		removing.add(uuid);
	}

	public static boolean containsRemoving(UUID uuid) {
		return removing.contains(uuid);
	}

	public static void removeRemoving(UUID uuid) {
		removing.remove(uuid);
	}

	public static void clearRemoving() {
		removing.clear();
	}

	public static ArrayList<UUID> getRemoving() {
		return removing;
	}

	public static void addKit(Kit kit) {
		kits.add(kit);
	}

	public static boolean containsKit(Kit kit) {
		return kits.contains(kit);
	}

	public static void removeKit(Kit kit) {
		kits.remove(kit);
	}

	public static void clearKits() {
		kits.clear();
	}

	public static ArrayList<Kit> getKits() {
		return kits;
	}

	public static void addAdding(UUID uuid) {
		adding.add(uuid);
	}

	public static boolean containsAdding(UUID uuid) {
		return adding.contains(uuid);
	}

	public static void removeAdding(UUID uuid) {
		adding.remove(uuid);
	}

	public static void clearAdding() {
		adding.clear();
	}

	public static ArrayList<UUID> getAdding() {
		return adding;
	}

	public static void addMakingKits(KitSetup kitSetup) {
		makingKits.add(kitSetup);
	}

	public static boolean containsMakingKits(KitSetup kitSetup) {
		return makingKits.contains(kitSetup);
	}

	public static void removeMakingKits(KitSetup kitSetup) {
		makingKits.remove(kitSetup);
	}

	public static void clearMakingKits() {
		makingKits.clear();
	}

	public static ArrayList<KitSetup> getMakingKits() {
		return makingKits;
	}

	public static void addEditing(UUID uuid) {
		editing.add(uuid);
	}

	public static boolean containsEditing(UUID uuid) {
		return editing.contains(uuid);
	}

	public static void removeEditing(UUID uuid) {
		editing.remove(uuid);
	}

	public static void clearEditing() {
		editing.clear();
	}

	public static ArrayList<UUID> getEditing() {
		return editing;
	}

	public static void addEditingKits(KitEditor kitEditor) {
		editingKits.add(kitEditor);
	}

	public static boolean containsEditingKits(KitEditor kitEditor) {
		return editingKits.contains(kitEditor);
	}

	public static void removeEditingKits(KitEditor kitEditor) {
		editingKits.remove(kitEditor);
	}

	public static void clearEditingKits() {
		editingKits.clear();
	}

	public static ArrayList<KitEditor> getEditingKits() {
		return editingKits;
	}
}