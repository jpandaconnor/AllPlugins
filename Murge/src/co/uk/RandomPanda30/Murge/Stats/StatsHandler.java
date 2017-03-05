package co.uk.RandomPanda30.Murge.Stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import co.uk.RandomPanda30.Murge.Handlers.PlayerDataHandler;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.TreeRHandler;
import co.uk.RandomPanda30.Murge.Stats.Beans.Amounts;
import co.uk.RandomPanda30.Murge.Stats.Beans.Lists;
import co.uk.RandomPanda30.Murge.Stats.Beans.Player;

public class StatsHandler {
	
	/* All the player methods */
	public static void addPlayer(UUID uuid) {
		Lists.players.add(uuid);
	}
	
	public static void removePlayer(UUID uuid) {
		Lists.players.remove(uuid);
	}
	
	public static void clearPlayers() {
		Lists.players.clear();
	}
	
	public static boolean inPlayers(UUID uuid) {
		return Lists.players.contains(uuid);
	}
	
	/* All the weird numbers go here */
	public static void incrementPurgeNo() {
		Amounts.purgeNo++;
	}
	
	public static void decrementPurgeNo() {
		Amounts.purgeNo--;
	}
	
	public static void addPurgeNo(int no) {
		Amounts.purgeNo += no;
	}
	
	public static void removePurgeNo(int no) {
		Amounts.purgeNo -= no;
	}
	
	public static void resetPurgeNo() {
		Amounts.purgeNo = 0;
	}
	
	public static int getPurgeNo() {
		return Amounts.purgeNo;
	}
	
	public static void incrementKillNo() {
		Amounts.totalKills++;
	}
	
	public static void decrementKillNo() {
		Amounts.totalKills--;
	}
	
	public static void addKillNo(int no) {
		Amounts.totalKills += no;
	}
	
	public static void removeKillNo(int no) {
		Amounts.totalKills -= no;
	}
	
	public static int getKillNo() {
		return Amounts.totalKills;
	}
	
	public static void resetKillNo() {
		Amounts.totalKills = 0;
	}
	
	/* All the spectator methods */
	public static void addSpectator(UUID uuid) {
		Lists.spectating.add(uuid);
	}
	
	public static void removeSpectator(UUID uuid) {
		Lists.spectating.remove(uuid);
	}
	
	public static void clearSpectators() {
		Lists.spectating.clear();
	}
	
	public static ArrayList<UUID> getSpectators() {
		return Lists.spectating;
	}
	
	public static boolean inSpectators(UUID uuid) {
		return Lists.spectating.contains(uuid);
	}
	
	/* All the player related stuff here (In the form of UUID) */
	public static void addFlySpeed(UUID uuid, int no) {
		Player.flySpeed.put(uuid, no);
	}
	
	public static void removeFlySpeed(UUID uuid) {
		Player.flySpeed.remove(uuid);
	}
	
	public static void replaceFlySpeed(UUID uuid, int no) {
		Player.flySpeed.put(uuid, no);
	}
	
	public static void clearFlySpeed() {
		Player.flySpeed.clear();
	}
	
	public static boolean inFlySpeed(UUID uuid) {
		return Player.flySpeed.containsKey(uuid);
	}
	
	public static HashMap<UUID, Integer> getFlySpeed() {
		return Player.flySpeed;
	}
	
	public static void addPlayerData(UUID uuid, PlayerDataHandler pdh) {
		Player.pdh.put(uuid, pdh);
	}
	
	public static void removePlayerData(UUID uuid) {
		Player.pdh.remove(uuid);
	}
	
	public static PlayerDataHandler getPlayerData(UUID uuid) {
		return Player.pdh.get(uuid);
	}
	
	public static void clearPlayerData(UUID uuid) {
		Player.pdh.clear();
	}
	
	public static boolean inPlayerData(UUID uuid) {
		return Player.pdh.containsKey(uuid);
	}
	
	public static void incrementPlayerKill(UUID uuid) {
		if (Player.playerKills.containsKey(uuid)) {
			Player.playerKills.put(uuid, ((Player.playerKills.put(uuid, (Player.playerKills.get(uuid)) + 1))));
		} else {
			Player.playerKills.put(uuid, 1);
		}
	}
	
	public static void decrementPlayerKill(UUID uuid) {
		if (Player.playerKills.containsKey(uuid)) {
			Player.playerKills.put(uuid, ((Player.playerKills.put(uuid, (Player.playerKills.get(uuid)) - 1))));
		}
	}
	
	public static void addPlayerKill(UUID uuid, int no) {
		if (Player.playerKills.containsKey(uuid)) {
			Player.playerKills.put(uuid, ((Player.playerKills.put(uuid, (Player.playerKills.get(uuid)) + no))));
		} else {
			Player.playerKills.put(uuid, no);
		}
	}
	
	public static void removePlayerKill(UUID uuid, int no) {
		if (Player.playerKills.containsKey(uuid)) {
			Player.playerKills.put(uuid, ((Player.playerKills.put(uuid, (Player.playerKills.get(uuid)) - no))));
		} else {
			removePlayerKill(uuid);
		}
	}
	
	public static void removePlayerKill(UUID uuid) {
		Player.playerKills.remove(uuid);
	}
	
	public static HashMap<UUID, Integer> getPlayerKill() {
		return Player.playerKills;
	}
	
	public static void clearPlayerKill() {
		Player.playerKills.clear();
	}
	
	public static boolean inPlayerKill(UUID uuid) {
		return Player.playerKills.containsKey(uuid);
	}
	
	public static void addCombatLog(UUID victim, UUID attacker) {
		Player.combatLog.put(victim, attacker);
	}
	
	public static void removeCombatLog(UUID victim) {
		Player.combatLog.remove(victim);
	}
	
	public static void clearCombatLog() {
		Player.combatLog.clear();
	}
	
	public static boolean inCombatLog(UUID uuid) {
		return Player.combatLog.containsKey(uuid);
	}
	
	public static ArrayList<OreRHandler> getOres() {
		return Lists.ores;
	}
	
	public static void addOre(OreRHandler handler) {
		Lists.ores.add(handler);
	}
	
	public static void removeOre(OreRHandler handler) {
		Lists.ores.remove(handler);
	}
	
	public static void clearOres() {
		Lists.ores.clear();
	}
	
	public static ArrayList<TreeRHandler> getTrees() {
		return Lists.trees;
	}
	
	public static void addTree(TreeRHandler handler) {
		Lists.trees.add(handler);
	}
	
	public static void removeTree(TreeRHandler handler) {
		Lists.trees.remove(handler);
	}
	
	public static void clearTrees() {
		Lists.trees.clear();
	}
}
