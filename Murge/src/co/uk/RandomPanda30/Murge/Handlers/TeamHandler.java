package co.uk.RandomPanda30.Murge.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamHandler {

	private ArrayList<TeamHandler> teams = new ArrayList<TeamHandler>();
	private HashMap<UUID, TeamHandler> playerTeams = new HashMap<UUID, TeamHandler>();

	private String teamColour;

	public TeamHandler (String colour) {
		this.teamColour = colour;
		teams.add(this);
	}

	public void add(TeamHandler team, UUID uuid) {
		playerTeams.put(uuid, team);
	}

	public void removePlayer(UUID uuid) {
		if (hasTeam(uuid)) {
			playerTeams.remove(uuid);
		}
	}

	public boolean hasTeam(UUID uuid) {
		return playerTeams.containsKey(uuid);
	}

	public TeamHandler getTeam(UUID uuid) {
		if (hasTeam(uuid)) {
			return playerTeams.get(uuid);
		}
		return null;
	}

	public String getTeamColour() {
		return teamColour;
	}
}