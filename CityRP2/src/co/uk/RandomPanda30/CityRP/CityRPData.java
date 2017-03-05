package co.uk.RandomPanda30.CityRP;

import java.util.ArrayList;

import co.uk.RandomPanda30.CityRP.MySQL.MySQL;
import co.uk.RandomPanda30.CityRP.Objects.Player;

public class CityRPData {
	
	public static Player editingLocation;
	
	public static MySQL mysql;

	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static Player getPlayerObject( org.bukkit.entity.Player player) {
		Player custom = null;
		for(Player p : players) {
			if(p.getPlayer().equals(player)) {
				custom = p;
			}
		}
		return custom;
	}
}