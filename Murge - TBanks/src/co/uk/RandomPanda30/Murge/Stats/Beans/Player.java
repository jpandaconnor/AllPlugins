package co.uk.RandomPanda30.Murge.Stats.Beans;

import java.util.HashMap;
import java.util.UUID;

import co.uk.RandomPanda30.Murge.Handlers.PlayerDataHandler;

public class Player {

	public static HashMap<UUID, Integer> flySpeed = new HashMap<UUID, Integer>();
	public static HashMap<UUID, PlayerDataHandler> pdh = new HashMap<UUID, PlayerDataHandler>();
	public static HashMap<UUID, Integer> playerKills = new HashMap<UUID, Integer>();
	public static HashMap<UUID, UUID> combatLog = new HashMap<UUID, UUID>();
}