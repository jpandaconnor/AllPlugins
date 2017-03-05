package co.uk.RandomPanda30.FunZone;

import java.util.HashMap;
import java.util.UUID;

/*
 *
 * For all who will read this: This class is a bean class.
 * It will basically store any shit that I need for the
 * plugin into here
 *
 */

public class PValues {

	public FunZone plugin;

	public PValues (FunZone plugin) {
		this.plugin = plugin;
	}

	public HashMap<UUID, Boolean> cooldown = new HashMap<UUID, Boolean>();



}