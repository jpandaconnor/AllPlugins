package co.uk.RandomPanda30.Murge.Collection.Bases;

import java.util.UUID;

public interface PlayerBase {
	
	public int getValue(UUID uuid);

	public void addValue(UUID uuid);

	public void removeValue(UUID uuid);

	public void addValues(UUID uuid, int amount);

	public void setValue(UUID uuid, int amount);

	public void removeValues(UUID uuid, int amount);

	public void resetValues(UUID uuid);

}