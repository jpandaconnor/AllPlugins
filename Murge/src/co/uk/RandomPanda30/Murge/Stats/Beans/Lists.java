package co.uk.RandomPanda30.Murge.Stats.Beans;

import java.util.ArrayList;
import java.util.UUID;

import co.uk.RandomPanda30.Murge.Handlers.Regeneration.OreRHandler;
import co.uk.RandomPanda30.Murge.Handlers.Regeneration.TreeRHandler;

public class Lists {

	// More lists can easily be added in here if needed

	public static ArrayList<UUID> spectating = new ArrayList<UUID>();
	public static ArrayList<UUID> players = new ArrayList<UUID>();
	
	public static ArrayList<OreRHandler> ores = new ArrayList<OreRHandler>();
	public static ArrayList<TreeRHandler> trees = new ArrayList<TreeRHandler>();
}
